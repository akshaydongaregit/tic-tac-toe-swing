package com.tresk.toe.board;

public class PlayerTurn {

	private boolean turn = true;
	
	public int getTurn() {
		turn = !turn;
		if(turn)
			return 2;
		else
			return 1;
	}
}
