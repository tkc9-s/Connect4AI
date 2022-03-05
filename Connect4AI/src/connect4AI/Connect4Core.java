package connect4AI;

/**
 * This is the core class for the game of Connect 4.
 * This contains all of the core game logic.
 * There is no AI in here.
 * 
 * @author Thomas Kennedy, Josh Banaszak
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
	 * @param size The dimensions of the board. Must be greater than 3 and less than 10.
	 */
	public Connect4Core(int size) {
		if (size > 3 && size < 10) boardSize = size;
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
	 * Copy constructor. Allows the game to be copied over to the AI class (Slightly buggy at the moment).
	 * @param game The game to be copied, exclusively used in AI class.
	 */
	public Connect4Core(Connect4Core game) {
		this.boardSize = game.boardSize;
		this.board = game.board;
		this.currentPlayer = game.currentPlayer;
		this.gameState = game.gameState;
		this.turnCount = game.turnCount;
	}
	/**
	 * A check to see if a move is allowed. Basically the first part of makeMove() but singled out into its own function.
	 * @param x the column of the move made
	 * @return true if it can be made, false if it is invalid
	 */
	public boolean isAllowed(int x) {
		boolean canMake = false;
		
		if(x >= 0 && x < boardSize) {
			for(int i = 0; i < boardSize; i++) {
				if (Character.getNumericValue(board[i][x]) == -1) {
					canMake = true;

			}
		}
	}
		return canMake;
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
	 * Handles undoing moves by setting the square to blank and decrementing the turn count. 
	 * Primarily used within the AI.
	 * @param x the row value
	 * @param y the column value
	 */
	public void undoMove(int x, int y) {
		if(board[x][y] == 'X' || board[x][y] == 'O') {
			setBoardAt(x, y, Character.MIN_VALUE);
			//turnCount--;
			//System.out.println("UNDOING"); //Debugging for the undo function
		}
		
		//System.out.println(toString()); //Debugging for the undo function 
		
		if (!gameState) {
			if (currentPlayer == 'X') currentPlayer = 'O';
			else currentPlayer = 'X';
		}

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
	public int[] getRow(int rowNum) {
		int[] row = new int [boardSize];
		for(int i = 0; i < boardSize; i++) {
			row[i] = getBoardAt(rowNum, i);
		}
		return row;
	}
	public int[] getCol(int colNum) {
		int[] col = new int [boardSize];
		for(int i = 0; i < boardSize; i++) {
			col[i] = getBoardAt(i, colNum);
		}
		return col;
	}
	
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
