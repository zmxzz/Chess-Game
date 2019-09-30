package View.ComponentTemplate;

import javax.swing.*;
import java.awt.*;

public class LabelTemplate extends JLabel {
  public LabelTemplate(String text, int size, int width, int height) {
    this.setText(text);
    this.setFont(new Font("Serif", Font.PLAIN, size));
    this.setHorizontalAlignment(JLabel.CENTER);
    this.setVerticalAlignment(JLabel.CENTER);
    this.setForeground(Color.white);
    this.setOpaque(false);
    this.setPreferredSize(new Dimension(width, height));
  }
}
