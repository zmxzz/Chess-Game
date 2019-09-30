package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class MenuPanel extends Observable implements ActionListener {
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem start;
  private JMenuItem undo;

  public MenuPanel() {
    this.menuBar = new JMenuBar();
    this.menu = new JMenu("Game");
    // Start and Undo Button
    this.start = new JMenuItem("Start New");
    this.start.addActionListener(this);
    this.menu.add(start);
    this.menuBar.add(menu);
  }

  public JMenuBar getBar() {
    return this.menuBar;
  }

  /**
   * Handles happening events
   * @param Event Listener
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    this.setChanged();
    this.notifyObservers("Start");
    this.clearChanged();
  }
}
