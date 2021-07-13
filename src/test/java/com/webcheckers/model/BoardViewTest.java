package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece.Color;
import com.webcheckers.util.Message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the {@link BoardView} component.
 *
 * @author Eric Landers esl7511@rit.edu
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
@Tag("Model-tier")
public class BoardViewTest {
    /**
     * The component-under-test (CuT).
     *
     * <p>
     * The {@link Player} component is thoroughly tested so
     * we can use it safely as a "friendly" dependency.
     */
    private BoardView CuT;

    private Move move;
    //mocks
    private Player red = mock(Player.class);
    private Player white = mock(Player.class);

    //friendly objects
    private Space startSpace;
    private Space endSpace;
    private Position start;
    private Position end;
    private Space[][] board;


    /**
     * Setup new objects for each test.
     */
    @BeforeEach
    public void setup() {
        // create a unique CuT for each test
        CuT = new BoardView(red, white);
        board = CuT.getBoard();
    }

    /**
     * Test that the constructor works correctly.
     */
    @Test
    public void test_constructor() {
        CuT = new BoardView(red, white);
        assertEquals(red.toString() + " : " + white.toString(), CuT.toString());
    }

    /**
     * Test that the copy constructor works correctly when flip is true.
     */
    @Test
    public void test_constructorFlipTrue() {
        //TODO
        BoardView board = new BoardView(red, white);
        CuT = new BoardView(board, true);
        for(int row = 0; row<8; row++){
            for (int cell = 0; cell < 8; cell++) {
                Space expected = board.getRow(8 - row - 1)[8 - cell - 1];
                Space actual = CuT.getRow(row)[cell];
                assertEquals(expected.getPiece(), actual.getPiece());
                assertEquals(expected.isValid(), actual.isValid());
                assertEquals(8 - expected.getCellIdx() - 1, actual.getCellIdx());
            }

        }
    }

    /**
     * Test that the copy constructor works correctly when flip is false.
     */
    @Test
    public void test_constructorFlipFalse() {
        //TODO
        BoardView board = new BoardView(red, white);
        CuT = new BoardView(board, false);
        for(int row = 0; row<8; row++){
            for (int cell = 0; cell < 8; cell++) {
                Space expected = board.getRow(row)[cell];
                Space actual = CuT.getRow(row)[cell];
                assertEquals(expected.getPiece(), actual.getPiece());
                assertEquals(expected.isValid(), actual.isValid());
                assertEquals(expected.getCellIdx(), actual.getCellIdx());
            }

        }
    }

    /**
     * Test that checkMove() works correctly when the starting and ending space of the move are null.
     */
    @Test
    public void test_checkMoveNullSpace() {
        start = new Position(12, 12);
        end = new Position(15, 15);
        move = new Move(start, end);

        assertEquals(BoardView.NULL_SPACE_MESSAGE, CuT.checkMove(move, red.getColor()));
    }

    /**
     * Test that checkMove() works correctly when the piece being moved is null.
     */
    @Test
    public void test_checkMoveNullPiece() {
        start = new Position(4, 4);
        end = new Position(4, 4);
        move = new Move(start, end);

        assertEquals(BoardView.NULL_START_PIECE_MESSAGE, CuT.checkMove(move, red.getColor()));
    }

    /**
     * Test that checkMove() works correctly when the piece being moved is the opponents piece.
     */
    @Test
    public void test_checkMoveOpponentsPiece() {
        start = new Position(1, 4);
        end = new Position(0, 0);
        move = new Move(start, end);

        assertEquals(BoardView.OPPONENTS_PIECE_MESSAGE, CuT.checkMove(move, red.getColor()));
    }

    /**
     * Test that checkMove() works correctly when piece is moved being moved to an occupied space.
     */
    @Test
    public void test_checkMoveOccupiedEndSpace() {
        start = new Position(7, 0);
        end = new Position(6, 1);
        move = new Move(start, end);

        assertEquals(BoardView.OCCUPIED_END_SPACE_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying to make two simple moves.
     */
    @Test
    public void test_checkMoveDoubleSimpleMove() {
        start = new Position(5, 0);
        end = new Position(4, 1);
        move = new Move(start, end);
        CuT.setLastMoveType(BoardView.MoveType.SIMPLE);

        assertEquals(BoardView.DOUBLE_MOVE_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying to make a simple move after jumping.
     */
    @Test
    public void test_checkMoveSimpleMoveAfterJump() {
        start = new Position(5, 0);
        end = new Position(4, 1);
        move = new Move(start, end);
        CuT.setLastMoveType(BoardView.MoveType.JUMP);

        assertEquals(BoardView.MOVE_AFTER_JUMPING_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying to make a simple move when a jump is possible.
     */
    @Test
    public void test_checkMoveSimpleMoveWhenJumpExists() {
        start = new Position(5, 0);
        end = new Position(4, 1);
        move = new Move(start, end);
        board[4][3].setPiece(new Single(Color.WHITE));

        assertEquals(BoardView.FORCED_JUMP_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying to make a valid simple move.
     */
    @Test
    public void test_checkMoveValidSimpleMove() {
        start = new Position(5, 0);
        end = new Position(4, 1);
        move = new Move(start, end);

        assertEquals(BoardView.VALID_MOVE_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying to make an invalid simple move.
     */
    @Test
    public void test_checkMoveInvalidSimpleMove() {
        start = new Position(5, 0);
        end = new Position(6, 1);
        move = new Move(start, end);
        board[6][1].setPiece(null);

        assertEquals(BoardView.ILLEGAL_MOVE_MESSAGE, CuT.checkMove(move, Color.RED));
    }

    /**
     * Test that checkMove() works correctly when the player is trying jump after making a simple move.
     */
    @Test
    public void test_checkMoveJumpAfterSimpleMove() {
        start = new Position(5, 2);
        end = new Position(3, 4);
        move = new Move(start, end);
        board[4][3].setPiece(new Single(Color.WHITE));
        CuT.setLastMoveType(BoardView.MoveType.SIMPLE);

        assertEquals(BoardView.JUMP_AFTER_MOVING_MESSAGE, CuT.checkMove(move, Color.RED));
    }
}
