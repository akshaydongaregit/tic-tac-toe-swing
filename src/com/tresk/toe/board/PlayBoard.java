package com.tresk.toe.board;

public class PlayBoard {
	
	int board[][]=new int[3][3];

	Direction directions[]=new Direction[8];
	
	public void initDirections() {
		directions[0] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x+1,y};
			}
		};
		directions[1] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x-1,y};
			}
		};directions[2] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x,y-1};
			}
		};directions[3] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x,y+1};
			}
		};directions[4] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x+1,y-1};
			}
		};directions[5] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x-1,y-1};
			}
		};directions[6] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x-1,y+1};
			}
		};
		directions[7] = new Direction() {
			@Override
			public int[] move(int x, int y) {
				return new int[]{x+1,y+1};
			}
		};
	}
	
	public int verifyBoard() {
		for(int x=0;x<3;x++) {
			for(int y=0;y<3;y++) {
				boolean player1=checkCell(x,y,1);
				if(player1) return 1;
				boolean player2=checkCell(x,y,2);
				if(player2) return 2;
			}
		}
		return 0;
	}

	public boolean checkCell(int x, int y,int player) {
		for(int i=0;i<8;i++) {
			if(checkInDirection(x,y,player,3,directions[i],i))
				return true;
		}
		return false;
	}
	
	public boolean checkInDirection(int x,int y,int player,int cutOff,Direction direction,int d) {
		boolean match=true;
		
		for(int i=0;i<cutOff;i++) {
			if( board[x][y]!=0 && board[x][y]==player) {
				int[] nextMove = direction.move(x, y);
				x = nextMove[0];
				y = nextMove[1];
				if(x>=cutOff || x<0 || y>=cutOff || y<0) {
					if(i==2)
						match = true;
					else
						match = false;
					break;
				}
			}else {
				match = false;
				break;
			}
		}
		
		
		return match;
	}
	
	public boolean setCell(int x,int y , int val) {
		
		if(board[x][y]==0)
			board[x][y] = val;
		else
			return false;
		
		return true;
	}
	
	public void reset() {
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				board[i][j] = 0;
	}
	
	public boolean verifyDraw() {
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				if(board[i][j] == 0)
					return false;
		return true;
	}
	
}
