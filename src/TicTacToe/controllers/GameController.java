package TicTacToe.controllers;

import TicTacToe.exceptions.DuplicateSymbolFoundException;
import TicTacToe.exceptions.InvalidPlayerCountException;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players) throws InvalidPlayerCountException, DuplicateSymbolFoundException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }
    public void makeMove(Game game){
        game.makeMove(game);
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
    public void undo(Game game){

    }
    public void displayBoard(Game game){
        game.displayBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
}
