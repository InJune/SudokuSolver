package src;

/**
 * Cell stuff.
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
        if (value == 0) {
            this.value = 0;
            return false;
        }
        if (isValid(value)) {
            //System.out.println("Replaced: " + this.value + " with " + value);
            this.value = value;
            return true;
        }
        //System.out.println(value + " would not be valid in this cell.");
        return false;
    }

    /**
     * @return
     */
    public boolean isValid(int value) {
        // First check the containing grid if it already contains this value.
        Cell[][] containingGridCells = containingGrid.getCells();
        for (int row = 0; row < containingGridCells.length; row++) {
            for (int col = 0; col < containingGridCells[row].length; col++) {
                if (containingGridCells[row][col].value == value) {
                    //System.out.println("isValid failed because there was already a " + value + " in the grid.");
                    return false;
                }
            }
        }

        // Second, check the row. We do NOT have to check the containing grid because
        // we've already checked all of the cells and none of them are the same.
        for (int links = 0; links < containingGrid.getLinkedRows().length; links++) {
            LinkedGrid nextGrid = GridManager.getLinkedGrid(containingGrid.getLinkedRows()[links]);

            Cell[] nextGridCells = nextGrid.getRow(rowLoc);
            for (int col = 0; col < nextGridCells.length; col++) {
                if (nextGridCells[col].value == value) {
                    //System.out.println("isValid failed because there was already a " + value + " in the row.");
                    return false;
                }
            }
        }

        // Last, check the column. We do NOT have to check the containing grid because
        // we've already checked all of the cells and none of them are the same.
        for (int links = 0; links < containingGrid.getLinkedColumns().length; links++) {
            LinkedGrid nextGrid = GridManager.getLinkedGrid(containingGrid.getLinkedColumns()[links]);

            Cell[] nextGridCells = nextGrid.getColumn(colLoc);
            for (int row = 0; row < nextGridCells.length; row++) {
                if (nextGridCells[row].value == value) {
                    //System.out.println("isValid failed because there was already a " + value + " in the column.");
                    return false;
                }
            }
        }
        return true;
    }
}
