package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    ChessBoard board = new ChessBoard();

    @Test
    public void testToString() {
        assertEquals("Rook", board.getPiece(0, 0).toString());
    }

    @Test
    public void isValidMove() {
        assertFalse(board.getPiece(0, 0).isValidMove(1, 0, board));
        board.setPiece(1, 0, new Queen(1, 0, true));
        assertTrue(board.getPiece(0, 0).isValidMove(1, 0, board));
        board.movePiece(0, 0, 1, 0);
        assertTrue(board.getPiece(1, 0).isValidMove(6, 0, board));
        assertFalse(board.getPiece(1, 0).isValidMove(7, 0, board));
        board.setPiece(6, 0, null);
        board.setPiece(7, 0, null);
        assertFalse(board.getPiece(1, 0).isValidMove(8, 0, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        board.setPiece(0, 3, new Queen(0,3, true));
        board.setPiece(1, 0, null);
        assertTrue(board.getPiece(0, 0).isValidMoveWithoutConsideringKing(3, 0, board));
        board.setPiece(6, 0, null);
        board.setPiece(7, 0, null);
        assertFalse(board.getPiece(0, 0).isValidMoveWithoutConsideringKing(8, 0, board));
    }
}