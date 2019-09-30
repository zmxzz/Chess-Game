package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElephantTest {

  ChessBoard board = new ChessBoard(10, 10);

  @Test
  public void testToString() {
    assertEquals("Elephant", board.getPiece(9, 8).toString());
    assertEquals("Elephant", board.getPiece(0, 8).toString());
  }

  @Test
  public void isValidMove() {
    assertFalse(board.getPiece(9, 8).isValidMove(7, 6, board));
    board.setPiece(8, 7, null);
    assertTrue(board.getPiece(9, 8).isValidMove(7, 6, board));
    board.setPiece(7, 6, new Pawn(7, 6, true));
    assertFalse(board.getPiece(9, 8).isValidMove(7, 6, board));
    assertFalse(board.getPiece(9, 8).isValidMove(11, 6, board));

  }

  @Test
  public void isValidMoveWithoutConsideringKing() {
    assertFalse(board.getPiece(9, 8).isValidMoveWithoutConsideringKing(7, 6, board));
    board.setPiece(8, 7, null);
    board.setPiece(9, 3, new Queen(9, 3, false));
    assertTrue(board.getPiece(9, 8).isValidMoveWithoutConsideringKing(7, 6, board));
    board.setPiece(7, 6, new Pawn(7, 6, true));
    assertFalse(board.getPiece(9, 8).isValidMoveWithoutConsideringKing(7, 6, board));
    assertFalse(board.getPiece(9, 8).isValidMoveWithoutConsideringKing(11, 6, board));
  }
}