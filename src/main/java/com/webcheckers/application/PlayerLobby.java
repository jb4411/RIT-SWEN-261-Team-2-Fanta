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

    public enum NameStatus {INVALID, VALID, DUPLICATE}

    /**
     * Create a new PlayerLobby to hold active players.
     */
    public PlayerLobby() {
        this.players = new HashMap<>();
    }

    /**
     * Try to add a new player, returns a message to the user if the selected name is already in use.
     *
     * @param name the name of the player to try to add
     * @return the status of the player creation
     */
    public synchronized NameStatus addPlayer(String name) {
        if(!name.matches("[a-zA-Z0-9]+[a-zA-Z0-9\\s]*$")) {
            return NameStatus.INVALID;
        } else if(players.containsKey(name)) {
            return NameStatus.DUPLICATE;
        }

        Player newPlayer = new Player(name);
        players.put(name, newPlayer);
        return NameStatus.VALID;
    }

    /**
     * Return the number of currently active players.
     *
     * @return the number of active players
     */
    public int getNumPlayers() {
        return this.players.size();
    }

    /**
     * Return the player with the name given, if a player with that name exists.
     *
     * @param name the name of the player to get
     * @return the player with the given name, if a player with that name exists
     */
    public Player getPlayer(String name){
        return players.get(name);
    }

    /**
     * Return a set of the names of all currently active players.
     *
     * @return a set of the names of all currently active players
     */
    public Set<String> getAllPlayers() {
        return players.keySet();
    }


}
