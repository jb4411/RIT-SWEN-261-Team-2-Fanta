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

    /**
     * Get the opponent of the player whose name is passed in, if an opponent exists.
     *
     * @param name the name of the current player
     * @return the current player's opponent, if an opponent exists
     */
    public Player getOpponent(String name) {
        CheckersGame game = getGame(name);
        if(game != null) {
            Player red = game.redPlayer();
            if(red.getName().equals(name)) {
                return game.whitePlayer();
            } else {
                return red;
            }
        }
        return null;
    }

    /**
     * Get the active player lobby.
     *
     * @return the player lobby
     */
    public PlayerLobby getLobby() {
        return lobby;
    }

    /**
     * Get the game the player whose name is passed in is currently playing, if they are in a game.
     *
     * @param name the name of the current player
     * @return the game the current player is in, if one exists
     */
    public CheckersGame getGame(String name) {
        return inGame.get(lobby.getPlayer(name));
    }

    /**
     * Create a new GameCenter.
     *
     * @param playerLobby the lobby of active players
     */
    public GameCenter(PlayerLobby playerLobby) {
        this.inGame = new HashMap<>();
        this.lobby = playerLobby;
    }

    /**
     * Add a player to the player lobby.
     *
     * @param name the name of the player
     */
    public void addPlayer(String name){
        this.lobby.addPlayer(name);
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

        CheckersGame game = new CheckersGame(red, white, CheckersGame.Mode.PLAY);
        inGame.put(red, game);
        inGame.put(white, game);

        LOG.info("New checkers game created for " + red.getName() + " and " + white.getName());

        return GameStatus.CREATED;
    }

    /**
     * Check if a player is already in a game.
     *
     * @param name the name of the player being checked
     * @return whether or not the player is already in a game
     */
    public synchronized boolean inGame(String name){
        return inGame.containsKey(lobby.getPlayer(name));
    }

}