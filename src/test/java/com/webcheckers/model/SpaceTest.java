package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.webcheckers.model.Piece.Color;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the {@link Space} component.
 *
 * @author Eric Landers esl7511@rit.edu
 */
@Tag("Model-Tier")
public class SpaceTest {
    private Color testColor = Color.WHITE;
    private Piece testPiece = new Single(testColor);
    private int cellIdx = 4;



    @Test void ctor_valid(){
        //checks to make sure constructor main works for valid piece
        Space CuT = new Space(cellIdx, null, true);
        assertEquals(cellIdx + Boolean.toString(true), CuT.toString());
    }

    @Test void ctor_invalid(){
        //checks to make sure constructor main works for invalid piece
        Space CuT = new Space(cellIdx, null, false);
        assertEquals(cellIdx + Boolean.toString(false), CuT.toString());
    }

    @Test void ctor_SpaceParamWithPiece(){
        Space space = new Space(cellIdx, testPiece, true);
        Space CuT = new Space(space, false);
        assertEquals(CuT.getPiece(), space.getPiece());
    }

    @Test
    public void isValidtest(){
        // valid because there is no piece and 'true' is passed through constructor
        final Space CuT = new Space(cellIdx, null, true);
        // assert it is valid
        assertTrue(CuT.isValid());
    }
    @Test
    public void falseValidParameter(){
        // invalid because of 'false' passed into constructor
        final Space CuT = new Space(cellIdx, null, false);
        // assert it is not valid
        assertFalse(CuT.isValid());
    }
    @Test void testPieceParameter(){
        // invalid because there is a piece on the space (passed through constructor)
        final Space CuT = new Space(cellIdx, testPiece, true);
        // assert it is not valid
        assertFalse(CuT.isValid());
    }
}
