package com.webcheckers.ui;

import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the Signin page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GetSigninRoute {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private final TemplateEngine templateEngine;

    public GetSigninRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSigninRoute is initialized.");
    }
}
