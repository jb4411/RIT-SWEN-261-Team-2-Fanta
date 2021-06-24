package com.webcheckers.application;

import java.util.logging.Logger;

/**
 * Used to coordinate the state of the WebCheckers Application.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private final PlayerLobby lobby;

    public GameCenter(PlayerLobby playerLobby) {
        this.lobby = playerLobby;
    }

    public void addPlayer(String username, String sessionID){
        this.lobby.addPlayer(username);
    }

}