package Service;

import ChessBoard.ChessBoard;
import Piece.Pawn;
import Piece.Piece;
import Piece.Queen;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidMovementHelperByChessBoardTest {
    ChessBoard board = new ChessBoard(10, 10);

    @Test
    public void kingIsSafeAfterMovement() {
        assertFalse(ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(4, 4, 3, 3, board));
        assertTrue(ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(1, 0, 2, 0, board));
        assertTrue(ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(8, 0, 7, 0, board));
        board.setPiece(0, 3, new Queen(0, 3, true));
        assertFalse(ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(1, 0, 2, 0, board));
        board.setPiece(6, 3, new Queen(6, 3, false));
        assertFalse(ValidMovementHelperByChessBoard.kingIsSafeAfterMovement(6, 0, 5, 0, board));
    }

    @Test
    public void targetPositionIsSameColor() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < board.getCol(); j++) {
                assertTrue(ValidMovementHelperByChessBoard.targetPositionIsSameColor(i, j, false, board));
            }
        }
        for (int i = board.getRow() - 2; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                assertTrue(ValidMovementHelperByChessBoard.targetPositionIsSameColor(i, j, true, board));
            }
        }
    }

    @Test
    public void verticalRouteIsClean() {
        assertTrue(ValidMovementHelperByChessBoard.verticalRouteIsClean(1, 0, 3, 0, board));
        assertTrue(ValidMovementHelperByChessBoard.verticalRouteIsClean(6, 0, 4, 0, board));
        assertFalse(ValidMovementHelperByChessBoard.verticalRouteIsClean(0, 0, 2, 0, board));
        assertFalse(ValidMovementHelperByChessBoard.verticalRouteIsClean(7, 0, 9, 0, board));
    }

    @Test
    public void horizontalRouteIsClean() {
        assertTrue(ValidMovementHelperByChessBoard.horizontalRouteIsClean(2, 0, 2, 3, board));
        assertTrue(ValidMovementHelperByChessBoard.horizontalRouteIsClean(5, 0, 5, 3, board));
        assertFalse(ValidMovementHelperByChessBoard.horizontalRouteIsClean(1, 0, 1, 3, board));
        assertFalse(ValidMovementHelperByChessBoard.horizontalRouteIsClean(9, 0, 9, 3, board));
    }

    @Test
    public void diagonalRouteIsClean() {
        assertTrue(ValidMovementHelperByChessBoard.diagonalRouteIsClean(2, 0, 4, 2, board));
        assertTrue(ValidMovementHelperByChessBoard.diagonalRouteIsClean(5, 5, 3, 3, board));
        assertFalse(ValidMovementHelperByChessBoard.diagonalRouteIsClean(0, 1, 2, 3, board));
        assertFalse(ValidMovementHelperByChessBoard.diagonalRouteIsClean(5, 0, 9, 4, board));
    }

    @Test
    public void byPassingIsValid() {
        board.movePiece(1, 0, 3, 0);
        board.setPiece(3, 1, new Pawn(3, 1, true));
        assertTrue(ValidMovementHelperByChessBoard.byPassingIsValid(3, 1, 2, 0, board));
        board = new ChessBoard();
        board.movePiece(6, 0, 4, 0);
        board.setPiece(4, 1, new Pawn(4, 1, false));
        assertTrue(ValidMovementHelperByChessBoard.byPassingIsValid(4, 1, 5, 0, board));
        assertFalse(ValidMovementHelperByChessBoard.byPassingIsValid(5, 5, 4, 4, board));
        assertFalse(ValidMovementHelperByChessBoard.byPassingIsValid(3, 1, 2, 1, board));
    }

    @Test
    public void isVerticalCannonRoute() {
        assertTrue(ValidMovementHelperByChessBoard.isVerticalCannonRoute(9, 9, 1, 9, board));
        assertFalse(ValidMovementHelperByChessBoard.isVerticalCannonRoute(9, 9, 10, 9, board));
        board.setPiece(9, 7, new Queen(9, 7, false));
        assertTrue(ValidMovementHelperByChessBoard.isHorizontalCannonRoute(9, 9, 9, 7, board));
    }
}