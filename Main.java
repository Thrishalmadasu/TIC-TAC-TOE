import Controller.GameController;
import Exceptions.InvalidMoveException;
import Source.Models.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        Scanner sc=new Scanner(System.in);
        GameController gameController = new GameController();
        int dimension = 3;
        List<Player> players = List.of(
            new Player("Thrishal", new Symbol('X'), PlayerType.HUMAN),
            new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );

        Game game = gameController.startGame(dimension, players);
        gameController.printBoard(game);

        while (game.getGameState() == GameState.IN_PROGRESS) {

            gameController.makeMove(game);

            gameController.printBoard(game);
        }

        if(game.getGameState() == GameState.ENDED) {
            System.out.println("The winner is: " + game.getWinner().getName());
        } else {
            System.out.println("The game is a draw");
        }
    }
}
