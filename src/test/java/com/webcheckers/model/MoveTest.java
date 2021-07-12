package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


/**
 * The unit test suite for the {@link Move} component.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
@Tag("Model-tier")
public class MoveTest {
    /**
     * The component-under-test (CuT).
     */
    private Move CuT;
    private Position start = new Position(1,3);
    private Position end = new Position(7,8);

    /**
     * Setup new objects for each test.
     */
    @BeforeEach
    public void setup() {
        CuT = new Move(start,end);
    }

    /**
     * Test that getStart() works correctly.
     */
    @Test
    public void getStartTest(){
        assertEquals(start, CuT.getStart());
    }

    /**
     * Test that getEnd() works correctly.
     */
    @Test
    public void getEndTest(){
        assertEquals(end,CuT.getEnd());
    }

    /**
     * Test that isSimpleMove() works correctly.
     */
    @Test
    public void isSimpleTest(){
        assertFalse(CuT.isSimpleMove());
        end = new Position(2,4);
        CuT = new Move(start, end);
        assertTrue(CuT.isSimpleMove());
    }

    /**
     * Test that isJump() works correctly.
     */
    @Test
    public void isJumpTest(){
        assertFalse(CuT.isJump());
        end = new Position(3,5);
        CuT = new Move(start, end);
        assertTrue(CuT.isJump());
    }

    /**
     * Test that inverse() works correctly.
     */
    @Test
    public void inverseTest(){
        Move inverse = new Move(start.inverse(), end.inverse());
        assertEquals(inverse, CuT.inverse());
    }
}
