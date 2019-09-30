package Service;

import ChessBoard.ChessBoard;
import Piece.*;

import java.util.List;

public abstract class GameStatusChecker {
    /**
     * Check if the king with given color is under attack
     *
     * @param color: color of the king
     * @param board: chess board
     * @return whether the king is under attack
     */
    public static boolean kingIsUnderAttack(boolean color, ChessBoard board) {
        Piece king = board.findKing(color);
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                Piece curr = board.getPiece(i, j);
                if (curr != null && curr.getColor() != color && curr.isValidMoveWithoutConsideringKing(king.getRow(), king.getCol(), board)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if there is anything in the pieceList can move
     *
     * @param pieceList: piece list of one side
     * @param board:     chess board
     * @return true if there is at least one piece can move
     */
    private static boolean onePieceCanMove(List<Piece> pieceList, ChessBoard board) {
        for (Piece each : pieceList) {
            if (board.getPiece(each.getRow(), each.getCol()) != each) {
                continue;
            }
            for (int i = 0; i < board.getRow(); i++) {
                for (int j = 0; j < board.getCol(); j++) {
                    if (each.isValidMove(i, j, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the king is checkmated
     *
     * @param pieceList: allies of the king that may defend attacks to the king
     * @param board:     chess board
     * @param color:     color of the pieceList
     * @return true if there is a checkmate
     */
    public static boolean checkMate(List<Piece> pieceList, ChessBoard board, boolean color) {
        return kingIsUnderAttack(color, board) && !onePieceCanMove(pieceList, board);
    }

    /**
     * Check if the game is in stalemate
     *
     * @param pieceList: a list of piece to check whether any of the can move
     * @param board:     chess board
     * @param color:     color of the pieceList
     * @return true if there is no piece can move and king is not under attack
     */
    public static boolean staleMate(List<Piece> pieceList, ChessBoard board, boolean color) {
        return !kingIsUnderAttack(color, board) && !onePieceCanMove(pieceList, board);
    }
}
