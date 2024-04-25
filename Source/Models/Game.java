package Source.Models;

import Exceptions.InvalidMoveException;
import Strategies.WinningAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int nextPlayerMoveIndex;
    private Player winner;
    private List<Move> moves;
    private GameState gameState;

    private WinningAlgorithm winningAlgorithm;
    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextPlayerMoveIndex = 0;
        this.winner = null;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winningAlgorithm = new WinningAlgorithm();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void printBoard() {
        this.board.printBoard();
    }

    public void makeMove() throws InvalidMoveException {
         Player currentPlayer = players.get(nextPlayerMoveIndex);
         System.out.println(currentPlayer.getName()+"'s turn");
         //Make a move
         Move move = currentPlayer.makeMove(board);
         //Validate the move
            if(!isValidMove(move)){
                throw new InvalidMoveException("Invalid Move made by"+currentPlayer.getName());
            }
         int row = move.getCell().getRow();
         int col = move.getCell().getCol();
         Cell cellToChange = board.getBoard().get(row).get(col);
         cellToChange.setPlayer(currentPlayer);
         cellToChange.setCellState(CellState.FILLED);

         Move finalMove = new Move(cellToChange, currentPlayer);
         moves.add(finalMove);
         nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

         //Check if the current move is winning move or not
        if(winningAlgorithm.checkWinner(board, finalMove)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        }
         return;
    }

    private boolean isValidMove(Move move) {
        //Check if the cell is empty
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()){
            return false;
        }
        Cell cell = move.getCell();
        if(cell.getCellState() != CellState.EMPTY){
            return false;
        }


        return true;
    }
}
