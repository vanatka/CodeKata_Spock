package com.adidas.kata.kata1;

public class Player {
    private IPlayer currentRole;
    private Game game;

    public Player(Game game){
        this.game = game;
        play();
    }

    IPlayer play() {
        currentRole = game.newRole();
        return currentRole;
    }

    IPlayer getCurrentRole() {
        return currentRole;
    }

    @Override
    public String toString() {
        return "role " + getCurrentRole().getRole() +  " " + " " + getCurrentRole().isAlive();
    }
}
