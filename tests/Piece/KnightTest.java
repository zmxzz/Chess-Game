package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {
    ChessBoard board = new ChessBoard();

    @Test
    public void testToString() {
        assertEquals("Knight", board.getPiece(0, 1).toString());
        assertEquals("Knight", board.getPiece(0, 6).toString());
        assertEquals("Knight", board.getPiece(7, 1).toString());
        assertEquals("Knight", board.getPiece(7, 6).toString());
    }

    @Test
    public void isValidMove() {
        assertTrue(board.getPiece(0, 1).isValidMove(2, 0, board));
        assertTrue(board.getPiece(0, 1).isValidMove(2, 2, board));
        assertFalse(board.getPiece(0, 1).isValidMove(-2, 0, board));
        board.setPiece(2, 0, new Queen(2, 0, true));
        assertTrue(board.getPiece(0, 1).isValidMove(2, 0, board));
        board.setPiece(2, 0, new Queen(2, 0, false));
        assertFalse(board.getPiece(0, 1).isValidMove(2, 0, board));
        board.setPiece(0, 3, new Queen(0, 3, true));
        assertFalse(board.getPiece(0, 1).isValidMove(2, 0, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        board.setPiece(0, 3, new Queen(0, 3, true));
        assertTrue(board.getPiece(0, 1).isValidMoveWithoutConsideringKing(2, 0, board));
    }
}