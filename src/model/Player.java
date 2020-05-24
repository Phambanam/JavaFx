package model;

public class Player {
	BoardState boardState; // ban co de xu li
	int playerFlag = 1; // danh dau la nguoi choi
	EvalBoard eBoard;
	public Player(BoardState board) {
		this.boardState = board;
		this.eBoard = new EvalBoard(board.width, board.height);
	}

	public int getPlayerFlag() {
		return playerFlag;
	}

	public void setPlayerFlag(int playerFlag) {
		this.playerFlag = playerFlag;
	}

	public BoardState getBoardState() {
		return boardState;
	}

}
