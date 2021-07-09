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

    /**
     * Checks if a move is a valid simple hop.
     *
     * @param move the move to be checked
     * @return whether or not the move is valid
     */
    @Override
    public boolean isMoveValid(Move move) {
        return false;
    }

    /**
     * Checks if a move is a valid jump.
     *
     * @param move         the move to be checked
     * @param jumpedSquare the square jumped over
     * @param endSpace     the jump this square ends on
     * @return whether or not the move is valid
     */
    @Override
    public boolean isJumpValid(Move move, Space jumpedSquare, Space endSpace) {
        return false;
    }

    /**
     * Returns whether or not this piece has any possible valid jumps.
     *
     * @param board     the current board
     * @param startRow  the row this piece is on
     * @param startCell the cell this piece is on
     * @return whether or not this piece can jump
     */
    @Override
    public boolean hasJump(BoardView board, int startRow, int startCell) {
        return false;
    }
}
