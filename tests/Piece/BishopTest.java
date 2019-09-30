package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class BishopTest {
    ChessBoard board = new ChessBoard();

    @Test
    public void toString1() {
        assertEquals("Bishop", board.getPiece(0, 2).toString());
        assertEquals("Bishop", board.getPiece(0, 5).toString());
        assertEquals("Bishop", board.getPiece(7, 2).toString());
        assertEquals("Bishop", board.getPiece(7, 5).toString());
    }

    @Test
    public void isValidMove() {
        assertFalse(board.getPiece(0, 2).isValidMove(1, 1, board));
        board.setPiece(1, 1, null);
        assertTrue(board.getPiece(0, 2).isValidMove(1, 1, board));
        board.setPiece(0, 3, new Rook(0, 1, true));
        assertTrue(board.getPiece(0, 2).isValidMove(1, 1, board));
        assertFalse(board.getPiece(0, 2).isValidMove(-2, 0, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        assertTrue(board.getPiece(0, 2).isValidMoveWithoutConsideringKing(1, 1, board));
        board.setPiece(1, 1, null);
        assertTrue(board.getPiece(0, 2).isValidMoveWithoutConsideringKing(1, 1, board));
        board.setPiece(0, 3, null);
        board.setPiece(0, 1, new Rook(0, 1, true));
        assertTrue(board.getPiece(0, 2).isValidMoveWithoutConsideringKing(1, 1, board));
    }
}