package com.tictactoe.strategy;

import com.tictactoe.model.Board;
import com.tictactoe.model.Move;

public class DiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        // int colNo = move.getCell().getCol();
        // String symbol = move.getCell().getPlayer().getSymbol();
        // Map<String,Integer> internalMap = counts.get(colNo);
        // internalMap.put(symbol, internalMap.get(symbol)-1);
    }

}
