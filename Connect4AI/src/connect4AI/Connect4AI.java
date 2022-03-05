package connect4AI;

import java.util.Random;


/**
 * This class is all about the AI for the Connect 4 Game.
 * It will be able to get the gamestate from the game itself, and make decisions based on that.
 * @author Thomas Kennedy, Josh Banaszak
 */
public class Connect4AI {
	private int difficulty;// How many layers deep the AI will compute potential moves 
	private int bestScore = 0;
	private int score;
	private int maxScore;
	private int minScore;
	//private int bestMove = -1;
	private int maxMove;
	private int minMove;
	private int playerOScore;
	private int playerXScore;
	public Connect4AI() {
		difficulty = 3;

	}
	
	public Connect4AI(int dif) {
		difficulty = dif;
	}


	public int score(Connect4Core game) { 
		
		//TODO tune this implementation or make a better one.
		bestScore = 0;
		playerOScore = 0;
		playerXScore = 0;
		int[] rowArray = new int[game.getBoardSize()];
		int [] colArray = new int[game.getBoardSize()];
		/**
		 * is supposed to gather score of all possible rows of 4, might be a better way to implement this
		 * 4 in a row yields highest value 
		 * 3 in a row has a high value but not as much as 4
		 * 2 in a row has minimal value
		 * Currently commented out because it makes the bot act very very dumb, comment ends at Line 204
		 */
		/**
		if(game.checkForWin() == true && game.getCurrentPlayer() == 'O') {
			playerOScore += 10000;
		}
		if(game.checkForWin() == true && game.getCurrentPlayer() == 'X') {
			playerXScore += 10000;
		}
		for(int i = 0; i < game.getBoardSize() - 3; i++) { 
			rowArray = game.getRow(i);
			
			if(rowArray[i] == 'O' && rowArray[i+1] == 'O' &&rowArray[i+2] == 'O' &&rowArray[i+3] == 'O') {
				playerOScore += 10000;
			}
		    if (rowArray[i] == 'X' && rowArray[i+1] == 'X' &&rowArray[i+2] == 'X' &&rowArray[i+3] == 'X') {
				playerXScore += 5000;
			}
			if (rowArray[i] == 'O' &&rowArray[i+2] == 'O' &&rowArray[i+3] == 'O' && rowArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (rowArray[i] == 'O' &&rowArray[i+1] == 'O' &&rowArray[i+3] == 'O' && rowArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (rowArray[i] == 'O' &&rowArray[i+1] == 'O' &&rowArray[i+2] == 'O' && rowArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (rowArray[i+1] == 'O' &&rowArray[i+2] == 'O' &&rowArray[i+3] == 'O' && rowArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (rowArray[i] == 'O' && rowArray[i+1] == 'O'&& rowArray[i+2] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (rowArray[i] == 'O' && rowArray[i+2] == 'O' && rowArray[i+1] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (rowArray[i] == 'O' && rowArray[i+3] == 'O' && rowArray[i+1] == Character.MIN_VALUE && rowArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (rowArray[i+1] == 'O' && rowArray[i+2] == 'O' && rowArray[i] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (rowArray[i+1] == 'O' && rowArray[i+3] == 'O' && rowArray[i] == Character.MIN_VALUE && rowArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (rowArray[i+2] == 'O' && rowArray[i+3] == 'O' && rowArray[i] == Character.MIN_VALUE && rowArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			

			if (rowArray[i] == 'X' &&rowArray[i+2] == 'X' &&rowArray[i+3] == 'X' && rowArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (rowArray[i] == 'X' &&rowArray[i+1] == 'X' &&rowArray[i+3] == 'X' && rowArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (rowArray[i] == 'X' &&rowArray[i+1] == 'X' &&rowArray[i+2] == 'X' && rowArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (rowArray[i+1] == 'X' &&rowArray[i+2] == 'X' &&rowArray[i+3] == 'X' && rowArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (rowArray[i] == 'X' && rowArray[i+1] == 'X'&& rowArray[i+2] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (rowArray[i] == 'X' && rowArray[i+2] == 'X' && rowArray[i+1] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (rowArray[i] == 'X' && rowArray[i+3] == 'X' && rowArray[i+1] == Character.MIN_VALUE && rowArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (rowArray[i+1] == 'X' && rowArray[i+2] == 'X' && rowArray[i] == Character.MIN_VALUE && rowArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (rowArray[i+1] == 'X' && rowArray[i+3] == 'X' && rowArray[i] == Character.MIN_VALUE && rowArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (rowArray[i+2] == 'X' && rowArray[i+3] == 'X' && rowArray[i] == Character.MIN_VALUE && rowArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			



		}
		
		for(int i = 0; i < game.getBoardSize() - 3; i++) { //is supposed to gather score of all possible columns of 4, might be a better way to implement this
			colArray = game.getCol(i);
			
			
			if(colArray[i] == 'O' && colArray[i+1] == 'O' &&colArray[i+2] == 'O' &&colArray[i+3] == 'O') {
				playerOScore += 10000;
			}
			if (colArray[i] == 'X' && colArray[i+1] == 'X' &&colArray[i+2] == 'X' &&colArray[i+3] == 'X') {
				playerXScore += 5000;
			}
			if (colArray[i] == 'O' &&colArray[i+2] == 'O' &&colArray[i+3] == 'O' && colArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (colArray[i] == 'O' &&colArray[i+1] == 'O' &&colArray[i+3] == 'O' && colArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (colArray[i] == 'O' &&colArray[i+1] == 'O' &&colArray[i+2] == 'O' && colArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (colArray[i+1] == 'O' &&colArray[i+2] == 'O' &&colArray[i+3] == 'O' && colArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 500;
			}
			if (colArray[i] == 'O' && colArray[i+1] == 'O'&& colArray[i+2] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (colArray[i] == 'O' && colArray[i+2] == 'O' && colArray[i+1] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (colArray[i] == 'O' && colArray[i+3] == 'O' && colArray[i+1] == Character.MIN_VALUE && colArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (colArray[i+1] == 'O' && colArray[i+2] == 'O' && colArray[i] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (colArray[i+1] == 'O' && colArray[i+3] == 'O' && colArray[i] == Character.MIN_VALUE && colArray[i+2] == Character.MIN_VALUE) {
				playerOScore += 50;
			}
			if (colArray[i+2] == 'O' && colArray[i+3] == 'O' && colArray[i] == Character.MIN_VALUE && colArray[i+1] == Character.MIN_VALUE) {
				playerOScore += 50;
			}

			if (colArray[i] == 'X' &&colArray[i+2] == 'X' &&colArray[i+3] == 'X' && colArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (colArray[i] == 'X' &&colArray[i+1] == 'X' &&colArray[i+3] == 'X' && colArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (colArray[i] == 'X' &&colArray[i+1] == 'X' &&colArray[i+2] == 'X' && colArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (colArray[i+1] == 'X' &&colArray[i+2] == 'X' &&colArray[i+3] == 'X' && colArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 500;
			}
			if (colArray[i] == 'X' && colArray[i+1] == 'X'&& colArray[i+2] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (colArray[i] == 'X' && colArray[i+2] == 'X' && colArray[i+1] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (colArray[i] == 'X' && colArray[i+3] == 'X' && colArray[i+1] == Character.MIN_VALUE && colArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (colArray[i+1] == 'X' && colArray[i+2] == 'X' && colArray[i] == Character.MIN_VALUE && colArray[i+3] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (colArray[i+1] == 'X' && colArray[i+3] == 'X' && colArray[i] == Character.MIN_VALUE && colArray[i+2] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			if (colArray[i+2] == 'X' && colArray[i+3] == 'X' && colArray[i] == Character.MIN_VALUE && colArray[i+1] == Character.MIN_VALUE) {
				playerXScore += 50;
			}
			
		}
		*/
		bestScore = playerXScore - playerOScore ;
		return bestScore;
		
	}
	public int evaluate(Connect4Core game) {
		int score = -10078; //temp value
		score = score(game);
		return score;
}
	
