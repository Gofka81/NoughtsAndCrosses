/*
 * Play.java
 *
 * Play a game of noughts and crosses
 * includes main method
 */

package aberdeen.assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

class Play{
    Game game;   // the noughts and crosses game
    Scanner input; // Scanner for user input
    Computer computer; // Computer bot
    public static void main(String[] args) {
        // main method - just create a Play object
        new Play();
    }

    public Play () {
        // constructor
        System.out.println("Welcome to noughts and crosses");
        game = new Game();  // create game board
        computer = new Computer(); // create computer bot
        input = new Scanner(System.in);  // Scanner for user input
        while (!game.isGameOver()) { // infinite loop
            if (game.getActiveTurn() == GameStatus.Human){
                game.printBoard(); // print board
                playerTurn(); // human turn
            }else {
                computerTurn(); // computer turn
            }
            game.nextTurn();
        }
        game.printResults(game.gameStatus());
    }
    public void playerTurn()  {
        // Player turn: read in a square and claim it for the human
        System.out.print("Take a square (1-9): ");
        try {
            int square = input.nextInt() - 1;
            game.setHuman(square);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 9.");
            input.next(); // Consume the invalid input
            playerTurn(); // Retry player's turn
        }
    }

    public void computerTurn() {
        // computer turn - currently does nothing other than print out a message
        System.out.println("Computer is thinking");
        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());
    }
}