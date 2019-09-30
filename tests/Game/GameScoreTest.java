package Game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameScoreTest {

  GameScore score = new GameScore();

  @Test
  public void getScore() {
    assertEquals(0, score.getScore(true));
    assertEquals(0, score.getScore(false));
  }

  @Test
  public void playerOneWins() {
    score.playerOneWins();
    assertEquals(1, score.getScore(true));
    assertEquals(0, score.getScore(false));
  }

  @Test
  public void playTwoWins() {
    score.playTwoWins();
    assertEquals(0, score.getScore(true));
    assertEquals(1, score.getScore(false));
  }
}