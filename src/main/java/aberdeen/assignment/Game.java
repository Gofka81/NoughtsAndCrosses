package aberdeen.assignment;

/*
 * Game.java
 *
 * Represents a game of noughts and crosses
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    BoxStatus[] board = new BoxStatus[9];  // The board contains 9 boxes
    private GameStatus currentTurn;

    /**
     * Creates a new instance of the game.
     */
    public Game() {
        for (int i = 0; i < 9; i++)
            board[i] = BoxStatus.Empty;  // Initially, each box is empty (not taken)

        if (new Random(System.currentTimeMillis()).nextBoolean())
            currentTurn = GameStatus.Human;
        else
            currentTurn = GameStatus.Computer;
    }

    public Game(BoxStatus[] board, GameStatus currentTurn) {
        this.board = board;
        this.currentTurn = currentTurn;
    }

    public Game(Game game) {
        this.board = game.board.clone();
        this.currentTurn = game.currentTurn;
    }

    public boolean isEmpty(int n) {
        // Check if a box is empty
        return (board[n] == BoxStatus.Empty);
    }

    public boolean isComputer(int n) {
        // Check if a box is taken by the Computer
        return (board[n] == BoxStatus.Computer);
    }

    public boolean isHuman(int n) {
        // Check if a box is taken by the Human
        return (board[n] == BoxStatus.Human);
    }

    public void setHuman(int n) {
        // Mark a square as claimed by the Human
        board[n] = BoxStatus.Human;
    }

    public void setComputer(int n) {
        // Mark a square as claimed by the Computer
        board[n] = BoxStatus.Computer;
    }

    public BoxStatus getBox(int n) {
        // Get the status of a square
        return board[n];
    }

    public BoxStatus[] getBoard() {
        // Get a copy of the game board
        return board.clone();
    }

    public void setTurn(GameStatus turn) {
        // Set the current turn
        currentTurn = turn;
    }

    public Game getNewState(int move) {
        // Create a new game state after a move
        BoxStatus[] newBoard = getBoard();
        if (currentTurn == GameStatus.Human)
            newBoard[move] = BoxStatus.Human;
        else
            newBoard[move] = BoxStatus.Computer;
        return new Game(newBoard, getNextTurn());
    }

    public char boxChar(int n) {
        // Get a character representing the status of a square (empty, Human, or Computer)
        switch (board[n]) {
            case Human:
                return 'H';
            case Computer:
                return 'C';
            case Empty:
                return '.';
        }
        return ' ';
    }

    public void printBoard() {
        // Print the game board
        System.out.println("Board");
        System.out.printf(
                "| %c %c %c |" +
                        "%n| %c %c %c |" +
                        "%n| %c %c %c |%n",
                boxChar(0), boxChar(1), boxChar(2),
                boxChar(3), boxChar(4), boxChar(5),
                boxChar(6), boxChar(7), boxChar(8));
    }

    public void printResults(GameStatus winner) {
        // Print the game results
        System.out.println("\tThe game finished - " + winner);
        printBoard();
    }

    public GameStatus getActiveTurn() {
        // Get the current active turn
        return currentTurn;
    }

    public void nextTurn() {
        // Switch to the next turn
        if (this.currentTurn == GameStatus.Human)
            currentTurn = GameStatus.Computer;
        else
            currentTurn = GameStatus.Human;
    }

    public GameStatus getNextTurn() {
        // Get the next turn
        GameStatus nextTurn;
        if (this.currentTurn == GameStatus.Human)
            nextTurn = GameStatus.Computer;
        else
            nextTurn = GameStatus.Human;
        return nextTurn;
    }

    public List<Integer> getAvailableMoves() {
        // Get a list of available moves (empty squares)
        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (isEmpty(i))
                availableMoves.add(i);
        }
        return availableMoves;
    }

    public BoxStatus checkHorizon() {
        // Check for a winning horizon (horizontal) line
        for (int i = 0; i < 9; i += 3) {
            if (!isEmpty(i) && boxChar(i) == boxChar(i + 1) && boxChar(i + 1) == boxChar(i + 2)) {
                return getBox(i);
            }
        }
        return BoxStatus.Empty;
    }

    public BoxStatus checkVertical() {
        // Check for a winning vertical line
        for (int i = 0; i < 3; i++) {
            if (!isEmpty(i) && boxChar(i) == boxChar(i + 3) && boxChar(i + 3) == boxChar(i + 6)) {
                return getBox(i);
            }
        }
        return BoxStatus.Empty;
    }

    public BoxStatus checkDiagonal() {
        // Check for a winning diagonal line
        if (!isEmpty(4) && (getBox(0) == getBox(4) && getBox(4) == getBox(8))
                || (getBox(2) == getBox(4) && getBox(4) == getBox(6))) {
            return getBox(4);
        }
        return BoxStatus.Empty;
    }

    public boolean isTie() {
        // Check if the game is a tie
        for (int i = 0; i < 9; i++) {
            if (isEmpty(i))
                return false;
        }
        return true;
    }

    public GameStatus getPlayer(BoxStatus square) {
        // Get the player (Human or Computer) who claimed a square
        if (square == BoxStatus.Human)
            return GameStatus.Human;
        return GameStatus.Computer;
    }

    public GameStatus gameStatus() {
        // Get the current status of the game (win, tie, or in progress)
        if (checkHorizon() != BoxStatus.Empty)
            return getPlayer(checkHorizon());
        if (checkVertical() != BoxStatus.Empty)
            return getPlayer(checkVertical());
        if (checkDiagonal() != BoxStatus.Empty)
            return getPlayer(checkDiagonal());
        if (isTie())
            return GameStatus.Tie;
        return GameStatus.InProgress;
    }

    public boolean isGameOver() {
        // Check if the game is over
        return gameStatus() != GameStatus.InProgress;
    }
}
