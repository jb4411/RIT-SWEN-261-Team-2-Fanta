package com.webcheckers.model;

import com.webcheckers.board.Piece;

/**
 * A class to represent Move object.
 *
 * @author Eric Landers esl7511@rit.edu
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class Move{

    private Position start;
    private Position end;

    /**
     * Create a new move.
     *
     * @param start the starting position of the move
     * @param end the ending position of the move
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    /**
     * Check if this move is a simple move.
     *
     * @return whether or not the move is a simple move
     */
    public boolean isSimpleMove() {
        return (Math.abs(start.getRow() - end.getRow()) == 1) && (Math.abs(start.getCell() - end.getCell()) == 1);
    }

    public boolean isJump() {
        return false;
    }
}