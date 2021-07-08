package com.webcheckers.ui;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * The unit test suite for the {@link GetHomeRoute} component.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
@Tag("UI-tier")
public class GetHomeRouteTest {
    /**
     * The component-under-test (CuT).
     *
     * <p>
     * This is a stateless component so we only need one.
     * The {@link GameCenter}, {@link PlayerLobby}, and {@link Player} components are thoroughly tested so
     * we can use them safely as "friendly" dependencies.
     */
    private GetHomeRoute CuT;

    // friendly objects
    private PlayerLobby lobby;

    // mock objects
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private GameCenter gameCenter;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        gameCenter = mock(GameCenter.class);

        // create a unique CuT for each test
        lobby = new PlayerLobby();
        CuT = new GetHomeRoute(engine, gameCenter, lobby);
    }

    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYERS_ATTR, 0);
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_USER_ATTR, null);
        //   * test view name
        testHelper.assertViewName("home.ftl");
    }

    /**
     * Test that CuT shows the Home view with a list of active players when the player is logged in.
     */
    @Test
    public void old_session() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        Set<String> players = new HashSet<>();
        players.add("player");
        lobby.addPlayer("player");

        // Arrange the test scenario: There is an existing session with a player who is logged in
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(false);
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute(GetHomeRoute.NUM_PLAYERS_ATTR, 1);
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_USER_ATTR, new Player("player"));
        testHelper.assertViewModelAttribute(GetHomeRoute.CURRENT_PLAYERS_ATTR, players);
        //   * test view name
        testHelper.assertViewName("home.ftl");
    }

    /**
     * Test that CuT redirects to the Game view when the current player is logged in and already in a game
     */
    @Test
    public void game_session() {
        // Arrange the test scenario: There is an existing session with a player who is logged in and already in a game
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(true);

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * redirect to the Game view
        verify(response).redirect(WebServer.GAME_URL);
    }

    /**
     * Test that CuT shows the Home view with the in game error message when the player is logged in and the request URL
     * contains "IN_GAME" as an error parameter.
     */
    @Test
    public void in_game_error() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        // Arrange the test scenario: The player is logged in and the request
        //     * URL contains "IN_GAME" as an error parameter
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(false);
        Set<String> mockSet = new HashSet<>();
        mockSet.add("Not empty.");
        when(request.queryParams()).thenReturn(mockSet);
        when(request.queryParams("error")).thenReturn("IN_GAME");
        when(request.queryParams("user")).thenReturn("other");
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, Message.error(String.format(GetHomeRoute.IN_GAME_ERROR_MSG, "other")));
    }

    /**
     * Test that CuT shows the Home view with the same player error message when the player is logged in and the request
     * URL contains "SAME_PLAYER" as an error parameter.
     */
    @Test
    public void same_player_error() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        // Arrange the test scenario: The player is logged in and the request
        //     * URL contains "SAME_PLAYER" as an error parameter
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(false);
        Set<String> mockSet = new HashSet<>();
        mockSet.add("Not empty.");
        when(request.queryParams()).thenReturn(mockSet);
        when(request.queryParams("error")).thenReturn("SAME_PLAYER");
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.SAME_PLAYER_ERROR_MSG);
    }

    /**
     * Test that CuT shows the Home view with the null player error message when the player is logged in and the request
     * URL contains "NULL_PLAYER" as an error parameter.
     */
    @Test
    public void null_player_error() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        // Arrange the test scenario: The player is logged in and the request
        //     * URL contains "NULL_PLAYER" as an error parameter
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(false);
        Set<String> mockSet = new HashSet<>();
        mockSet.add("Not empty.");
        when(request.queryParams()).thenReturn(mockSet);
        when(request.queryParams("error")).thenReturn("NULL_PLAYER");
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.NULL_PLAYER_ERROR_MSG);
    }

    /**
     * Test that CuT shows the Home view when the error query parameter is invalid.
     */
    @Test
    public void invalid_error_query_parameter() {
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        // Arrange the test scenario: The player is logged in and the request
        //     * URL contains "NULL_PLAYER" as an error parameter
        when(session.attribute("name")).thenReturn("player");
        when(gameCenter.inGame("player")).thenReturn(false);
        Set<String> mockSet = new HashSet<>();
        mockSet.add("Not empty.");
        when(request.queryParams()).thenReturn(mockSet);
        when(request.queryParams("error")).thenReturn("bad parameter");
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_ATTR, GetHomeRoute.WELCOME_MSG);
    }

}
