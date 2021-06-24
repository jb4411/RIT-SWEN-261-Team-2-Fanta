package com.webcheckers.board;

public class Piece {
    private enum Type {
        KING,
        SINGLE
    }

    private enum Color {
        RED,
        WHITE
    }

    private Type rank;
    private Color color;

    public Piece(Type rank, Color color){
        this.rank = rank;
        this.color = color;
    }

    public Type getType(){
        return rank;

    }

    public Color getColor(){
        return color;
    }

}
