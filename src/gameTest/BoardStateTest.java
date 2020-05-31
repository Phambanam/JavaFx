package gameTest;

import model.BoardState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardStateTest {
      BoardState boardState = new BoardState();
      int[][] a = new int[20][20];
    @Test
    void getBoard() {

    assertTrue(boardState.getBoard().length== a.length);
    }

    @Test
    void resetBoard() {
        boardState.getBoard()[1][1] = 5;
        boardState.resetBoard();
      assertTrue(boardState.getBoard()[1][1] == 0);
    }

    @Test
    void checkEnd() {
        boardState.getBoard()[1][1] = 1;
        boardState.getBoard()[1][2] = 1;
        boardState.getBoard()[1][3] = 1;
        boardState.getBoard()[1][5] = 1;
        boardState.getBoard()[1][4] = 1;
        assertEquals(1,boardState.checkEnd(1,1));
        assertEquals(1,boardState.checkEnd(1,2));
        assertEquals(1,boardState.checkEnd(1,3));
        assertEquals(1,boardState.checkEnd(1,4));
        assertEquals(1,boardState.checkEnd(1,5));

        boardState.getBoard()[6][6] = 2;
        boardState.getBoard()[2][2] = 2;
        boardState.getBoard()[3][3] = 2;
        boardState.getBoard()[4][4] = 2;
        boardState.getBoard()[5][5] = 2;
        assertEquals(2,boardState.checkEnd(3,3));
        assertEquals(2,boardState.checkEnd(2,2));
        assertEquals(2,boardState.checkEnd(4,4));
        assertEquals(2,boardState.checkEnd(5,5));
        assertEquals(2,boardState.checkEnd(6,6));
        boardState.getBoard()[1][7] = 2;
        boardState.getBoard()[2][7] = 2;
        boardState.getBoard()[3][7] = 2;
        boardState.getBoard()[4][7] = 2;
        boardState.getBoard()[5][7] = 2;
        assertEquals(2,boardState.checkEnd(6,7));
        assertEquals(2,boardState.checkEnd(2,7));
        assertEquals(2,boardState.checkEnd(3,7));
        assertEquals(2,boardState.checkEnd(4,7));
        assertEquals(2,boardState.checkEnd(5,7));
    }

    @Test
    void setPosition() {
        boardState.setPosition(5,6,2);
        assertTrue(boardState.getBoard()[5][6] == 2);
        boardState.setPosition(5,6,3);
        assertFalse(boardState.getBoard()[5][6] == 2);
    }

}