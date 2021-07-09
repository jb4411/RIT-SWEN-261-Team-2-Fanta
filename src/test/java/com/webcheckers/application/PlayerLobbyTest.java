package com.webcheckers.application;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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

    /**
     * The component-under-test (CuT).
     */
    private PlayerLobby CuT;

    private Set<String> players;
    private int numPlayers;
    private String lobbyString;

    @BeforeEach
    public void testSetup() {
        // Setup CuT
        CuT = new PlayerLobby();
        players = new HashSet<>(CuT.getAllPlayers());
        numPlayers = CuT.getNumPlayers();
        lobbyString = CuT.toString();
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
        Set<String> playerSet = new HashSet<>();

        // Analyze results
        //  1) player set object exists
        assertNotNull(CuT.getAllPlayers());
        //  2) there are no players in the lobby
        assertEquals(0, CuT.getNumPlayers());
        assertEquals(String.format(PlayerLobby.LOBBY_STRING_FORMAT, 0, ""), CuT.toString());
        assertEquals(playerSet, CuT.getAllPlayers());
    }

    /**
     * Test adding a valid player.
     */
    @Test
    public void test_add_valid_player() {
        // Perform action
        final PlayerLobby.NameStatus result = CuT.addPlayer("Valid");
        players.add("Valid");

        // Analyze results
        assertNotNull(result);
        assertEquals(PlayerLobby.NameStatus.VALID, result);
        // validate that the player has been added
        assertEquals(1, CuT.getNumPlayers());
        assertEquals(String.format(PlayerLobby.LOBBY_STRING_FORMAT, 1, "Valid"), CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test adding a null player.
     */
    @Test
    public void test_add_null_player() {
        // Perform action
        final PlayerLobby.NameStatus result = CuT.addPlayer(null);

        // Analyze results
        assertNotNull(result);
        assertEquals(PlayerLobby.NameStatus.INVALID, result);
        // attempting to add a null player does not change the active players
        assertEquals(numPlayers, CuT.getNumPlayers());
        assertEquals(lobbyString, CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test adding an invalid player.
     */
    @Test
    public void test_add_invalid_player() {
        // Perform action
        final PlayerLobby.NameStatus result = CuT.addPlayer("");

        // Analyze results
        assertNotNull(result);
        assertEquals(PlayerLobby.NameStatus.INVALID, result);
        // attempting to add an invalid player does not change the active players
        assertEquals(numPlayers, CuT.getNumPlayers());
        assertEquals(lobbyString, CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test adding a duplicate player.
     */
    @Test
    public void test_add_duplicate_player() {
        // Perform action
        CuT.addPlayer("Duplicate");
        final PlayerLobby.NameStatus result = CuT.addPlayer("Duplicate");
        players.add("Duplicate");

        // Analyze results
        assertNotNull(result);
        assertEquals(PlayerLobby.NameStatus.DUPLICATE, result);
        // attempting to add a duplicate player does not change the active players
        assertEquals(1, CuT.getNumPlayers());
        assertEquals(String.format(PlayerLobby.LOBBY_STRING_FORMAT, 1, "Duplicate"), CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test adding multiple players.
     */
    @Test
    public void test_add_multiple_players() {
        // Perform action
        final PlayerLobby.NameStatus result1 = CuT.addPlayer("player1");
        final PlayerLobby.NameStatus result2 = CuT.addPlayer("player2");
        players.add("player1");
        players.add("player2");

        // Analyze results
        assertNotNull(result1);
        assertEquals(PlayerLobby.NameStatus.VALID, result1);
        assertNotNull(result2);
        assertEquals(PlayerLobby.NameStatus.VALID, result2);
        // validate that both players have been added
        assertEquals(2, CuT.getNumPlayers());
        assertEquals(String.format(PlayerLobby.LOBBY_STRING_FORMAT, 2, "player1, player2"), CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test removing a player.
     */
    @Test
    public void test_remove_player() {
        CuT.addPlayer("player");
        CuT.removePlayer("player");
        assertEquals(numPlayers, CuT.getNumPlayers());
        assertEquals(lobbyString, CuT.toString());
        assertEquals(players, CuT.getAllPlayers());
    }

    /**
     * Test getting a player.
     */
    @Test
    public void test_get_player() {
        Player player = new Player("player");
        CuT.addPlayer("player");
        assertEquals(player, CuT.getPlayer("player"));
    }
}
