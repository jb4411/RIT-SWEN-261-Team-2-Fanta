package com.webcheckers.model;

/**
 * A class to represent a king piece.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class King extends Piece {
    /**
     * Create a new king piece.
     *
     * @param color the color this piece should be
     */
    public King(Color color) {
        super(Type.KING, color);
    }

    /**
     * A copy constructor to create a copy of this piece.
     *
     * @return a copy of this piece
     */
    @Override
    public Piece getCopy() {
        return new King(this.getColor());
    }

    @Override
    public boolean isMoveValid(Move move) {
        return false;
    }

    @Override
    public boolean isJumpValid(Move move) {
        return false;
    }

}
