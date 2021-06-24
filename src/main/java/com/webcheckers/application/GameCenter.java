package com.webcheckers.application;

public class GameCenter {

    private PlayerLobby lobby;

    public GameCenter(PlayerLobby lobby){
        this.lobby = lobby;
    }
    public void addPlayer(String username, String sessionID){
        this.lobby.addPlayer(username, sessionID);
    }

}
