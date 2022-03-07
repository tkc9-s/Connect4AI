package connect4AI;
import java.util.Scanner;

public class CoreTest {
	public static void main(String[] args) {
		/*
		 * Runs with or without command line args
		 */
		Scanner input = new Scanner(System.in);
		if(args.length > 0) {
				String N = args[0];
				String M = args[1];
				String H = args[2];
				
				int boardSize = Integer.parseInt(N);
				int winSize = Integer.parseInt(M); //Supposed to be the win length as listed, but not implemented atm.
				int startPlayer = Integer.parseInt(H);
				char starter = ' ';
				if(startPlayer == 1) {
					starter = 'X';
				}
				else if(startPlayer == 0) {
					starter = 'O';
				}
				
				Connect4Core game = new Connect4Core(boardSize, starter);
				while(!game.getGameState()) {
					
					Connect4AI AI = new Connect4AI();
					System.out.println("Enter column to place piece, the first column starts at 0: ");
					
					int inputHold = input.nextInt();
					while(inputHold >= game.getBoardSize()) {
						System.out.println("Invalid, try again!!");
						inputHold = input.nextInt();
					}
					game.makeMove(inputHold);
					System.out.println("YOUR MOVE: " + inputHold);
					System.out.println("");
					
					int AImove = AI.decide(game);
					game.makeMove(AImove);

					System.out.println("AI MOVE: " + AImove);
					AI = null; //makes the AI smarter for some reason. 
					
				
				}

				
		}
		else {
			System.out.println("Enter the size of the N by N square grid: ");
			System.out.println("If n < 3 or n > 10, a default of 4 will be picked. ");
			int gameSize = input.nextInt();
			
			
			
			Connect4Core game = new Connect4Core(gameSize, 'X');
			
			while(!game.getGameState()) {
				Connect4AI AI = new Connect4AI();
				System.out.println("Enter column to place piece, the first column starts at 0: ");
				
				int inputHold = input.nextInt();
				while(inputHold >= game.getBoardSize()) {
					System.out.println("Invalid, try again!!");
					inputHold = input.nextInt();
				}
				game.makeMove(inputHold);
				System.out.println("YOUR MOVE: " + inputHold);
				System.out.println("");
				
				int AImove = AI.decide(game);
				game.makeMove(AImove);

				System.out.println("AI MOVE: " + AImove);
				AI = null; //makes the AI smarter for some reason. 
				
			
			
		}
		
		
		
		
		

		

		
			
			}
		input.close();
		}
	
	
}
