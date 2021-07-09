package com.webcheckers.model;

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
    public boolean isMoveValid(Move move) {
        return move.isSimpleMove() && ((move.getStart().getRow() - move.getEnd().getRow()) == 1);
    }

    @Override
    public boolean isJumpValid(Move move, Space jumpedSquare) {
        boolean captured = (jumpedSquare.getPiece() != null) && (jumpedSquare.getPiece().getColor() != this.getColor());
        return move.isJump() && ((move.getStart().getRow() - move.getEnd().getRow()) == 2) && captured;
    }
}
