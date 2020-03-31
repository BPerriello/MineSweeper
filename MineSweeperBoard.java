package cs2114.minesweeper;

public class MineSweeperBoard extends MineSweeperBoardBase{
	
	private int width;
	private int height;
	private int number_of_mines;
	private MineSweeperCell[][] board;
	
	MineSweeperBoard(int width, int height, int number_of_mines) {
		board = new MineSweeperCell[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				board[i][j] = MineSweeperCell.COVERED_CELL;
			}
		}
		for(int k = 0; k < number_of_mines; k++) {
			int x = (int)(Math.random()*width);
			int y = (int)(Math.random()*height);
			if(board[x][y] == MineSweeperCell.COVERED_CELL) {
			board[x][y] = MineSweeperCell.MINE;
			}
		
		
		}
	}

	@Override
	public int width() {
		return this.width;
	}

	@Override
	public int height() {
		return this.height;
	}

	@Override
	public MineSweeperCell getCell(int x, int y) {
		return board[x][y];
	}

	@Override
	public void uncoverCell(int x, int y) {
		int count = 0;
		if (board[x][y] == MineSweeperCell.FLAG) {
			board[x][y] = MineSweeperCell.FLAG;
		} else if (board[x][y] != MineSweeperCell.MINE) {
			for(int i = x-1; i <= x + 1; i++) {
				for(int j = y-1; j <= y + 1; j++) {
					if (board[i][j] == MineSweeperCell.MINE || board[i][j] == MineSweeperCell.FLAGGED_MINE) {
						count++;
					}
				}
			}
			//Show number of adjacent bombs
			switch (count) {
			case 0: board[x][y] = MineSweeperCell.ADJACENT_TO_0;
			break;
			case 1: board[x][y] = MineSweeperCell.ADJACENT_TO_1;
			break;
			case 2: board[x][y] = MineSweeperCell.ADJACENT_TO_2;
			break;
			case 3: board[x][y] = MineSweeperCell.ADJACENT_TO_3;
			break;
			case 4: board[x][y] = MineSweeperCell.ADJACENT_TO_4;
			break;
			case 5: board[x][y] = MineSweeperCell.ADJACENT_TO_5;
			break;
			case 6: board[x][y] = MineSweeperCell.ADJACENT_TO_6;
			break;
			case 7: board[x][y] = MineSweeperCell.ADJACENT_TO_7;
			break;
			case 8: board[x][y] = MineSweeperCell.ADJACENT_TO_8;
			break;
			}
		} else if (board[x][y] == MineSweeperCell.MINE) {
			board[x][y] = MineSweeperCell.UNCOVERED_MINE;
			//Game Over

		}//Do nothing if cell is already uncovered or invalid.
		
	}

	@Override
	public void flagCell(int x, int y) {
		if (board[x][y] == MineSweeperCell.COVERED_CELL) {
			board[x][y] = MineSweeperCell.FLAG;
		}
		if (board[x][y] == MineSweeperCell.MINE) {
			board[x][y] = MineSweeperCell.FLAGGED_MINE;
		}
		if (board[x][y] == MineSweeperCell.FLAG) {
			board[x][y] = MineSweeperCell.COVERED_CELL;
		}
		if (board[x][y] == MineSweeperCell.FLAGGED_MINE) {
			board[x][y] = MineSweeperCell.MINE;
		}
	}

	@Override
	public boolean isGameLost() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if (board[i][j] == MineSweeperCell.UNCOVERED_MINE) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isGameWon() {
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if (board[i][j] == MineSweeperCell.FLAGGED_MINE) {
					count1 = count1 + 1;
				}
				if (board[i][j] == MineSweeperCell.FLAG) {
					count2 = count2 + 1;
				}
				if (board[i][j] == MineSweeperCell.COVERED_CELL) {
					count3 = count3 + 1;
				}
			}
		}
		if(count1 == number_of_mines) {
			return count2 == 0 && count3 == 0;
		}
		return false;
	}

	@Override
	public int numberOfAdjacentMines(int x, int y) {
		int count = 0;
		for(int i = x-1; i <= x + 1; i++) {
			for(int j = y-1; j <= y + 1; j++) {
				if (board[i][j] == MineSweeperCell.MINE || board[i][j] == MineSweeperCell.FLAGGED_MINE) {
					count++;
				}
			}
		}
		
		return count;
	}

	@Override
	public void revealBoard() {
		if (isGameWon() || isGameLost()) {
			for(int i = 0; i < width; i++) {
				for(int j = 0; j < height; j++) {
					uncoverCell(i,j);
				}
			}
			
		}
		
	}

	@Override
	protected void setCell(int x, int y, MineSweeperCell value) {
		board[x][y] = value;
	}

}
