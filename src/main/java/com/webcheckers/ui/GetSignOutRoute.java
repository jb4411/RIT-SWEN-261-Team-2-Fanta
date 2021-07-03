package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class GetSignOutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private final TemplateEngine templateEngine;

    public GetSignOutRoute(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSignOutRoute is initialized");
    }

    /**
     * Signs the player out
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("GetSignOutRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        return null;
    }
}
