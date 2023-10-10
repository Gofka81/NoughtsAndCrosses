/*
 * Play.java
 *
 * Play a game of noughts and crosses
 * includes main method
 */

package aberdeen.assignment;

import java.util.Scanner;

class Play{
    Game game;   // the noughts and crosses game
    Scanner input;
    public static void main(String[] args) {
        // main method - just create a Play object
        new Play();
    }

    public Play () {
        // constructor
        System.out.println("Welcome to noughts and crosses");
        game = new Game();  // create game board
        input = new Scanner(System.in);  // Scanner for user input
        boolean status = true;
        while (status) { // infinite loop
            game.printBoard(); // print board
            playerTurn(); // human turn
            status = updateStatus();
            if (!status)
                break;
            computerTurn(); // computer turn
            status = updateStatus();
        }
        game.printResults(game.gameStatus());
    }
    public void playerTurn()  {
        // Player turn: just read in a sqaure and claim it for human
        System.out.print("Take a square (1-9): ");
        // Reading data using readLine
        int square = input.nextInt();
        game.setHuman(square);
    }

    public void computerTurn() {
        // computer turn - currently does nothing other than print out a message
        System.out.println("Computer is thinking");
    }

    public boolean updateStatus(){
        return game.gameStatus() == GameStatus.InProgress;
    }
}