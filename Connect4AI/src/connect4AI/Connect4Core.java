package connect4AI;
/**
 * This is the core class for the game of Connect 4.
 * This contains all of the core game logic.
 * There is no AI in here.
 * 
 * @author Thomas Kennedy
 */
public class Connect4Core {
	private int boardSize;
	private char[][] board;
	private char currentPlayer;
	private boolean gameState;
	private int turnCount;
	
	/**
	 * Default Constructor. Makes a Connect4Core with default settings
	 */
	public Connect4Core() {
		boardSize = 6;
		board = new char[boardSize][boardSize];
		currentPlayer = 'X';
		gameState = false;
		turnCount = 0;
	}
	
	/**
	 * Optional Constructor. Allows the specification of a board size (All boards are squares)
	 * @param size The dimensions of the board. Must be greater than 3.
	 */
	public Connect4Core(int size) {
		if (size > 3) boardSize = size;
		else boardSize = 4;
		
		board = new char[boardSize][boardSize];
		currentPlayer = 'X';
		gameState = false;
		turnCount = 0;
	}
	
	/**
	 * Optional Constructor. Allows the specification of board size and who goes first.
	 * @param size The dimensions of the board (Boards are squares). 
	 * @param firstPlayer Which player is going first. Should either be an X or O. If it isn't either of those, defaults to X
	 */
	public Connect4Core(int size, char firstPlayer) {
		if (size > 3) boardSize = size;
		else boardSize = 4;
		
		board = new char[boardSize][boardSize];
		if (Character.toUpperCase(firstPlayer) == 'X' || Character.toUpperCase(firstPlayer) == 'O')currentPlayer = Character.toUpperCase(firstPlayer);
		else currentPlayer = 'X';
		gameState = false;
		turnCount = 0;
	}
	
	/**
	 * Handles if a move a player makes is valid and, if so, makes the move
	 * Evaluates a win state after a valid move is made.
	 * If there is a winner, end the game.
	 * @param x The move the player is trying to make.
	 */
	public void makeMove(int x) {
		boolean canMake = false;
		int height = 0;
		
		if (x >= 0 && x < boardSize) {
			for (int i = 0; i < boardSize; i++) {
				if (Character.getNumericValue(board[i][x]) == -1) {
					canMake = true;
					height = i;
				}
			}
		}
		if (canMake && !gameState) {
			board[height][x] = currentPlayer;
			System.out.println(toString());
			turnCount++;
			gameState = checkForWin();
			
			if (turnCount == boardSize * boardSize && !gameState){
				System.out.println("Stalemate!");
				gameState = true;
			}
			else if (!gameState) {
				if (currentPlayer == 'X') currentPlayer = 'O';
				else currentPlayer = 'X';
			}
			else {
				System.out.println("'" + currentPlayer + "' Wins!");
			}
		}
		else if (gameState) {
			System.out.println("Game is over!");
		}
		else {
			System.out.println("Invalid Move! Try again!");
		}
	}
	
	/**
	 * Checks if the game has a winner.
	 * This method was courtesy of StackOverflow and I am not afraid to admit that.
	 * @return true if there is a winner, false if there is no winner
	 */
	public boolean checkForWin() {
		// horizontalCheck 
		for (int j = 0; j<boardSize-3 ; j++ ){
	    	for (int i = 0; i<boardSize; i++){
	    		if (this.board[i][j] == currentPlayer && this.board[i][j+1] == currentPlayer && this.board[i][j+2] == currentPlayer && this.board[i][j+3] == currentPlayer) {
	    			return true;
	            }           
	       	}
	    }
	    // verticalCheck
	    for (int i = 0; i<boardSize-3 ; i++ ){
	    	for (int j = 0; j<boardSize; j++){
	    		if (this.board[i][j] == currentPlayer && this.board[i+1][j] == currentPlayer && this.board[i+2][j] == currentPlayer && this.board[i+3][j] == currentPlayer) {
	    			return true;
	            }    
	      	}
	    }
	    // ascendingDiagonalCheck 
	    for (int i=3; i<boardSize; i++){
	    	for (int j=0; j<boardSize-3; j++){
	    		if (this.board[i][j] == currentPlayer && this.board[i-1][j+1] == currentPlayer && this.board[i-2][j+2] == currentPlayer && this.board[i-3][j+3] == currentPlayer) {
	    			return true;
	    		}
	        }
	    }
	    // descendingDiagonalCheck
	   	for (int i=3; i<boardSize; i++){
	       	for (int j=3; j<boardSize; j++){
	       		if (this.board[i][j] == currentPlayer && this.board[i-1][j-1] == currentPlayer && this.board[i-2][j-2] == currentPlayer && this.board[i-3][j-3] == currentPlayer) {
	       			return true;
	       		}
	       	}
	    }
	    return false;
	}
	
	
	/**
	 * Handles resetting the game if the player(s) wish to go again.
	 * This one is for retaining the same board size
	 */
	public void resetGame() {
		board = new char[boardSize][boardSize];
		// Loser goes first in a reset.
		if (currentPlayer == 'X') currentPlayer = 'O';
		else currentPlayer = 'X';
		gameState = false;
		turnCount = 0;
	}
	
	/**
	 * Handles resetting the game if the player(s) wish to go again.
	 * @param size the size of the board for this game.
	 */
	public void resetGame(int size) {
		boardSize = size;
		board = new char[boardSize][boardSize];
		// Loser goes first in a reset.
		if (currentPlayer == 'X') currentPlayer = 'O';
		else currentPlayer = 'X';
		gameState = false;
		turnCount = 0;
	}
	
	/**
	 * Returns the game board.
	 */
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

	public char getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(char currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public boolean getGameState() {
		return gameState;
	}
	
	public void setGameState(boolean state) {
		gameState = state;
	}
}
