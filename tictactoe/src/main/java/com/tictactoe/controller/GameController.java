package com.tictactoe.controller;

import java.util.List;

import com.tictactoe.model.Game;
import com.tictactoe.model.GameState;
import com.tictactoe.model.Player;
/***
 * There is a concept of Stateful VS Stateless
 * Need to learn about it
 *
 * 
 */
public class GameController {
    /* Will act as entry point for the game logic and handle user inputs and game state management.
    *  Methods to be implemented:
    *  - startGame()
    *  - makeMove(int position)
    *  - showBoard()
    *  - doUndo()
    *  - getWinner()
     */

    public Game startGame(int dimension, List<Player> players){
        return new Game(dimension, players);
    }

    public GameState checkGameState(Game game){
        return game.getGameState();
    }

    /* * *
    * two ways to define business logic:
    * way 1: ADD business logix in your models. this is mostly done in Interactive Machine Coding where you are not using any DB
    * way 2: You add business logic in your service class. This is mostly done in Web API methods where you are using database.
    * 
    * In this project, we will use way 1 
    */

    public void makeMove(Game game){
        game.makeMove();    
    }

    public void undo(Game game){
        game.undo();
    }

    public void displayBoard(Game game){
        game.printBoard();
    }
    public Player getWinner(Game game){
        return game.getWinner();
    }
}
