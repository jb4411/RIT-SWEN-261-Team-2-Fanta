package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Used to coordinate the state of the WebCheckers Application.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GameCenter {
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private final PlayerLobby lobby;
    private final HashMap<Player, CheckersGame> inGame;

    public enum GameStatus {
        IN_GAME,
        NULL_PLAYER,
        SAME_PLAYER,
        CREATED
    }

    public GameCenter(PlayerLobby playerLobby) {
        this.inGame = new HashMap<>();
        this.lobby = playerLobby;
    }

    public void addPlayer(String username, String sessionID){
        this.lobby.addPlayer(username);
    }

    /**
     * Attempts to create a new game of WebCheckers.
     *
     * @param redPlayerName the name of the player using red checkers
     * @param whitePlayerName the name of the player using white checkers
     * @return whether or not a game was successfully created
     */
    public synchronized GameStatus createGame(String redPlayerName, String whitePlayerName) {
        Player red = lobby.getPlayer(redPlayerName);
        Player white = lobby.getPlayer(whitePlayerName);

        if(inGame.containsKey(red) || inGame.containsKey(white)) {
            return GameStatus.IN_GAME;
        } else if(red == null || white == null) {
            return GameStatus.NULL_PLAYER;
        } else if(red.equals(white)) {
            return GameStatus.SAME_PLAYER;
        }

        CheckersGame game = new CheckersGame(red, white);
        inGame.put(red, game);
        inGame.put(white, game);

        LOG.info("New checkers game created for " + red.getName() + " and " + white.getName());

        return GameStatus.CREATED;
    }

}