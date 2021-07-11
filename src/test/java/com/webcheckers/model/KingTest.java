package com.webcheckers.model;

import org.junit.BeforeEach;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class KingTest {

    private King CuT;
    private Move move;
    private Position start;
    private Position end;

    private Request request;
    private Session session;

    @BeforeEach
    public void setup(){
        CuT = new King(Piece.Color.RED);
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

    }

    @Test
    public void cTorTest(){
        King test = new King(Piece.Color.RED);
        assertNotNull(test);
    }

    @Test
    public void copyTest(){
        King test = CuT.getCopy();
        assertNotNull(test);
        assertEquals(test, CuT);
    }

    @Test
    public void validMoveTest(){
        start = new Position(1,2);
        end = new Position(2,3);
        move = new Move(start, end);
        assertTrue(CuT.isMoveValid(move));
    }


}
