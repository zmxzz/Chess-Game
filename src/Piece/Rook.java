package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByChessBoard;

public class Rook extends Piece {
  // Inherited Constructor
  public Rook(int row, int col, boolean color) {
    super(row, col, color);
  }

  // toString
  @Override
  public String toString() {
    return "Rook";
  }

  /**
   * Determine if the movement is valid, horizontal, vertical, considering if king is safe after movement
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
    return ValidMovementHelperByChessBoard.verticalRouteIsClean(this.getRow(), this.getCol(), row, col, board) ||
            ValidMovementHelperByChessBoard.horizontalRouteIsClean(this.getRow(), this.getCol(), row, col, board);
  }

  /**
   * Determine if the movement is valid, horizontal, vertical, diagonal, not considering if king is safe after movement
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
    return ValidMovementHelperByChessBoard.verticalRouteIsClean(this.getRow(), this.getCol(), row, col, board) ||
            ValidMovementHelperByChessBoard.horizontalRouteIsClean(this.getRow(), this.getCol(), row, col, board);
  }

}