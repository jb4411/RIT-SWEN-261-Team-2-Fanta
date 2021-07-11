package com.webcheckers.model;

import org.junit.BeforeEach;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class MoveTest {

    private Move CuT;
    private Position start = new Position(1,3);
    private Position end = new Position(7,8);

    private Request request;
    private Session session;

    @Setup
    private void setup(){
        CuT = new Move(start,end);
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
    }

    @Test
    public void getStartTest(){
        assertEquals(start, CuT.getStart());
    }

    @Test
    public void getEndTest(){
        assertEquals(end,CuT.getEnd());
    }

    @Test
    public void isSimpleTest(){
        assertFalse(CuT.isSimpleMove());
        end = new Position(2,4);
        assertTrue(CuT.isSimpleMove());
    }

    @Test
    public void isJumpTest(){
        assertFalse(CuT.isJump());
        end = new Position(3,5);
        assertTrue(CuT.isJump());
    }

    @Test
    public void inverseTest(){
        Move inverse = new Move(start.inverse(), end.inverse());
        assertEquals(inverse, CuT.inverse());
    }

}
