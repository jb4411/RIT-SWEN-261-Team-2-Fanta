package com.webcheckers.board;

import com.webcheckers.model.Move;

/**
 * A abstract class to represent a checker piece.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
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

    /**
     * An abstract definition of a copy constructor to create a copy of this piece.
     *
     * @return a copy of this piece
     */
    public abstract Piece getCopy();

    /**
     * Create a new piece. Used as a super constructor by King.java and Single.java.
     *
     * @param rank the type of piece this piece should be
     * @param color the color this piece should be
     */
    public Piece(Type rank, Color color){
        this.rank = rank;
        this.color = color;
    }

    /**
     * Return what type of piece this piece is.
     *
     * @return this pieces type
     */
    public Type getType(){
        return rank;

    }

    /**
     * Return what color this piece is.
     *
     * @return this pieces color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Checks if a move is a valid simple hop.
     *
     * @param move the move to be checked
     * @return whether or not the move is valid
     */
    public abstract boolean isMoveValid(Move move);

    /**
     * Checks if a move is a valid jump.
     *
     * @param move the move to be checked
     * @return whether or not the move is valid
     */
    public abstract boolean isJumpValid(Move move);

}
