package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.board.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link Player} component
 *
 * @author <a href='mailto:jlz6146@rit.edu'>Jess Zhao</a>
 */
@Tag("Model-tier")
public class PlayerTest {

    private String NAME = "Test Name";

    /**
     * Component under testing
     */
    private Player CuT = new Player(NAME);

    /**
     * Ensures getName() method works
     */
    @Test
    public void getNameTest(){
        assertEquals(NAME, CuT.getName());
    }

    /**
     * Ensures that the player's color is set correctly, and returned correctly
     * by setColor() and getColor() methods
     */
    @Test
    public void colorTest(){
        CuT.setColor(Piece.Color.RED);
        assertEquals(Piece.Color.RED, CuT.getColor());
    }

    /**
     * Ensures equals() method works properly in discerning two players
     */
    @Test
    public void equalsTest(){
        Player SAME_NAME = new Player(NAME);
        Player DIFF_NAME = new Player("Nope");

        boolean SAME = SAME_NAME.equals(CuT);
        boolean DIFF = DIFF_NAME.equals(CuT);

        assertTrue(SAME);
        assertFalse(DIFF);
    }

    /**
     * Ensures hashCode() method works properly in generating hashCode for a player
     */
    @Test
    public void hashcodeTest(){
        assertEquals(NAME.hashCode(), CuT.hashCode());
    }

}
