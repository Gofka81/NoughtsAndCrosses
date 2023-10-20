package aberdeen.assignment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ResultDetectionTest {

    @Test
    public void testHorizontalWin() {
        Game humanWins = new Game();
        Game computerWins = new Game();

        // Creating a horizontal win scenario for humans
        humanWins.setHuman(0);
        humanWins.setHuman(1);
        humanWins.setHuman(2);

        // Creating a horizontal win scenario for computer
        computerWins.setComputer(3);
        computerWins.setComputer(4);
        computerWins.setComputer(5);

        assertEquals(GameStatus.Human, humanWins.gameStatus());
        assertEquals(GameStatus.Computer, computerWins.gameStatus());
    }

    @Test
    public void testVerticalWin() {
        Game humanWins = new Game();
        Game computerWins = new Game();

        // Creating a vertical win scenario for humans
        humanWins.setHuman(0);
        humanWins.setHuman(3);
        humanWins.setHuman(6);

        // Creating a vertical win scenario for computer
        computerWins.setComputer(2);
        computerWins.setComputer(5);
        computerWins.setComputer(8);

        assertEquals(GameStatus.Human, humanWins.gameStatus());
        assertEquals(GameStatus.Computer, computerWins.gameStatus());
    }

    @Test
    public void testDiagonalWin() {
        Game humanWins = new Game();
        Game computerWins = new Game();

        // Creating a diagonal win scenario for humans
        humanWins.setHuman(0);
        humanWins.setHuman(4);
        humanWins.setHuman(8);

        // Creating a diagonal win scenario for computer
        computerWins.setComputer(2);
        computerWins.setComputer(4);
        computerWins.setComputer(6);

        assertEquals(GameStatus.Human, humanWins.gameStatus());
        assertEquals(GameStatus.Computer, computerWins.gameStatus());
    }

    @Test
    public void testNoWinYet() {
        Game game = new Game();

        // No win yet, the game is still in progress
        game.setHuman(0);
        game.setComputer(1);
        game.setHuman(2);
        game.setComputer(3);

        assertEquals(GameStatus.InProgress, game.gameStatus());
    }

    @Test
    public void testTieGame() {
        Game game = new Game();

        // Create a tie game scenario
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
}
