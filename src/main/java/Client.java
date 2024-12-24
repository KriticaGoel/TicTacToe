import controller.GameController;

public class Client {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.startGame();
    }

    //Display
    //Ask current player to make a move
    //After making move if state changes
    //    Check winner or draw
    //       if yes game over
    //       else game continue
}