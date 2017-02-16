package com.mac.j.pathoflowestcost;


public class Grid {
    private int[][] grid;
    private int nRows;
    private int nCols;

    public static class BadGridException extends RuntimeException {
        public BadGridException(String message) {
            super(message);
        }
    }

    Grid(int[][] grid) {
        this.grid = grid;
    }

    Grid(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;

        this.grid = new int[nRows][nCols];
    }

    Grid(String gridString) {
        try {
            String[] lines = gridString.split("\\n");
            nRows = lines.length;
            nCols = lines[0].split(" ").length;
            grid = new int[nRows][nCols];

            for (int i = 0; i < nRows; i++) {
                String[] line = lines[i].split(" ");
                for (int j = 0; j < nCols; j++) {
                    grid[i][j] = Integer.parseInt(line[j]);
                }
            }
        } catch (Exception e) {
            throw new BadGridException("Grid not formatted correctly");
        }
    }

    public int get(int i, int j) {
        return grid[getWrappedRow(i)][getWrappedCol(j)];
    }

    public void set(int i, int j, int value) {
        grid[getWrappedRow(i)][getWrappedCol(j)] = value;
    }

    public int getWrappedRow(int i) {
        return (i % nRows + nRows) % nRows;
    }

    public int getWrappedCol(int j) {
        return (j % nCols + nCols) % nCols;
    }

    public int getNumRows() {
        return nRows;
    }

    public int getNumCols() {
        return nCols;
    }
}
