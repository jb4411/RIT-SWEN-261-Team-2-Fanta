package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.board.Piece;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static com.webcheckers.ui.PostValidateMoveRoute.GAME_ID_ATTR;
import static com.webcheckers.ui.WebServer.HOME_URL;

/**
 * The UI Controller to GET the Game page.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    static final String RED_PLAYER_NAME_ATTR = "red";
    static final String WHITE_PLAYER_NAME_ATTR = "white";
    static final String CURRENT_USER_ATTR = "currentUser";
    static final String VIEW_MODE_ATTR = "viewMode";
    static final String MODE_OPTIONS_JSON_ATTR = "modeOptionsAsJSON";
    static final String RED_PLAYER_ATTR = "redPlayer";
    static final String WHITE_PLAYER_ATTR = "whitePlayer";
    static final String ACTIVE_COLOR_ATTR = "activeColor";
    static final String BOARD_ATTR = "board";
    static final String MESSAGE_ATTR = "message";

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
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        String name = request.session().attribute("name");
        if (name == null) {
            response.redirect(WebServer.HOME_URL);
            return null;
        } else if(request.queryParams().isEmpty() && !gameCenter.inGame(name)) {
            response.redirect(WebServer.HOME_URL);
            return null;
        } else if(!request.queryParams().isEmpty() && !gameCenter.inGame(name)) {
            String redPlayerName = request.queryParams(RED_PLAYER_NAME_ATTR);
            String whitePlayerName = request.queryParams(WHITE_PLAYER_NAME_ATTR);
            LOG.finer(redPlayerName + " is trying to start a game with " + whitePlayerName);

            switch (gameCenter.createGame(redPlayerName, whitePlayerName)) {
                case IN_GAME:
                    response.redirect(WebServer.HOME_URL + "?error=" + GameCenter.GameStatus.IN_GAME + "&user="
                            + whitePlayerName);
                    return null;
                case SAME_PLAYER:
                    response.redirect(WebServer.HOME_URL + "?error=" + GameCenter.GameStatus.SAME_PLAYER);
                    return null;
                case NULL_PLAYER:
                    response.redirect(WebServer.HOME_URL + "?error=" + GameCenter.GameStatus.NULL_PLAYER);
                    return null;
                case CREATED:
                    response.redirect(WebServer.GAME_URL);
                    break;
            }
        }

        Player opponent = gameCenter.getOpponent(name);
        if(opponent == null) {
          response.redirect(HOME_URL);
          return null;
        }

        Map<String, Object> vm = new HashMap<>();
        CheckersGame game = gameCenter.getGame(name);
        PlayerLobby lobby = gameCenter.getLobby();
        Player current = lobby.getPlayer(name);

        vm.put(CURRENT_USER_ATTR, current);
        vm.put(VIEW_MODE_ATTR, game.getMode());
        //vm.put(MODE_OPTIONS_JSON__ATTR, ) //not used in sprint 1
        vm.put(RED_PLAYER_ATTR, game.redPlayer());
        vm.put(WHITE_PLAYER_ATTR, game.whitePlayer());
        vm.put(ACTIVE_COLOR_ATTR, current.getColor());
        if(current.getColor() == Piece.Color.RED) {
            vm.put(BOARD_ATTR, game.getBoard(false));
        } else {
            vm.put(BOARD_ATTR, game.getBoard(true));
        }
        vm.put("title", name + " VS " + opponent.getName());

        // render the View
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
