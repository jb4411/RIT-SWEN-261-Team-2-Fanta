package com.webcheckers.board;


public class King extends Piece {
    @Override
    public Piece getCopy() {
        return new King(this.getType(), this.getColor());
    }

    public King(Type rank, Color color) {
        super(rank, color);
    }


}
