package com.webcheckers.application;

/**
 * Holds all currently logged in players and is used to add more players as they log in.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class PlayerLobby {

    public synchronized boolean addPlayer(String name) {
        if(!name.matches("^[a-zA-Z\\s]*$")) {
            return false;
        }

    }


}
