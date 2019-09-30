package View.Intro;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class NameDialog extends Observable {
  JFrame parent;
  private String playerOneName;
  private String playerTwoName;

  public NameDialog(JFrame parent) {
    this.parent = parent;
  }

  /**
   * Record size preference and save it to the row, col
   */
  public void showDialog() {
    this.playerOneName = JOptionPane.showInputDialog(this.parent, "Please Enter Name of First Player:", null);
    this.playerTwoName = JOptionPane.showInputDialog(this.parent, "Please Enter Name of Second Player:", null);
  }

  /**
   * Return player one's name
   */
  public String getPlayerOneName() {
    return this.playerOneName;
  }

  /**
   * Return player two's name
   */
  public String getPlayerTwoName() {
    return this.playerTwoName;
  }

}
