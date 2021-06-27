package com.webcheckers.board;

public class Single extends Piece{
    public Single(Piece.Type rank, Piece.Color color) {
        super(rank, color);
    }

    @Override
    public Piece getCopy() {
        return new Single(this.getType(), this.getColor());
    }
}
