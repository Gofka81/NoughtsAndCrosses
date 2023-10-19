package aberdeen.assignment;

public class Computer {
    private int choice;

    public int getChoice(){
        return choice;
    }

    public int score(Game game, int depth) {
        if (game.gameStatus() == GameStatus.Computer) {
            return 10 - depth;
        } else if (game.gameStatus() == GameStatus.Human) {
            return depth - 10;
        } else {
            return 0;
        }
    }

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
