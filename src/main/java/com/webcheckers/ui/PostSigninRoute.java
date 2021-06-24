package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;

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
    private final PlayerLobby lobby;

    private static final String SIGNIN_MESSAGE_ATTR = "message";
    private static final String VIEW_NAME = "signin.ftl";

    private static final String INVALID_NAME_MESSAGE = "The name you entered is invalid. Please enter a name " +
            "containing only letters and spaces.";
    private static final String DUPLICATE_NAME_MESSAGE = "The name you entered is already in use. " +
            "Please enter a different name.";

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signin} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("PostSigninRoute is initialized.");
        this.lobby = lobby;
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
        ModelAndView mv;

        if(request.session().attribute(GetSigninRoute.PLAYER_NAME_ATTR) != null) {
            response.redirect(WebServer.HOME_URL);
            return null;
        }

        String name = request.queryParams(GetSigninRoute.PLAYER_NAME_ATTR);
        PlayerLobby.NameStatus result = this.lobby.addPlayer(name);
        switch (result) {
            case INVALID:
                vm.put(SIGNIN_MESSAGE_ATTR, Message.error(INVALID_NAME_MESSAGE));
                mv = new ModelAndView(vm, VIEW_NAME);
                return templateEngine.render(mv);

            case DUPLICATE:
                vm.put(SIGNIN_MESSAGE_ATTR, Message.error(DUPLICATE_NAME_MESSAGE));
                mv = new ModelAndView(vm, VIEW_NAME);
                return templateEngine.render(mv);

            case VALID:
                request.session().attribute(GetSigninRoute.PLAYER_NAME_ATTR, name);
                response.redirect(WebServer.HOME_URL);
                return null;
        }
        return null;
    }
}
