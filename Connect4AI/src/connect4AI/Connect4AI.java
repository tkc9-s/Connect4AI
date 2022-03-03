package connect4AI;

/**
 * This class is all about the AI for the Connect 4 Game.
 * It will be able to get the gamestate from the game itself, and make decisions based on that.
 * @author Thomas Kennedy
 */
public class Connect4AI {
	private int difficulty; // How many layers deep the AI will compute potential moves
	
	public Connect4AI() {
		difficulty = 3;
	}
	
	public Connect4AI(int dif) {
		difficulty = dif;
	}
	
	public int decide(char[][] board) {
		//TODO: Write the AI decision stuff
		return 0;
	}
}
