package Piece;

import ChessBoard.ChessBoard;
import Service.ValidMovementHelperByBasicRule;
import Service.ValidMovementHelperByChessBoard;

public class King extends Piece {
  // Inherited Constructor
  public King(int row, int col, boolean color) {
    super(row, col, color);
  }

  private boolean rookHasMoved(int row, int col, ChessBoard board) {
    int rookCol = col == 2 ? 0 : 7;
    return board.getPiece(row, rookCol) == null || board.getPiece(row, rookCol).isMoved();
  }

  // toString
  @Override
  public String toString() {
    return "King";
  }

  /**
   * Determine if the movement is valid, containing, move one, castling, considering if king will be under attack
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMove(int row, int col, ChessBoard board) {
    if (!super.isValidMove(row, col, board)) {
      return false;
    }
    if (ValidMovementHelperByChessBoard.targetPositionIsSameColor(row, col, this.getColor(), board) ||
            !ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(this.getRow(), this.getCol(), row, col, board)) {
      return false;
    }
    // Normal movement
    if (ValidMovementHelperByBasicRule.isAround(this.getRow(), this.getCol(), row, col)) {
      return true;
    }
    // Castling - Not Moved, route is clean, king is safe
    return ValidMovementHelperByChessBoard.isShortCastling(this.getRow(), this.getCol(), row, col, board) ||
            ValidMovementHelperByChessBoard.isLongCastling(this.getRow(), this.getCol(), row, col, board);
  }

  /**
   * Determine if the movement is valid, not considering if king will be under attack
   * @param row: target row number
   * @param col: target col number
   * @param board: chess board
   * @return if the movement is valid
   */
  @Override
  public boolean isValidMoveWithoutConsideringKing(int row, int col, ChessBoard board) {
    return super.isValidMoveWithoutConsideringKing(row, col, board) &&
            ValidMovementHelperByBasicRule.isAround(this.getRow(), this.getCol(), row, col);
  }

}
