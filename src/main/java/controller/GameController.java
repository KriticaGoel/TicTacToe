package controller;

import model.Game;
import model.GameState;
import model.Players;
import strategy.winningStrategy.WinnerStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Players> players, List<WinnerStrategy> winnerStrategyList){
        return new Game.GameBuilder().setBoard(size).setPlayers(players).setWinningStrategies(winnerStrategyList).build();

    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }
    public void displayBoard(Game game){
        game.getBoard().printBoard(game);
    }

    public void makeMove(Game game){
            game.makeMove();
    }

    public void undo(Game game){
        game.undoMove();
    }

    public Players getWinner(Game game){
        return game.getWinner();
    }
}
