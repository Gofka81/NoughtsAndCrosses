package aberdeen.assignment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ResultDetectionTest {

    @Test
    public void testHorizontalWin() {
        Game firstRow = new Game();
        Game secondRow = new Game();
        Game thirdRow = new Game();

        /* Creating a horizontal win scenario at 1st row
           Board:
                 H | H | H
                -----------
                   |   |
                -----------
                   |   |
         */
        firstRow.setHuman(0);
        firstRow.setHuman(1);
        firstRow.setHuman(2);

        /* Creating a horizontal win scenario at 2nd row
           Board:
                   |   |
                -----------
                 C | C | C
                -----------
                   |   |
         */
        secondRow.setComputer(3);
        secondRow.setComputer(4);
        secondRow.setComputer(5);

        /* Creating a horizontal win scenario at 3rd row
           Board:
                   |   |
                -----------
                   |   |
                -----------
                 H | H | H
         */
        thirdRow.setHuman(6);
        thirdRow.setHuman(7);
        thirdRow.setHuman(8);

        assertEquals("The fist row is not correct", GameStatus.Human, firstRow.gameStatus());
        assertEquals("The second row is not correct", GameStatus.Computer, secondRow.gameStatus());
        assertEquals("The third row is not correct", GameStatus.Human,thirdRow.gameStatus());
    }

    @Test
    public void testVerticalWin() {
        Game firstColumn = new Game();
        Game secondColumn = new Game();
        Game thirdColumn = new Game();

        /* Creating a vertical win scenario at 1st column
           Board:
                 H |   |
                -----------
                 H |   |
                -----------
                 H |   |
         */
        firstColumn.setHuman(0);
        firstColumn.setHuman(3);
        firstColumn.setHuman(6);

        /* Creating a vertical win scenario at 2nd column
           Board:
                   | C |
                -----------
                   | C |
                -----------
                   | C |
         */
        secondColumn.setComputer(1);
        secondColumn.setComputer(4);
        secondColumn.setComputer(7);

        /* Creating a vertical win scenario at 3rd column
           Board:
                   |   | H
                -----------
                   |   | H
                -----------
                   |   | H
         */
        thirdColumn.setHuman(2);
        thirdColumn.setHuman(5);
        thirdColumn.setHuman(8);

        assertEquals("The fist column is not correct", GameStatus.Human, firstColumn.gameStatus());
        assertEquals("The second column is not correct", GameStatus.Computer, secondColumn.gameStatus());
        assertEquals("The third column is not correct", GameStatus.Human, thirdColumn.gameStatus());
    }

    @Test
    public void testDiagonalWin() {
        // Here we check both diagonals
        Game downhillDiagonal = new Game();
        Game uphillDiagonal = new Game();

        /* Creating a diagonal win scenario for downhill diagonal
           Board:
                 H |   |
                -----------
                   | H |
                -----------
                   |   | H
         */
        downhillDiagonal.setHuman(0);
        downhillDiagonal.setHuman(4);
        downhillDiagonal.setHuman(8);


        /* Creating a diagonal win scenario for uphill diagonal
           Board:
                   |   | C
                -----------
                   | C |
                -----------
                 C |   |
         */
        uphillDiagonal.setComputer(2);
        uphillDiagonal.setComputer(4);
        uphillDiagonal.setComputer(6);

        assertEquals("The downhill diagonal is not correct", GameStatus.Human, downhillDiagonal.gameStatus());
        assertEquals("The uphill diagonal is not correct", GameStatus.Computer, uphillDiagonal.gameStatus());
    }

    @Test
    public void testNoWinYet() {
        Game game = new Game();

        /* No win yet, the game is still in progress
           Board:
                 H | C | H
                -----------
                 C |   |
                -----------
                   |   |
         */
        game.setHuman(0);
        game.setComputer(1);
        game.setHuman(2);
        game.setComputer(3);

        assertEquals(GameStatus.InProgress, game.gameStatus());
    }

    @Test
    public void testTieGame() {
        Game game = new Game();

        /* Create a tie game scenario
           Board:
                 H | C | H
                -----------
                 H | H | C
                -----------
                 C | H | C
         */
        game.setHuman(0);
        game.setComputer(1);
        game.setHuman(2);
        game.setHuman(3);
        game.setHuman(4);
        game.setComputer(5);
        game.setComputer(6);
        game.setHuman(7);
        game.setComputer(8);

        assertEquals(GameStatus.Tie, game.gameStatus());
    }

    @Test
    public void testLastTurnWin() {
        Game game = new Game();

        /* Create a last turn win game scenario
           Board:
                 H | C | C
                -----------
                 H | H | C
                -----------
                 C | H |
         */
        game.setHuman(0);
        game.setComputer(1);
        game.setComputer(2);
        game.setHuman(3);
        game.setHuman(4);
        game.setComputer(5);
        game.setComputer(6);
        game.setHuman(7);
        game.setComputer(8);

        assertEquals(GameStatus.Computer, game.gameStatus());
    }
}
