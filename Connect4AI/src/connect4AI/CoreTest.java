package connect4AI;
import java.util.Scanner;

public class CoreTest {
	public static void main(String[] args) {
		// This whole class is just proof that the core game actually works.
		Connect4Core game = new Connect4Core(4, 'X');
		Connect4AI AI = new Connect4AI();
		Scanner input = new Scanner(System.in);
		
		while(!game.getGameState()) {

		System.out.println("Enter column to place: ");
		int inputHold = input.nextInt();
		game.makeMove(inputHold);
		int AImove = AI.decide(game);
		game.makeMove(AImove);

		System.out.println("AI MOVE: = " + AImove);// Debug line to make sure AI moves
		
		
		
		/**
		 * Prompts the user to reset the game if a player has just won.
		 * Could make it its own method in Connect4Core if needed
		 * Currently does not work properly with the AI due to the X and O being swapped.
		 */
		if(game.checkForWin() == true) {
			boolean reset = true;
			System.out.println("Would you like to reset the game?");
			System.out.println("Type Y for Yes or N for No");
			while(reset == true){
			String response = input.next().toUpperCase();

				if(response.charAt(0) == 'Y') {
					System.out.println("Enter the board size, type 0 for the same size");
					int size = input.nextInt();
					
					if(size > 3 && size < 10) {
						game.resetGame(size);
						reset = false;
						}
					else {
						game.resetGame();
						game.setCurrentPlayer('X');
						reset = false;
						}
	
					}
				else if(response.charAt(0) == 'N') {
					game.setGameState(true);
					reset = false;
					input.close();
					}
				else {
					System.out.println("Invalid input, try again");
					}
				

				}
			
			
			}
		}
	}
}
