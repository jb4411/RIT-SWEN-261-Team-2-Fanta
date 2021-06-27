package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.logging.Logger;

/**
 * The UI Controller to POST validating a move.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class PostValidateMoveRoute implements Route  {
    private static final Logger LOG = Logger.getLogger(PostSigninRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /validateMove} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostValidateMoveRoute(TemplateEngine templateEngine) {
        LOG.finer("PostValidateMoveRoute is invoked.");
        this.templateEngine = templateEngine;
    }

    /**
     * Check if the move made is valid.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the game page
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
