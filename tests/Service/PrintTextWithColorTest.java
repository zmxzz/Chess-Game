package Service;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrintTextWithColorTest {

  @Test
  public void printCell() {
    PrintTextWithColor.printCell("White", false, true, false);
    PrintTextWithColor.printCell("Cyan", false, false, false);
    PrintTextWithColor.printCell("Blue", true, false, false);
    PrintTextWithColor.printCell("Yellow", true, true, false);
    PrintTextWithColor.printCell("Green", false, false, true);
  }
}