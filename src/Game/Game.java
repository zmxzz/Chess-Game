package Game;

import ChessBoard.ChessBoard;
import Piece.*;
import Service.GameStatusChecker;

import java.util.*;

public class Game {
  private ChessBoard board;
  private boolean player;             // player one / white as true, player two / black as false
  private List<Piece> whitePiece;
  private List<Piece> blackPiece;
  private Scanner reader;
  private Piece movingPiece;
  private List<int[]> possibleDest;
  private GameHistory history;
  private GameScore score;
  /**
   * Constructor, create a new chess board
   */
  public Game(int row, int col) {
    this.reader = new Scanner(System.in);
    this.board = new ChessBoard(row, col);
    this.player = true;
    this.history = new GameHistory();
    this.score = new GameScore();
    this.initializePieceList();
  }

  /**
   * Default constructor, create 8 * 8 chess board
   */
  public Game() {
    this.reader = new Scanner(System.in);
    this.board = new ChessBoard(8, 8);
    this.player = true;
    this.history = new GameHistory();
    this.score = new GameScore();
    this.initializePieceList();
  }


  /**
   * Return the chessboard
   */
  public ChessBoard getBoard() {
    return this.board;
  }

  /**
   * Reset chessboard
   */
  public void reset() {
    this.board = new ChessBoard(this.board.getRow(), this.board.getCol());
    this.player = true;
    this.history = new GameHistory();
    this.initializePieceList();
  }

  /**
   * @return current player in turn
   */
  public boolean getPlayer() {
    return this.player;
  }

  /**
   * @return current score of two players
   */
  public int getScore(boolean player) {
    return this.score.getScore(player);
  }

  /**
   * Add score to the given player
   */
  public void addScore(boolean player) {
    if (player) {
      this.score.playerOneWins();
    }
    else {
      this.score.playTwoWins();
    }
  }

  /**
   * Helper function to initialize piece lists
   */
  private void initializePieceList() {
    this.whitePiece = new ArrayList<Piece>();
    this.blackPiece = new ArrayList<Piece>();
    for (int i = 0; i < this.board.getRow(); i++) {
      for (int j = 0; j < this.board.getCol(); j++) {
        Piece curr = this.board.getPiece(i, j);
        if (curr != null) {
          (curr.getColor() ? this.whitePiece : this.blackPiece).add(curr);
        }
      }
    }
  }

  /**
   * Undo the movement for the current player, if it is current player's turn, undo twice, else undo once
   */
  public void undo(boolean player) {
    // If the player has not made a movement, ignore undo
    if (this.history.size() == 0 || (this.history.size() == 1 && !player)) {
      return;
    }
    do {
      this.undoOnce();
    } while (this.history.peek() != null && this.history.peek().getColor() == player);
    this.player = player;
  }

  /**
   * Undo the last movement
   */
  private void undoOnce() {
    // Put moved piece back to start position
    // Put capture piece back to end position
    GameState prev = this.history.poll();
    int[] startPos = prev.getStartPos();
    int[] endPos = prev.getEndPos();
    Piece capturedPiece = prev.getCapturedPiece();
    this.board.setPiece(startPos[0], startPos[1], prev.getMovedPiece());
    this.board.setPiece(endPos[0], endPos[1], null);
    if (capturedPiece != null) {
      this.board.setPiece(capturedPiece.getRow(), capturedPiece.getCol(), capturedPiece);
    }
    prev.reset();
  }

  /**
   * Select Piece if there is no piece selected
   * @param row row number of the piece
   * @param col col number of the piece
   */
  public void selectPiece(int row, int col) {
    this.movingPiece = this.board.getPiece(row, col);
    this.possibleDest = this.board.getPossibleDest(this.movingPiece);
    if (this.movingPiece == null ||
        this.movingPiece.getColor() != this.player ||
        this.board.getPossibleDest(this.movingPiece).size() == 0) {
      this.movingPiece = null;
      this.possibleDest = null;
    }
  }

  /**
   * Move Piece if the piece is selected
   * @param row row number of the destination
   * @param col col number of the destination
   */
  public void movePiece(int row, int col) {
    if (this.movingPiece != null &&
            this.movingPiece.isValidMove(row, col, this.board)) {
      int startRow = this.movingPiece.getRow();
      int startCol = this.movingPiece.getCol();
      // Add info about current movement to history
      this.board.movePiece(this.movingPiece.getRow(), this.movingPiece.getCol(), row, col, this.history);
      this.player = !this.player;
    }
    this.movingPiece = null;
    this.possibleDest = null;
  }

  /**
   * @return if there is selected piece
   */
  public boolean hasSelectedPiece() {
    return this.movingPiece != null;
  }

  /**
   * @return possible destination if there is piece selected
   */
  public List<int[]> getPossibleDest() {
    return this.possibleDest;
  }

  /**
   * @return if the game is in checkmate
   */
  public boolean checkMate() {
    return GameStatusChecker.checkMate(this.player ? this.whitePiece : this.blackPiece, this.board, this.player);
  }

  /**
   * @return if the game is in stalemate
   */
  public boolean staleMate() {
    return GameStatusChecker.staleMate(this.player ? this.whitePiece : this.blackPiece, this.board, this.player);
  }

  /**
   * Return winner
   */
  public boolean getWinner() {
    return !this.player;
  }

  // Below are for text game running...

  /**
   * Game loop for testing
   */
  public void run() {
    while (true) {
      int startRow = this.reader.nextInt();
      int startCol = this.reader.nextInt();
      this.board.printBoardWithSelected(startRow, startCol);
      int endRow = this.reader.nextInt();
      int endCol = this.reader.nextInt();
      this.move(startRow, startCol, endRow, endCol);
      if (this.checkMate()) {
        this.printWinner();
        break;
      }
      if (this.staleMate()){
        System.out.println("Stale mate");
        break;
      }
    }
  }

  /**
   * Move the piece if it can be moved
   * @param startRow: row number of moving piece
   * @param startCol: col number of moving piece
   * @param endRow: target row number
   * @param endCol: target col number
   */
  public void move(int startRow, int startCol, int endRow, int endCol) {
    Piece movingPiece = this.board.getPiece(startRow, startCol);
    if (movingPiece == null || movingPiece.getColor() != player) {
      System.out.println("Not valid start position");
      this.board.printBoard();
      return;
    }
    if (!movingPiece.isValidMove(endRow, endCol, board)) {
      System.out.println("Not valid end position");
      this.board.printBoard();
      return;
    }
    this.board.movePiece(startRow, startCol, endRow, endCol, this.history);
    this.board.printBoard();
    this.player = !this.player;
  }

  /**
   * Print winner: Player one or two
   */
  public void printWinner() {
    System.out.println("Player " + (this.player ? "two" : "one") + " wins");
  }
}
