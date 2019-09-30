package Service;

import ChessBoard.ChessBoard;
import Game.Game;
import Piece.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameStatusCheckerTest {
    ChessBoard board;
    List<Piece> whitePiece;
    List<Piece> blackPiece;

    @Before
    public void init() {
        board = new ChessBoard();
        whitePiece = new ArrayList<>();
        blackPiece = new ArrayList<>();
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                Piece curr = board.getPiece(i, j);
                if (curr != null) {
                    (curr.getColor() ? whitePiece : blackPiece).add(curr);
                }
            }
        }
    }

    @Test
    public void kingIsUnderAttack() {
        assertFalse(GameStatusChecker.kingIsUnderAttack(true, board));
        assertFalse(GameStatusChecker.kingIsUnderAttack(false, board));
        board.movePiece(7, 3, 0, 3);
        assertTrue(GameStatusChecker.kingIsUnderAttack(false, board));
        board.movePiece(0, 0, 7, 3);
        assertTrue(GameStatusChecker.kingIsUnderAttack(true, board));
    }

    @Test
    public void checkMate() {
        assertFalse(GameStatusChecker.checkMate(whitePiece, board, true));
        assertFalse(GameStatusChecker.checkMate(blackPiece, board, false));
        board.movePiece(7, 3, 0, 3);
        assertFalse(GameStatusChecker.checkMate(blackPiece, board, false));
        board.setPiece(1, 4, new Queen(1, 4, true));
        assertTrue(GameStatusChecker.checkMate(blackPiece, board, false));
        board.setPiece(7, 3, new Queen(7, 3, false));
        assertFalse(GameStatusChecker.checkMate(whitePiece, board, true));
        board.setPiece(6, 4, new Queen(6, 4, false));
        assertTrue(GameStatusChecker.checkMate(blackPiece, board, true));
    }

    @Test
    public void staleMate() {
        assertFalse(GameStatusChecker.staleMate(whitePiece, board, true));
        assertFalse(GameStatusChecker.staleMate(blackPiece, board, true));
        board.emptyBoard();
        whitePiece = new ArrayList<>();
        blackPiece = new ArrayList<>();
        board.setPiece(5, 5, new King(5, 5, true));
        whitePiece.add(board.getPiece(5, 5));
        board.setPiece(0, 4, new Queen(0, 4, false));
        board.setPiece(0, 6, new Queen(0, 6, false));
        board.setPiece(4, 4, new Rook(4, 4, false));
        board.setPiece(6, 6, new Rook(6, 6, false));
        board.printBoard();
        assertTrue(GameStatusChecker.staleMate(whitePiece, board, true));
    }
}