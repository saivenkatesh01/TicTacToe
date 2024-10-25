package TicTacToe.models;

import java.util.Scanner;

public class Player {
    private String name;
    private Long id;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Long id, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.id = id;
        this.symbol = symbol;
        this.playerType = playerType;
        scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        System.out.println("pls enter row number");
        int row = scanner.nextInt();
        System.out.println("pls enter col number");
        int col = scanner.nextInt();

        return new Move(new Cell(row,col),this)   ;
    }
}
