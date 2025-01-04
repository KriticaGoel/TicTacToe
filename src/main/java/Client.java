import controller.GameController;
import model.*;
import strategy.winningStrategy.RowWInningStrategyImpl;
import strategy.winningStrategy.WinnerStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    //Game start
    // Display the board
    //Ask current player to make a move
    //After making move if state changes
    //    Check winner or draw
    //       if yes game over
    //       else game continues

    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        //1. Start the game
            // board size
        int size=3;
            //list of Players
        List<Players> players = new ArrayList<Players>();
        players.add(new HumanPlayer(1,"Kritica",'*'));
        players.add(new BotPlayer(2,"Bot",'0',BotDifficultyLevel.EASY));
            //WinningStrategy
        List<WinnerStrategy> winnerStrategiesList= new ArrayList<>();
        winnerStrategiesList.add(new RowWInningStrategyImpl());

        Game game=gameController.startGame(size,players,winnerStrategiesList);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)) {

            //3. Make move - take input from user
            gameController.makeMove(game);
            //2. Display the board
            gameController.displayBoard(game);
            //4. Update the game state if required
           while(true) {
               System.out.println("Do you want to undo [Y/N]");

               String undoAns=scanner.nextLine();
               if (undoAns.equalsIgnoreCase("Y") ||undoAns.equalsIgnoreCase("Yes")) {
                   gameController.undo(game);
                   gameController.displayBoard(game);
               }
               else{
                   break;
               }
           }
            //5. Update the turn
        }
        if(gameController.getGameState(game).equals(GameState.SUCCESS)) {
            System.out.println("Winner is "+ gameController.getWinner(game).getName());
        } else if (gameController.getGameState(game).equals(GameState.DRAW)) {
            System.out.println("Game has been ended in draw");
        }

    }


}