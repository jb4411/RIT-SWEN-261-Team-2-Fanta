package com.webcheckers.model;

/**
 * A class to represent Position object.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class Position{
    private int row;
    private int cell;

    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    public int getCell(){
        return cell;
    }

    public int getRow(){
        return row;
    }
}