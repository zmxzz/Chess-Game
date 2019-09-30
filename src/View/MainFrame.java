package View;

import ChessBoard.ChessBoard;
import View.Board.ChessBoardContainer;
import View.Intro.NameDialog;
import View.Intro.SizeDialog;
import View.MatchInfo.MatchInfoContainer;
import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public class MainFrame {
  private JFrame mainFrame;
  private ChessBoardContainer chessBoardContainer;
  private MenuPanel menuBar;
  private MatchInfoContainer matchInfoContainer;
  private SizeDialog sizeDialog;
  private NameDialog nameDialog;
  private String nameOne;
  private String nameTwo;

  public MainFrame(Observer observer) {
    this.mainFrame = new JFrame("Chess");
    this.sizeDialog = new SizeDialog(observer, this.mainFrame);
    this.sizeDialog.showDialog();
    this.nameDialog = new NameDialog(this.mainFrame);
    this.nameDialog.showDialog();
    this.nameOne = this.nameDialog.getPlayerOneName();
    this.nameTwo = this.nameDialog.getPlayerTwoName();
  }

  /**
   * Set up the board with the given chess board
   */
  public void setUp(ChessBoard chessBoard, Observer observer) {
    this.chessBoardContainer = new ChessBoardContainer(chessBoard);
    this.chessBoardContainer.addObserver(observer);
    this.menuBar = new MenuPanel();
    this.menuBar.addObserver(observer);
    this.matchInfoContainer = new MatchInfoContainer(chessBoard.getRow(), observer, this.nameOne, this.nameTwo);
    this.setUpFrame();
  }

  /**
   * Destroy the current window
   */
  public void destroy() {
    this.mainFrame.setVisible(false);
    this.mainFrame.dispose();
  }

  /**
   * Announce the winner by popping up a dialog
   */
  public void announceWinner(boolean winner) {
    JOptionPane.showMessageDialog(this.mainFrame,
            (winner ? this.nameOne : this.nameTwo) + " wins",
            "Game Over",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * Announce stalemate popping up a dialog
   */
  public void announceStaleMate() {
    JOptionPane.showMessageDialog(this.mainFrame,
            "StaleMate",
            "Game Over",
            JOptionPane.PLAIN_MESSAGE);
  }

  /**
   * @return chess board container for update
   */
  public ChessBoardContainer getChessBoardContainer() {
    return this.chessBoardContainer;
  }

  /**
   * @return match info container for update
   */
  public MatchInfoContainer getMatchInfoContainer() {
    return this.matchInfoContainer;
  }

  /**
   * Set some properties of the main frame such as size, visible, etc.
   */
  private void setUpFrame() {
    this.mainFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Take off margins between panels
    this.mainFrame.setResizable(false); // Make chess board not resizable
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // After close
    this.mainFrame.add(this.chessBoardContainer.getChessBoardPanel()); // Add chessboard panel to the main frame
    this.mainFrame.add(this.matchInfoContainer.getInfoPanel());  // Add match information panel to the main frame
    this.mainFrame.setJMenuBar(this.menuBar.getBar());
    this.mainFrame.pack();
    this.mainFrame.setVisible(true);
  }

}
