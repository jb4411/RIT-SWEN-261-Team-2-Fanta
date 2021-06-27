package com.webcheckers.board;

public abstract class Piece {
    private Type rank;
    private Color color;

    public enum Type {
        KING,
        SINGLE
    }

    public enum Color {
        RED,
        WHITE
    }

    public abstract Piece getCopy();

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
