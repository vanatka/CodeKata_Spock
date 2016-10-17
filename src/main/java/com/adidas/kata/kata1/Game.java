package com.adidas.kata.kata1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.adidas.kata.kata1.Game.ROLES.*;

public class Game {
    public interface ROLES {
        int SCISSORS = 1, PAPER = 2, SPOCK = 3, ROCK = 4, LIZARD = 5;
    }

    private Role
            scissors = new Role(new int[]{LIZARD, PAPER}, SCISSORS),
            paper = new Role(new int[]{ROCK, SPOCK}, PAPER),
            spock = new Role(new int[]{SCISSORS, ROCK}, SPOCK),
            rock = new Role(new int[]{SCISSORS, LIZARD}, ROCK),
            lizard = new Role(new int[]{PAPER, SPOCK}, LIZARD);

    private HashMap<Integer, Role> possibleRoles = new HashMap();

    {
        possibleRoles.put(SCISSORS, scissors);
        possibleRoles.put(LIZARD, lizard);
        possibleRoles.put(PAPER, paper);
        possibleRoles.put(SPOCK, spock);
        possibleRoles.put(ROCK, rock);
    }

    private List<Player> players = new ArrayList<Player>();
    private int rounds = 0;

    public Game generatePlayers() {
        final int generatedCount = randInt(2, 1000);

        for( int i = 0; i < generatedCount; i++) {
            players.add(new Player(this));
        }

        return this;
    }

    public Game crashUntilWinner() {
        crashEveryOne(getAlivePlayers(this.players));
        return this;
    }

    public List<Player> getAlivePlayers(List<Player> players) {
        final List<Player> oldPlayers = players;
        players = new ArrayList<>();

        for(Player player : oldPlayers) {
            if(player.getCurrentRole().isAlive()) {
                player.play();
                players.add(player);
                System.out.println("alive is " + player + " " + player.getCurrentRole().getRole());
            }
        }

        return players;
    }

    public List<Player> crashEveryOne(List<Player> players) {
        rounds++;
        if( players.size() == 1) {
            return players;
        }

        for(Player player : players) {
            for(Player p : players) {
                if( p.equals(player)) {
                    continue;
                }else {
                    player.getCurrentRole().try2Kill(p.getCurrentRole());
                }
            }
        }


        List<Player> alivePlayers = getAlivePlayers(players);
        System.out.println("alive " + alivePlayers.size());
        if( !alivePlayers.isEmpty() ) {
            crashEveryOne(alivePlayers);
        }

        return alivePlayers;
    }

    public IPlayer newRole() {
        return possibleRoles.get(randInt(SCISSORS, LIZARD));
    }

    public static void main(String[] args) {
        Game game = new Game().generatePlayers().crashUntilWinner();
        System.out.println("Finished with rounds: " + game.rounds);
    }

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
