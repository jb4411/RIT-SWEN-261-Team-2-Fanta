package com.webcheckers.application;

import com.webcheckers.model.CheckersGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link GameCenter} component.
 *
 * @author Dominic Kavanagh dsk1354@rit.edu
 */
@Tag("Application-tier")
public class GameCenterTest {

    private GameCenter CuT;
    private PlayerLobby playerLobby;
    private CheckersGame game;

    @BeforeEach
    public void testSetup() {
        // Setup CuT and create the game
        playerLobby = new PlayerLobby();
        CuT = new GameCenter(playerLobby);
        game = CuT.getGame("Player1");
    }
    /**
     * Test that you can construct a new GameCenter.
     */
    @Test
    public void test_create_center() {
        new GameCenter(playerLobby);
    }

    /**
     * Test that you can create a game.
     */
    @Test
    public void test_make_game() {
        final GameCenter CuT = new GameCenter(playerLobby);
        CuT.addPlayer(("Player1"));
        CuT.addPlayer(("Player2"));
        CuT.createGame("Player1","Player2");

        final CheckersGame game = CuT.getGame("Player1");

        // Analyze the results
        // 1) the returned game is real
        assertNotNull(game);
        // 2) the game is in mode PLAY
        assertEquals(CheckersGame.Mode.PLAY, game.getMode());

        // 3) The players have the correct colors.
        assertEquals("Player1",game.redPlayer().getName());
        assertEquals("Player2",game.whitePlayer().getName());
    }




}
