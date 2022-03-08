package connectMAI;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int size = -1;
		int tokens = -1; // Unimplemented. Couldn't adapt the evaluation function to variable lengths
		char firstPlayer = 'X';
		
		if (args.length == 3) {
			size = Integer.parseInt(args[0]);
			tokens = Integer.parseInt(args[1]);
			if (Integer.parseInt(args[2]) == 0) {
				firstPlayer = 'O';
			}
		}
		else {
			System.out.println("Enter the size of the N by N square grid");
			System.out.println("If n < 4 or n > 10, a default of 4 will be picked.");
			System.out.print("Size: ");
			size = input.nextInt();
			// Unimplemented. Couldn't adapt the evaluation function to variable lengths
			// System.out.println("Enter the number of consecutive tokens to win");
			// System.out.println("If n < 2 or n > size, a default of 4 will be picked.");
			// System.out.print("Tokens to Win: ");
			// tokens = input.nextInt();
		}
		// Initialized the game, the AI, and a boolean for if the player is done playing yet
		ConnectMCore game = new ConnectMCore(size, firstPlayer/*, tokens*/);
		ConnectMAI AI;
		boolean done = false;
		System.out.println("Enter column to place piece, the first column starts at 0");
		System.out.println(game.toString());
		while(!done) {
			AI = new ConnectMAI();
			System.out.print("Your Move: ");
			// Ensures that both the game is still ongoing and which player is currently taking their turn
			if (!game.getGameState() && game.getCurrentPlayer() == 'X') {
				int inputHold = input.nextInt();
				while(!game.isAllowed(inputHold)) {
					System.out.println("Invalid, try again!!");
					inputHold = input.nextInt();
				}
				game.makeMove(inputHold);
			}
			// Ensures that both the game is still ongoing and which player is currently taking their turn
			if (!game.getGameState() && game.getCurrentPlayer() == 'O') { //Ensures that moves are not being made when the game is over
				int AImove = AI.decide(game);
				System.out.println("AI MOVE: " + AImove);
				game.makeMove(AImove);
				AI = null; //makes the AI smarter for some reason. 
			}
			// If the game ends, prompt for a new game
			if (game.getGameState()) {
				System.out.print("Would you like to play again (Y/N): ");
				if (input.next().equalsIgnoreCase("Y")) {
					game.resetGame();
					System.out.println(game.toString());
				}
				else {
					done = true;
				}
			}
		}
		input.close();
	}
}
