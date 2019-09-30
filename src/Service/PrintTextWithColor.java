package Service;


public abstract class PrintTextWithColor {
  // Some constants for background with color
  private static final String black = "\033[0;30m";
  private static final String red = "\033[0;31m";
  private static final String green = "\033[0;32m";
  private static final String yellow = "\033[0;33m";
  private static final String blue = "\033[0;34m";
  private static final String purple = "\033[0;35m";
  private static final String cyan = "\033[0;36m";
  private static final String white = "\033[0;37m";
  private static final String reset = "\u001B[0m";

  /**
   * Print given cell
   */
  public static void printCell(String text, boolean selected, boolean color, boolean possible) {
    if (selected && color) {
      printSelectedWhite(text);
    }
    else if (selected) {
      printSelectedBlack(text);
    }
    else if (possible) {
      printPossibleEnd(text);
    }
    else if (color) {
      printWhite(text);
    }
    else if (!text.equals("***")) {
      printBlack(text);
    }
    else {
      printText("***");
    }
  }

  /**
   * Helper function for print text
   */
  private static void printText(String text) {
    System.out.print(text + " ");
    for (int i = 0; i < 8 - text.length(); i++) {
      System.out.print(" ");
    }
    System.out.print(reset);
  }

  /**
   * Print given text with red background
   */
  private static void printSelectedBlack(String text) {
    System.out.print(blue);
    printText(text);
  }

  /**
   * Print given text with green background
   */
  private static void printPossibleEnd(String text) {
    System.out.print(green);
    printText(text);
  }

  /**
   * Print given text with yellow background
   */
  private static void printSelectedWhite(String text) {
    System.out.print(yellow);
    printText(text);
  }

  /**
   * Print given text with white background
   */
  private static void printWhite(String text) {
    System.out.print(black);
    printText(text);
  }

  /**
   * Print given text with black background
   */
  private static void printBlack(String text) {
    System.out.print(cyan);
    printText(text);
  }



}
