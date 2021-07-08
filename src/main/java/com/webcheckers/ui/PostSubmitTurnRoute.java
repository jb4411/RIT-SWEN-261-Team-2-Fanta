package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import spark.*;
import java.util.logging.Logger;

/**
 * UI controller to POST if the whole turn is valid.
 *
 * @author Dominic Kavanagh dsk1354@rit.edu
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */

public class PostSubmitTurnRoute implements Route{

    private static final Logger LOG = Logger.getLogger(PostSigninRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /submitTurn} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostSubmitTurnRoute(TemplateEngine templateEngine, GameCenter gameCenter){
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        LOG.config("PostSubmitTurnRoute is initialized.");
    }

    /**
     * Check if turn is valid.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return json message
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String name = request.session().attribute("name");
        CheckersGame game = gameCenter.getGame(name);

        Gson gson = new Gson();
        return gson.toJson(game.submitTurn());
    }
}
