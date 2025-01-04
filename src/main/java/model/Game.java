package model;

import strategy.winningStrategy.WinnerStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private List<Players> players;
    private Integer nextPlayerIndex;
    private GameState gameState;
    private List<Move> moves;
    private Players winner;
    private List<WinnerStrategy> winningStrategies;

    private Game(Game.GameBuilder gameBuilder) {
        //Validation will be
        //1. No. of palyers =size-1
        if(gameBuilder.getPlayers().size()>gameBuilder.getBoard().getSize()-1) {
            System.out.println("No. of players can't be greater then size");
            throw new IllegalArgumentException("No. of players can't be greater then size");
        }
        //2. all players should have distinct symbols
        HashSet<Character> sym= new HashSet<Character>();
        for(Players player:gameBuilder.getPlayers()){
            if(sym.contains(player.getSymbol().getSymbolChar()))
                throw new IllegalArgumentException("Player symbol already used");
            else
                sym.add(player.getSymbol().getSymbolChar());
        }
        //3. We have only max one bot
        int countBotPlayer=0;
        for(Players players : gameBuilder.getPlayers()) {
            if(players.getType().equals(PlayerType.BOT)) {
                countBotPlayer++;
            }
        }
        if(countBotPlayer>1)
            System.out.println("Max one bot is allowed");

        this.board = gameBuilder.getBoard();
        this.players = gameBuilder.getPlayers();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.winningStrategies = gameBuilder.getWinningStrategies();
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }

    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(Integer nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Players getWinner() {
        return winner;
    }

    public void setWinner(Players winner) {
        this.winner = winner;
    }

    public List<WinnerStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinnerStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    private boolean validateMove(Move move){
        //row and column inside the cell
        int row= move.getCell().getRow();
        int col= move.getCell().getCol();
        if(row <0 && row>= board.getSize() && col <0 && col>= board.getSize())
            return false;
        return board.getGrid().get(row).get(col).getCellState().equals(CellState.EMPTY);
        //still move is not added to board below code always return true
        //return move.getCell().getCellState()== CellState.EMPTY;

    }

    private boolean checkWinner(Move move){
        //Checking Winner
        for(WinnerStrategy winnerStrategy : winningStrategies) {
            if(winnerStrategy.checkWinner(board, move))
                return true;
        }
        return false;
    }
    public void makeMove() {
        Players currentPlayer= players.get(nextPlayerIndex);
        System.out.println("Current player: "+currentPlayer.getName());

        Move move =currentPlayer.makeMove(board);
        if(!validateMove(move)){
            System.out.println("Invalid move. Please try again");
            return;
        }

        // Add move on board - Change the state of cell on board
        int row= move.getCell().getRow();
        int col= move.getCell().getCol();

        Cell cellToChange = board.getGrid().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());

        //update move with cell info
        move.setCell(cellToChange);
        move.setPlayer(currentPlayer);
        moves.add(move);

        //increment next player index
        nextPlayerIndex++;
        nextPlayerIndex %= players.size();

        //check winner
        if(checkWinner(move)){
            winner = currentPlayer;
            gameState = GameState.SUCCESS;
        } else if (moves.size() == board.getSize()* board.getSize()) {
            gameState = GameState.DRAW;

        }


    }

    public void undoMove() {
        if(moves.size()>0) {
            //last move identify
            Move lastMove = moves.get(moves.size() - 1);
            //last row column
            int lastRow = lastMove.getCell().getRow();
            int lastCol = lastMove.getCell().getCol();
            //last cell ka status empty and remove the symbol
            board.getGrid().get(lastRow).get(lastCol).setCellState(CellState.EMPTY);
            board.getGrid().get(lastRow).get(lastCol).setSymbol(null);
            moves.remove(lastMove);
            //go to previous move
            nextPlayerIndex--;
            nextPlayerIndex =(nextPlayerIndex+players.size()) % players.size();

            // Update the row count after undo
            for(WinnerStrategy winningStrategy: winningStrategies){
                winningStrategy.handleUndo(board, lastMove);
            }

            setGameState(GameState.IN_PROGRESS);
            setWinner(null);




        }else{
            System.out.println("Nothing to undo");
        }
    }

    public static GameBuilder getBuilder() {
        return new Game.GameBuilder();
    }

    public static class GameBuilder {
        //only those elements which we need from user
        private Board board;
        private List<Players> players;
        private List<WinnerStrategy> winningStrategies;

        public Board getBoard() {
            return board;
        }

        public GameBuilder setBoard(int size) {
            this.board = new Board(size);
            return this;
        }

        public List<Players> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Players> players) {
            this.players = players;
            return this;
        }



        public List<WinnerStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public GameBuilder setWinningStrategies(List<WinnerStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() {
           return new Game(this);
        }


    }
}

