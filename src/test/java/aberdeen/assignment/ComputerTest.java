package aberdeen.assignment;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertSame;

public class ComputerTest {

    private Game game;
    private Computer computer;

    @Before
    public void setUp(){
        computer = new Computer();
        game = new Game();
        game.setTurn(GameStatus.Computer);
    }

    @Test
    public void testComputerWinningMove() {
        /*
        Test if the computer chooses a winning move rather then block

        Init board:
             H |   |
            -----------
             H | C |
            -----------
               | C |

        Expected board:
             H | C |
            -----------
             H | C |
            -----------
               | C |
        */
        game.setHuman(0);
        game.setHuman(3);
        game.setComputer(4);
        game.setComputer(7);

        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());

        assertSame(BoxStatus.Computer, game.getBox(1));
    }

    @Test
    public void testBlockHumanWinningMove() {
        /*
        Test if the computer blocks a winning move by the human player

        Init board:
             H | H |
            -----------
               | C |
            -----------
               |   |

        Expected board:
             H | H | C
            -----------
               | C |
            -----------
               |   |
        */
        game.setHuman(0);
        game.setHuman(1);
        game.setComputer(4);

        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());

        assertSame(BoxStatus.Computer, game.getBox(2));
    }

    @Test
    public void testForkingMove() {
        /*
        Test if the computer creates a winning fork

        Init board:
             C | H | C
            -----------
             H |   |
            -----------
               |   |

        Expected board:
             C | H | C
            -----------
             H | C |
            -----------
               |   |
        */
        game.setHuman(1);
        game.setHuman(3);
        game.setComputer(0);
        game.setComputer(2);

        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());

        assertSame(BoxStatus.Computer, game.getBox(4));
    }

    @Test
    public void testBestMove1() {
        /*
        Test if the computer makes the best available move

        Init board:
             H |   | C
            -----------
               | C |
            -----------
               |   | H

        Expected board:
             H |   | C
            -----------
               | C |
            -----------
             C |   | H
        */
        game.setHuman(0);
        game.setHuman(8);
        game.setComputer(2);
        game.setComputer(4);

        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());

        assertSame(BoxStatus.Computer, game.getBox(6));
    }

    @Test
    public void testNeverGiveUp() {
        /*
        Test if the computer algorithm continues to play smart even when the game is already lost

        Init board:
               | H |
            -----------
               |   | H
            -----------
             C | C | H

        Expected board:
               | H | C
            -----------
               |   | H
            -----------
             C | C | H
        */
        game.setHuman(1);
        game.setHuman(5);
        game.setHuman(8);
        game.setComputer(6);
        game.setComputer(7);

        computer.minimax(new Game(game), 0);
        game.setComputer(computer.getChoice());

        assertSame(BoxStatus.Computer, game.getBox(2));
    }
}
