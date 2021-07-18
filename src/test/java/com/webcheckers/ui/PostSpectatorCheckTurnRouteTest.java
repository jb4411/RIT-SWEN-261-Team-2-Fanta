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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the {@link PostSpectatorCheckTurnRoute} component.
 *
 * 
 * @author esl7511@rit.edu
 *
 */
@Tag("UI-Tier")
public class PostSpectatorCheckTurnRouteTest {
    //friendly objects
    private PlayerLobby playerLobby;
    private Gson gson;

    //mock objects
    private PostSpectatorCheckTurnRoute CuT;
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
        CuT = new PostSpectatorCheckTurnRoute(gameCenter, playerLobby);
    }

    @Test
    public void nullNameTest() throws Exception{
        //setup
        when(session.attribute("name")).thenReturn(null);
        final TemplateEngineTester tester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
        //invoke
        CuT.handle(request, response);
        //analyze the results
        // * verify it redirects to homepage when the players name is null
        verify(response).redirect(WebServer.HOME_URL);
    }

    @Test
    public void test_nullSpectatedGame() throws Exception{
        //setup
        when(session.attribute("name")).thenReturn("not null");
        final TemplateEngineTester tester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
        when(gameCenter.getGameBySpectator(any())).thenReturn(null);
        //invoke
        CuT.handle(request, response);
        //analyze the results
        // * verify it redirects to homepage when the players name is null
        verify(response).redirect(WebServer.HOME_URL);

    }
}
