package com.mac.j.pathoflowestcost;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathOfLowestCostTest {
    @Test
    public void example1Solution_isCorrect() throws Exception {
        PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);
        Grid grid = new Grid(
            "3 4 1 2 8 6\n"+
            "6 1 8 2 7 4\n" +
            "5 9 3 9 9 5\n" +
            "8 4 1 3 2 6\n" +
            "3 7 2 8 6 4"
        );

        PathOfLowestCost.Solution solution = polc.solve(grid);

        assertEquals(16, solution.totalCost);
        assertArrayEquals(new Integer[]{1,2,3,4,4,5}, solution.pathRows);
    }



    @Test
    public void example2Solution_isCorrect() throws Exception {
        PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);

        Grid grid = new Grid(
                  "3 4 1 2 8 6\n"
                + "6 1 8 2 7 4\n"
                + "5 9 3 9 9 5\n"
                + "8 4 1 3 2 6\n"
                + "3 7 2 1 2 3"
        );

        PathOfLowestCost.Solution solution = polc.solve(grid);

        assertEquals(11, solution.totalCost);
        assertArrayEquals(new Integer[]{1,2,1,5,4,5}, solution.pathRows);
    }

    @Test
    public void example3Solution_isCorrect() throws Exception {
        PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);
        Grid grid = new Grid(
            "19 10 19 10 19\n"
            + "21 23 20 19 12\n"
            + "20 12 20 11 10\n"
        );

        PathOfLowestCost.Solution solution = polc.solve(grid);

        assertEquals(48, solution.totalCost);
        assertArrayEquals(new Integer[]{1,1,1}, solution.pathRows);
    }

    @Test
    public void negativeSolution_isCorrect() throws Exception {
        PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);
        Grid grid = new Grid(
                "-19 -10 -19 -10 -19\n"
                + "-21 -23 -20 -19 -12\n"
                + "-20 -12 -20 -11 -10\n"
        );

        PathOfLowestCost.Solution solution = polc.solve(grid);

        assertEquals(-102, solution.totalCost);
        assertArrayEquals(new Integer[]{2,2,2,2,1}, solution.pathRows);
    }
}
