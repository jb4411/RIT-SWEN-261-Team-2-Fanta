package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test suite for the {@link CheckersGame} component.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
@Tag("Model-tier")
public class CheckersGameTest {
    /**
     * The component-under-test (CuT).
     *
     * <p>
     * The {@link GameCenter}, {@link PlayerLobby}, and {@link Player} components are thoroughly tested so
     * we can use them safely as "friendly" dependencies.
     */
    private CheckersGame CuT;

    // friendly objects
    private Player red;
    private Player white;

    // mock objects


    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        red = new Player("red");
        white = new Player("white");

        // create a unique CuT for each test
        CuT = new CheckersGame(red, white, CheckersGame.Mode.PLAY);
    }

    /**
     * Test that getBoard() works correctly.
     */
    @Test
    public void test_getBoard() {
        BoardView expected = new BoardView(red, white);
        BoardView flipped = new BoardView(expected, true);

        assertEquals(expected.toString(), CuT.getBoard(false).toString());
        assertEquals(flipped.toString(), CuT.getBoard(true).toString());
    }

    /**
     * Test that getCurrentColor() works correctly.
     */
    @Test
    public void test_getCurrentColor() {
        assertEquals(Piece.Color.RED, CuT.getCurrentColor());
    }

    /**
     * Test that isGameOver() and endGame() work correctly.
     */
    @Test
    public void test_isGameOver_and_endGame() {
        assertFalse(CuT.isGameOver());
        CuT.endGame();
        assertTrue(CuT.isGameOver());
    }

    /**
     * Test that gameOverMessage() works correctly.
     */
    @Test
    public void test_gameOverMessage() {
        assertNull(CuT.gameOverMessage());
        CuT.endGame();
        assertEquals(CheckersGame.GAME_OVER_MESSAGE, CuT.gameOverMessage());
    }

    /**
     * Test that redPlayer() works correctly.
     */
    @Test
    public void test_redPlayer() {
        assertEquals(red, CuT.redPlayer());
    }

    /**
     * Test that whitePlayer() works correctly.
     */
    @Test
    public void test_whitePlayer() {
        assertEquals(white, CuT.whitePlayer());
    }

    /**
     * Test that getMode() works correctly.
     */
    @Test
    public void test_getMode() {
        // mode = PLAY
        CuT = new CheckersGame(red, white, CheckersGame.Mode.PLAY);
        assertEquals(CheckersGame.Mode.PLAY, CuT.getMode());

        // mode = SPECTATOR
        CuT = new CheckersGame(red, white, CheckersGame.Mode.SPECTATOR);
        assertEquals(CheckersGame.Mode.SPECTATOR, CuT.getMode());

        // mode = REPLAY
        CuT = new CheckersGame(red, white, CheckersGame.Mode.REPLAY);
        assertEquals(CheckersGame.Mode.REPLAY, CuT.getMode());
    }

    /**
     * Test that getGameID() works correctly.
     */
    @Test
    public void test_getGameID() {
        assertEquals(Objects.hash(red, white, CheckersGame.Mode.PLAY), CuT.getGameID());
    }

    /**
     * Test that testMove() works correctly.
     */
    @Test
    public void test_testMove() {
        //TODO
        assertEquals(Objects.hash(red, white, CheckersGame.Mode.PLAY), CuT.getGameID());
    }

    /**
     * Test that clearTurnMoves() works correctly.
     */
    @Test
    public void test_clearTurnMoves() {
        assertEquals(Objects.hash(red, white, CheckersGame.Mode.PLAY), CuT.getGameID());
    }
}
