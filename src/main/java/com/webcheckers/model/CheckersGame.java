package com.webcheckers.model;

import com.webcheckers.board.BoardView;

import java.util.Objects;

/**
 * A class to represent a game of web checkers.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class CheckersGame {
    private Player red;
    private Player white;
    private Mode mode;
    private BoardView board;
    private int gameID;
    private Player.Color currentColor;

    public enum Mode {
        PLAY,
        SPECTATOR,
        REPLAY
    }

    /**
     * Get a copy of the current game board. This copy can be flipped if necessary.
     *
     * @param flip whether or not to flip the board
     * @return a copy of the board
     */
    public BoardView getBoard(boolean flip){
        if(!flip) {
            return new BoardView(board, false);
        } else {
            return new BoardView(board, true);
        }
    }

    /**
     * Create a new game of web checkers.
     *
     * @param red the player using red pieces
     * @param white the player using white pieces
     * @param mode what mode the game is in
     */
    public CheckersGame(Player red, Player white, Mode mode) {
        this.red = red;
        red.setColor(Player.Color.RED);
        this.white = white;
        white.setColor(Player.Color.WHITE);
        this.mode = mode;
        this.board = new BoardView(red, white);
        this.gameID = Objects.hash(red, white, mode);
        this.currentColor = Player.Color.RED;
    }

    /**
     * Return the player using red pieces.
     *
     * @return the player using red pieces
     */
    public Player redPlayer() {
        return red;
    }

    /**
     * Return the player using white pieces.
     *
     * @return the player using white pieces
     */
    public Player whitePlayer() {
        return white;
    }

    /**
     * Return the mode the game is in.
     *
     * @return the mode the game is in
     */
    public Mode getMode() {
        return mode;
    }
}