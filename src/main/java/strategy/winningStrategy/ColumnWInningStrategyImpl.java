package strategy.winningStrategy;

import model.Board;
import model.Move;

public class ColumnWInningStrategyImpl implements  WinnerStrategy{


    @Override
    public Boolean checkWinner(Board borad, Move move) {
        System.out.println("Winner is Column winning Strategy");
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {

    }
}
