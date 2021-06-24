package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");

  private static final String CURRENT_USER_ATTR = "currentUser";
  private static final String CURRENT_PLAYERS_ATTR = "currentPlayers";
  private static final String NUM_PLAYERS_ATTR = "numPlayers";

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;
  private final PlayerLobby lobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, GameCenter gameCenter, PlayerLobby lobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.gameCenter = gameCenter;
    this.lobby = lobby;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");

    // display a user message in the Home page
    vm.put("message", WELCOME_MSG);
    String name = request.session().attribute("name");

    if(name != null) {
      vm.put(CURRENT_USER_ATTR, lobby.getPlayer(name));
      vm.put(CURRENT_PLAYERS_ATTR, lobby.getAllPlayers());
    } else {
      vm.put(CURRENT_USER_ATTR, null);
    }
    vm.put(NUM_PLAYERS_ATTR, lobby.getNumPlayers());

    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
