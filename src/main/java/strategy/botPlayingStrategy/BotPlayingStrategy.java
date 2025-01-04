package strategy.botPlayingStrategy;

import model.Board;
import model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
