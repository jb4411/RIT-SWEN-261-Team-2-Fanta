package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

/**
 * Unit test for {@link GetSignInRoute} component
 *
 *  @author <a href='mailto:jlz6146@rit.edu'>Jess Zhao</a>
 */
@Tag("UI-tier")
public class GetSigninRouteTest {

    /**
     * Component under test, CuT
     */
    private GetSigninRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    static final String PLAYER_USED_NAME = "realPlayer";


    /**
     * Initialize necessary components as mock classes for each test
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        CuT = new GetSigninRoute(engine);

    }

    /**
     * Test that the sign-in page initializes correctly
     */
    @Test
    public void not_null(){
        final TemplateEngineTester tester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());

        CuT.handle(request, response);

        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();

        tester.assertViewName("signin.ftl");
    }

    /**
     * Verifies that attempting to sign-in while already signed-in returns to the home page
     */
    @Test
    public void previously_signed_in(){
        when(request.session().attribute(GetSigninRoute.PLAYER_NAME_ATTR)).thenReturn(PLAYER_USED_NAME);

        CuT.handle(request, response);

        verify(response).redirect(WebServer.HOME_URL);

    }


    /**
     * Test that CuT does not take a null engine parameter in its construction
     */
    @Test
    public void engine_render_fail(){
        when(engine.render(any(ModelAndView.class))).thenReturn(null);
        try {
            CuT = new GetSigninRoute(null);
            fail("Null render, cannot create route.");
        } catch (NullPointerException noArg){
            //expected
        }
    }

    /**
     * Test that CuT does not accept a null name
     */
    @Test
    public void null_name(){
        when(request.session().attribute(GetSigninRoute.PLAYER_NAME_ATTR)).thenReturn(null);
        try {
            CuT.handle(request, response);
            fail("Null attribute cannot be handled.");
        } catch (IllegalArgumentException noArg){

        }
    }






}
