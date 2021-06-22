package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the Signin page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GetSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /signin} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetSigninRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSigninRoute is initialized.");
    }

    /**
     * Render the WebCheckers Signin page.
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSigninRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        // render the View
        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}
