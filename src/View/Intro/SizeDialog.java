package View.Intro;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class SizeDialog extends Observable {
  JFrame parent;
  private int row;
  private int col;
  public SizeDialog(Observer observer, JFrame parent) {
    this.addObserver(observer);
    this.parent = parent;
  }

  /**
   * Record size preference and save it to the row, col
   */
  public void showDialog() {
    while (this.row == 0) {
      String row = JOptionPane.showInputDialog(this.parent, "Please Enter Row Number of the Chess Board:", null);
      try {
        this.row = Integer.parseInt(row);
      } catch (NumberFormatException e) {}
    }
    while (this.col == 0) {
      String col = JOptionPane.showInputDialog(this.parent, "Please Enter Column Number of the Chess Board:", null);
      try {
        this.col = Integer.parseInt(col);
      } catch (NumberFormatException e) {}
    }
    this.setChanged();
    this.notifyObservers("Size");
    this.clearChanged();
  }

  /**
   * Return row size
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Return col size
   */
  public int getCol() {
    return this.col;
  }

}
