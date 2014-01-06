package src;

/**
 * Stores information about specific cells such as value and location.
 * <p/>
 * Created by Synchronised on 05/01/14.
 */
public class Cell {
    private LinkedGrid containingGrid;
    private int value;

    private int rowLoc;
    private int colLoc;

    private boolean fixed;

    public Cell(LinkedGrid containingGrid, int rowLoc, int colLoc, int value) {
        this.containingGrid = containingGrid;
        this.value = value;
        this.rowLoc = rowLoc;
        this.colLoc = colLoc;
        this.fixed = true;

        if (value == 0) {
            fixed = false;
        }
    }

    public boolean isFixed() {
        return fixed;
    }

    public int getValue() {
        return value;
    }

    public boolean setValue(int value) {
        if (isValid(value) || value == 0) {
            this.value = value;
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    public boolean isValid(int value) {
        // First check the containing grid if it already contains this value.
        Cell[][] containingGridCells = containingGrid.getCells();
        for (int row = 0, rlen = containingGridCells.length; row < rlen; row++) {
            for (int col = 0, clen = containingGridCells[row].length; col < clen; col++) {
                if (containingGridCells[row][col].value == value) {
                    return false;
                }
            }
        }

        // Second, check the row. We do NOT have to check the containing grid because
        // we've already checked all of the cells and none of them are the same.
        for (int links = 0; links < containingGrid.getLinkedRows().length; links++) {
            LinkedGrid nextGrid = GridManager.getInstance().getLinkedGrid(containingGrid.getLinkedRows()[links]);

            Cell[] nextGridCells = nextGrid.getRow(rowLoc);
            for (int col = 0, clen = nextGridCells.length; col < clen; col++) {
                if (nextGridCells[col].value == value) {
                    return false;
                }
            }
        }

        // Last, check the column. We do NOT have to check the containing grid because
        // we've already checked all of the cells and none of them are the same.
        for (int links = 0; links < containingGrid.getLinkedColumns().length; links++) {
            LinkedGrid nextGrid = GridManager.getInstance().getLinkedGrid(containingGrid.getLinkedColumns()[links]);

            Cell[] nextGridCells = nextGrid.getColumn(colLoc);
            for (int row = 0, rlen = nextGridCells.length; row < rlen; row++) {
                if (nextGridCells[row].value == value) {
                    return false;
                }
            }
        }
        return true;
    }
}
