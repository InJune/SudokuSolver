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
        new GridManager(); // Yes, this actually does stuff. No, I don't need the instance. Leave it alone.

        for (int i = 0; i < 9; i++) {
            GridManager.getLinkedGrid(GridLocation.getGridByIndex(i)).setData(TEMPORARY_GRID[i]);
        }
    }

    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();

        board.printBoard();
        if (board.solve()) {
            System.out.println("\nSolved!");
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
                //printBoard();
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
                for (Cell c : GridManager.getLinkedGrid(GridLocation.getGridByIndex(i)).getFullRow(j)) {
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
                    Cell[] cells = GridManager.getLinkedGrids().get(currBoard + bOffset).getRow(currRow);
                    for (Cell c : cells) {
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
