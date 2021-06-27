package com.webcheckers.board;

import com.webcheckers.model.Player;

import java.util.*;

/**
 * A class to represent the game board.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class BoardView implements Iterable<Row>{
    private Player red;
    private Player white;
    private Space[][] board;

    private static final int NUM_ROWS = 8;
    private static final int NUM_COLS = 8;

    /**
     * Creates a new game board.
     *
     * @param red the player using red pieces
     * @param white the player using white pieces
     */
    public BoardView(Player red, Player white){
        this.red = red;
        this.white = white;
        this.board = new Space[NUM_ROWS][NUM_COLS];
        initBoard();
    }

    /**
     * A copy constructor that can also return a flipped copy of the board.
     *
     * @param board the board to be copied
     * @param flip whether or not the board should be flipped
     */
    public BoardView(BoardView board, boolean flip) {
        this.board = new Space[NUM_ROWS][NUM_COLS];
        for(int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                if(flip) {
                    this.board[row][col] = new Space(board.getRow(NUM_ROWS - row - 1)[NUM_COLS - col - 1]);
                } else {
                    this.board[row][col] = new Space(board.getRow(row)[col]);
                }
            }
        }
    }

    /**
     * Returns the row at the index given.
     *
     * @param rowIdx the index of the desired row
     * @return the row at the given index
     */
    private Space[] getRow(int rowIdx) {
        return board[rowIdx];
    }

    /**
     * Initialize the board to a valid initial board state.
     */
    public void initBoard() {
        for(int row = 0; row < NUM_ROWS; row++) {
            for(int col = 0; col < NUM_COLS; col++) {
                if((row + col) % 2 == 1) {
                    if(row <= 2) {
                        board[row][col] = new Space(col, new Single(Piece.Color.WHITE), true);
                    } else if(row >= 5) {
                        board[row][col] = new Space(col, new Single(Piece.Color.RED), true);
                    } else {
                        board[row][col] = new Space(col, null, true);
                    }
                } else {
                    board[row][col] = new Space(col, null, false);
                }
            }
        }
    }

    /**
     * Return an iterator of the board.
     *
     * @return a linked list of the rows in the board
     */
    @Override
    public Iterator<Row> iterator(){
        Collection<Row> lst = new LinkedList<>();
        for(int row = 0; row < NUM_ROWS; row++) {
            lst.add(new Row(row, Arrays.asList(board[row])));
        }
        return lst.iterator();
    }

}