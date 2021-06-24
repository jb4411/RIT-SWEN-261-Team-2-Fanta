package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to POST the Signin page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class PostSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSigninRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostSigninRoute(TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("PostSigninRoute is initialized.");
    }


    /**
     * Render the WebCheckers Signin page.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("GetSigninRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        System.out.println(request.queryParams("name"));
        return null;
    }
}
