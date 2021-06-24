package com.webcheckers.model;

public class Player{
    private String name;

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