package com.webcheckers.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

/**
 * The unit test suite for the {@link GetGameRoute} component.
 *
 * @author dsk1354@rit.edu
 */
@Tag("UI-tier")
public class GetGameRouteTest {

    /**
     * The component-under-test (CuT).
     */
    private GetGameRoute CuT;

    private PlayerLobby playerLobby;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
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

        // create a unique CuT for each test
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter(playerLobby);
        CuT = new GetGameRoute(engine, gameCenter);

    }

    /**
     * Test that the Game view will create a new game if none exists.
     */
    @Test
    public void new_game()throws Exception{
        // Arrange the test scenario: the playerLobby has two players willing to play.
        playerLobby.addPlayer("player1");
        playerLobby.addPlayer("player2");

        when(session.attribute("name")).thenReturn("player1");


        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the content passed into the render method
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(GetGameRoute.CURRENT_USER_ATTR, playerLobby.getPlayer("player1"));
        testHelper.assertViewModelAttribute(GetGameRoute.VIEW_MODE_ATTR, CheckersGame.Mode.PLAY);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, playerLobby.getPlayer("player1"));
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, playerLobby.getPlayer("player2"));
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Player.Color.RED);

        //   * test view name
        testHelper.assertViewName("game.ftl");

    }

}
