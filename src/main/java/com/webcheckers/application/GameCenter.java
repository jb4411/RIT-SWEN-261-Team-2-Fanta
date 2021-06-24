package com.webcheckers.application;

import java.util.logging.Logger;

/**
 * Used to coordinate the state of the WebCheckers Application.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private PlayerLobby playerLobby;

    public GameCenter(PlayerLobby playerLobby) {
        this.playerLobby = playerLobby;
    }
}
