package strategy.winningStrategy;

import model.Board;
import model.Move;

public class DiagonalWinningStrategy implements WinnerStrategy{
    @Override
    public Boolean checkWinner(Board board, Move move) {
        return null;
    }

    @Override
    public void handleUndo(Board board, Move move) {

    }
}