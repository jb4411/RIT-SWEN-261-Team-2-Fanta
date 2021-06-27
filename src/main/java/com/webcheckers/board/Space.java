package com.webcheckers.board;

public class Space {
    private int cellIndx;

    /**
     * Not currently implemented
     * @return The index of this space within the row.
     */
    public int getCellIdx(){
        return cellIndx;
    }

    /**
     * Not currently fully implemented
     * Checks whether this space is a valid space to move a piece
     * @return Java Iterator of the Spaces within a single row.
     */
    public boolean isValid(){
        if (getPiece() != null){
            return false;
        }
        return true;
    }
    /**
     * Not currently implemented
     * Creates a Java Iterator of the Spaces within a single row.
     * @return the piece if there is a piece, otherwise null.
     */
    public Piece getPiece(){
        return null;
    }


}
