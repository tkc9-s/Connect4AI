package connect4AI;
import java.util.Random;

public class CoreTest {
	public static void main(String[] args) {
		// This whole class is just proof that the core game actually works.
		Connect4Core game = new Connect4Core(2);
		Random rng = new Random();
		for (int i = 0; i < 60; i ++) {
			game.makeMove(rng.nextInt(game.getBoardSize()));
		}
	}
}
