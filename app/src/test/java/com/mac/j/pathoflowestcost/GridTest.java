package com.mac.j.pathoflowestcost;

import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void grid_works() throws Exception {
        Grid grid = new Grid(
                "3 4 1 2 8 6\n"+
                "6 1 8 2 7 4\n" +
                "5 9 3 9 9 5\n" +
                "8 4 1 3 2 6\n" +
                "3 7 2 8 6 4"
        );

        assertEquals(3,grid.get(0,0));
        assertEquals(1,grid.get(1,1));
        assertEquals(3,grid.get(2,2));
        assertEquals(3,grid.get(3,3));
        assertEquals(6,grid.get(4,4));
        assertEquals(7,grid.get(-1,1));
        assertEquals(4,grid.get(5,1));
    }
}
