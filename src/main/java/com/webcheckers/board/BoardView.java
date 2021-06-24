package com.webcheckers.board;

import com.webcheckers.model.Player;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import com.webcheckers.model.Player;

public class BoardView implements Iterable<Row>{
    private Player player1;
    private Player player2;
    private List<Row> board;

    public BoardView(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        this.board = new LinkedList<>();
        initBoard();
    }

    public List<Row> getBoard(){
        return board;
    }

    public void initBoard(){
        boolean valid = true;

        for(int i=0; i<8; i++){
            board.add(new Row());
            valid = !valid;
        }
    }

    @Override
    public Iterator<Row> iterator(){
        return board.getBoard().iterator();
    }

}