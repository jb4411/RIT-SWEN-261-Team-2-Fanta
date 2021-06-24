package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashMap;

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

    public synchronized boolean addPlayer(String name) {
        if(!name.matches("^[a-zA-Z\\s]*$")) {
            return false;
        } else if(players.containsKey(name)) {
            return false;
        }

        Player newPlayer = new Player(name);
        players.put(name, newPlayer);
        return true;
    }

    public int getNumPlayers(){
        return this.players.size();
    }

    public Player getPlayer(String name){
        return players.get(name);
    }


}
