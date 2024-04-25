package Source.Models;
import java.net.StandardSocketOptions;
import java.util.*;
public class Board {

    private int size;
    private List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(new Cell(i, j, null, CellState.EMPTY));
            }
            board.add(row);
        }
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printBoard() {
        for(List<Cell> row: board) {
            for(Cell cell: row) {
                if(cell.getCellState()==CellState.EMPTY){
                    System.out.print("| - |");
                }else {
                    System.out.print("| "+cell.getPlayer().getSymbol().getaChar()+" |");
                }
            }
            System.out.println();
        }
    }
}