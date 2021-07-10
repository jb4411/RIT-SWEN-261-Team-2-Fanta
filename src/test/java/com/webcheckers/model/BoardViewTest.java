package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece.Color;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the {@link BoardView} component.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class BoardViewTest {
    Player red = mock(Player.class);
    Player white = mock(Player.class);
    
    @Test
    public void ctor_Test(){
        BoardView CuT = new BoardView(red, white);
        assertEquals(red.toString() + " : " + white.toString(), CuT.toString());
    }

    // not fully implemented need to fix
    @Test
    public void ctor_FlipTestTrue(){
        BoardView board = new BoardView(red, white);
        BoardView CuT = new BoardView(board, true);
        for(int row = 0; row<8; row++){
            assertEquals(board.getRow(8 - row - 1), CuT.getRow(row));
        }
    }

    //not fully implemented need to fix
    @Test
    public void ctor_FlipTestFalse(){
        BoardView board = new BoardView(red, white);
        BoardView CuT = new BoardView(board, false);
        for(int row = 0; row<8; row++){
            assertEquals(board.getRow(row), CuT.getRow(row));
        }
    }


}
