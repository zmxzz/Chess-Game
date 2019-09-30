package ChessBoard;

import Game.Game;
import Game.GameHistory;
import Game.GameState;
import Piece.*;
import Service.PrintTextWithColor;
import Service.ValidMovementHelperByChessBoard;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
  Piece[][] board;
  // Template roles for auto generate chess board
  private final static String[] ROLE_TEMPLATE = new String[]{"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook", "Elephant", "Cannon"};
  // If the given row and column are smaller than default rows and columns, set board with default size
  private final int DEFAULT_ROW = 8;
  private final int DEFAULT_COl = 8;
  // Size of the board
  private int row;
  private int col;
  // Initial row numbers that contains Pieces
  private int[] INITIAL_ROW_NUMBER;

  /**
   * Default Constructor: initialize the board with 8 * 8 size
   */
  public ChessBoard() {
    this.initialize(this.DEFAULT_ROW, this.DEFAULT_COl);
  }

  /**
   * Constructor: initialize the board with given row and column
   */
  public ChessBoard(int row, int col) {
    this.initialize(row, col);
  }

  /**
   * @param row row number of the board
   * @param col column number of the board
   * Helper function for the constructors, initialize the board with given row and column
   */
  private void initialize(int row, int col) {
    this.row = Math.max(row, this.DEFAULT_ROW);
    this.col = Math.max(col, this.DEFAULT_COl);
    this.board = new Piece[this.row][this.col];
    this.INITIAL_ROW_NUMBER = new int[]{0, 1, this.row - 2, this.row - 1}; // Top two rows and bottom two rows
    for (int rowNumber : this.INITIAL_ROW_NUMBER) {
      boolean color = rowNumber > this.row / 2;  // Define color for each row
      for (int colNumber = 0; colNumber < this.col; colNumber++) {
        this.board[rowNumber][colNumber] = this.generatePieceByRole(this.getRole(rowNumber, colNumber), rowNumber, colNumber, color);
      }
    }
    this.printBoard();
  }

  /**
   * @return row size and col size of the board
   */
  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  /**
   * Sanity check whether the input is valid
   * @param row: target row number
   * @param col: target col number
   * @return if the target position is inside board
   */
  public boolean insideBoard(int row, int col) {
    return row >= 0 && row < this.row && col >= 0 && col < this.col;
  }

  /**
   * If target position is inside board, return the piece, otherwise null
   * @param row: row number of the target piece
   * @param col: col number of the target piece
   * @return piece on the given place
   */
  public Piece getPiece(int row, int col) {
    return this.insideBoard(row, col) ? this.board[row][col] : null;
  }

  public Piece findKing(boolean color) {
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        if (this.board[i][j] != null && this.board[i][j].getColor() == color && this.board[i][j].toString().equals("King")) {
          return this.board[i][j];
        }
      }
    }
    System.out.println("Cannot find King");
    return null;
  }

  /**
   * @param row: row number that current piece is set to
   * @param col: column number that current piece is set to
   * @param curr: current piece about to be set
   */
  public void setPiece(int row, int col, Piece curr) {
    if (!this.insideBoard(row, col)) {
      return;
    }
    this.board[row][col] = curr;
  }

  /**
   * Empty everything on board
   */
  public void emptyBoard() {
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.board[i][j] = null;
      }
    }
  }

  /**
   * @param currRow: curr row number of the moving piece
   * @param currCol: curr col number of the moving piece
   * @param nextRow: target row number of the moving piece
   * @param nextCol: target col number of the moving piece
   * @param history: a class records history movement
   */
  public void movePiece(int currRow, int currCol, int nextRow, int nextCol, GameHistory history) {
    if (this.getPiece(currRow, currCol) == null || !this.insideBoard(nextRow, nextCol)) {
      return;
    }
    if (ValidMovementHelperByChessBoard.isShortCastling(currRow, currCol, nextRow, nextCol, this) ||
            ValidMovementHelperByChessBoard.isLongCastling(currRow, currCol, nextRow, nextCol, this)) {
      doCastling(currRow, currCol, nextRow, nextCol, history);
    }
    else if (ValidMovementHelperByChessBoard.byPassingIsValid(currRow, currCol, nextRow, nextCol, this)) {
      this.doByPassing(currRow, currCol, nextRow, nextCol, history);
    }
    else {
      history.offer(new GameState(this.getPiece(currRow, currCol), this.getPiece(nextRow, nextCol), currRow, currCol, nextRow, nextCol));
      this.movePiece(currRow, currCol, nextRow, nextCol);
    }
  }

  /**
   * Do the en passent for pawn
   * @param currRow: curr row number of the moving piece
   * @param currCol: curr col number of the moving piece
   * @param nextRow: target row number of the moving piece
   * @param nextCol: target col number of the moving piece
   * @param history: a class records history movement
   */
  private void doByPassing(int currRow, int currCol, int nextRow, int nextCol, GameHistory history) {
    Piece curr = this.getPiece(currRow, currCol);
    Piece next = this.getPiece(currRow, nextCol);
    history.offer(new GameState(curr, next, currRow, currCol, nextRow, nextCol));
    this.movePiece(currRow, currCol, nextRow, nextCol);
    this.setPiece(next.getRow(), next.getCol(), null);
  }

  /**
   * Do the castling for king and rook
   * @param currRow: curr row number of the moving piece
   * @param currCol: curr col number of the moving piece
   * @param nextRow: target row number of the moving piece
   * @param nextCol: target col number of the moving piece
   * @param history: a class records history movement
   */
  private void doCastling(int currRow, int currCol, int nextRow, int nextCol, GameHistory history) {
    Piece king = this.getPiece(currRow, currCol);
    Piece rook = this.getPiece(currRow, currCol < nextCol ? 7 : 0);
    int nextRookCol = currCol > nextCol ? nextCol + 1 : nextCol - 1;
    history.offer(new GameState(king, null, currRow, currCol, nextRow, nextCol));
    history.offer(new GameState(rook, null, rook.getRow(), rook.getCol(), nextRow, nextRookCol));
    this.movePiece(currRow, currCol, nextRow, nextCol);
    this.movePiece(rook.getRow(), rook.getCol(), nextRow, nextRookCol);
  }

  /**
   * @param currRow: curr row number of the moving piece
   * @param currCol: curr col number of the moving piece
   * @param nextRow: target row number of the moving piece
   * @param nextCol: target col number of the moving piece
   */
  public void movePiece(int currRow, int currCol, int nextRow, int nextCol) {
    Piece movingPiece = this.board[currRow][currCol];
    movingPiece.move(nextRow, nextCol);
    this.board[nextRow][nextCol] = movingPiece;
    this.board[currRow][currCol] = null;
  }

  /**
   * @param row: initial row of the piece
   * @param col: initial col of the piece
   * @return role name of the piece on given place
   */
  String getRole(int row, int col) {
    if (col < 0 || col >=this.ROLE_TEMPLATE.length) {
      return null;
    }
    return row == 0 || row == this.row - 1 ? ROLE_TEMPLATE[col] : "Pawn";
  }



  /**
   * @param role: role name of the piece
   * @param row: initial row number of the piece
   * @param col: initial col number of the piece
   * @param color: color of the piece: white as true, black as false
   * @return initialized Piece with the given parameters
   */
  Piece generatePieceByRole(String role, int row, int col, boolean color) {
    if (role == null) {
      return null;
    }
    switch (role) {
      case "Rook":
        return new Rook(row, col, color);
      case "Knight":
        return new Knight(row, col, color);
      case "Bishop":
        return new Bishop(row, col, color);
      case "Queen":
        return new Queen(row, col, color);
      case "King":
        return new King(row, col, color);
      case "Pawn":
        return new Pawn(row, col, color);
      case "Elephant":
        return new Elephant(row, col, color);
      case "Cannon":
        return new Cannon(row, col, color);
      default:
        return null;
    }
  }

  /**
   * Print Board
   */
  public void printBoard() {
    for (int i = 0; i < this.row; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < this.col; j++) {
        this.printCell(i, j);
      }
      System.out.println();
    }
    for (int i = 0; i < this.col; i++) {
      System.out.print("    " + i + "    ");
    }
    System.out.println();
    System.out.println();
  }

  // Helper function to print a single cell
  private void printCell(int row, int col) {
    if (this.board[row][col] == null) {
      PrintTextWithColor.printCell("***", false, false, false);
    }
    else if (this.board[row][col].getColor()) {
      PrintTextWithColor.printCell(this.board[row][col].toString(), false, true, false);
    }
    else {
      PrintTextWithColor.printCell(this.board[row][col].toString(), false, false, false);
    }
  }

  /**
   * Print Board considering selected Piece
   */
  public void printBoardWithSelected(int row, int col) {
    if (!this.insideBoard(row, col) || this.board[row][col] == null) {
      this.printBoard();
      return;
    }
    Piece selected = this.board[row][col];
    for (int i = 0; i < this.row; i++) {
      System.out.print(i + "  ");
      for (int j = 0; j < this.col; j++) {
        this.printCellWithSelected(selected, i, j);
      }
      System.out.println();
    }
    for (int i = 0; i < this.col; i++) {
      System.out.print("    " + i + "    ");
    }
    System.out.println();
    System.out.println();
  }

  // Helper function for printBoardWithSelected
  private void printCellWithSelected(Piece selected, int row, int col) {
    Piece target = this.board[row][col];
    String targetStr = target == null ? "***" : target.toString();
    if (selected.isValidMove(row, col, this)) {
      PrintTextWithColor.printCell(targetStr, false, false, true);
    }
    else if (target == selected) {
      PrintTextWithColor.printCell(targetStr, true, target.getColor(), false);
    }
    else {
      this.printCell(row, col);
    }
  }

  /**
   * @param movingPiece Selected piece that will move
   * @return viable destination for the moving piece
   */
  public List<int[]> getPossibleDest(Piece movingPiece) {
    if (movingPiece == null) {
      return null;
    }
    List<int[]> possibleDestList = new ArrayList<>();
    // Go through the board to see if there is any valid movement
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        if (movingPiece.isValidMove(i, j, this)) {
          possibleDestList.add(new int[]{i, j});
        }
      }
    }
    return possibleDestList;
  }

}
