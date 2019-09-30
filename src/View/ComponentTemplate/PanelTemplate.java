package View.ComponentTemplate;

import javax.swing.*;
import java.awt.*;

public class PanelTemplate extends JPanel {
  /**
   * Constructor for the template panel
   * @param width: preferred width of the panel
   * @param height: preferred height of the panel
   */
  public PanelTemplate(int width, int height, Color backgroundColor) {
    this.setBackground(backgroundColor);
    this.setPreferredSize(new Dimension(width, height));
  }
}
