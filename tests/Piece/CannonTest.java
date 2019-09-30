package Piece;

import ChessBoard.ChessBoard;
import org.junit.Test;

import static org.junit.Assert.*;

public class CannonTest {

  ChessBoard board = new ChessBoard(10, 10);

  @Test
  public void testToString() {
    assertEquals("Cannon", board.getPiece(0, 9).toString());
    assertEquals("Cannon", board.getPiece(9, 9).toString());
  }

  @Test
  public void isValidMove() {
    assertTrue(board.getPiece(9, 9).isValidMove(1, 9, board));
    board.setPiece(1, 9, null);
    assertTrue(board.getPiece(9, 9).isValidMove(0, 9, board));
    assertFalse(board.getPiece(9, 9).isValidMove(2, 9, board));
    assertFalse(board.getPiece(9, 9).isValidMove(9, 7, board));
    board.setPiece(8, 9, null);
    assertFalse(board.getPiece(9, 9).isValidMove(0, 9, board));
    assertTrue(board.getPiece(9, 9).isValidMove(1, 9, board));
    board.movePiece(9, 9, 2, 9);
    assertTrue(board.getPiece(2, 9).isValidMove(2, 0, board));
    assertFalse(board.getPiece(0, 9).isValidMove(-1, 9, board));
  }

  @Test
  public void isValidMoveWithoutConsideringKing() {
    assertTrue(board.getPiece(9, 9).isValidMoveWithoutConsideringKing(1, 9, board));
    board.setPiece(9, 3, new Queen(9, 3, false));
    assertTrue(board.getPiece(9, 9).isValidMoveWithoutConsideringKing(1, 9, board));
    assertFalse(board.getPiece(9, 9).isValidMoveWithoutConsideringKing(10, 9, board));
  }
}