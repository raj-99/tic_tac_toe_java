package com.tictactoe.strategy;

import com.tictactoe.model.Board;
import com.tictactoe.model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board p);
}
