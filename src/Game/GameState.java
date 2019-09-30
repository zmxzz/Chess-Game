package Game;

import Piece.*;

public class GameState {
  // The class stores basic information for single movement
  private int startRow;
  private int startCol;
  private int endRow;
  private int endCol;
  private boolean moved;
  private boolean movedTwo;
  private Piece movedPiece;
  private Piece capturedPiece;

  /**
   *  Constructor for the GameState Info
   *  Record everything down before movement
   */
  public GameState(Piece movedPiece, Piece capturedPiece, int startRow, int startCol, int endRow, int endCol) {
    this.startRow = startRow;
    this.startCol = startCol;
    this.endRow = endRow;
    this.endCol = endCol;
    this.movedPiece = movedPiece;
    this.capturedPiece = capturedPiece;
    this.moved = this.movedPiece.isMoved();
    this.movedTwo = this.movedPiece.getMovedTwo();
  }

  /**
   * @return start position of the moved piece
   */
  public int[] getStartPos() {
    return new int[]{this.startRow, this.startCol};
  }

  /**
   * @return moved piece
   */
  public Piece getMovedPiece() {
    return this.movedPiece;
  }

  /**
   * @return end position of the moved piece
   */
  public int[] getEndPos() {
    return new int[]{this.endRow, this.endCol};
  }

  /**
   * @return captured piece
   */
  public Piece getCapturedPiece() {
    return this.capturedPiece;
  }

  /**
   * @return color of moved piece
   */
  public boolean getColor() {
    return this.movedPiece.getColor();
  }

  /**
   * Reset movedPiece and capturedPiece to their original state
   */
  public void reset() {
    this.movedPiece.setRow(this.startRow);
    this.movedPiece.setCol(this.startCol);
    this.movedPiece.setMoved(this.moved);
    this.movedPiece.setMovedTwo(this.movedTwo);
  }
}
