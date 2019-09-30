package Service;

import Piece.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ImageIconHandler {
  /**
   * @param curr: piece that requires a image
   * @return an imageIcon representing the piece
   */
  public static ImageIcon readPieceImage(Piece curr) {
    if (curr == null) {
      return null;
    }
    try {
      // Read image file, create a label, add label onto the piece
      BufferedImage icon = ImageIO.read(new File(getFileName(curr)));
      return new ImageIcon(icon);
    } catch (IOException e) {
      System.out.println("Cannot Open Image File: " + e);
      return null;
    }
  }

  /**
   * @param curr current piece that panel contains
   * @return file name representing the piece
   */
  private static String getFileName(Piece curr) {
    return "img/" + (curr.getColor() ? "white_" : "black_") + curr.toString().toLowerCase() + ".png";
  }

  /**
   * @return imageIcon representing possible destination with dot image
   */
  public static ImageIcon readDotImage() {
    try {
      // Read image file, create a label, add label onto the piece
      BufferedImage icon = ImageIO.read(new File("img/dot.png"));
      return new ImageIcon(icon);
    } catch (IOException e) {
      System.out.println("Cannot Open Image File: " + e);
      return null;
    }
  }

  public static ImageIcon mergeBackgroundImage(BufferedImage one, BufferedImage two, Color curr) {
    BufferedImage newImage = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphic = newImage.createGraphics();
    Color oldColor = graphic.getColor();
    graphic.setPaint(curr);
    graphic.fillRect(0, 0, 64, 64);
    graphic.setColor(oldColor);
    graphic.drawImage(one, null, 0, 0);
    graphic.drawImage(two, null, 0, 0);
    graphic.dispose();
    return new ImageIcon(newImage);
  }

}
