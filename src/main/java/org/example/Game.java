package org.example;


import java.util.ArrayList;
import java.util.List;


public class Game {
    private List<Player> registeredPlayers;

    public Game() {
        this.registeredPlayers = new ArrayList<>();
    }

    public void register(Player player) {
        if (!registeredPlayers.contains(player)) {
            registeredPlayers.add(player);
        }
    }

    public Player findByName(String name) {
        for (Player player : registeredPlayers) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("One or both players are not registered");
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }

    public List<Player> getRegisteredPlayers() {
        return registeredPlayers;
    }
}

