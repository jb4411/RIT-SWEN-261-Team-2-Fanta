package com.webcheckers.model;
/**
 * Player class: holds all player information.
 *
 * @author Eric Landers esl7511@rit.edu
 */

public class Player{
    private String name;
    private String sessionID;

    public Player(String name, String sessionID){
        this.name = name;
        this.sessionID = sessionID;
    }

    public String getName(){
        return name;
    }

    public String getSessionID(){
        return sessionID;
    }
    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        if(!(obj instanceof Player)){
            return false;
        }
        final Player that = (Player) object;
        return this.name.equals(that.name);
    }
    @Override
    public int hashCode(){
        return name.hashCode();
    }
}