package Piece;

import ChessBoard.ChessBoard;

public abstract class Piece {
  int row;          // Row position of the piece on chess board
  int col;          // Column position of the piece on chess board
  boolean isMoved;  // Whether the piece has moved or not
  boolean color;    // Color of the piece, white or black. white: true, black: false
  protected boolean movedTwo; // Check if Pawn piece moved two steps forward
  // Constructor
  public Piece(int row, int col, boolean color) {
    this.row = row;
    this.col = col;
    this.isMoved = false;
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    Piece piece = (Piece)o;
    return this.row == piece.row &&
            this.col == piece.col &&
            this.color == piece.color &&
            this.isMoved == piece.isMoved &&
            this.movedTwo == piece.movedTwo;
  }

  /**
   * Set row number into the given row number
   * @param new row number
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Set col number into the given col number
   * @param new col number
   */
  public void setCol(int col) {
    this.col = col;
  }

  /**
   * Getter for row number, col number, color, and move
   */
  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public boolean isMoved() {
    return this.isMoved;
  }

  public void setMoved(boolean isMoved) {
    this.isMoved = isMoved;
  }

  public boolean getColor() {
    return this.color;
  }

  /**
   * Set current row and col to the given row and col
   */
  public void move(int row, int col) {
    this.setRow(row);
    this.setCol(col);
    this.isMoved = true;
  }

  /**
   * @return if the piece has moved 2 forward as Pawn
   */
  public boolean getMovedTwo() {
    return this.movedTwo;
  }

  /**
   * Set movedTwo
   */
  public void setMovedTwo(boolean movedTwo) {
    this.movedTwo = movedTwo;
  }

  /**
   * Needs override
   * @return if the movement to target position is a valid move
   */
  public boolean isValidMove(int row, int col, ChessBoard board) {
    return board.insideBoard(row, col);
  }

  /**
   * Needs override
   * @return if the movement to target position is valid without considering king's position
   */
  public boolean isValidMoveWithoutConsideringKing(int row, int col, ChessBoard board) {
    return board.insideBoard(row, col);
  }

}
