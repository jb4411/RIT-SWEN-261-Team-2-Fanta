package com.webcheckers.ui;

import spark.*;
import java.util.logging.Logger;

/**
 * UI controller to POST if the whole turn is valid.
 *
 * @author Dominic Kavanagh dsk1354@rit.edu
 */

public class PostSubmitTurnRoute implements Route{

    private static final Logger LOG = Logger.getLogger(PostSigninRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /submitTurn} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostSubmitTurnRoute(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
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
        return null;
    }
}
