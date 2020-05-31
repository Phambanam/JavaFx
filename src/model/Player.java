package model;

public class Player {
	BoardState boardState; // ban co de xu li
	int playerFlag = 1; // danh dau la nguoi choi
	public Player(BoardState board) {
		this.boardState = board;
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
