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

    public BoardView(BoardView board, boolean flip) {
        this.board = new Space[NUM_ROWS][NUM_COLS];
        for(int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                this.board[row][col] = new Space(board.getRow(NUM_ROWS - row - 1)[NUM_COLS - col - 1]);
            }
        }
    }

    private Space[] getRow(int rowIdx) {
        return board[rowIdx];
    }

    public void initBoard() {
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                if((row + col) % 2 == 1) {
                    if(row <= 2) {
                        board[row][col] = new Space(col, new Single(Piece.Type.SINGLE, Piece.Color.WHITE), true);
                    } else if(row >= 5) {
                        board[row][col] = new Space(col, new Single(Piece.Type.SINGLE, Piece.Color.RED), true);
                    } else {
                        board[row][col] = new Space(col, null, true);
                    }
                } else {
                    board[row][col] = new Space(col, null, false);
                }
            }
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