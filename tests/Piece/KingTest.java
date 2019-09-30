package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {
    ChessBoard board = new ChessBoard();

    @Test
    public void toString1() {
        assertEquals("King", board.getPiece(0, 4).toString());
        assertEquals("King", board.getPiece(7, 4).toString());
    }

    @Test
    public void isValidMove() {
        assertFalse(board.getPiece(0, 4).isValidMove(0, 3, board));
        assertFalse(board.getPiece(0, 4).isValidMove(-1, 4, board));
        assertFalse(board.getPiece(7, 4).isValidMove(7, 3, board));
        assertFalse(board.getPiece(7, 4).isValidMove(7, 5, board));
        int[] irrelevantPiece = new int[]{1, 2, 3, 5, 6};
        for (int i: irrelevantPiece) {
            board.setPiece(0, i, null);
            board.setPiece(7, i, null);
        }
        assertTrue(board.getPiece(0, 4).isValidMove(0, 2, board));
        assertTrue(board.getPiece(0, 4).isValidMove(0, 6, board));
        assertTrue(board.getPiece(7, 4).isValidMove(7, 2, board));
        assertTrue(board.getPiece(7, 4).isValidMove(7, 6, board));
        assertTrue(board.getPiece(0, 4).isValidMove(0 ,3, board));
        assertFalse(board.getPiece(0, 4).isValidMove(7, 7, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        board.setPiece(1, 4, new King(1, 4, true));
        board.setPiece(1, 5, new Queen(1, 5, true));
        assertTrue(board.getPiece(0, 4).isValidMoveWithoutConsideringKing(1, 4, board));
    }
}