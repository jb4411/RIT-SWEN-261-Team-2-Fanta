package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece.Color;
import com.webcheckers.util.Message;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the {@link BoardView} component.
 *
 * @author Eric Landers esl7511@rit.edu
 */
public class BoardViewTest {
    BoardView CuT;
    Move move;
    //mocks
    Player red = mock(Player.class);
    Player white = mock(Player.class);
    

    //friendly objects
    Space startSpace;
    Space endSpace;
    
    @Test
    public void ctor_Test(){
        BoardView CuT = new BoardView(red, white);
        assertEquals(red.toString() + " : " + white.toString(), CuT.toString());
    }

    // not fully implemented need to fix
    @Test
    public void ctor_FlipTestTrue(){
        BoardView board = new BoardView(red, white);
        CuT = new BoardView(board, true);
        for(int row = 0; row<8; row++){
            for (int cell = 0; cell < 8; cell++) {
                assertEquals(board.getRow(8 - row - 1)[8 - cell - 1], CuT.getRow(row)[cell]);
            }

        }
    }

    //not fully implemented need to fix
    @Test
    public void ctor_FlipTestFalse(){
        BoardView board = new BoardView(red, white);
        CuT = new BoardView(board, false);
        for(int row = 0; row<8; row++){
            assertEquals(board.getRow(row), CuT.getRow(row));
        }
    }

    @Test
    public void checkMoveNullTest(){
        CuT = new BoardView(red, white);
        Position start = new Position(12, 12);
        Position end = new Position(15, 15);
        Move move = new Move(start, end);

        Message nullSpaceMessage = CuT.checkMove(move, red.getColor());

        assertEquals(BoardView.NULL_SPACE_MESSAGE, nullSpaceMessage);
    }



}
