package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByBasicRule;
import Service.ValidMovementHelperByChessBoard;

public class Pawn extends Piece {
  // Inherited Constructor
  public Pawn(int row, int col, boolean color) {
    super(row, col, color);
  }

  // Move the pawn
  @Override public void move(int row, int col) {
    if (Math.abs(this.getRow() - row) == 2) {
      this.movedTwo = true;
    }
    else {
      this.movedTwo = false;
    }
    super.move(row, col);
  }

  // toString
  @Override
  public String toString() {
    return "Pawn";
  }

  /**
   * Determine if the movement is valid, containing, move one forward - not moved and move two forward - en passent
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMove(int row, int col, ChessBoard board) {
    if (!super.isValidMove(row, col, board) ||
            ValidMovementHelperByChessBoard.targetPositionIsSameColor(row, col, this.getColor(), board) ||
            !ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(this.getRow(), this.getCol(), row, col, board)) {
      return false;
    }

    // If move forward by one, then it's valid
    // If move forward by two and has not moved, it's valid

    if (ValidMovementHelperByBasicRule.isForwardByOne(this.getRow(), this.getCol(), row, col, this.getColor()) &&
            board.getPiece(row, col) == null) {
      return true;
    }
    if (!this.isMoved() &&
            ValidMovementHelperByBasicRule.isForwardByTwo(this.getRow(), this.getCol(), row, col, this.getColor()) &&
            board.getPiece(row, col) == null) {
      return true;
    }

    if (ValidMovementHelperByBasicRule.isPawnEating(this.getRow(), this.getCol(), row, col, this.getColor()) &&
            board.getPiece(row, col) != null) {
      return true;
    }

    return ValidMovementHelperByChessBoard.byPassingIsValid(this.getRow(), this.getCol(), row, col, board);
  }

  /**
   * Determine if the movement is valid, containing, move one forward - not moved and move two forward
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMoveWithoutConsideringKing(int row, int col, ChessBoard board) {
    if (!super.isValidMoveWithoutConsideringKing(row, col, board)) {
      return false;
    }
    if (ValidMovementHelperByBasicRule.isPawnEating(this.getRow(), this.getCol(), row, col, this.getColor()) &&
            board.getPiece(row, col) != null) {
      return true;
    }
    return ValidMovementHelperByBasicRule.isForwardByOne(this.getRow(), this.getCol(), row, col, this.getColor()) ||
            (ValidMovementHelperByBasicRule.isForwardByTwo(this.getRow(), this.getCol(), row, col, this.getColor()) &&
                    !this.isMoved());
  }

}
