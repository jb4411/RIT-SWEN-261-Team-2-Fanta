package com.webcheckers.board;

import com.webcheckers.model.Player;

import java.util.Iterator;

import javax.swing.text.PlainDocument;

public class GameView implements Iterable<Row>{

    private BoardView game;

    public GameView(Player player1, Player player2){
        this.game = new BoardView(player1, player2);
    }

    @Override
    public Iterator<Row> iterator(){
        return game.getBoard().iterator();
    }
}
