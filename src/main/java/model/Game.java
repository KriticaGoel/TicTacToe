package model;

import strategy.WinnerStrategy;

import java.util.List;

public class Game {
    private Board board;
    private List<Players> players;
    private Integer nextPlayerIndex;
    private GameState gameState;
    private List<Move> moves;
    private Players winner;
    private  List<WinnerStrategy> winningStrategies;

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
}
