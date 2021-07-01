package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * UI controller to POST INFO or ERROR message for removing last valid move.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class PostBackupMoveRoute implements Route{
    private static final Logger LOG = Logger.getLogger(PostBackupMoveRoute.class.getName());

    public PostBackupMoveRoute(){
        LOG.config("PostBackupMoveRoute is initialized.");
    }

    /**
     * Removes last valid move.
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