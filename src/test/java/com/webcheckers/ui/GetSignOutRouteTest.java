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
public class GetSignOutRouteTest {

    /**
     * Component under test, CuT
     */
    private GetSignOutRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;



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
        CuT = new GetSignOutRoute(engine);

    }








}
