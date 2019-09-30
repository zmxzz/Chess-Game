package Service;

import Service.ValidMovementHelperByBasicRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidMovementHelperByBasicRuleTest {

    @Test
    public void isSameRow() {
        assertTrue(ValidMovementHelperByBasicRule.isSameRow(0, 0));
        assertFalse(ValidMovementHelperByBasicRule.isSameRow(0, 1));
    }

    @Test
    public void isSameCol() {
        assertTrue(ValidMovementHelperByBasicRule.isSameCol(0, 0));
        assertFalse(ValidMovementHelperByBasicRule.isSameCol(0, 1));
    }

    @Test
    public void isDiagonal() {
        assertTrue(ValidMovementHelperByBasicRule.isDiagonal(0, 0, 1, 1));
        assertTrue(ValidMovementHelperByBasicRule.isDiagonal(1, 1, 0, 0));
        assertTrue(ValidMovementHelperByBasicRule.isDiagonal(1, 2, 2, 1));
        assertTrue(ValidMovementHelperByBasicRule.isDiagonal(2, 2, 1, 1));
        assertFalse(ValidMovementHelperByBasicRule.isDiagonal(0, 0, 2, 1));
    }

    @Test
    public void isAround() {
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 0, 0));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 0, 1));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 0, 2));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 1, 0));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 1, 2));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 2, 0));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 2, 1));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 2, 2));
        assertTrue(ValidMovementHelperByBasicRule.isAround(1, 1, 1, 1));
        assertFalse(ValidMovementHelperByBasicRule.isAround(1, 1, 3, 3));
    }

    @Test
    public void isKnightMove() {
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 0, 1));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 1, 0));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 3, 0));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 4, 1));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 4, 3));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 3, 4));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 1, 4));
        assertTrue(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 0, 3));
        assertFalse(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 3, 3));
        assertFalse(ValidMovementHelperByBasicRule.isKnightMove(2, 2, 5, 5));
    }

    @Test
    public void isShortCastling() {
        assertTrue(ValidMovementHelperByBasicRule.isShortCastling(0, 4, 0, 6, false));
        assertTrue(ValidMovementHelperByBasicRule.isShortCastling(7, 4, 7, 6, true));
        assertFalse(ValidMovementHelperByBasicRule.isShortCastling(0, 4, 0, 0, false));
        assertFalse(ValidMovementHelperByBasicRule.isShortCastling(7, 4, 7, 0, true));
    }

    @Test
    public void isLongCastling() {
        assertTrue(ValidMovementHelperByBasicRule.isLongCastling(0, 4, 0, 2, false));
        assertTrue(ValidMovementHelperByBasicRule.isLongCastling(7, 4, 7, 2, true));
        assertFalse(ValidMovementHelperByBasicRule.isLongCastling(0, 4, 0, 0, false));
        assertFalse(ValidMovementHelperByBasicRule.isLongCastling(7, 4, 7, 0, true));
    }

    @Test
    public void isByPassing() {
        assertTrue(ValidMovementHelperByBasicRule.isByPassing(3, 1, 2, 0, true));
        assertTrue(ValidMovementHelperByBasicRule.isByPassing(3, 1, 2, 2, true));
        assertTrue(ValidMovementHelperByBasicRule.isByPassing(4, 1, 5, 0, false));
        assertTrue(ValidMovementHelperByBasicRule.isByPassing(4, 1, 5, 2, false));
    }

    @Test
    public void isForwardByOne() {
        assertTrue(ValidMovementHelperByBasicRule.isForwardByOne(4, 4, 3, 4, true));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByOne(4, 4, 5, 4, false));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByOne(3, 6, 2, 6, true));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByOne(3, 6, 4, 6, false));

        assertFalse(ValidMovementHelperByBasicRule.isForwardByOne(4, 4, 3, 4, false));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByOne(4, 4, 5, 4, true));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByOne(3, 6, 2, 6, false));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByOne(3, 6, 4, 6, true));
    }

    @Test
    public void isForwardByTwo() {
        assertTrue(ValidMovementHelperByBasicRule.isForwardByTwo(4, 4, 2, 4, true));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByTwo(4, 4, 6, 4, false));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByTwo(3, 6, 1, 6, true));
        assertTrue(ValidMovementHelperByBasicRule.isForwardByTwo(3, 6, 5, 6, false));

        assertFalse(ValidMovementHelperByBasicRule.isForwardByTwo(4, 4, 2, 4, false));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByTwo(4, 4, 6, 4, true));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByTwo(3, 6, 1, 6, false));
        assertFalse(ValidMovementHelperByBasicRule.isForwardByTwo(3, 6, 5, 6, true));

    }

    @Test
    public void isPawnEating() {
        assertTrue(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 2, 3, true));
        assertTrue(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 2, 5, true));
        assertTrue(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 4, 3, false));
        assertTrue(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 4, 5, false));

        assertFalse(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 2, 3, false));
        assertFalse(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 2, 5, false));
        assertFalse(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 4, 3, true));
        assertFalse(ValidMovementHelperByBasicRule.isPawnEating(3, 4, 4, 5, true));

    }

    @Test
    public void isElephantMove() {
        assertTrue(ValidMovementHelperByBasicRule.isElephantMove(0, 0, 2, 2));
        assertFalse(ValidMovementHelperByBasicRule.isElephantMove(0, 0, 1, 1));
    }
}