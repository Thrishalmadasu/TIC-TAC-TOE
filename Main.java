import Controller.GameController;
import Exceptions.InvalidMoveException;
import Source.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.rowset.spi.SyncResolver;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        Scanner sc=new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("What do you want the size of the board to be : ");
        int dimension = sc.nextInt();

        System.out.println("Do you want to play with a bot or with a Person");
        System.out.println("If you want to play with a bot press 1");
        System.out.println("Else press any other number");
        int choose = sc.nextInt();
        List<Player> players= new ArrayList<>();
        if(choose == 1){
        System.out.println("Enter the name of the player : ");
        String name = sc.next();
        players = List.of(
            new Player(name, new Symbol('X'), PlayerType.HUMAN),
            new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
        );
        }else{
            System.out.println("How many players do you want on the board");
            int noOfPlayers = sc.nextInt();
            for(int i=0;i<noOfPlayers;i++){
                System.out.println("Enter the name of the player");
                String s = sc.next();
                System.out.println("Enter your Symbol (It must be a single character)");
                String char1 = sc.next();
                players.add(new Player(s, new Symbol(char1.charAt(0)), PlayerType.HUMAN));
            }
         }

        Game game = gameController.startGame(dimension, players);
        gameController.printBoard(game);

        System.out.println();
        System.out.println("Note : The cell indexes are zero based");
        System.out.println();

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
