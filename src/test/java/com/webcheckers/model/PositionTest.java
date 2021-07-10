package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link Position} component
 *
 * @author Jess Zhao jlz6146@rit.edu
 */
@Tag("Model-tier")
public class PositionTest {


    private Position CuT;
    private Request request;
    private Session session;

    @BeforeEach
    public void setup(){
        CuT = new Position(0, 1);
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);

    }

    /**
     * Test for getRow() function
     */
    @Test
    public void getRowTest(){
        assertEquals(0, CuT.getRow());
    }

    @Test
    public void getCellTest(){
        assertEquals(1, CuT.getCell());
    }

    /**
     * Ensures inverse() method works properly in creating a new inverted position
     */
    @Test
    public void inverseTest(){
        Position inverse = new Position(BoardView.NUM_ROWS-CuT.getRow()-1, BoardView.NUM_COLS-CuT.getCell()-1);
        assertEquals(inverse, CuT.inverse());
    }

    /**
     * Ensures hashCode() method works properly in generating hashCode for a player
     */
    @Test
    public void isValidTest(){
        Position invalidRow = new Position(9, 1);
        Position invalidCell = new Position(1, 9);

        assertFalse(invalidRow.isValid());
        assertFalse(invalidCell.isValid());
        assertTrue(CuT.isValid());
    }


}
