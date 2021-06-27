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

    public Space(Space space) {
        this.cellIdx = space.getCellIdx();
        if(space.getPiece() != null) {
            this.piece = space.getPiece().getCopy();
        } else {
            this.piece = null;
        }
        this.valid = space.isValid();
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

        if(!this.valid){
            return this.valid;
        }
        else {
            if(this.piece == null)return true;
            else return false;
        }
    }
    /**
     * Not currently implemented
     * @return the piece if there is a piece, otherwise null.
     */
    public Piece getPiece(){
        return piece;
    }


}
