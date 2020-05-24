
package model;
public class EvalBoard {
	public int height, width;
	public int[][] EBoard;
	public EvalBoard(int height, int width) {
		this.height = height;
		this.width = width;
		EBoard = new int[height][width];
	}
}
