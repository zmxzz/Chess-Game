package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    ChessBoard board = new ChessBoard();

    @Test
    public void testToString() {
        assertEquals("Pawn", board.getPiece(1, 0).toString());
    }

    @Test
    public void isValidMove() {
        assertTrue(board.getPiece(1, 0).isValidMove(2, 0, board));
        assertTrue(board.getPiece(1, 0).isValidMove(3, 0, board));
        board.movePiece(1, 0, 3, 0);
        board.setPiece(3, 1, new Pawn(3, 1, true));
        assertTrue(board.getPiece(3, 1).isValidMove(2, 0, board));
        board.setPiece(2, 2, new Pawn(4, 0, false));
        assertTrue(board.getPiece(3, 1).isValidMove(2, 2, board));
        assertFalse(board.getPiece(6, 0).isValidMove(6, -1, board));
    }

    @Test
    public void isValidMoveWithoutConsideringKing() {
        board.setPiece(0, 3, new Queen(0, 3, true));
        assertTrue(board.getPiece(1, 0).isValidMoveWithoutConsideringKing(2, 0, board));
        assertTrue(board.getPiece(1, 0).isValidMoveWithoutConsideringKing(3, 0, board));
        assertFalse(board.getPiece(1, 0).isValidMoveWithoutConsideringKing(1, -1, board));
        board.movePiece(6, 0, 5, 0);
    }
}