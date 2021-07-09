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
    private Color testColor1 = Color.RED;
    private Piece testPiece = new Single(testColor);
    private Piece nullPiece = null;
    private int cellIdx = 4;


    @Test 
    public void ctor_valid(){
        // checks to make sure constructor main works for valid piece
        Space CuT = new Space(cellIdx, null, true);
        assertEquals(cellIdx + Boolean.toString(true), CuT.toString());
    }

    @Test 
    public void ctor_invalid(){
        // checks to make sure constructor main works for invalid piece
        Space CuT = new Space(cellIdx, null, false);
        assertEquals(cellIdx + Boolean.toString(false), CuT.toString());
    }

    @Test 
    public void ctor_TrueFlip(){
        // checks to make sure copy constructor flips the space when the flip boolean is true
        Space space = new Space(cellIdx, testPiece, true);
        Space CuT = new Space(space, true);
        // assert the original and copied pieces are equal
        assertEquals(CuT.getPiece(), space.getPiece());
        // assert the cellIdx are flipped and equal
        assertEquals(Integer.toString(BoardView.NUM_COLS - space.getCellIdx() - 1), Integer.toString(CuT.getCellIdx()));
    }

    @Test 
    public void ctor_FalseFlip(){
        // checks to make sure copy constructor doesn't flip when the flip boolean is false
        Space space = new Space(cellIdx, testPiece, true);
        Space CuT = new Space(space, false);
        // assert the original and copied pieces are equal
        assertEquals(CuT.getPiece(), space.getPiece());
        // assert the cellIdx are flipped and equal
        assertEquals(Integer.toString(space.getCellIdx()), Integer.toString(CuT.getCellIdx()));
    }

    @Test
    public void ctor_withNullPiece(){
        // checks to make sure copy constructor sets the piece equal to null when a space with a null piece is passed
        Space space = new Space(cellIdx, nullPiece, true);
        Space CuT = new Space(space, true);
        // assert the pieces are equal and null
        assertEquals(space.getPiece(), CuT.getPiece());
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

    @Test 
    public void testPieceParameter(){
        // invalid because there is a piece on the space (passed through constructor)
        final Space CuT = new Space(cellIdx, testPiece, true);
        // assert it is not valid
        assertFalse(CuT.isValid());
    }

    @Test
    public void setPieceTest(){
        // checks to make sure setPiece method is working properly
        Space CuT = new Space(cellIdx, testPiece, true);
        Piece piece = new Single(testColor1);
        // invoke
        CuT.setPiece(piece);
        // assert the two pieces are equal
        assertEquals(piece, CuT.getPiece());
    }

}
