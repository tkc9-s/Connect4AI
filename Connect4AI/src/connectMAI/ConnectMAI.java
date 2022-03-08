package connectMAI;

import java.util.Random;


/**
 * This class is all about the AI for the Connect 4 Game.
 * It will be able to get the gamestate from the game itself, and make decisions based on that.
 * @author Josh Banaszak, Thomas Kennedy
 */
public class ConnectMAI {
	private int difficulty;// How many layers deep the AI will compute potential moves 
	private int maxScore;
	private int minScore;
	private int bestMove;

	public ConnectMAI() {
		difficulty = 2; //CURRENTLY ONLY WORKS ON DIFFICULTY 2
		bestMove = -1;
	}
	
	public ConnectMAI(int dif) {
		difficulty = dif;
		bestMove = -1;
	}

	/**
	 * This is the evaluation function for minimax(). Returns a score that is used to determine the next move.
	 * @param game the current game board
	 * @return bestScore which is the highest score that was calculated
	 */
	public int score(ConnectMCore game) { 
	    int bestScore = 0;
	    int playerOScore = 0;
	    int playerXScore = 0; 

		
		if(game.checkForWin() == true && game.getCurrentPlayer() == 'O') {
			playerOScore += 1000;
		}
		if(game.checkForWin() == true && game.getCurrentPlayer() == 'X') {
			playerXScore += 10000000;
		}

		bestScore = playerOScore - playerXScore ;
		//System.out.println(bestScore);
		
		return bestScore;
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
	public int minimax(int depth, ConnectMCore game, boolean isMax, int alpha, int beta) { 
		int score = 0;
		if(depth == difficulty || game.checkForWin() == true) {
			return score(game);
		}
		
		
		if(isMax == true) {//maximum
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
						 	bestMove = j;
						 	}

						 alpha = Math.max(alpha, score);
						 if(beta <= alpha){
							 break;
						 }
						 }

					 }

				}

			 return maxScore;
			 }
			 
		
		else { //minimum 
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
							 		//minMove = j;
							 }

							 
							 beta = Math.min(beta, score);
							 if(beta <= alpha) {
								 break;
							 }

					}

				}
			}
		return minScore;
		}
}
	/**
	 * Launches the initial call to minimax, begins the search tree.
	 * @param game, the entire game state that minimax will use.
	 * @return bestMove which represents the column that will be played.
	 */
	public int decide(ConnectMCore game) {
		bestMove = -1;
		Random rng = new Random();
		ConnectMCore copy = new ConnectMCore(game);
		
		minimax(0, copy, true, Integer.MIN_VALUE, Integer.MAX_VALUE); 

		//System.out.println(move); 
		if(bestMove == 0|| game.isAllowed(bestMove) == false) { //randomly picks a column
		bestMove = rng.nextInt(game.getBoardSize());
	     while(game.isAllowed(bestMove) == false) {
			bestMove = rng.nextInt(game.getBoardSize());
		}

		}
		return bestMove;
	}
}

