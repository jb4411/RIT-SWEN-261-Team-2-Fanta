package com.webcheckers.board;

import java.util.Iterator;

import javax.swing.text.PlainDocument;

public class BoardView implements Iterable<Row>{

    private GameView game;

    public BoardView(Player player1, PLayer player2){
        this.game = new GameView(player1, player2);
    }

    @Override
    public Iterator<Row> iterator(){
        return game.getBoard().iterator();
    }
}
