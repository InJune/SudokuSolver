package src;

import java.util.Arrays;

/**
 * This class holds a 3x3 grid of values and also all other grids that could be in relation to this one.
 * For example: Grid 0 is in relation to Grids 1 and 2 because they all form a row and Grid 0 also has relations
 * to Grids 3 and 6 because they form a column.
 * <p/>
 * This class does NOT store the actual instances of the grid relationships but instead holds the grid positions.
 * <p/>
 * Created by Synchronised on 05/01/14.
 */
public class LinkedGrid {
    private final GridLocation[] LINKED_ROWS;
    private final GridLocation[] LINKED_COLUMNS;
    private GridLocation gridLocation;
    private Cell[][] cells;

    public LinkedGrid(GridLocation gridLocation, GridLocation... ids) {
        LINKED_ROWS = new GridLocation[2];
        LINKED_COLUMNS = new GridLocation[2];
        cells = new Cell[3][3];
        this.gridLocation = gridLocation;

        System.arraycopy(ids, 0, LINKED_ROWS, 0, 2);
        System.arraycopy(ids, 2, LINKED_COLUMNS, 0, 2);
        for (int row = 0, cellsLength = cells.length; row < cellsLength; row++) {
            for (int col = 0, cells1Length = cells[row].length; col < cells1Length; col++) {
                cells[row][col] = new Cell(this, row, col, 0);
            }
        }
    }

    // TEMPORARY METHOD. OVERRIDES CHECKING. DO NOT USE.
    public void setData(int[][] values) {
        for (int row = 0, cellsLength = cells.length; row < cellsLength; row++) {
            for (int col = 0, cells1Length = cells[row].length; col < cells1Length; col++) {
                cells[row][col] = new Cell(this, row, col, values[row][col]);
            }
        }
    }

    public GridLocation[] getLinkedRows() {
        return LINKED_ROWS;
    }

    public GridLocation[] getLinkedColumns() {
        return LINKED_COLUMNS;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell[] getRow(int row) {
        return cells[row];
    }

    public Cell[] getFullRow(int row) {
        int[] inOrder = new int[3];
        inOrder[0] = getGridLocation().getIndex();
        inOrder[1] = LINKED_ROWS[0].getIndex();
        inOrder[2] = LINKED_ROWS[1].getIndex();
        Arrays.sort(inOrder);

        Cell[] fullRow = new Cell[9];
        int offset = 0;
        for (int i : inOrder) {
            LinkedGrid linkedGrid = GridManager.getInstance().getLinkedGrid(GridLocation.getGridByIndex(i));
            for (Cell c : linkedGrid.getRow(row)) {
                fullRow[offset++] = c;
            }
        }

        return fullRow;
    }

    public Cell[] getColumn(int col) {
        Cell[] tempArray = new Cell[3];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = cells[i][col];
        }
        return tempArray;
    }

    public GridLocation getGridLocation() {
        return gridLocation;
    }
}
