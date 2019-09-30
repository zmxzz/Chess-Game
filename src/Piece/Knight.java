package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByBasicRule;
import Service.ValidMovementHelperByChessBoard;

public class Knight extends Piece {
  // Inherited Constructor
  public Knight(int row, int col, boolean color) {
    super(row, col, color);
  }

  // toString
  @Override
  public String toString() {
    return "Knight";
  }

  /**
   * Determine if the movement is valid, considering if king will be under attack
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
    return ValidMovementHelperByBasicRule.isKnightMove(this.getRow(), this.getCol(), row, col);
  }

  /**
   * Determine if the movement is valid, containing, not considering if king will be under attack
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMoveWithoutConsideringKing(int row, int col, ChessBoard board) {
    return super.isValidMoveWithoutConsideringKing(row, col, board) && ValidMovementHelperByBasicRule.isKnightMove(this.getRow(), this.getCol(), row, col);
  }

}