package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {

    ChessBoard board = new ChessBoard();

    @Test
    public void testToString() {
        assertEquals("Queen", board.getPiece(0, 3).toString());
    }

    @Test
    public void isValidMove() {
        assertFalse(board.getPiece(0, 3).isValidMove(1, 2, board));
        board.setPiece(1, 2, null);
        assertTrue(board.getPiece(0, 3).isValidMove(1, 2, board));
        assertFalse(board.getPiece(0, 3).isValidMove(4, -1, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        board.setPiece(1, 4, new Queen(1, 4, true));
        board.setPiece(1, 2, null);
        assertFalse(board.getPiece(0, 3).isValidMoveWithoutConsideringKing(4, - 1, board));
    }
}