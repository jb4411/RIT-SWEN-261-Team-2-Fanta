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

    /**
     * Checks if a move is a valid simple hop.
     *
     * @param move the move to be checked
     * @return whether or not the move is valid
     */
    @Override
    public boolean isMoveValid(Move move) {
        return move.isSimpleMove() && ((move.getStart().getRow() - move.getEnd().getRow()) == 1);
    }

    /**
     * Checks if a move is a valid jump.
     *
     * @param move the move to be checked
     * @param jumpedSquare the square jumped over
     * @return whether or not the move is valid
     */
    @Override
    public boolean isJumpValid(Move move, Space jumpedSquare) {
        boolean captured = (jumpedSquare.getPiece() != null) && (jumpedSquare.getPiece().getColor() != this.getColor());
        return move.isJump() && ((move.getStart().getRow() - move.getEnd().getRow()) == 2) && captured;
    }

    /**
     * Returns whether or not this piece has any possible valid jumps.
     *
     * @param startRow the row this piece is on
     * @param startCell the cell this piece is on
     * @param board the current board
     * @return whether or not this piece can jump
     */
    @Override
    public boolean hasJump(BoardView board, int startRow, int startCell) {
        Move jump;
        Position start = new Position(startRow, startCell);
        Position end = new Position(startRow - 1, startCell - 1);
        jump = new Move(start, end);
        if(this.isJumpValid(jump, board.getJumpedSquare(jump))) {
            return true;
        } else {
            end = new Position(startRow - 1, startCell + 1);
            jump = new Move(start, end);
            return this.isJumpValid(jump, board.getJumpedSquare(jump));
        }
    }


}
