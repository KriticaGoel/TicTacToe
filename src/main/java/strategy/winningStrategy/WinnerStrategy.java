package strategy.winningStrategy;

import model.Board;
import model.Game;
import model.Move;

public interface WinnerStrategy {
    public Boolean checkWinner(Board board, Move move);
    public void handleUndo(Board board, Move move);
}
