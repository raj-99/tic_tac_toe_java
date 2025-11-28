package com.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tictactoe.strategy.ColumnWinningStrategy;
import com.tictactoe.strategy.DiagonalWinningStrategy;
import com.tictactoe.strategy.RowWinningStrategy;
import com.tictactoe.strategy.WinningStrategy;

public class Game {
    private List<Player> players; // Required
    private Board board; // Not Required
    private GameState gameState; // Not Required
    private List<Move> moves; // Not Required
    private Player winner; // Not Required
    private int nextMovePlayerIndex; // Not Required
    private List<WinningStrategy> winningStrategies; // Required

    public Game(int dimension, List<Player> players){
        this.board = new Board(dimension);
        this.players = players;
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = Arrays.asList(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy());
        this.moves = new ArrayList<>();
        this.winner = null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void makeMove() {
        /*
         * Steps:
         * 1. Get the player using nextMovePlayerIndex
         * 2. Take Input from player using scanner
         * 3. Validate and update the cell
         * 4. Store the move in List of Moves
         * 5. Increment the nextMovePlayerIndex
         * 6. Chcek for Winner. If the player has won, then mark this player as winner
         *  
        */

        Player currentPlayer = players.get(nextMovePlayerIndex);
        System.out.println("It is player: "+ currentPlayer.getName());

        Move currentMove = currentPlayer.getInputAndMakeMove(board);
        if(invalidMove(currentMove)){
            System.out.println("Invalid move by the player!");
            return;
        }

        int currentRow = currentMove.getCell().getRow();
        int currentCol = currentMove.getCell().getCol();
        System.out.println("Move is made in ---> Row: "+ currentRow + ", Column: " + currentCol);

        Cell currCell = board.getBoard().get(currentRow).get(currentCol);
        currCell.setCellState(CellState.FILLED);
        currCell.setPlayer(currentPlayer);

        Move newMove = new Move(currCell, currentPlayer);
        moves.add(newMove);

        nextMovePlayerIndex += 1;
        nextMovePlayerIndex %= players.size();

        if(checkWinner(board, newMove)){
            gameState = GameState.WIN;
            winner = currentPlayer;
        }
        else if(moves.size() == this.getBoard().getSize()*this.getBoard().getSize()){
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Board board, Move newMove) {
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board, newMove)){
                System.out.println("Check winner returns true");
                return true;
            }
        }
        return false;
    }

    private boolean invalidMove(Move currentMove) {
        /**
         * ToDO: Check for validations...
         *  But you guys will have to handle the validation cases.
         * 1. Row>=0 , Col>=0 && row<n && col <n
         * 2. If the current cell state is NOT EMPTY -- then return true.
         */
        return false;
    }

    public void undo() {
        /*
         * Steps for UNDO:
         * 1.  Get the last move from moves list
         * 2. Remove the last move from the list
         * 3. Update the cell status to EMPTY and player to nulll in CELL
         * 4. Decrement the lastMovePlayerIndex
         * 5. handle undo in winning strategy
         */
        if(moves.isEmpty()){
            System.out.println("No moves made yet.");
            return;
        }
        if(!gameState.equals(GameState.IN_PROGRESS)){
            System.out.println("Game is not in progress.");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);
        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + players.size()) % players.size();

        //reduce the count in winning strategy
        for(WinningStrategy winningStrategy : winningStrategies){
            winningStrategy.handleUndo(board, lastMove);
        }
        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setPlayer(null);
    }

    public void printBoard() {
        board.printBoard();
    }
    
}
