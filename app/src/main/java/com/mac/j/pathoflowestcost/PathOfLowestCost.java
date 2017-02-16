package com.mac.j.pathoflowestcost;


import java.util.ArrayList;

public class PathOfLowestCost {
    private int maxCost;
    private int maxRows;
    private int maxCols;
    private int minRows;
    private int minCols;

    public static final class Solution {
        public boolean isAllColumnsTraversed;
        public int totalCost;
        public Integer[] pathRows;

        public Solution() {

        }

        public Solution(boolean isAllColumnsTraversed, int totalCost, Integer[] pathRows) {
            this.isAllColumnsTraversed = isAllColumnsTraversed;
            this.totalCost = totalCost;
            this.pathRows = pathRows;
        }
    }

    public static class BadRowException extends RuntimeException {
        public BadRowException(String message) {
            super(message);
        }
    }

    public static class BadColException extends RuntimeException {
        public BadColException(String message) {
            super(message);
        }
    }


    public PathOfLowestCost(int maxCost, int minRows, int minCols, int maxRows, int maxCols) {
        this.maxCost = maxCost;
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        this.minRows = minRows;
        this.minCols = minCols;
    }

    // Dynamic programming
    // Solution is min{C(i,1) for i in 1..nRows}
    //   where C(i,j) = grid[i][j] + min{C(i-1, j+1, C(i,j+1), C(i+1,j)}
    //         C(i,nCols) = grid[i][nCols]
    //         C = lowest cost
    public Solution solve(Grid grid) {
        int numRows = grid.getNumRows();
        int numCols = grid.getNumCols();

        if (numRows > maxRows || numRows < minRows) {
            throw new BadRowException("Grid rows must be between 1 and 10");
        }

        if (numCols > maxCols || numCols < minCols) {
            throw new BadColException("Grid columns must be between 5 and 100");
        }

        Grid costGrid = new Grid(grid.getNumRows(), grid.getNumCols());

        Solution solution = new Solution();
        int mindex = 0;

        solution.isAllColumnsTraversed = true;

        for (int lastCol = numCols; lastCol > 1; lastCol--) {
            for (int i = 0; i < numRows; i++) {
                costGrid.set(i, lastCol-1, grid.get(i,lastCol-1));
            }

            for (int j = lastCol-2; j >= 0; j--) {
                for (int i = 0; i < numRows; i++) {
                    costGrid.set(i,j, grid.get(i,j) + minAt(costGrid,i,j));
                }
            }


            mindex = mindex(costGrid);

            if (costGrid.get(mindex,0) <= maxCost) {
                solution.totalCost = costGrid.get(mindex, 0);
                solution.pathRows = buildPath(costGrid, mindex, lastCol);

                return  solution;
            }

            solution.isAllColumnsTraversed = false;
        }

        return null;
    }

    public Integer[] buildPath(Grid grid, int mindex, int lastCol) {
        ArrayList<Integer> path = new ArrayList<>();

        path.add(mindex + 1);

        for (int j = 0; j < lastCol-1; j++) {
            mindex = mindexAt(grid, mindex, j);
            path.add(grid.getWrappedRow(mindex) + 1);
        }

        return path.toArray(new Integer[path.size()]);
    }

    private int mindex(Grid costGrid) {
        int min = costGrid.get(0,0);
        int index = 0;

        for (int i = 0; i < costGrid.getNumRows(); i++) {
            int x = costGrid.get(i,0);
            if (x < min) {
                min = x;
                index = i;
            }
        }

        return index;
    }


    public int minAt(Grid cost, int i, int j) {
        int x = cost.get(i-1,j+1);
        int y = cost.get(i,j+1);
        int z = cost.get(i+1,j+1);

        return Math.min(x, Math.min(y,z));
    }


    public int mindexAt(Grid cost, int i, int j) {
        int x = cost.get(i-1,j+1);
        int y = cost.get(i,j+1);
        int z = cost.get(i+1,j+1);
        int min = Math.min(x, Math.min(y,z));

        if (min == x) return i-1;
        if (min == y) return i;
        if (min == z) return i+1;

        return i;
    }

    public static void main(String[] args) {
        PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);

        printSolution(polc.solve(new Grid(
                "3 4 1 2 8 6\n" +
                        "6 1 8 2 7 4\n" +
                        "5 9 3 9 9 5\n" +
                        "8 4 1 3 2 6\n" +
                        "3 7 2 8 6 4\n"
        )));

        printSolution(polc.solve(new Grid(
                "3 4 1 2 8 6\n" +
                        "6 1 8 2 7 4\n" +
                        "5 9 3 9 9 5\n" +
                        "8 4 1 3 2 6\n" +
                        "3 7 2 1 2 3\n"
        )));

        printSolution(polc.solve(new Grid(
                "19 10 19 10 19\n" +
                        "21 23 20 19 12\n" +
                        "20 12 20 11 10\n"
        )));
    }

    public static void printSolution(Solution solution) {
        System.out.println(solution.isAllColumnsTraversed? "yes" : "no");
        System.out.println(solution.totalCost);

        for (int i : solution.pathRows) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println();
    }
}
