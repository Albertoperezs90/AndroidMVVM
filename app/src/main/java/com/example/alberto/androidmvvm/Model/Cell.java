package com.example.alberto.androidmvvm.Model;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty(){
        return player == null;
    }
}