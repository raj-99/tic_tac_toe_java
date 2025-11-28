package com.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tictactoe.controller.GameController;
import com.tictactoe.model.Game;
import com.tictactoe.model.GameState;
import com.tictactoe.model.Player;
import com.tictactoe.model.PlayerType;

/*
 * This is the client class
 *
 * 
 */
public class Main {

    public static void main(String[] args) {
        // System.out.println("Hello world!");
        // This class will interact with my GameController
        /*S1 . Choose dimension
         *S2 . Create Player List
         *S3 . Create Game
         */
        Scanner scanner = new Scanner(System.in);
        try {
            int dimension = 3;
            List<Player> players = new ArrayList<>();

            players.add(new Player(1L, "yash", "x", PlayerType.HUMAN));
            players.add(new Player(2L, "bot", "o", PlayerType.BOT));

            GameController gameController = new GameController();
            Game game = gameController.startGame(dimension, players);
            
            while (gameController.checkGameState(game).equals(GameState.IN_PROGRESS)) {
                /*
             * Display Board
             * Decide the nextPlayerIndex
             * Ask player to make move
             * 
             * Check whether the player has won or not.
             * If the player has won the game, set gameState = WIN
             * If the game is drawn, set the gameState  = DRAW
             * 
             * 
                 */
                gameController.displayBoard(game);
                System.out.println("Please make a move...");
                gameController.makeMove(game);
            }
            System.out.println("Game is FInsihed!!!");
            if (gameController.checkGameState(game).equals(GameState.WIN)) {
                System.out.println("Game has been won by player: " + gameController.getWinner(game));
            } else {
                System.out.println("Game has been drawn.");
            }
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e);
        }

    }
}
