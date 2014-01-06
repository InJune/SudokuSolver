package src;

/**
 * Created by Synchronised on 05/01/14.
 */
public class SudokuBoard {
    private final int[][][] TEMPORARY_GRID = {
            {
                    {0, 0, 9},
                    {0, 0, 0},
                    {4, 8, 0}
            },
            {
                    {0, 1, 4},
                    {0, 0, 0},
                    {9, 0, 0},
            },
            {
                    {8, 0, 0},
                    {4, 0, 0},
                    {0, 0, 3}
            },
            {
                    {5, 0, 0},
                    {6, 0, 0},
                    {0, 0, 1}
            },
            {
                    {0, 7, 0},
                    {1, 0, 5},
                    {0, 4, 0}
            },
            {
                    {1, 0, 0},
                    {0, 0, 2},
                    {0, 0, 7}
            },
            {
                    {1, 0, 0},
                    {0, 0, 8},
                    {0, 0, 6}
            },
            {
                    {0, 0, 2},
                    {0, 0, 0},
                    {5, 8, 0}
            },
            {
                    {0, 4, 8},
                    {0, 0, 0},
                    {7, 0, 0}
            },

    };

    public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            GridManager.getInstance().getLinkedGrid(GridLocation.getGridByIndex(i)).setData(TEMPORARY_GRID[i]);
        }
    }

    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        board.printBoard();
        long startTime = System.currentTimeMillis();
        if (board.solve()) {
            long endTime = System.currentTimeMillis();
            System.out.println("\nSolved in " + (endTime - startTime) + " milliseconds");
            board.printBoard();
        } else {
            System.out.println("Not solvable");
        }
    }

    public boolean solve() {
        Cell currCell = getNextEmptyCell();
        if (currCell == null) {
            return true;
        }

        for (int i = 1; i < 10; i++) {
            if (currCell.setValue(i)) {
                if (solve()) {
                    return true;
                }
                currCell.setValue(0);
            }
        }
        return false;
    }

    public Cell getNextEmptyCell() {
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 3; j++) {
                Cell[] fullRow = GridManager.getInstance().getLinkedGrid(GridLocation.getGridByIndex(i)).getFullRow(j);
                for (int i1 = 0, fullRowLength = fullRow.length; i1 < fullRowLength; i1++) {
                    Cell c = fullRow[i1];
                    if ((c.getValue() == 0) && (!c.isFixed())) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public void printBoard() {
        for (int currBoard = 0; currBoard < 9; currBoard += 3) {
            for (int currRow = 0; currRow < 3; currRow++) {
                for (int bOffset = 0; bOffset < 3; bOffset++) {
                    Cell[] cells = GridManager.getInstance().getLinkedGrids().get(currBoard + bOffset).getRow(currRow);
                    for (int i = 0, cellsLength = cells.length; i < cellsLength; i++) {
                        Cell c = cells[i];
                        System.out.print(c.getValue() + " ");
                    }
                    if (bOffset != 2) {
                        System.out.print("| ");
                    }
                }
                System.out.println();
            }
            System.out.println(" ------------------- ");
        }
    }
}
