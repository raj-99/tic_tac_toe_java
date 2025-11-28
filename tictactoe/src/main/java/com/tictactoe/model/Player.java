package com.tictactoe.model;

import java.util.Scanner;

public class Player {
    private String symbol;
    private String name;
    private Long id;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id, String name, String symbol, PlayerType type){
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = type;
        this.scanner = new Scanner(System.in);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move getInputAndMakeMove(Board board) {
        System.out.println("Please select the row: ");
        int row = scanner.nextInt();

        System.out.println("Please select the column: ");
        int col = scanner.nextInt();
        return new Move(new Cell(row, col), this);
    }
}
