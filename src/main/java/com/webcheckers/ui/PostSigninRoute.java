package com.webcheckers.ui;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The UI Controller to POST the Signin page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class PostSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(PostSigninRoute.class.getName());

    @Override
    public Object handle(Request request, Response response) throws Exception {
        LOG.finer("GetSigninRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        return null;
    }
}
