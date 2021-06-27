package com.webcheckers.board;

import com.webcheckers.model.Player;

import java.util.*;

import com.webcheckers.model.Player;

public class BoardView implements Iterable<Row>{
    private Player red;
    private Player white;
    private Space[][] board;

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;


    public BoardView(Player red, Player white){
        this.red = red;
        this.white = white;
        this.board = new Space[NUM_ROWS][NUM_COLS];
        initBoard();
    }

    public Space[][] getBoard(){
        return board;
    }

    public void initBoard(){
        boolean valid = true;

        for(int i=0; i<8; i++){
            //board.add(new Row());
            valid = !valid;
        }
    }

    @Override
    public Iterator<Row> iterator(){
        Collection<Row> lst = new LinkedList<>();
        for(int row = 0; row < NUM_ROWS; row++) {
            lst.add(new Row(row, Arrays.asList(board[row])));
        }
        return lst.iterator();
    }

}