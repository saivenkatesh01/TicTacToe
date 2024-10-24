package TicTacToe.models;

import java.awt.*;
import java.util.List;

public class Game {
    private Board board;
    private Player winner;
    private List<Move> moves;
    private List<Player> players;
    private int nextPlayerMoveIndex;

    public Game(Board board, Player winner, List<Move> moves, List<Player> players, int nextPlayerMoveIndex) {
        this.board = board;
        this.winner = winner;
        this.moves = moves;
        this.players = players;
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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
}
