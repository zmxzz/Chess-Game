package View.MatchInfo;
import View.ComponentTemplate.LabelTemplate;
import View.ComponentTemplate.PanelTemplate;

import javax.swing.*;
import java.awt.*;

public class ScoreContainer {
  private LabelTemplate scoreLabel;
  private PanelTemplate scorePanel;

  /**
   * Constructor for each player's score info panel
   * @param text: Score of corresponding player
   */
  public ScoreContainer(String text) {
    this.setUpLabel(text);
    this.scorePanel = new PanelTemplate(200, 64, null);
    this.scorePanel.setOpaque(false);
    this.scorePanel.add(this.scoreLabel);
  }

  /**
   * @return Score information panel to display
   */
  public PanelTemplate getScorePanel() {
    return this.scorePanel;
  }

  /**
   * @return Score label for update
   */
  public void updateScore(String text) {
    this.scoreLabel.setText(text);
  }


  /**
   * Set up properties for labels
   * @param text: Score of corresponding player
   */
  private void setUpLabel(String text) {
    this.scoreLabel = new LabelTemplate(text, 25, 200, 64);
    this.scoreLabel.setHorizontalAlignment(JLabel.CENTER);
    this.scoreLabel.setVerticalAlignment(JLabel.CENTER);
  }


}