	/**
	 * The alpha beta search algorithm. looks through all possible moves, simulates it, gets a score for it and attempts to pick the best possible move.
	 * @param depth how deep the search should go
	 * @param game the entire game state
	 * @param isMax true if it's the maximizing player (the AI), false if minimizing (the human player)
	 * @param alpha the alpha value in the alpha beta pruning
	 * @param beta the beta value of the alpha beta pruning
	 * @return an int representing the column the move will be placed in.
	 */
	public int minimax(int depth, Connect4Core game, boolean isMax, int alpha, int beta) { 
		if(depth == difficulty) {
			bestScore = evaluate(game);
			return evaluate(game);
		}
		
		
		if(isMax == true) {//maximum (alpha)
			maxScore = Integer.MIN_VALUE;
			 for(int i = 0; i < game.getBoardSize(); i++) {
				 for(int j = 0; j < game.getBoardSize(); j++) {
					 if(game.getBoardAt(i, j) == Character.MIN_VALUE) { // should ensure a move exists
						 game.setBoardAt(i, j, 'O');
						 game.setCurrentPlayer('O');
						 score = minimax(depth + 1, game, false, alpha, beta);
						 game.undoMove(i,j);
						 if(score > maxScore) {
							 maxScore = score;
							 maxMove = j;
						 	}
						 alpha = Math.max(alpha, maxScore);
						 if(beta <= alpha){
							 break;
						 }
						 }

					 }

				}

			 return maxMove;
			 }
			 
		
		else { //minimum (beta)
			minScore = Integer.MAX_VALUE;
				for(int i = 0; i < game.getBoardSize(); i++) {
					for(int j = 0; j < game.getBoardSize(); j++) {
						if(game.getBoardAt(i, j) == Character.MIN_VALUE) {
							 game.setBoardAt(i, j, 'X');
							 game.setCurrentPlayer('X');
							 score = minimax(depth + 1, game, true, alpha, beta);
							 game.undoMove(i,j);
							 if(score < minScore) {
								 minScore = score;
								 minMove = j;
							 }
							 beta = Math.min(beta, minScore);
							 if(beta <= alpha) {
								 break;
							 }

					}

				}
			}
		return minMove;
		}
}
	/**
	 * Launches the initial call to minimax, begins the search tree.
	 * @param game, the entire game state that minimax will use.
	 * @return move which represents the column that will be played.
	 */
	public int decide(Connect4Core game) {
		Random rng = new Random();
		int move = 5;
		Connect4Core copy = new Connect4Core(game);
		
		move = minimax(0, copy, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
		//System.out.println(bestScore + "move" + move);
	
		
		//System.out.println(move); 
		if(maxScore < 0|| move > game.getBoardSize() || game.isAllowed(move) == false) { //randomly picks a column, currently unused
		move = rng.nextInt(game.getBoardSize());
		while(game.isAllowed(move) == false) {
			move = rng.nextInt(game.getBoardSize());
		}
		System.out.println("rng used!!!!!");
		}
		return move;
	}
}

