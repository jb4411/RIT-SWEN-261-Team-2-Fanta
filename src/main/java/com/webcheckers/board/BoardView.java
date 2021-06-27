package com.webcheckers.board;

import com.webcheckers.model.Player;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import com.webcheckers.model.Player;

public class BoardView implements Iterable<Row>{
    private Player red;
    private Player white;
    private List<Row> board;

    public BoardView(Player red, Player white){
        this.red = red;
        this.white = white;
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
        //return board.getBoard().iterator();
        return null;
    }

}