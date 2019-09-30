package ChessBoard;

import Game.GameHistory;
import Piece.Pawn;
import Piece.Rook;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChessBoardTest {

    ChessBoard board = new ChessBoard();
    ChessBoard largeBoard = new ChessBoard(10, 10);
    GameHistory history = new GameHistory();

    @Test
    public void getRow() {
        assertEquals(8, board.getRow());
    }

    @Test
    public void getCol() {
        assertEquals(8, board.getCol());
    }

    @Test
    public void insideBoard() {
        assertTrue(board.insideBoard(5, 5));
        assertFalse(board.insideBoard(8, 8));
    }

    @Test
    public void setPiece() {
        board.setPiece(8, 8, null);
    }

    @Test
    public void findKing() {
        board.printBoard();
        assertEquals(board.getPiece(0, 4), board.findKing(false));
        assertEquals(board.getPiece(7, 4), board.findKing(true));
        board.setPiece(0, 4, null);
        assertEquals(null, board.findKing(false));
    }

    @Test
    public void movePiece() {
        board.movePiece(1, 0, 2, 0, history);
        assertEquals(null, board.getPiece(1, 0));
        board.movePiece(-1, -1, 1, 1, history);
        board.setPiece(6, 0, new Pawn(6, 0, false));
        board.getPiece(6, 0).setMovedTwo(true);
        board.movePiece(6, 1, 5, 0, history);
        assertNull(board.getPiece(6, 0));
        board.setPiece(7, 1, null);
        board.setPiece(7, 2, null);
        board.setPiece(7,3, null);
        board.movePiece(7, 4, 7, 2, history);
        assertTrue(board.getPiece(7, 3) instanceof Rook);
        board.printBoardWithSelected(7, 2);
    }

    @Test
    public void emptyBoard() {
        board.emptyBoard();
        assertEquals(null, board.getPiece(0, 0));
    }

    @Test
    public void generateRole() {
        assertNull(board.generatePieceByRole(null, 0, 0, true));
    }

    @Test
    public void getPossibleDest() {
        List<int[]> empty = board.getPossibleDest(board.getPiece(7, 0));
        assertTrue(empty.isEmpty());
        assertNull(board.getPossibleDest(null));
        largeBoard.printBoard();
        List<int[]> cannon = largeBoard.getPossibleDest(largeBoard.getPiece(9, 9));
        assertArrayEquals(new int[]{1, 9}, cannon.get(0));
    }
}