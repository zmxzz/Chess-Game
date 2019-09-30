package Service;

import ChessBoard.ChessBoard;
import Piece.*;

public abstract class ValidMovementHelperByChessBoard {
    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is valid based on the situation on the chess board
     */
    public static boolean kingIsSafeAfterMovement(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        // If the movement is making King of the same color under attack, return false
        Piece begin = board.getPiece(row, col); // Piece at begin position
        Piece target = board.getPiece(nextRow, nextCol); // Piece at target position
        if (begin == null) {
            return false;
        }
        Piece king = board.findKing(begin.getColor()); // Find king of the same color, then check if it's under attack
        board.setPiece(row, col, null);
        board.setPiece(nextRow, nextCol, begin);
        begin.setRow(nextRow);
        begin.setCol(nextCol);
        boolean result = true;
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                Piece curr = board.getPiece(i, j);
                // If king is under attack after movement, then the movement is invalid
                if (curr != null &&
                        curr.getColor() != king.getColor() &&
                        curr.isValidMoveWithoutConsideringKing(king.getRow(), king.getCol(), board)) {
                    result = false;
                    break;
                }
            }
        }
        begin.setRow(row);
        begin.setCol(col);
        board.setPiece(row, col, begin);
        board.setPiece(nextRow, nextCol, target);
        return result;
    }

    /**
     * Determine if the target position contains piece with same color
     *
     * @param row:   target row number
     * @param col:   target col number
     * @param color: color of the moving piece
     * @param board: chess board
     * @return if the moving piece can stop at target position
     */
    public static boolean targetPositionIsSameColor(int row, int col, boolean color, ChessBoard board) {
        return board.getPiece(row, col) != null && board.getPiece(row, col).getColor() == color;
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is valid through a vertical route
     */
    public static boolean verticalRouteIsClean(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        // Sanity Check if the route is vertical
        if (!ValidMovementHelperByBasicRule.isSameCol(col, nextCol)) {
            return false;
        }

        // Check if there is any obstacle on the route
        for (int i = Math.min(row, nextRow) + 1; i < Math.max(row, nextRow); i++) {
            if (board.getPiece(i, col) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is valid through a vertical route
     */
    public static boolean horizontalRouteIsClean(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        // Sanity Check if the route is horizontal
        if (!ValidMovementHelperByBasicRule.isSameRow(row, nextRow)) {
            return false;
        }
        // Check if there is any obstacle on the route
        for (int i = Math.min(col, nextCol) + 1; i < Math.max(col, nextCol); i++) {
            if (board.getPiece(row, i) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is valid through a diagonal route
     */
    public static boolean diagonalRouteIsClean(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        // Sanity Check if the route is diagonal
        if (!ValidMovementHelperByBasicRule.isDiagonal(row, col, nextRow, nextCol)) {
            return false;
        }
        // Current row and col to search the board
        int currRow = row;
        int currCol = col;
        // Check if there is any obstacle on the route
        while (currRow != nextRow) {
            if (currRow != row && board.getPiece(currRow, currCol) != null) {
                return false;
            }
            currRow = currRow < nextRow ? currRow + 1 : currRow - 1;
            currCol = currCol < nextCol ? currCol + 1 : currCol - 1;
        }
        return true;
    }


    /**
     * Simple helper function for byPassingIsValid to check the piece is valid
     */
    private static boolean isPawn(Piece curr) {
        return curr != null && curr.toString().equals("Pawn");
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the by passing movement is valid
     */
    public static boolean byPassingIsValid(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        Piece begin = board.getPiece(row, col);
        Piece end = board.getPiece(row, nextCol);
        if (!isPawn(begin) || !isPawn(end)) {
            return false;
        }
        return ValidMovementHelperByBasicRule.isByPassing(row, col, nextRow, nextCol, begin.getColor()) &&
                begin.getColor() != end.getColor() &&
                end.getMovedTwo();
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the route valid vertical route for cannon
     */
    public static boolean isVerticalCannonRoute(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        if (!ValidMovementHelperByBasicRule.isSameCol(col, nextCol)) {
            return false;
        }
        int count = 0;
        for (int i = Math.min(row, nextRow) + 1; i < Math.max(row, nextRow); i++) {
            if (board.getPiece(i, col) != null) {
                count++;
            }
        }
        return count == 1;
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the route valid horizontal route for cannon
     */
    public static boolean isHorizontalCannonRoute(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        if (!ValidMovementHelperByBasicRule.isSameRow(row, nextRow)) {
            return false;
        }
        int count = 0;
        for (int i = Math.min(col, nextCol) + 1; i < Math.max(col, nextCol); i++) {
            if (board.getPiece(row, i) != null) {
                count++;
            }
        }
        return count == 1;
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is short castling
     */
    public static boolean isShortCastling(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        Piece king = board.getPiece(row, col);
        Piece rook = board.getPiece(row, col < nextCol ? 7 : 0);
        if (king == null || rook == null) {
            return false;
        }
        return ValidMovementHelperByBasicRule.isShortCastling(row, col, nextRow, nextCol, king.getColor()) &&
                ValidMovementHelperByChessBoard.horizontalRouteIsClean(row, col, nextRow, nextCol, board) &&
                !king.isMoved() &&
                !rook.isMoved();
    }

    /**
     * @param row:     current row number of the piece
     * @param col:     current col number of the piece
     * @param nextRow: target row number of the piece
     * @param nextCol: target col number of the piece
     * @param board:   board containing all the pieces
     * @return if the movement is short castling
     */
    public static boolean isLongCastling(int row, int col, int nextRow, int nextCol, ChessBoard board) {
        Piece king = board.getPiece(row, col);
        Piece rook = board.getPiece(nextRow, col < nextCol ? 7 : 0);
        if (king == null || rook == null) {
            return false;
        }
        return ValidMovementHelperByBasicRule.isLongCastling(row, col, nextRow, nextCol, king.getColor()) &&
                ValidMovementHelperByChessBoard.horizontalRouteIsClean(row, col, nextRow, nextCol, board) &&
                !king.isMoved() &&
                !rook.isMoved();
    }

}
