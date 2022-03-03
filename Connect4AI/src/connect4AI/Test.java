package connect4AI;
import java.util.Random;

public class Test {
	public static void main(String[] args) {
		// This whole class is just proof that the core game actually works.
		Connect4Core game = new Connect4Core(20);
		Random rng = new Random();
		for (int i = 0; i < 100; i ++) {
			game.makeMove(rng.nextInt(game.getBoardSize()));
		}
		System.out.println(game.toString());
	}
}
