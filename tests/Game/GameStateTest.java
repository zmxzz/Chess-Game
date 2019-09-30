package Game;

import Piece.Queen;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
  Queen moving = new Queen(0, 0, true);
  Queen capture = new Queen(1, 1, false);
  GameState one = new GameState(moving, capture, 0, 0, 1, 1);

  @Test
  public void getStartPos() {
    assertArrayEquals(new int[]{0, 0}, one.getStartPos());
  }

  @Test
  public void getMovedPiece() {
    assertEquals(moving, one.getMovedPiece());
  }

  @Test
  public void getEndPos() {
    assertArrayEquals(new int[]{1, 1}, one.getEndPos());
  }

  @Test
  public void getCapturedPiece() {
    assertEquals(capture, one.getCapturedPiece());
  }

  @Test
  public void getColor() {
    assertTrue(one.getColor());
  }

  @Test
  public void reset() {
    one.reset();
    assertEquals(one.getMovedPiece().getRow(), 0);
  }
}