package com.webcheckers.model;

import com.google.gson.Gson;


/**
 * A class to represent Move object.
 *
 * @author Eric Landers esl7511@rit.edu
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class Move{

    private Position start;
    private Position end;

    public Move(){

    }

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }
}