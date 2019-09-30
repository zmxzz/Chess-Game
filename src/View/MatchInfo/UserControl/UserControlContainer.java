package View.MatchInfo.UserControl;

import View.ComponentTemplate.PanelTemplate;
import View.MatchInfo.UserControl.ButtonContainer.UserControlButtonContainer;
import View.MatchInfo.UserControl.ButtonContainer.UserControlForfeitContainer;
import View.MatchInfo.UserControl.ButtonContainer.UserControlRestartContainer;
import View.MatchInfo.UserControl.ButtonContainer.UserControlUndoContainer;

import java.awt.*;
import java.util.Observer;

public class UserControlContainer {
  private UserControlButtonContainer forfeitContainer;
  private UserControlButtonContainer restartContainer;
  private UserControlButtonContainer undoContainer;
  private PanelTemplate controlPanel;
  private static final Color BROWN = new Color(0x884600);

  /**
   * Constructor for the user control container
   * @param observer: to be notified when any of the button is clicked
   */
  public UserControlContainer(Observer observer, boolean player) {
    // Initialize Container for all the user controls
    this.forfeitContainer = new UserControlForfeitContainer(player);
    this.restartContainer = new UserControlRestartContainer(player);
    this.undoContainer = new UserControlUndoContainer(player);
    // Set up GUI for the control view
    this.controlPanel = new PanelTemplate(200, 128, BROWN);
    this.controlPanel.add(forfeitContainer.getButton());
    this.controlPanel.add(restartContainer.getButton());
    this.controlPanel.add(undoContainer.getButton());
    this.controlPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Take off margins between small piece panels
    // Add observers
    this.forfeitContainer.addObserver(observer);
    this.restartContainer.addObserver(observer);
    this.undoContainer.addObserver(observer);
  }

  /**
   * Return the control panel to the match information panel
   */
  public PanelTemplate getControlPanel() {
    return this.controlPanel;
  }

  /**
   * Reset restart buttons
   */
  public void resetRestart() {
    this.restartContainer.updateColor(false);
  }
}
