package connect4AI;
/**
 * This is the core class for the game of Connect 4.
 * This contains all of the core game logic.
 * This does not have any AI or visual code or control logic in it.
 * This is just the game and it's rules.
 * 
 * @author Thomas Kennedy
 */
public class Connect4Core {
	private int boardSize;
	private int[][] board;
	private boolean currentPlayer;
	
	public Connect4Core(int size) {
		boardSize = size;
		board = new int[boardSize][boardSize];
		currentPlayer = false;
	}
	
	public Connect4Core(int size, boolean firstPlayer) {
		boardSize = size;
		board = new int[boardSize][boardSize];
		currentPlayer = firstPlayer;
	}
	
	public void makeMove(int x) {
		boolean canMake = false;
		int height = boardSize - 1;
		
		if (x >= 0 && x < boardSize) {
			for (int i = height; i >= 0; i--) {
				if (board[x][i] == 0) {
					canMake = true;
					height = i;
				}
			}
		}
		
		if (canMake) {
			if (!currentPlayer) {
				board[x][height] = 1;
				currentPlayer = true;
			}
			else if(currentPlayer) {
				board[x][height] = 2;
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
	
	public int checkForWin() {
		//TODO: Find a good way to actually do this without brute forcing a solution.
		return 0;
	}
	
	//-------------Getters and Setters below this line-------------\\
	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public int getBoardAt(int x, int y) {
		return board[x][y];
	}
	
	// Should only be used to make a value either 0, 1, or two
	public void setBoardAt(int x, int y, int value) {
		board[x][y] = value;
	}

	public boolean isCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(boolean currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
}
