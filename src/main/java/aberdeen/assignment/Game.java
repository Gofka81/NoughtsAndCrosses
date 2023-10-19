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

    BoxStatus[] board = new BoxStatus[9];  // board contains 9 boxes
    GameStatus currentTurn;


    /**
     * Creates a new instance of game
     */
    public Game() {
        for (int i = 0; i < 9; i++)
            board[i] = BoxStatus.Empty;  // initially each box is empty (not taken)

        if(new Random(System.currentTimeMillis()).nextBoolean())
            currentTurn = GameStatus.Human;
        else
            currentTurn = GameStatus.Computer;
    }

    public Game(BoxStatus[] board, GameStatus currentTurn){
        this.board = board;
        this.currentTurn = currentTurn;
    }

    public Game(Game game){
        this.board = game.board.clone();

        this.currentTurn = game.currentTurn;
    }

    public boolean isEmpty(int n) {
        // is a box empty?
        return (board[n] == BoxStatus.Empty);
    }

    public boolean isComputer(int n) {
        // is a box taken by the Computer?
        return (board[n] == BoxStatus.Computer);
    }

    public boolean isHuman(int n) {
        // is a box taken by the Human?
        return (board[n] == BoxStatus.Human);
    }

    public void setHuman(int n) {
        // human claims square N
        board[n] = BoxStatus.Human;
    }

    public void setComputer(int n) {
        // computer claims square N
        board[n] = BoxStatus.Computer;
    }

    public BoxStatus getBox(int n) {
        // return square N
        return board[n];
    }

    public BoxStatus[] getBoard(){
        return board.clone();
    }

    public Game getNewState(int move){
        BoxStatus[] newBoard = getBoard();
        if (currentTurn == GameStatus.Human)
            newBoard[move] = BoxStatus.Human;
        else
            newBoard[move] =  BoxStatus.Computer;
        return new Game(newBoard, getNextTurn());
    }

    public char boxChar(int n) {
        // return a character which shows whether a square is empty, taken by the computer, or taken by the human
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
        // print the noard on System.out
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
        System.out.println("\tThe game finished - " + winner);
        printBoard();
    }

    public GameStatus getActiveTurn(){
        return currentTurn;
    }

    public void nextTurn(){
        if (this.currentTurn == GameStatus.Human)
            currentTurn = GameStatus.Computer;
        else
            currentTurn = GameStatus.Human;
    }

    public GameStatus getNextTurn(){
        GameStatus nextTurn;
        if (this.currentTurn == GameStatus.Human)
            nextTurn = GameStatus.Computer;
        else
            nextTurn = GameStatus.Human;
        return nextTurn;
    }

    public List<Integer> getAvailableMoves() {
        List<Integer> availableMoves = new ArrayList<>();
        for(int i=0; i<9; i++){
            if(isEmpty(i))
                availableMoves.add(i);
        }
        return availableMoves;
    }

    public BoxStatus checkHorizon() {
        for (int i = 0; i < 9; i +=3)
            if (!isEmpty(i) && boxChar(i) == boxChar(i+1) && boxChar(i+1) == boxChar(i+2))
                return getBox(i);
        return BoxStatus.Empty;
    }

    public BoxStatus checkVertical() {
        for (int i = 0; i < 3; i++)
            if (!isEmpty(i) && boxChar(i) == boxChar(i+3) && boxChar(i+3) == boxChar(i+6))
                return getBox(i);
        return BoxStatus.Empty;
    }

    public BoxStatus checkDiagonal(){
        if (!isEmpty(4) && (getBox(0) == getBox(4) && getBox(4) == getBox(8))
        || (getBox(2) == getBox(4) && getBox(4) == getBox(6)))
            return getBox(4);
        return BoxStatus.Empty;
    }

    public boolean isTie(){
        for (int i = 0; i<9; i++)
            if(isEmpty(i))
                return false;
        return true;
    }

    public GameStatus getPlayer(BoxStatus square){
        if (square == BoxStatus.Human)
            return GameStatus.Human;
        return GameStatus.Computer;
    }

    public GameStatus gameStatus(){
        if (checkHorizon() != BoxStatus.Empty)
            return getPlayer(checkHorizon());
        if (checkVertical() != BoxStatus.Empty)
            return getPlayer(checkVertical());
        if (checkDiagonal() != BoxStatus.Empty)
            return getPlayer(checkDiagonal());
        if(isTie())
            return GameStatus.Tie;
        return GameStatus.InProgress;
    }

    public boolean isGameOver(){
        return gameStatus() != GameStatus.InProgress;
    }
}

