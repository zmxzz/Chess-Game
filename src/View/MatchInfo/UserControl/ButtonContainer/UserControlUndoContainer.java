package View.MatchInfo.UserControl.ButtonContainer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserControlUndoContainer extends UserControlButtonContainer implements ActionListener {
  private static final Color BROWN = new Color(0x884600);
  private static final Color YELLOW = new Color(0xFFCD9E);
  /**
   * Constructor for undo button
   */
  public UserControlUndoContainer(boolean player) {
    super("Undo", player);
    this.getButton().addActionListener(this);
    // When the button is pressed, change background
    this.getButton().addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        getButton().setBackground(YELLOW);
      }
      @Override
      public void mouseReleased(MouseEvent e) {
        getButton().setBackground(BROWN);
      }
    });
  }

  /**
   * @param event: When clicked, notify observers
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    this.setChanged();
    this.notifyObservers("Undo");
    this.clearChanged();
  }
}
