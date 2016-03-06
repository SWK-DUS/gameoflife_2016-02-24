package org.softwerkskammer.dus.kata.gameoflife;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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
        int aliveNeighbours = countAliveNeighbours(board,1,1);
        assertThat(aliveNeighbours, equalTo(0));
    }

    @Test
    public void countAliveNeighbours_oneSingleDeadCell_OneNeighbour() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        int aliveNeighbours = countAliveNeighbours(board,1,1);
        assertThat(aliveNeighbours, equalTo(1));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_TwoNeighbours() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;
        int aliveNeighbours = countAliveNeighbours(board,1,1);
        assertThat(aliveNeighbours, equalTo(2));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_ThreeNeighbours() throws Exception {
        boolean[][] board = new boolean[3][3];
        board[0][0] = true;
        board[0][1] = true;
        board[1][2] = true;
        int aliveNeighbours = countAliveNeighbours(board,1,1);
        assertThat(aliveNeighbours, equalTo(3));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_FiveByFiveBoard() throws Exception {
        boolean[][] board = new boolean[5][5];
        board[3][3] = true;
        board[2][3] = true;
        board[1][3] = true;
        int aliveNeighbours = countAliveNeighbours(board, 2,2);
        assertThat(aliveNeighbours, equalTo(3));
    }

    @Test
    public void countAliveNeighbours_oneSingleAliveCell_FiveByFiveBoard_TwoNeighbours() throws Exception {
        boolean[][] board = new boolean[5][5];
        board[3][3] = true;
        board[2][3] = true;
        int aliveNeighbours = countAliveNeighbours(board, 2,2);
        assertThat(aliveNeighbours, equalTo(2));
    }

    private int countAliveNeighbours(boolean[][] board, int x, int y) {
        int numberOfAliveNeighbours = 0;
        for (int i = x-1; i <= x+1; i++) {
            for (int j = y-1; j <= y+1; j++) {
                if (board[i][j] && !(i == x && j == y)) {
                    numberOfAliveNeighbours++;
                }
            }
        }

        return numberOfAliveNeighbours;
    }


    private boolean middleCellIsAliveInNextGeneration(boolean[][] board) {
        return (countAliveNeighbours(board,1,1) == 2 && board[1][1])
                || countAliveNeighbours(board,1,1) == 3;

    }

}
