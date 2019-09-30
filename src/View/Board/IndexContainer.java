package View.Board;

import View.ComponentTemplate.LabelTemplate;
import View.ComponentTemplate.PanelTemplate;
import java.awt.*;

public class IndexContainer {
  private static final Color BROWN = new Color(0x884600);
  private LabelTemplate label;
  private PanelTemplate indexPanel;
  /**
   *
   * @param text: index text of current index panel
   */
  public IndexContainer(String text) {
    this.indexPanel = new PanelTemplate(64, 64, BROWN);
    this.label = new LabelTemplate(text, 14, 64, 64);
    this.indexPanel.add(this.label);
  }

  /**
   * @return Index Panel created from the template
   */
  public PanelTemplate getIndexPanel() {
    return this.indexPanel;
  }

}
