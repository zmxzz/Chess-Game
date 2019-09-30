package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByChessBoard;

public class Cannon extends Piece {
  // Inherited Constructor
  public Cannon(int row, int col, boolean color) {
    super(row, col, color);
  }

  // toString
  @Override
  public String toString() {
    return "Cannon";
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
    return this.isValidMoveWithoutCapture(row, col, board) ||
            this.isValidMoveWithCapture(row, col, board);
  }

  /**
   * Determine if the movement is valid without capturing.
   * If not capturing any Piece, the cannon can only move vertically or horizontally and must be stopped by obstacle
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  private boolean isValidMoveWithoutCapture(int row, int col, ChessBoard board) {
    if (board.getPiece(row, col) != null) {
      return false;
    }
    return ValidMovementHelperByChessBoard.verticalRouteIsClean(this.getRow(), this.getCol(), row, col, board) ||
            ValidMovementHelperByChessBoard.horizontalRouteIsClean(this.getRow(), this.getCol(), row, col, board);
  }

  /**
   * Determine if the movement is valid with capturing.
   * If  capturing any Piece, the cannon move jump over a piece vertically or horizontally
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  private boolean isValidMoveWithCapture(int row, int col, ChessBoard board) {
    if (board.getPiece(row, col) == null) {
      return false;
    }
    return ValidMovementHelperByChessBoard.isHorizontalCannonRoute(this.getRow(), this.getCol(), row, col, board) ||
            ValidMovementHelperByChessBoard.isVerticalCannonRoute(this.getRow(), this.getCol(), row, col, board);
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
    return this.isValidMoveWithCapture(row, col, board) ||
            this.isValidMoveWithoutCapture(row, col, board);
  }

}
