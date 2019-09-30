package Game;

public class GameScore {
  private int playerOneScore;
  private int playerTwoScore;

  /**
   * Constructor for the Game Score, initialize both to zero
   */
  public GameScore() {
    this.playerOneScore = 0;
    this.playerTwoScore = 0;
  }

  /**
   * @return two players' score in an int array
   */
  public int getScore(boolean player) {
    return player ? this.playerOneScore : this.playerTwoScore;
  }

  /**
   * Player one wins, inc player one's score
   */
  public void playerOneWins() {
    this.playerOneScore++;
  }

  /**
   * Player two wins, inc player two's score
   */
  public void playTwoWins() {
    this.playerTwoScore++;
  }
}
