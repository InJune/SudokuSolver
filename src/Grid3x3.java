package src;

/**
 * Stores a 3x3 grid. There is 9 3x3 grids per game.
 *
 * Created by Synchronised on 04/01/14.
 */
public class Grid3x3 {
    // [0->[0], [1], [2]]
    // [1->[0], [1], [2]]
    // [2->[0], [1], [2]]
    private Cell[][] cells;

    public Grid3x3() {
        cells = new Cell[3][3];

        // Init all cells to 0.
        for(int i = 0; i < cells.length; i++) {
            for(int x = 0; x < cells[x].length; x++) {
                cells[i][x] = new Cell(0);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell[] getRow(int row) {
        return cells[row];
    }
}
