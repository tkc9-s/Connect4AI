package connect4AI;
/**
 * This is the core class for the game of Connect 4.
 * This contains all of the core game logic.
 * 
 * @author Thomas Kennedy
 */
public class Connect4Core {
	private int boardSize;
	private char[][] board;
	private boolean currentPlayer;
	
	public Connect4Core() {
		boardSize = 5;
		board = new char[boardSize][boardSize];
		currentPlayer = false;
	}
	
	public Connect4Core(int size) {
		boardSize = size;
		board = new char[boardSize][boardSize];
		currentPlayer = false;
	}
	
	public Connect4Core(int size, boolean firstPlayer) {
		boardSize = size;
		board = new char[boardSize][boardSize];
		currentPlayer = firstPlayer;
	}
	
	public void makeMove(int x) {
		boolean canMake = false;
		int height = 0;
		
		if (x >= 0 && x < boardSize) {
			for (int i = 0; i < boardSize; i++) {
				if (board[i][x] == 0) {
					canMake = true;
					height = i;
				}
			}
		}
		
		if (canMake) {
			if (!currentPlayer) {
				board[height][x] = 'X';
				currentPlayer = true;
			}
			else if(currentPlayer) {
				board[height][x] = 'O';
				currentPlayer = false;
			}
		}
		else {
			System.out.println("Invalid Move! Try again!");
		}
	}
	
	// This is not intended to actually be used in normal play.
	// It's mostly for debugging and testing purposes
	public void changePlayer() {
		currentPlayer = !currentPlayer;
	}
	
	public boolean checkForWin() {
		//TODO: Find a good way to actually do this without brute forcing a solution.
		return false;
	}
	
	//-------------Getters and Setters below this line-------------\\
	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	public int getBoardAt(int x, int y) {
		return board[x][y];
	}
	
	// Should only be used to make a value either 'X', 'O', or blank
	public void setBoardAt(int x, int y, char value) {
		board[x][y] = value;
	}

	public boolean isCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(boolean currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public String toString() {
		String ret = "";
		
		for (int i = 0; i < boardSize * 2; i++) {
			if (i%2 == 1) {
				ret += "| ";
			}
			for (int j = 0; j < boardSize; j++) {
				if (i%2 == 0) {
					ret += "+---";
				}
				else {
					ret += board[i/2][j] + " | ";
				}
			}
			if (i%2 == 0) {
				ret += "+";
			}
			ret+="\n";
			
		}
		for (int i = 0; i < boardSize; i++) {
			ret += "+---";
		}
		ret += "+";
		
		return ret;
	}
}
