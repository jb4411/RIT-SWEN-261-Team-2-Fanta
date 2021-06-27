package com.webcheckers.board;

import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space> {
    private int index;
    private List<Space> spaces;

    public Row(int index, List<Space> spaces) {
        this.index = index;
        this.spaces = spaces;
    }

    /**
     * Getter for the index of the row withint he board.
     * @return The index of this row within the board.
     */
    public int getIndex(){
        return index;
    }

    /**
     * Not currently implemented
     * Creates a Java Iterator of the Spaces within a single row.
     * @return Java Iterator of the Spaces within a single row.
     */
    public Iterator<Space>iterator(){
        return spaces.iterator();
    }

}
