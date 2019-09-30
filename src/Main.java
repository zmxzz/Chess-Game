import Controller.GameController;

public class Main {
    public static void main(String[] args) {
	      // write your code here
        if (isMac()) {
            // If current OS is Mac, put menu bar onto the top of the screen
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        GameController gameController = new GameController();
    }

    private static boolean isMac() {
        return System.getProperty("os.name").contains("Mac");
    }
}
