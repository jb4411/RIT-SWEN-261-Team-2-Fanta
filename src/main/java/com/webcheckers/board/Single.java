package com.webcheckers.board;

import com.webcheckers.model.Move;

/**
 * A class to represent a single piece.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class Single extends Piece{
    /**
     * Create a new single piece.
     *
     * @param color the color this piece should be
     */
    public Single(Piece.Color color) {
        super(Type.SINGLE, color);
    }

    /**
     * A copy constructor to create a copy of this piece.
     *
     * @return a copy of this piece
     */
    @Override
    public Piece getCopy() {
        return new Single(this.getColor());
    }

    @Override
    public boolean isHopValid(Move move) {
        return false;
    }

    @Override
    public boolean isJumpValid(Move move) {
        return false;
    }
}
