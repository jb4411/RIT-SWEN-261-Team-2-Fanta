package com.webcheckers.application;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import java.util.*;
import java.util.logging.Logger;

/**
 * Used to coordinate the state of the WebCheckers Application.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GameCenter {
    //Values used for holding active games and players
    private static final Logger LOG = Logger.getLogger(GameCenter.class.getName());
    private final PlayerLobby lobby;
    private final HashMap<Player, CheckersGame> inGame;
    private final HashMap<Integer, CheckersGame> games;
    private final HashMap<Integer, Set<Player>> spectatedGames;
    private final HashMap<Player, CheckersGame> spectators;

    /**
     * An enum used when returning info about the status of game creation.
     */
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
     * Get a game by its unique identifier.
     *
     * @param gameID the ID of the game
     * @return the game if it exists
     */
    public CheckersGame getGameByID(int gameID) {
        return games.get(gameID);
    }

    /**
     * Create a new GameCenter.
     *
     * @param playerLobby the lobby of active players
     */
    public GameCenter(PlayerLobby playerLobby) {
        this.inGame = new HashMap<>();
        this.lobby = playerLobby;
        this.games = new HashMap<>();
        this.spectatedGames = new HashMap<>();
        this.spectators = new HashMap<>();
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
        games.put(Objects.hash(redPlayerName, whitePlayerName), game);

        LOG.info("New checkers game created for " + red.getName() + " and " + white.getName());

        return GameStatus.CREATED;
    }

    /**
     * End the game the player is in, and remove both players from the game.
     *
     * @param name the name of the player to be removed
     */
    public synchronized void endGame(String name) {
        Player player = lobby.getPlayer(name);
        Player opponent = getOpponent(name);
        if(player != null && inGame(name)) {
            inGame.remove(player);
            if(opponent != null && inGame(opponent.getName())) {
                inGame.remove(opponent);
                games.remove(Objects.hash(name, opponent.getName()));
            }
        }
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

    /**
     * End an active game if the player is in one, then remove them from the player lobby.
     *
     * @param name the name of the player to be removed
     */
    public synchronized void removePlayer(String name){
        if(inGame(name)) {
            endGame(name);
        }
        lobby.removePlayer(name);
    }

    /**
     * Get all active games and their game id's.
     *
     * @return A map of all actives games anf their game id's
     */
    public synchronized Map<String, String> getAllActiveGames() {
        HashMap<String, String> activeGames = new HashMap<>();
        for(int id : games.keySet()) {
            String gameStr = games.get(id).toString();
            activeGames.put(gameStr, Integer.toString(id));
        }
        return activeGames;
    }

    /**
     * Add a player to the set of players spectating the game with the gameID passed in.
     *
     * @param gameID the ID of the game to spectate
     * @param spectator the player to spectate said game
     */
    public void addSpectator(int gameID, Player spectator) {
        this.spectators.put(spectator, this.getGameByID(gameID));
        if(!this.spectatedGames.containsKey(gameID)) {
            this.spectatedGames.put(gameID, new HashSet<>());
        }
        this.spectatedGames.get(gameID).add(spectator);
    }

    /**
     * Remove the player passed in from the set of players spectating the game with the gameID passed in.
     *
     * @param gameID the ID of the game to remove the spectator from
     * @param spectator the player that will no longer be spectating said game
     */
    public void removeSpectator(int gameID, Player spectator) {
        this.spectatedGames.get(gameID).remove(spectator);
    }

    /**
     * Get the game the player whose name is passed in is currently spectating, if they are spectating a game.
     *
     * @param spectator the name of the current player
     * @return the game the spectator is spectating, if one exists
     */
    public CheckersGame getGameBySpectator(Player spectator) {
        return this.spectators.get(spectator);
    }
}
