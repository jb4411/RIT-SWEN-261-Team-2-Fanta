package com.webcheckers.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PlayerLobby} component.
 *
 * @author Jesse Burdick-Pless jb4411@g.rit.edu
 */
@Tag("Application-tier")
public class PlayerLobbyTest {

    private PlayerLobby CuT;

    @BeforeEach
    public void testSetup() {
        // Setup CuT
        CuT = new PlayerLobby();
    }

    /**
     * Test that you can construct a new PlayerLobby.
     */
    @Test
    public void test_create_service() {
        new PlayerLobby();
    }

    /**
     * Test the creation of a new PlayerLobby.
     */
    @Test
    public void test_create_lobby() {
        // Analyze results
        //  1) player set object exists
        assertNotNull(CuT.getAllPlayers());
        //  2) there are no players in the lobby
        assertEquals(CuT.getNumPlayers(), 0);
        assertEquals(CuT.toString(), "{Lobby, Number of Players: 0, Active Players: []}");
    }

    /**
     * Test adding a valid player.
     */
    @Test
    public void test_add_valid_player() {
        final PlayerLobby.NameStatus result = CuT.addPlayer("Valid");


    }
}
