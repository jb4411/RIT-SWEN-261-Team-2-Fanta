package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import spark.*;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

/**
 * UI controller to POST INFO or ERROR message for whether or not the opponent has submitted their turn.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class PostCheckTurnRoute implements Route{
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());
    private final GameCenter gameCenter;
    private final PlayerLobby playerLobby;

    public PostCheckTurnRoute(GameCenter gameCenter, PlayerLobby playerLobby){
        this.gameCenter = gameCenter;
        this.playerLobby = playerLobby;
        LOG.config("PostCheckTurnRoute is initialized.");
    }

    /**
     * checks to see if the opponent has submitted their turn.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return json message
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("PostCheckTurnRoute is invoked.");
        Gson gson = new Gson();

        String name = request.session().attribute("name");
        Player player = this.playerLobby.getPlayer(name);

        String json; 
        if(player.getColor() == gameCenter.getGame(player.getName()).getCurrentColor()){
            json = gson.toJson(Message.info("true"));
        }
        else{
            json = gson.toJson(Message.info("false"));
        }
        return json;
    }
}