package View.MatchInfo.UserControl.ButtonContainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserControlRestartContainer extends UserControlButtonContainer implements ActionListener {
  /**
   * Constructor for undo button
   */
  public UserControlRestartContainer(boolean player) {
    super("Restart", player);
    this.getButton().addActionListener(this);
  }

  /**
   * @param event: When clicked, notify observers
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    this.setChanged();
    this.notifyObservers("Restart");
    this.clearChanged();
  }
}
