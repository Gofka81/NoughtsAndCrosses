package aberdeen.assignment;

/**
 * The Computer class represents a player-controlled by the computer in a game.
 * It is responsible for making intelligent moves using the minimax algorithm to determine the best move.
 */
public class Computer {
    private int choice;

    /**
     * Get the last chosen move by the computer.
     * @return The computer's choice.
     */
    public int getChoice(){
        return choice;
    }

    /**
     * Calculate the score for a given game state.
     * @param game The game to evaluate.
     * @param depth The depth of the current search in the minimax tree.
     * @return The calculated score based on the game's state and depth.
     */
    public int score(Game game, int depth) {
        if (game.gameStatus() == GameStatus.Computer) {
            return 10 - depth;
        } else if (game.gameStatus() == GameStatus.Human) {
            return depth - 10;
        } else {
            return 0;
        }
    }

    /**
     * Implement the minimax algorithm to find the best move for the computer player.
     * @param game The current game state.
     * @param depth The depth of the current search in the minimax tree.
     * @return The best score for the computer player at the given depth.
     */
    public int minimax(Game game, int depth) {
        if (game.gameStatus() != GameStatus.InProgress) {
            return score(game, depth);
        }

        depth++;
        int[] scores = new int[game.getAvailableMoves().size()];
        int[] moves = new int[game.getAvailableMoves().size()];

        for (int i = 0; i < game.getAvailableMoves().size(); i++) {
            int move = game.getAvailableMoves().get(i);
            Game possibleGame = game.getNewState(move);
            scores[i] = minimax(possibleGame, depth);
            moves[i] = move;
        }

        if (game.getActiveTurn() == GameStatus.Computer) {
            // Max calculation
            int maxScoreIndex = 0;
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] > scores[maxScoreIndex]) {
                    maxScoreIndex = i;
                }
            }
            choice = moves[maxScoreIndex];
            return scores[maxScoreIndex];
        } else {
            // Min calculation
            int minScoreIndex = 0;
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] < scores[minScoreIndex]) {
                    minScoreIndex = i;
                }
            }
            choice = moves[minScoreIndex];
            return scores[minScoreIndex];
        }
    }
}
