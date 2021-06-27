package com.webcheckers.board;

public class Space {
    private int cellIdx;
    private Piece piece;
    private boolean valid;

    public Space(int cellIdx, Piece piece, boolean valid) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.valid = valid;
    }

    /**
     * Not currently implemented
     * @return The index of this space within the row.
     */
    public int getCellIdx(){
        return cellIdx;
    }

    /**
     * Not currently fully implemented
     * Checks whether this space is a valid space to move a piece
     * @return Java Iterator of the Spaces within a single row.
     */
    public boolean isValid(){
        return this.valid;
    }
    /**
     * Not currently implemented
     * Creates a Java Iterator of the Spaces within a single row.
     * @return the piece if there is a piece, otherwise null.
     */
    public Piece getPiece(){
        return piece;
    }


}
