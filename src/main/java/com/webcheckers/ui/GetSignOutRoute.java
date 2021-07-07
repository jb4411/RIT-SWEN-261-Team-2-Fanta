package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * The UI Controller to GET the SignOut page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
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
        Session session = request.session();
        LOG.finer("GetSignOutRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();

        Player player = session.attribute(GetHomeRoute.CURRENT_USER_ATTR);
        if (player.getName() != null){

            //need to remove player from the game somehow : maybe add a 'removePlayer method to gameCenter?

            response.redirect(WebServer.HOME_URL);
            return null;

        }
        /*else{
            return templateEngine.render(new ModelAndView(vm , ""));
        }*/
        return null;
    }
}
