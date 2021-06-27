package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.util.Objects;
import java.util.logging.Logger;

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    private static final String RED_PLAYER_ATTR = "red";
    private static final String WHITE_PLAYER_ATTR = "white";

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /game} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     * @param gameCenter the game center used to coordinate the state of the WebCheckers Application.
     */
    public GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println("Red: " + request.queryParams(RED_PLAYER_ATTR));
        System.out.println("White: " + request.queryParams(WHITE_PLAYER_ATTR));


        return null;
    }
}
