package TicTacToe.models;
import TicTacToe.exceptions.DuplicateSymbolFoundException;
import TicTacToe.exceptions.InvalidPlayerCountException;
import TicTacToe.strategy.gameWinningStrategy.ColumnWinningStrategy;
import TicTacToe.strategy.gameWinningStrategy.DiagonalWinningStrategy;
import TicTacToe.strategy.gameWinningStrategy.GameWinningStrategy;
import TicTacToe.strategy.gameWinningStrategy.RowWinningStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private Player winner;
    private List<Move> moves;
    private List<Player> players;
    private int nextPlayerMoveIndex;
    private GameState gameState;
    private List<GameWinningStrategy> gameWinningStrategies;

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    private Game(int dimension, List<Player> players, List<GameWinningStrategy> gameWinningStrategies ) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
        this.gameState=GameState.IN_PROGRESS;
        this.gameWinningStrategies = gameWinningStrategies;
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public void displayBoard(){
        board.printBoard();
    }

    public void makeMove(Game game) {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is " + currentPlayer.getName()+"'s move.");
        Move move = currentPlayer.makeMove(board);
        //validate move
        validateMove(move,board);
        // place move on the board
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();

        Cell cell=board.getBoard().get(row).get(col);

        cell.setPlayer(currentPlayer);
        cell.setCellState(CellState.FILLED);

        Move finalMove = new Move(cell,currentPlayer);
        nextPlayerMoveIndex++;
        nextPlayerMoveIndex %= players.size();

        //check the winner
        if(checkWinner(board,move)){
            gameState=GameState.ENDED;
            winner=currentPlayer;
        }
        else if(moves.size()==board.getSize() * board.getSize()){
            gameState=GameState.DRAW;
        }
    }

    private boolean checkWinner(Board board, Move move) {
        // check all winning strategy
        for(GameWinningStrategy winningStrategy : gameWinningStrategies){
              if(winningStrategy.checkWinner(board, move)) return true;
        }
       return false;
    }

    private boolean validateMove(Move move, Board board){
        int row= move.getCell().getRow();
        int col=move.getCell().getCol();

        Cell cell =board.getBoard().get(row).get(col);
        return row>=0 && row<board.getSize()
                && col>=0 && col<board.getSize()
                && cell.isEmpty();
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validateCount() throws InvalidPlayerCountException {
            if (players.size() >= dimension) throw new InvalidPlayerCountException("no of players should be 1 less than the dimension of board");

        }
        private void validateUniqueSymbols() throws DuplicateSymbolFoundException {
            HashSet<Character> symbolsCheck = new HashSet<Character>();

            for(Player player : players){
                symbolsCheck.add(player.getSymbol().getaChar());
            }
            if(symbolsCheck.size()< players.size()){
                throw new DuplicateSymbolFoundException("no 2 players can have same symbols");
            }
        }
        private void valdateBotCount(){
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
                if(botCount >= 2){
                    throw new RuntimeException("only 1 bot is allowed per game");
                }
            }
        }
        private void validateGame(int dimension, List<Player> players) throws DuplicateSymbolFoundException, InvalidPlayerCountException {
            validateCount();
            validateUniqueSymbols();
            valdateBotCount();
        }
        public Game build() throws InvalidPlayerCountException, DuplicateSymbolFoundException {
            // validation
            validateGame(dimension,players);
            return new Game(
                    dimension,
                    players,
                    List.of(
                            new RowWinningStrategy(),
                            new ColumnWinningStrategy(),
                            new DiagonalWinningStrategy()
                    ));
        }

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

    public List<GameWinningStrategy> getGameWinningStrategies() {
        return gameWinningStrategies;
    }

    public void setGameWinningStrategies(List<GameWinningStrategy> gameWinningStrategies) {
        this.gameWinningStrategies = gameWinningStrategies;
    }
}
