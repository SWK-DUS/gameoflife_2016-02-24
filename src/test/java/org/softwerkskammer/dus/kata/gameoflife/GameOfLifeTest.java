package org.softwerkskammer.dus.kata.gameoflife;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static java.lang.Math.min;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

    @Test
    public void nextGeneration_ofSingleCellBoard_isDeadBoard() {
        boolean[][] board = new boolean[8][8];
        board[1][1] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(false));
    }

    @Test
    public void nextGeneration_aliveCellWithOneNeighbour_isDead() {
        boolean[][] board = new boolean[3][3];
        board[1][1] = true;
        board[0][1] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(false));
    }

    @Test
    public void nextGeneration_aliveCellWithThreeNeighbours_isAlive() {
        boolean[][] board = new boolean[3][3];
        board[1][1] = true;
        board[0][1] = true;
        board[0][0] = true;
        board[2][1] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(true));
    }

    @Test
    public void nextGeneration_aliveCellWithTwoNeighbours_staysAlive() {
        boolean[][] board = new boolean[3][3];
        board[1][1] = true;
        board[0][1] = true;
        board[2][1] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(true));
    }

    @Test
    public void nextGeneration_deadCellWithThreeNeighbours_isBorn() {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;
        board[0][2] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(true));
    }

    @Test
    public void nextGeneration_deadCellWithTwoNeighbours_isNotBorn() {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;

        boolean result = middleCellIsAliveInNextGeneration(board);

        assertThat(result, is(false));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_noNeighbours() {
        boolean[][] board = new boolean[3][3];
        board[1][1] = true;
        int aliveNeighbours = countAliveNeighbours(board, 1, 1);
        assertThat(aliveNeighbours, equalTo(0));
    }

    @Test
    public void countAliveNeighbours_oneSingleDeadCell_OneNeighbour() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        int aliveNeighbours = countAliveNeighbours(board, 1, 1);
        assertThat(aliveNeighbours, equalTo(1));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_TwoNeighbours() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;
        int aliveNeighbours = countAliveNeighbours(board, 1, 1);
        assertThat(aliveNeighbours, equalTo(2));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_ThreeNeighbours() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;
        board[1][2] = true;
        int aliveNeighbours = countAliveNeighbours(board, 1, 1);
        assertThat(aliveNeighbours, equalTo(3));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_FiveByFiveBoard() throws Exception {
        boolean[][] board = new boolean[5][5];
        board[3][3] = true;
        board[2][3] = true;
        board[1][3] = true;
        int aliveNeighbours = countAliveNeighbours(board, 2, 2);
        assertThat(aliveNeighbours, equalTo(3));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_FiveByFiveBoard_TwoNeighbours() throws Exception {
        boolean[][] board = new boolean[5][5];
        board[3][3] = true;
        board[2][3] = true;
        int aliveNeighbours = countAliveNeighbours(board, 2, 2);
        assertThat(aliveNeighbours, equalTo(2));
    }


    @Test
    public void nextGeneration_upperLeftCellWithNoNeighbours_isDead() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        assertThat(givenCellIsAliveInNextGeneration(board, 0, 0), is(false));

    }

    @Test
    public void nextGeneration_upperLeftCellWithTwoNeighbours_staysAlive() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][1] = true;
        board[1][0] = true;
        board[0][0] = true;
        assertThat(givenCellIsAliveInNextGeneration(board, 0, 0), is(true));

    }


    @Test
    public void countAlive_upperLeftCellWithNoNeighbours_0() throws Exception {
        boolean[][] board = new boolean[3][3];
        assertThat(countAliveNeighbours(board, 0, 0), is(0));

    }

    @Test
    public void countAlive_upperLeftCellWithTwoNeighbours_2() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][1] = true;
        board[1][0] = true;
        assertThat(countAliveNeighbours(board, 0, 0), is(2));

    }


    @Test
    public void countAlive_lowerRightCellWithTwoNeighbours_2() throws Exception {
        boolean[][] board = new boolean[3][3];

        board[2][1] = true;
        board[1][2] = true;
        assertThat(countAliveNeighbours(board, 2, 2), is(2));

    }

    @Test
    public void calculateBoardNextGeneration_SingleCell_AllDead() throws Exception {
        boolean[][] board = new boolean[3][3];

        board[2][2] = true;

        boolean[][] boardNextGeneration = calculateBoardNextGeneration(board);

        assertThat(boardNextGeneration, equalTo(new boolean[][]{
                new boolean[]{false, false, false},
                new boolean[]{false, false, false},
                new boolean[]{false, false, false}}));
    }

    @Test
    public void calculateBoardNextGeneration_Blinker_Rotates() throws Exception {
        boolean[][] board = new boolean[][]{
                new boolean[]{false, false, false},
                new boolean[]{true, true, true},
                new boolean[]{false, false, false}};

        boolean[][] boardNextGeneration = calculateBoardNextGeneration(board);

        assertThat(boardNextGeneration, equalTo(new boolean[][]{
                new boolean[]{false, true, false},
                new boolean[]{false, true, false},
                new boolean[]{false, true, false}}));
    }

    @Test
    public void calculateBoardNextGeneration_BlinkerOnBigBoard_Rotates() throws Exception {
        boolean[][] board = new boolean[][]{
                new boolean[]{false, false, false, false, false},
                new boolean[]{true, true, true, false, false},
                new boolean[]{false, false, false, false, false},
                new boolean[]{false, false, false, false, false}};

        boolean[][] boardNextGeneration = calculateBoardNextGeneration(board);

        assertThat(boardNextGeneration, equalTo(new boolean[][]{
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, true, false, false, false},
                new boolean[]{false, false, false, false, false}}));
    }

    private boolean[][] calculateBoardNextGeneration(boolean[][] board) {
        boolean[][] boardNextGeneration = new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                boardNextGeneration[row][column] = givenCellIsAliveInNextGeneration(board, row, column);
            }
        }

        return boardNextGeneration;
    }

    private int countAliveNeighbours(boolean[][] board, int row, int column) {
        int numberOfAliveNeighbours = 0;
        int rowMin = max(row - 1, 0);
        for (int i = rowMin; i <= min(row + 1, board.length - 1); i++) {
            int columnMIn = max(column - 1, 0);
            for (int j = columnMIn; j <= min(column + 1, board[i].length - 1); j++) {
                if (board[i][j] && !(i == row && j == column)) {
                    numberOfAliveNeighbours++;
                }
            }
        }

        return numberOfAliveNeighbours;
    }


    private boolean middleCellIsAliveInNextGeneration(boolean[][] board) {
        return givenCellIsAliveInNextGeneration(board, 1, 1);
    }

    private boolean givenCellIsAliveInNextGeneration(boolean[][] board, int row, int column) {
        return (countAliveNeighbours(board, row, column) == 2 && board[row][column])
                || countAliveNeighbours(board, row, column) == 3;

    }

    @Test
    public void printEmptyBoard() throws Exception {
        boolean[][] emptyBoard = new boolean[3][3];
        assertThat(printBoard(emptyBoard), equalTo("...\n...\n..."));

    }

    @Test
    public void printFullBoard() throws Exception {

        boolean[][] immediatelyFullBoard = {
                new boolean[]{true, true, true},
                new boolean[]{true, true, true},
                new boolean[]{true, true, true}};
        assertThat(printBoard(immediatelyFullBoard), equalTo("XXX\nXXX\nXXX"));

    }

    public String printBoard(boolean[][] board) {
        return Stream.of(board).map(this::printRow).collect(Collectors.joining("\n"));

    }

    private String printRow(boolean[] row) {
        Boolean[] objectRow = ArrayUtils.toObject(row);
        return Stream.of(objectRow).map(cell -> cell ? "X" : ".").collect(Collectors.joining());


    }


}
