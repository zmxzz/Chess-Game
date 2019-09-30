package Game;

import Piece.Queen;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameHistoryTest {

  GameHistory history = new GameHistory();

  @Test
  public void size() {
    assertEquals(0, history.size());
  }

  @Test
  public void peek() {
    assertNull(history.peek());
  }

  @Test
  public void poll() {
    assertNull(history.poll());
  }

  @Test
  public void offer() {
    GameState last = new GameState(new Queen(0, 0, true), new Queen(1, 1, false), 0, 0, 0, 0);
    history.offer(last);
    assertEquals(last, history.peek());
    assertEquals(last, history.poll());
  }
}