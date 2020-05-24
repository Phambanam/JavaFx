package gameTest;

import model.BoardState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardStateTest {

    @Test
    void checkEnd() {
         int width = 20;
         int height = 20;
        BoardState boardState = new BoardState(width,height);
        boardState.boardArr = new int[width][height];
        boardState. boardArr[1][1] = 1;
        boardState.boardArr[1][2] = 1;
        boardState.boardArr[1][3] = 1;
        boardState.boardArr[1][5] = 1;
        boardState.boardArr[1][4] = 1;
        assertEquals(1,boardState.checkEnd(1,1));
        assertEquals(1,boardState.checkEnd(1,2));
        assertEquals(1,boardState.checkEnd(1,3));
        assertEquals(1,boardState.checkEnd(1,4));
        assertEquals(1,boardState.checkEnd(1,5));

        boardState.boardArr[6][6] = 2;
        boardState.boardArr[2][2] = 2;
        boardState.boardArr[3][3] = 2;
        boardState.boardArr[4][4] = 2;
        boardState.boardArr[5][5] = 2;
        assertEquals(2,boardState.checkEnd(3,3));
        assertEquals(2,boardState.checkEnd(2,2));
        assertEquals(2,boardState.checkEnd(4,4));
        assertEquals(2,boardState.checkEnd(5,5));
        assertEquals(2,boardState.checkEnd(6,6));
        boardState.boardArr[1][7] = 2;
        boardState.boardArr[2][7] = 2;
        boardState.boardArr[3][7] = 2;
        boardState.boardArr[4][7] = 2;
        boardState.boardArr[5][7] = 2;
        assertEquals(2,boardState.checkEnd(6,7));
        assertEquals(2,boardState.checkEnd(2,7));
        assertEquals(2,boardState.checkEnd(3,7));
        assertEquals(2,boardState.checkEnd(4,7));
        assertEquals(2,boardState.checkEnd(5,7));

    }
}