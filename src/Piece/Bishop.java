package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByChessBoard;

public class Bishop extends Piece {
  // Inherited Constructor
  public Bishop(int row, int col, boolean color) {
    super(row, col, color);
  }

  // toString
  @Override
  public String toString() {
    return "Bishop";
  }

  /**
   * Determine if the movement is valid, diagonal movement, considering if king will be under attack
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMove(int row, int col, ChessBoard board) {
    return super.isValidMove(row, col, board) &&
            ValidMovementHelperByChessBoard.diagonalRouteIsClean(this.getRow(), this.getCol(), row, col, board) &&
            ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(this.getRow(), this.getCol(), row, col, board) &&
            !ValidMovementHelperByChessBoard.targetPositionIsSameColor(row, col, this.getColor(), board);
  }

  /**
   * Determine if the movement is valid, not considering if king will be under attaack
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMoveWithoutConsideringKing(int row, int col, ChessBoard board) {
    return super.isValidMoveWithoutConsideringKing(row, col, board) &&
            ValidMovementHelperByChessBoard.diagonalRouteIsClean(this.getRow(), this.getCol(), row, col, board);
  }

}