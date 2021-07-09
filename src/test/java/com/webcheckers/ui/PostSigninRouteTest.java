package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;


/**
 * The unit test suite for the {@link PostSigninRoute} component.
 *
 * @author Eric Landers esl7511@rit.edu
 */
@Tag("UI-tier")
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
    private final String VALID_NAME = "player01";
    private final String handleResult = null;
    private final String notNull = "notNull";
    
    //mock objects
    private PostSigninRoute CuT;
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;

    //friendly objects
    private PlayerLobby lobby;

    /**
     *  Setup mock objects for the tests
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        //build the friendly PlayerLobby object
        lobby = new PlayerLobby();
        lobby.addPlayer(ALREADY_IN_USE);

        //create a unique CuT for each test
        CuT = new PostSigninRoute(engine, lobby);
    }

    /**
     * Test that CuT show signin view and invalid message when invalid username in used.
     */
    @Test
    public void invalidUserName(){
        //when an invalid name is used
        when(request.queryParams(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(NOT_VALID_USERNAME);
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke the test
        CuT.handle(request, response);

        //analyze the results:
        // * model is a non-null map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        // * model contains View-Model data (i.e. correct 'INVALID_NAME_MESSAGE')
        testHelper.assertViewModelAttribute(PostSigninRoute.SIGNIN_MESSAGE_ATTR, Message.error(PostSigninRoute.INVALID_NAME_MESSAGE));
        // * tests for correct view name
        testHelper.assertViewName("signin.ftl");
        // * returns null
        assertEquals(handleResult, CuT.handle(request, response));
    }

    @Test
    public void takenUserName(){
        //when a duplicate or taken username is used
        when(request.queryParams(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(ALREADY_IN_USE);
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke the test
        CuT.handle(request, response);

        //analyze the results:
        // * model is a non-null map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        // * model contains ViewModel data (i.e. 'DUPLICATE_NAME_MESSAGE')
        testHelper.assertViewModelAttribute(PostSigninRoute.SIGNIN_MESSAGE_ATTR, Message.error(PostSigninRoute.DUPLICATE_NAME_MESSAGE));
        // * tests for correct view name
        testHelper.assertViewName("signin.ftl");
        // * returns null
        assertEquals(handleResult, CuT.handle(request, response));
        
    }

    @Test
    public void validUserName(){
        //when the username is valid
        when(request.queryParams(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(VALID_NAME);
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        //invoke the test
        CuT.handle(request, response);
        //analyze the results:
        // * redirect to the homepage
        verify(response).redirect(WebServer.HOME_URL);
        // * returns null
        assertEquals(handleResult, CuT.handle(request, response));

    }

    @Test
    public void notNull(){
        //when player name attribute isn't null
        when(request.session().attribute(eq(GetSigninRoute.PLAYER_NAME_ATTR))).thenReturn(notNull);
        //invoke the test
        CuT.handle(request, response);
        //analyze the results:
        // * redirects to the homepage
        verify(response).redirect(WebServer.HOME_URL);
        // * retruns null
        assertEquals(handleResult, CuT.handle(request, response));
    }
    

}
