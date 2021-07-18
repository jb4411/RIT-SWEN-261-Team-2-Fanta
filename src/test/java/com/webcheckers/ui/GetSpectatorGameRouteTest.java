package com.webcheckers.ui;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the {@link GetSpectatorGameRouteTest} component.
 *
 * @author dsk1354@rit.edu
 */
@Tag("UI-tier")
public class GetSpectatorGameRouteTest {
    /**
     * The component-under-test (CuT).
     * <p>
     * This is a stateless component so we only need one.
     * The {@link PlayerLobby} component is thoroughly tested so
     * we can use it safely as a "friendly" dependency.
     */
    private GetSpectatorGameRoute CuT;

    // friendly objects
    private PlayerLobby playerLobby;

    // mock objects
    private GameCenter gameCenter;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;


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
        playerLobby = new PlayerLobby();
        CuT = new GetSpectatorGameRoute(engine, gameCenter, playerLobby);

    }

    /**
     * Test that the view is correct through spectating.
     */
    @Test
    public void test_spectateGame(){
        playerLobby.addPlayer("player1");
        playerLobby.addPlayer("player2");
        playerLobby.addPlayer("spectator");
        Player player1 = playerLobby.getPlayer("player1");
        player1.setColor(Piece.Color.RED);
        Player player2 = playerLobby.getPlayer("player2");
        when(session.attribute("name")).thenReturn("spectator");
        Player spectator = playerLobby.getPlayer("spectator");
        gameCenter.createGame("player1","player2");
        CheckersGame game = gameCenter.getGame("player1");
        when(gameCenter.getGameByID(anyInt())).thenReturn(game);


        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the content passed into the render method
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(GetSpectatorGameRoute.CURRENT_USER_ATTR, spectator);
        testHelper.assertViewModelAttribute(GetSpectatorGameRoute.VIEW_MODE_ATTR, CheckersGame.Mode.SPECTATOR);
        testHelper.assertViewModelAttribute(GetSpectatorGameRoute.RED_PLAYER_ATTR, player1);
        testHelper.assertViewModelAttribute(GetSpectatorGameRoute.WHITE_PLAYER_ATTR, player2);
        testHelper.assertViewModelAttribute(GetSpectatorGameRoute.ACTIVE_COLOR_ATTR, "RED");
        verify(response).redirect(WebServer.GAME_URL);

        //   * test view name
        testHelper.assertViewName("game.ftl");
    }

    /**
     * Test that when given a null player, redirects to home page
     */
    @Test
    public void test_nullPlayer_error() {
        // Arrange the test scenario: null player
        when(request.session().attribute("name")).thenReturn(null);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the results
        verify(response).redirect(WebServer.HOME_URL);
    }

    /**
     * Test that when given empty params, redirects to home page
     */
    @Test
    public void test_emptyParams() {
        // Arrange the test scenario: empty params
        when(request.queryParams().isEmpty()).thenReturn(true);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the results
        verify(response).redirect(WebServer.HOME_URL);
    }
}