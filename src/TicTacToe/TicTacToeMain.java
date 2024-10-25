package TicTacToe;

import TicTacToe.controllers.GameController;
import TicTacToe.exceptions.DuplicateSymbolFoundException;
import TicTacToe.exceptions.InvalidPlayerCountException;
import TicTacToe.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) throws InvalidPlayerCountException, DuplicateSymbolFoundException {
        //play the game
        System.out.println("please enter ");
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        List<Player> players = new ArrayList<>();
        players.add(new Player("venki",1L,new Symbol('v'), PlayerType.BOT));
        players.add(new Player("deep",2L,new Symbol('d'), PlayerType.HUMAN));
        Game game = gameController.startGame(3,players);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);

        }
        if(gameController.getGameState(game).equals(GameState.DRAW)){
            System.out.println("Game has drawn");
        }
        else if(gameController.getGameState(game).equals(GameState.ENDED)) {
            System.out.println("the winner is "+gameController.getWinner(game).getName());
        }

    }
}
