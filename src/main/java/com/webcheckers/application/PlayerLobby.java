package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Set;

/**
 * Holds all currently logged in players and is used to add more players as they log in.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class PlayerLobby {
    private final HashMap<String, Player> players;

    public PlayerLobby() {
        this.players = new HashMap<>();
    }

    public enum NameStatus {INVALID, VALID, DUPLICATE}

    public synchronized NameStatus addPlayer(String name) {
        if(!name.matches("[a-zA-Z]+[a-zA-Z\\s]*$")) {
            return NameStatus.INVALID;
        } else if(players.containsKey(name)) {
            return NameStatus.DUPLICATE;
        }

        Player newPlayer = new Player(name);
        players.put(name, newPlayer);
        return NameStatus.VALID;
    }

    public int getNumPlayers() {
        return this.players.size();
    }

    public Player getPlayer(String name){
        return players.get(name);
    }

    public Set<String> getAllPlayers() {
        return players.keySet();
    }


}
