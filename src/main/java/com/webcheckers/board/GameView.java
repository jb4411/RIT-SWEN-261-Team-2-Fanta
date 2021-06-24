package com.webcheckers.board;

import java.util.Iterator;

import javax.swing.text.PlainDocument;

public class GameView implements Iterable<Row>{

    private BoardView game;

    public GameView(Player player1, PLayer player2){
        this.game = new GameView(player1, player2);
    }

    @Override
    public Iterator<Row> iterator(){
        return game.getBoard().iterator();
    }
}
