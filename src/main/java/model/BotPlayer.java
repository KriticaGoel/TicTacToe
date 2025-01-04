package model;

import strategy.botPlayingStrategy.BotPlayingStrategyFactory;

public class BotPlayer extends Players{
    private BotDifficultyLevel botDifficultyLevel;

    public BotPlayer(Integer id, String name, char symbol,BotDifficultyLevel botDifficultyLevel) {
        super(id,name,PlayerType.BOT,new Symbol(symbol));
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
       return BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel).makeMove(board);
    }
}
