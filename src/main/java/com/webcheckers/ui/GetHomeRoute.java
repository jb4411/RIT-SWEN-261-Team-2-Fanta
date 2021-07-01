package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String IN_GAME_ERROR_MSG = "Sorry, %s is already in a game.";
  static final Message SAME_PLAYER_ERROR_MSG = Message.info("Sorry, you cannot start a game against yourself.");
  static final Message NULL_PLAYER_ERROR_MSG = Message.info("Sorry, the selected player does not exist.");

  private static final String CURRENT_USER_ATTR = "currentUser";
  private static final String CURRENT_PLAYERS_ATTR = "currentPlayers";
  private static final String NUM_PLAYERS_ATTR = "numPlayers";

  private final TemplateEngine templateEngine;
  private final GameCenter gameCenter;
  private final PlayerLobby lobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine the HTML template rendering engine
   * @param gameCenter the game center used to coordinate the state of the WebCheckers Application.
   * @param lobby the lobby used to hold all players that are currently logged in
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
    if(!request.queryParams().isEmpty() && name != null) {
      switch (request.queryParams("error")) {
        case "IN_GAME":
          vm.put("message", Message.info(String.format(IN_GAME_ERROR_MSG, request.queryParams("user"))));
          break;

        case "SAME_PLAYER":
          vm.put("message", SAME_PLAYER_ERROR_MSG);
          break;

        case "NULL_PLAYER":
          vm.put("message", NULL_PLAYER_ERROR_MSG);
          break;
      }
    }

    if(gameCenter.inGame(name)) {
      response.redirect(WebServer.GAME_URL);
      return null;
    }

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
