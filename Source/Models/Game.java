package Source.Models;

import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    Move nextPlayerMoveIndex;
    Player winner;
    List<Move> moves;
    GameState gameState;
}
