package View.MatchInfo.UserControl.ButtonContainer;

import View.ComponentTemplate.LabelTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class UserControlButtonContainer extends Observable {
  private LabelTemplate buttonLabel;
  private JButton button;
  private static final Color BROWN = new Color(0x884600);
  private static final Color YELLOW = new Color(0xFFCD9E);
  private boolean player;
  /**
   * Constructor for the game control buttons
   * @param text: restart, forfeit or undo
   */
  public UserControlButtonContainer(String text, boolean player) {
    this.player = player;
    this.buttonLabel = new LabelTemplate(text, 20, 200, 43);
    this.setUpButton();
    this.button.add(this.buttonLabel);
  }

  /**
   * Update color of the restart button
   */
  public void updateColor(boolean isYellow) {
    if (isYellow) {
      this.button.setBackground(YELLOW);
    }
    else {
      this.button.setBackground(BROWN);
    }
  }



  /**
   * Return button to the control container
   */
  public JButton getButton() {
    return this.button;
  }

  /**
   * Return player of the control button
   */
  public boolean getPlayer() {
    return this.player;
  }

  /**
   * Set up properties for the button
   */
  private void setUpButton() {
    this.button = new JButton();
    this.button.setOpaque(true);
    this.button.setBorder(null);
    this.button.setBackground(BROWN);
    this.button.setPreferredSize(new Dimension(200, 43));
    this.button.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Take off margins between small piece panels
  }
}
