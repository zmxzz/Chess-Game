package Controller;

import Game.Game;
import View.Intro.SizeDialog;
import View.MainFrame;
import View.Board.PieceContainer;
import View.MatchInfo.UserControl.ButtonContainer.UserControlButtonContainer;

import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
  private Game game;
  private MainFrame mainFrame;
  private boolean playerOneRestart;
  private boolean playerTwoRestart;
  private int row;
  private int col;

  public GameController() {
    this.mainFrame = new MainFrame(this);
    this.game = new Game(this.row, this.col);
    this.mainFrame.setUp(this.game.getBoard(), this);
    this.playerOneRestart = false;
    this.playerTwoRestart = false;
  }

  /**
   * Update GUI Board
   */
  private void updateGUIBoard() {
    this.mainFrame.getChessBoardContainer().updateBoard(this.game.getBoard());
  }

  /**
   * Update GUI Possible Destination
   */
  private void updateGUIPossibleDest() {
    this.mainFrame.getChessBoardContainer().updatePossibleDest(this.game.getPossibleDest());
  }

  /**
   * @param row row number getting moved or selected
   * @param col col number getting moved or selected
   */
  private void selectOrMovePiece(int row, int col) {
    if (!this.game.hasSelectedPiece()) {
      this.game.selectPiece(row, col);
      this.updateGUIPossibleDest();
      return;
    }
    this.game.movePiece(row, col);
    this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
    this.updateGUIBoard();
    if (this.game.checkMate()) {
      boolean winner = this.game.getWinner();
      this.mainFrame.announceWinner(winner);
      this.game.addScore(winner);
      this.mainFrame.getMatchInfoContainer().updateScore(winner, this.game.getScore(winner));
      this.game.reset();
      this.updateGUIBoard();
      this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
    }
    else if (this.game.staleMate()) {
      this.mainFrame.announceStaleMate();
      this.game.reset();
      this.updateGUIBoard();
      this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
    }
  }

  /**
   * Reset game, add one point to the winner, update score board
   * @param buttonContainer: container tells you who forfeits
   */
  private void forfeit(UserControlButtonContainer buttonContainer) {
    boolean player = buttonContainer.getPlayer();
    this.game.addScore(!player);
    this.mainFrame.getMatchInfoContainer().updateScore(!player, this.game.getScore(!player));
    this.game.reset();
    this.updateGUIBoard();
    this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
  }

  /**
   * Undo the movements in the game
   */
  private void undo(UserControlButtonContainer buttonContainer) {
    this.game.undo(buttonContainer.getPlayer());
    this.updateGUIBoard();
    this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
  }

  /**
   * Reset everything in the game
   */
  private void resetEverything() {
    this.mainFrame.destroy();
    this.mainFrame = new MainFrame(this);
    this.game = new Game(this.row, this.col);
    this.mainFrame.setUp(this.game.getBoard(), this);
  }

  /**
   * If two players all agree with restart, restart the game
   */
  private void restart(UserControlButtonContainer buttonContainer) {
    boolean player = buttonContainer.getPlayer();
    // Turn player restart selection over
    if (player) {
      this.playerOneRestart = !this.playerOneRestart;
    }
    else {
      this.playerTwoRestart = !this.playerTwoRestart;
    }
    // If both agree, restart game, reset chess board
    if (this.playerOneRestart && this.playerTwoRestart) {
      this.mainFrame.getMatchInfoContainer().resetRestartButton();
      this.game.reset();
      this.updateGUIBoard();
      this.mainFrame.getMatchInfoContainer().highlightPlayerName(this.game.getPlayer());
      this.playerOneRestart = this.playerTwoRestart = false;
    }
    else {
      buttonContainer.updateColor(player ? this.playerOneRestart : this.playerTwoRestart);
    }
  }

  /**
   * Set up sizes for the chess board
   * @param sizeDialog: size dialog that records the input
   */
  private void setSize(SizeDialog sizeDialog) {
    this.row = sizeDialog.getRow();
    this.col = sizeDialog.getCol();
  }

  /**
   * Handle updates observed from Observables
   * @param observable: one observable that sends notification about update
   * @param curr: description about current update
   */
  @Override
  public void update(Observable observable, Object curr) {
    switch (curr.toString()) {
      case "Move":
        PieceContainer clickedPieceContainer = (PieceContainer)observable;
        this.selectOrMovePiece(clickedPieceContainer.getRow(), clickedPieceContainer.getCol());
        break;
      case "Start":
        this.resetEverything();
        break;
      case "Undo":
        this.undo((UserControlButtonContainer)observable);
        break;
      case "Forfeit":
        this.forfeit((UserControlButtonContainer)observable);
        break;
      case "Restart":
        this.restart((UserControlButtonContainer)observable);
        break;
      case "Size":
        this.setSize((SizeDialog)observable);
        break;
      default:
        break;
    }
  }
}
