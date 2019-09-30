package View.MatchInfo;

import View.ComponentTemplate.LabelTemplate;
import View.ComponentTemplate.PanelTemplate;
import View.MatchInfo.UserControl.UserControlContainer;

import java.awt.*;
import java.util.Observer;

public class MatchInfoContainer {
  private PanelTemplate infoPanel;
  private NameContainer playerOneNameContainer;
  private NameContainer playerTwoNameContainer;
  private UserControlContainer playerOneControl;
  private UserControlContainer playerTwoControl;
  private ScoreContainer playerOneScore;
  private ScoreContainer playerTwoScore;
  private LabelTemplate versusLabel;
  private String playerOneName;
  private String playerTwoName;
  private static final Color BROWN = new Color(0x884600);

  /**
   * Constructor for the match information container
   * @param row: row number of the board
   * @param observer: observers to be notified
   */
  public MatchInfoContainer(int row, Observer observer, String playerOneName, String playerTwoName) {
    this.playerOneName = playerOneName;
    this.playerTwoName = playerTwoName;
    this.setUpInfoPanel(row);
    this.setUpVersusLabel(row);
    this.initializeName();
    this.initializeControl(observer);
    this.initializeScore();
    this.addAllComponents();
  }

  /**
   * Update score for the given player
   */
  public void updateScore(boolean winner, int score) {
    if (winner) {
      this.playerOneScore.updateScore("Player One: " + score);
    }
    else {
      this.playerTwoScore.updateScore("Player Two: " + score);
    }
  }

  /**
   * Reset restart to original color
   */
  public void resetRestartButton() {
    this.playerOneControl.resetRestart();
    this.playerTwoControl.resetRestart();
  }

  /**
   * Highlight name board of current player
   * @param player: current player in turn
   */
  public void highlightPlayerName(boolean player) {
    this.playerOneNameContainer.setHighlighted(player);
    this.playerTwoNameContainer.setHighlighted(!player);
  }

  /**
   * Simply Add all the panels into information panel in order
   */
  private void addAllComponents() {
    this.infoPanel.add(this.playerTwoNameContainer.getNamePanel());
    this.infoPanel.add(this.playerTwoControl.getControlPanel());
    this.infoPanel.add(this.playerTwoScore.getScorePanel());
    this.infoPanel.add(this.versusLabel);
    this.infoPanel.add(this.playerOneScore.getScorePanel());
    this.infoPanel.add(this.playerOneControl.getControlPanel());
    this.infoPanel.add(this.playerOneNameContainer.getNamePanel());
  }

  /**
   * @return information panel on the right side
   */
  public PanelTemplate getInfoPanel() {
    return this.infoPanel;
  }

  /**
   * Initialize name information of both players
   */
  private void initializeName() {
    this.playerOneNameContainer = new NameContainer(this.playerOneName);
    this.playerOneNameContainer.setHighlighted(true);
    this.playerTwoNameContainer = new NameContainer(this.playerTwoName);
  }

  /**
   * Initialize player control group
   */
  private void initializeControl(Observer observer) {
    this.playerOneControl = new UserControlContainer(observer, true);
    this.playerTwoControl = new UserControlContainer(observer, false);
  }

  /**
   * Initialize score container
   */
  private void initializeScore() {
    this.playerOneScore = new ScoreContainer("Player One: 0");
    this.playerTwoScore = new ScoreContainer("Player Two: 0");
  }


  /**
   * Set up properties of information panel on the side bar
   */
  private void setUpInfoPanel(int row) {
    this.infoPanel = new PanelTemplate(200, 64 * (row + 1), BROWN);
    this.infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Take off margins between small piece panels
  }

  /**
   * Set up versus label
   */
  private void setUpVersusLabel(int row) {
    this.versusLabel = new LabelTemplate("VS.", 30, 200, (row - 7) * 64);
  }
}
