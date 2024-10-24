package TicTacToe.models;

import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size, List<List<Cell>> board) {
        this.size = size;
        this.board = board;
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
}
