package View.Board;

import Piece.*;
import Service.ImageIconHandler;
import View.ComponentTemplate.LabelTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Constructor for a single Piece cell
 */
public class PieceContainer extends Observable implements ActionListener {
  private int row;
  private int col;
  private JButton pieceButton;
  private LabelTemplate background;
  private Color backgroundColor;
  private Piece prevPiece;
  private boolean markedAsPossibleDest;
  private static final Color YELLOW = new Color(0xFFCD9E);
  private static final Color BROWN = new Color(0xD08A47);

  public PieceContainer(Piece curr, boolean color, int row, int col) {
    this.prevPiece = curr;
    this.markedAsPossibleDest = false;
    this.pieceButton = new JButton();
    this.background = new LabelTemplate(null, 0, 64, 64);
    setUpButton(color);
    if (curr != null) {
      this.background.setIcon(ImageIconHandler.readPieceImage(curr));
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Set up basic button properties
   */
  private void setUpButton(boolean color) {
    this.pieceButton.add(this.background);
    this.pieceButton.setPreferredSize(new Dimension(64, 64));  // Size of single Piece
    this.backgroundColor = color ? this.YELLOW : this.BROWN;
    this.pieceButton.setBackground(this.backgroundColor); // Background Color
    this.pieceButton.setBorder(null);
    this.pieceButton.setOpaque(true);
    this.pieceButton.addActionListener(this);
  }

  /**
   * Get Row Info
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Get Col Info
   */
  public int getCol() {
    return this.col;
  }

  /**
   * Get current piece button
   */
  JButton getPieceButton() {
    return this.pieceButton;
  }

  /**
   * @param curr: current piece updates to the container
   * @return if current image icon is consistent with previous one
   */
  boolean sameAsPrev(Piece curr) {
    if (this.prevPiece == null && curr == null) {
      return true;
    }
    if (this.prevPiece == null || curr == null) {
      return false;
    }
    return this.prevPiece.equals(curr);
  }

  /**
   * Update the information of the board based on the given piece
   * @param curr current piece in the position
   */
  void update(Piece curr) {
    if (this.sameAsPrev(curr) && !this.markedAsPossibleDest) {
      return;
    }
    this.background.setIcon(null);
    if (curr != null) {
      this.background.setIcon(ImageIconHandler.readPieceImage(curr));
    }
    this.prevPiece = curr;
    this.markedAsPossibleDest = false;
  }

  /**
   * Set background to green if current position is a possible destination for the selected piece
   */
  void setBackGroundAsPossibleDest() {
    if (this.background.getIcon() == null) {
      this.background.setIcon(ImageIconHandler.readDotImage());
    }
    else {
      BufferedImage currBackground = (BufferedImage) (((ImageIcon)this.background.getIcon()).getImage());
      BufferedImage dotBackground = (BufferedImage) (ImageIconHandler.readDotImage().getImage());
      this.background.setIcon(ImageIconHandler.mergeBackgroundImage(currBackground, dotBackground, this.backgroundColor));
    }
    this.markedAsPossibleDest = true;
  }

  /**
   * Post it to the controller
   */
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    this.setChanged();
    this.notifyObservers("Move");
    this.clearChanged();
  }

}
