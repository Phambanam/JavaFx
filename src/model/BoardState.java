
package model;

public class BoardState {
	// Mang luu lai cac trang thai cac quan co
	private   int[][] boardArr;
	// chieu rong cua ban co
	public static final int width = 20 ;
	// chieu cao cua ban co
	public static final int height = 20 ;

	// khoi tao
	public BoardState() {
		boardArr = new int[width][height];
	}

	public int[][] getBoard() {
		return this.boardArr;
	}

	// reset ban co
	public void resetBoard(){
		boardArr = new int[width][height];
	}
	// Check chien thang
	public int checkEnd(int row, int col) {
		int r = 0, c = 0;
		int i;
		boolean human1, human2;
		// Check hang ngang
		while (c < width - 4) {
			human1 = true;
			human2 = true;
			for (i = 0; i < 5; i++) {
				if (boardArr[row][c + i] != 1)
					human1 = false;
				if (boardArr[row][c + i] != 2)
					human2 = false;
			}
			if (human1)
				return 1;
			if (human2)
				return 2;
			c++;
		}

		// Check  hang doc
		while (r < height - 4) {
			human1 = true;
			human2 = true;
			for (i = 0; i < 5; i++) {
				if (boardArr[r + i][col] != 1)
					human1 = false;
				if (boardArr[r + i][col] != 2)
					human2 = false;
			}
			if (human1)
				return 1;
			if (human2)
				return 2;
			r++;
		}

		// Check duong cheo xuong
		r = row;
		c = col;
		while (r > 0 && c > 0) {
			r--;
			c--;
		}
		while (r < height - 4 && c < width - 4) {
			human1 = true;
			human2 = true;
			for (i = 0; i < 5; i++) {
				if (boardArr[r + i][c + i] != 1)
					human1 = false;
				if (boardArr[r + i][c + i] != 2)
					human2 = false;
			}
			if (human1)
				return 1;
			if (human2)
				return 2;
			r++;
			c++;
		}

		// Check duong cheo len
		r = row;
		c = col;
		while (r < height - 1 && c > 0) {
			r++;
			c--;
		}

		while (r >= 4 && c < height - 4) {
			human1 = true;
			human2 = true;
			for (i = 0; i < 5; i++) {
				if (boardArr[r - i][c + i] != 1)
					human1 = false;
				if (boardArr[r - i][c + i] != 2)
					human2 = false;
			}
			if (human1)
				return 1;
			if (human2)
				return 2;
			r--;
			c++;
		}
		return 0;
	}
	// set trang thai cho 1 quan co xac dinh
	public int setPosition(int x, int y, int player) {
		boardArr[x][y] = player;
		if(player == 1) return 1 ;
		if(player == 2) return 2;
		return 0;
	}
}
