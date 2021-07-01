package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * UI controller to POST INFO or ERROR message for whether or not the opponent has submitted their turn.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class PostCheckTurnRoute implements Route{
    private static final Logger LOG = Logger.getLogger(PostCheckTurnRoute.class.getName());

    public PostCheckTurnRoute(){
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
    public Object handle(){
        return null;
    }
}