package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;

public class GetSpectatorStopWatchingRoute implements Route {

    /**
     * Stop spectating the current game and return the spectator to the Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   null (redirect to the home page)
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
