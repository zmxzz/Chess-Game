package View.Board;

import ChessBoard.ChessBoard;
import View.ComponentTemplate.PanelTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observer;

public class ChessBoardContainer {
  private PieceContainer[][] pieceContainer;
  private JPanel chessBoardPanel;
  private int row;
  private int col;

  /**
   * Constructor for the chess board panel
   * @param board
   */
  public ChessBoardContainer(ChessBoard board) {
    // Row and Col based on the given board
    this.row = board.getRow();
    this.col = board.getCol();
    this.pieceContainer = new PieceContainer[this.row][this.col];
    this.chessBoardPanel = new PanelTemplate((this.row + 1) * 64, (this.row + 1) * 64, null);
    this.chessBoardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Take off margins between small piece panels
    this.chessBoardPanel.add(new IndexContainer("").getIndexPanel());
    this.addIndexRow();
    this.addPiecePanels(board);
  }

  /**
   * Return chess board panel
   */
  public JPanel getChessBoardPanel() {
    return this.chessBoardPanel;
  }

  /**
   * Add First Index Row
   */
  private void addIndexRow() {
    for (int i = 0; i < this.col; i++) {
      this.chessBoardPanel.add(new IndexContainer((char)('A' + i) + "").getIndexPanel());
    }
  }

  /**
   * Add Pieces into the panel
   */
  private void addPiecePanels(ChessBoard board) {
    // Initialize board by color and pieces
    // Add Observer to each Panel
    for (int i = 0; i < this.row; i++) {
      boolean color = i % 2 == 0;
      this.chessBoardPanel.add(new IndexContainer((i + 1) + "").getIndexPanel());
      for (int j = 0; j < this.col; j++) {
        this.pieceContainer[i][j] = new PieceContainer(board.getPiece(i, j), color, i, j);
        this.chessBoardPanel.add(this.pieceContainer[i][j].getPieceButton());
        color = !color;
      }
    }
  }

  /**
   * Add observer to each button
   * @param observer controller as observer to detect changes
   */
  public void addObserver(Observer observer) {
    for (int i = 0; i < this.row; i++) {
      for (int j = 0; j < this.col; j++) {
        this.pieceContainer[i][j].addObserver(observer);
      }
    }
  }

  /**
   * Update Piece Panel Array with given chess board
   */
  public void updateBoard(ChessBoard board) {
    for (int i = 0; i < board.getRow(); i++) {
      for (int j = 0; j < board.getCol(); j++) {
        this.pieceContainer[i][j].update(board.getPiece(i, j));
      }
    }
  }

  /**
   * Update Piece Panel Array Background with possible destination
   * @param possibleDestination, possible destination in the form of int array
   */
  public void updatePossibleDest(List<int[]> possibleDestination) {
    if (possibleDestination == null) {
      return;
    }
    for (int[] dest: possibleDestination) {
      this.pieceContainer[dest[0]][dest[1]].setBackGroundAsPossibleDest();
    }
  }
}
