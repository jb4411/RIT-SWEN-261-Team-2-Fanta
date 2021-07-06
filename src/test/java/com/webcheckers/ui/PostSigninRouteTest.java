package com.webcheckers.ui;

import com.webcheckers.ui.PostSigninRoute;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


/**
 * The unit test suite for the {@link PostSigninRoute} component.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class PostSigninRouteTest {
    /**
        * The component-under-test (CuT).
     *
     * <p>
     * This is a stateless component so we only need one.
     * The {@link PlayerLobby} component IS thoroughly tested so
     * we can use it safely as "friendly" dependencies.
     */

    private final String NOT_VALID_USERNAME = " asdf";
    private final String ALREADY_IN_USE = "player";
    
    //mock objects
    private PostSigninRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;

    //friendly objects
    private PlayerLobby lobby;

    /**
     *  Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // build the friendly PlayerLobby object
        lobby = new PlayerLobby();
        lobby.addPlayer(ALREADY_IN_USE);

        // create a unique CuT for each test
        CuT = new PostSigninRoute(engine, lobby);
    }

    @Test
    public void invalidUserName(){
        when(request.queryParams(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(NOT_VALID_USERNAME);

        final TemplateEngineTester testHelper = new TemplateEngineTester();

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(PostSigninRoute.SIGNIN_MESSAGE_ATTR, Message.error(PostSigninRoute.INVALID_NAME_MESSAGE));
    }

    @Test
    public void takenUserName(){
        when(request.queryParams(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(ALREADY_IN_USE);

        final TemplateEngineTester testHelper = new TemplateEngineTester();

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(PostSigninRoute.SIGNIN_MESSAGE_ATTR, Message.error(PostSigninRoute.DUPLICATE_NAME_MESSAGE));
        
    }

}
