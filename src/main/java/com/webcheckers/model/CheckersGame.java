package com.webcheckers.model;

import com.webcheckers.board.BoardView;

public class CheckersGame {
    private Player red;
    private Player white;
    private Mode mode;
    private BoardView board;

    public BoardView getBoard(boolean flip){
        if(!flip) {
            return board;
        } else {
            return new BoardView(board, true);
        }
    }

    public enum Mode {
        PLAY,
        SPECTATOR,
        REPLAY
    }

    public CheckersGame(Player red, Player white, Mode mode) {
        this.red = red;
        red.setColor(Player.Color.RED);
        this.white = white;
        white.setColor(Player.Color.WHITE);
        this.mode = mode;
        this.board = new BoardView(red, white);
    }

    public Player redPlayer() {
        return red;
    }

    public Player whitePlayer() {
        return white;
    }

    public Mode getMode() {
        return mode;
    }
}