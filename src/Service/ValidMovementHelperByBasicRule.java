package Service;

public abstract class ValidMovementHelperByBasicRule {
    /**
     * @param currRow: current row number of the piece
     * @param nextRow: next row number of the piece
     * @return if two row numbers are the same
     */
    public static boolean isSameRow(int currRow, int nextRow) {
        return currRow == nextRow;
    }

    /**
     * @param currCol: current col number of the piece
     * @param nextCol: next col number of the piece
     * @return if two col numbers are the same
     */
    public static boolean isSameCol(int currCol, int nextCol) {
        return currCol == nextCol;
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @return if the line crossing two points are
     */
    public static boolean isDiagonal(int currRow, int currCol, int nextRow, int nextCol) {
        return Math.abs(currRow - nextRow) == Math.abs(currCol - nextCol);
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @return if the two positions are around each other
     */
    public static boolean isAround(int currRow, int currCol, int nextRow, int nextCol) {
        return Math.abs(currRow - nextRow) <= 1 && Math.abs(currCol - nextCol) <= 1;
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @return if the knight can move to the target position
     */
    public static boolean isKnightMove(int currRow, int currCol, int nextRow, int nextCol) {
        int rowDiff = Math.abs(currRow - nextRow);
        int colDiff = Math.abs(currCol - nextCol);
        // The distance between old and new positions is always sqrt(5)
        return rowDiff * rowDiff + colDiff * colDiff == 5;
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can do short castling
     */
    public static boolean isShortCastling(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        if (currCol != 4 || nextCol != 6) {
            return false;
        }
        return color ? (currRow == 7 && nextRow == 7) : (currRow == 0 && nextRow == 0);
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can do long castling
     */
    public static boolean isLongCastling(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        if (color) {
            return currRow == 7 && currCol == 4 && nextRow == 7 && nextCol == 2;
        }
        return currRow == 0 && currCol == 4 && nextRow == 0 && nextCol == 2;
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can do bypassing
     */
    public static boolean isByPassing(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        if (color) {
            return currRow == nextRow + 1 && Math.abs(currCol - nextCol) == 1;
        }
        return currRow == nextRow - 1 && Math.abs(currCol - nextCol) == 1;
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can move forward by one
     */
    public static boolean isForwardByOne(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        return currCol == nextCol && (color ? currRow - 1 == nextRow : currRow + 1 == nextRow);
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can move forward by two
     */
    public static boolean isForwardByTwo(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        return currCol == nextCol && (color ? currRow - 2 == nextRow : currRow + 2 == nextRow);
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the piece can move one step forward diagonally
     */
    public static boolean isPawnEating(int currRow, int currCol, int nextRow, int nextCol, boolean color) {
        return Math.abs(currCol - nextCol) == 1 && (color ? currRow - 1 == nextRow : currRow + 1 == nextRow);
    }

    /**
     * @param currRow: current row number of the piece
     * @param currCol: current col number of the piece
     * @param nextRow: next row number of the piece
     * @param nextCol: next col number of the piece
     * @param color:   color of the piece
     * @return if the movement follows elephant's rule
     */
    public static boolean isElephantMove(int currRow, int currCol, int nextRow, int nextCol) {
        return Math.abs(currRow - nextRow) == 2 && Math.abs(currCol - nextCol) == 2;
    }
}
