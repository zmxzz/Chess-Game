package Game;

import ChessBoard.ChessBoard;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class GameTest {
  Game game = new Game(10, 10);

  @Test
  public void reset() {
    ChessBoard curr = game.getBoard();
    game.reset();
    assertNotEquals(curr, game.getBoard());
  }

  @Test
  public void getPlayer() {
    assertTrue(game.getPlayer());
    game.selectPiece(8, 0);
    game.movePiece(6, 0);
    assertFalse(game.getPlayer());
  }

  @Test
  public void selectPiece() {
    game.selectPiece(6, 0);
    assertEquals(null, game.getPossibleDest());
    assertFalse(game.hasSelectedPiece());
    game.selectPiece(8, 0);
    assertTrue(game.hasSelectedPiece());
  }

  @Test
  public void movePiece() {
    game.movePiece(0, 0);
    game.selectPiece(8, 0);
    game.movePiece(6, 0);
  }

  @Test
  public void getAndAddScore() {
    assertEquals(0, game.getScore(true));
    game.addScore(true);
    assertEquals(1, game.getScore(true));
    game.addScore(false);
    assertEquals(1, game.getScore(false));
  }

  @Test
  public void undo() {
    game.undo(true);
    game.selectPiece(8, 0);
    game.movePiece(6, 0);
    game.selectPiece(1, 0);
    game.movePiece(3, 0);
    game.selectPiece(6, 0);
    game.movePiece(5, 0);
    game.undo(true);
    assertTrue(game.getBoard().getPiece(6, 0).getMovedTwo());
  }

  @Test
  public void runStaleMate() {
    try {
      System.setIn(new BufferedInputStream(new FileInputStream("./staleMate.txt")));
      Game game = new Game();
      game.run();
    } catch (IOException e) {
      System.out.println("Error" + e);
    } finally {
      System.setIn(System.in);
    }
  }

  @Test
  public void runCheckMate() {
    try {
      System.setIn(new BufferedInputStream(new FileInputStream("./checkMate.txt")));
      Game game = new Game();
      game.run();
      assertTrue(game.getWinner());
    } catch (IOException e) {
      System.out.println(e);
    } finally {
      System.setIn(System.in);
    }

  }
}