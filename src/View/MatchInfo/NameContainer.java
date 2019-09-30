package View.MatchInfo;

import View.ComponentTemplate.LabelTemplate;
import View.ComponentTemplate.PanelTemplate;

import java.awt.*;

public class NameContainer {
  private LabelTemplate nameLabel;
  private PanelTemplate namePanel;
  private static final Color BROWN = new Color(0x884600);
  private static final Color YELLOW = new Color(0xF7AF6A);

  /**
   * Constructor for the name info container
   * @param name: Name title for the player
   */
  public NameContainer(String name) {
    this.nameLabel = new LabelTemplate(name, 20, 200, 64);
    this.namePanel = new PanelTemplate(200, 64, null);
    this.namePanel.setBackground(BROWN);
    this.namePanel.add(this.nameLabel);
  }

  /**
   * Set board background color based on highlight input
   */
  public void setHighlighted(boolean highlighted) {
    if (highlighted) {
      this.namePanel.setBackground(YELLOW);
    }
    else {
      this.namePanel.setBackground(BROWN);
    }
  }

  /**
   * @return namePanel for display
   */
  public PanelTemplate getNamePanel() {
    return this.namePanel;
  }
}
