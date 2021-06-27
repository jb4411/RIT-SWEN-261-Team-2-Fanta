package com.webcheckers.model;
/**
 * Player class: holds all player information.
 *
 * @author Eric Landers esl7511@rit.edu
 */

public class Player{
    private String name;
    private Color color;

    public enum Color {
        RED,
        WHITE
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object object){
        if(object == this){
            return true;
        }
        if(!(object instanceof Player)){
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