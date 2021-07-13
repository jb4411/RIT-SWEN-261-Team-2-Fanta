package com.webcheckers.ui;
import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;
import spark.utils.Assert;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the {@link PostCheckTurnRoute} component.
 *
 * @author dsk1354@rit.edu
 * @author esl7511@rit.edu
 */
@Tag("UI-tier")
public class PostCheckTurnRouteTest {

    /**
     * The component-under-test (CuT).
     */
    private PostCheckTurnRoute CuT;

    //friendly
    private PlayerLobby playerLobby;
    private Gson gson;

    //mock
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
        gson = new Gson();

        // create a unique CuT for each test
        playerLobby = new PlayerLobby();
        CuT = new PostCheckTurnRoute(gameCenter, playerLobby);

    }

    /**
     * Test that when given a player name and it is their turn, end result is true
     */
    @Test
    public void player_turn_true() throws Exception {
        // Arrange the test scenario: null player
        playerLobby.addPlayer("player");
        playerLobby.addPlayer("player2");
        Player player = playerLobby.getPlayer("player");
        Player player2 = playerLobby.getPlayer("player2");

        when(session.attribute("name")).thenReturn(player.getName());
        player.setColor(Piece.Color.RED);
        CheckersGame game = mock(CheckersGame.class);
        when(gameCenter.getGame(anyString())).thenReturn(game);
        when(game.getCurrentColor()).thenReturn(Piece.Color.RED);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the results
        assertEquals(gson.toJson(Message.info("true")), CuT.handle(request, response));


    }

    /**
     * Test that when given a player name and it is their turn, end result is true
     */
    @Test
    public void player_turn_false() throws Exception {
        // Arrange the test scenario: null player
        playerLobby.addPlayer("player");
        Player player = playerLobby.getPlayer("player");
        when(session.attribute("name")).thenReturn("player");
        player.setColor(Piece.Color.WHITE);
        CheckersGame game = mock(CheckersGame.class);
        when(gameCenter.getGame(anyString())).thenReturn(game);
        when(game.getCurrentColor()).thenReturn(Piece.Color.RED);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the results
        assertEquals(CuT.handle(request, response), gson.toJson(Message.info("false")));


    }

}
