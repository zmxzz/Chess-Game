package Game;

import java.util.ArrayDeque;
import java.util.Deque;

public class GameHistory {
  private Deque<GameState> history;

  public GameHistory() {
    this.history = new ArrayDeque<>();
  }

  public int size() {
    return this.history.size();
  }

  public GameState peek() {
    return this.history.isEmpty() ? null : this.history.peekLast();
  }

  public GameState poll() {
    return this.history.isEmpty() ? null : this.history.pollLast();
  }

  public void offer(GameState curr) {
    this.history.offerLast(curr);
  }
}
