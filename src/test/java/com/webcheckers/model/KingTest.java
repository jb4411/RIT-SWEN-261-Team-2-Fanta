package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("Model-tier")
public class KingTest {

    private King CuT;
    private Move move;
    private Position start = new Position(1,2);
    private Position end = new Position(2,3);
    private BoardView board;

    private Request request;
    private Session session;

    @BeforeEach
    public void setup(){
        CuT = new King(Piece.Color.RED);
        request = mock(Request.class);
        session = mock(Session.class);
        board = mock(Boardview.class);
        when(request.session()).thenReturn(session);

    }

    @Test
    public void cTorTest(){
        King test = new King(Piece.Color.RED);
        assertNotNull(test);
    }

    @Test
    public void copyTest(){
        King test = (King) CuT.getCopy();
        assertNotNull(test);
        assertEquals(test, CuT);
    }

    @Test
    public void validMoveTest(){

        move = new Move(start, end);
        assertTrue(CuT.isMoveValid(move));
    }
/**
    @Test
    public void validJumpTest(){

    }

    @Test
    public void hasJumpTest(){
        board = new BoardView(new Player("A"), new Player("B"));


    }
*/
}
