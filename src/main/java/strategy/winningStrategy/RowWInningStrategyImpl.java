package strategy.winningStrategy;

import model.Board;
import model.Move;

import java.util.HashMap;

public class RowWInningStrategyImpl implements WinnerStrategy{

    HashMap<Integer, HashMap<Character, Integer>> rowCount = new HashMap<>();

    @Override
    public Boolean checkWinner(Board board, Move move) {
        System.out.println("Winner is the row winning strategy");
        //1. From Move get Row and symbol
        Integer row = move.getCell().getRow();
        Character sym = move.getPlayer().getSymbol().getSymbolChar();

        //2. row is not present n hashmap
        if(!rowCount.containsKey(row)){
            rowCount.put(row, new HashMap<>());
        }
        //3. symbol is not present in HashMap
        HashMap<Character, Integer> counts = rowCount.get(row);
        if(!counts.containsKey(sym))
            counts.put(sym, 0);
        counts.put(sym, counts.get(sym) + 1);

        //4. check count with total count to identify winner or not
        if(counts.get(sym) == board.getSize())
            return true;
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Character sym = move.getPlayer().getSymbol().getSymbolChar();

        rowCount.get(row).put(sym, rowCount.get(row).get(sym) - 1);
    }
}
