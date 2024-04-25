package Controller;

import Exceptions.InvalidMoveException;
import Source.Models.*;
import java.util.*;

public class GameController {
    public Game startGame(int dimension, List<Player> players) {
        HashSet<Symbol> symbols = new HashSet<>();
        for(Player player: players) {
            symbols.add(player.getSymbol());
        }
        if(symbols.size() != players.size()) {
            throw new IllegalArgumentException("Symbols should be unique");
        }
        return new Game(dimension, players);
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState checkStatus(Game game){
        return game.getGameState();
    }

    public Player checkWinner(Game game){
        return game.getWinner();
    }

    public void printBoard(Game game){
        game.printBoard();
    }
}
