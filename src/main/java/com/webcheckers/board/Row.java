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
     * Not currently implemented
     * @return The index of this row within the board.
     */
    public int getIndex(){
        return 0;
    }

    /**
     * Not currently implemented
     * Creates a Java Iterator of the Spaces within a single row.
     * @return Java Iterator of the Spaces within a single row.
     */
    public Iterator<Space>iterator(){
        return null;
    }

}
