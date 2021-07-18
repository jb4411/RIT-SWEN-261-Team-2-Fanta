package com.webcheckers.ui;

package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;

import
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static com.webcheckers.ui.GetSpectatorGameRoute.GAME_ID_ATTR;
import static org.mockito.Mockito.*;

@Tag("UI-tier")
public class GetSpectatorStopWatchingRouteTest {

    private GetSpectatorStopWatchingRoute CuT;
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private GameCenter gameCenter;



    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        gameCenter = mock(GameCenter.class);

        CuT = new GetSpectatorStopWatchingRoute(gameCenter);
    }


    @Test
    public void testRedirect(){
        CuT.handle(request, response);
        verify(response).redirect(WebServer.HOME_URL);
    }

    @Test
    public void test_Spectator_removed() {
        //when(session.attribute("name")).thenReturn("player");
        //when(gameCenter.inGame("player")).thenReturn(true);
        //int id = gameCenter.getGame("player").getGameID();
        int gameID = Integer.parseInt(request.queryParams(GAME_ID_ATTR));
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        gameCenter.addSpectator(gameID, new Player("spectator"));

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * redirect to the Game view
        verify(response).redirect(WebServer.HOME_URL);
        // make sure gameCenter.removeSpectator() was called
        verify(gameCenter).removeSpectator("spectator");
        // make sure the spectator was not logged out
        assertNotNull(gameCenter.getLobby().getPlayer("spectator"));
    }
}
