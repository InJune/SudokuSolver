package src;

import java.util.ArrayList;

/**
 * Singleton made to handle each grid and whatever grid it is linked to.
 * <p/>
 * Created by Synchronised on 05/01/14.
 */
public class GridManager {
    private static GridManager ourInstance = new GridManager();
    private ArrayList<LinkedGrid> LINKED_GRIDS;

    private GridManager() {
        LINKED_GRIDS = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            LINKED_GRIDS.add(createLinkedGrid(GridLocation.getGridByIndex(i)));
        }
    }

    public static GridManager getInstance() {
        return ourInstance;
    }

    private LinkedGrid createLinkedGrid(GridLocation gridLocation) {
        switch (gridLocation) {
            case GRID_0_0:
                return new LinkedGrid(GridLocation.GRID_0_0,
                        GridLocation.GRID_0_1,
                        GridLocation.GRID_0_2,
                        GridLocation.GRID_1_0,
                        GridLocation.GRID_2_0
                );
            case GRID_0_1:
                return new LinkedGrid(GridLocation.GRID_0_1,
                        GridLocation.GRID_0_0,
                        GridLocation.GRID_0_2,
                        GridLocation.GRID_1_1,
                        GridLocation.GRID_2_1
                );
            case GRID_0_2:
                return new LinkedGrid(GridLocation.GRID_0_2,
                        GridLocation.GRID_0_0,
                        GridLocation.GRID_0_1,
                        GridLocation.GRID_1_2,
                        GridLocation.GRID_2_2
                );
            case GRID_1_0:
                return new LinkedGrid(GridLocation.GRID_1_0,
                        GridLocation.GRID_1_1,
                        GridLocation.GRID_1_2,
                        GridLocation.GRID_0_0,
                        GridLocation.GRID_2_0
                );
            case GRID_1_1:
                return new LinkedGrid(GridLocation.GRID_1_1,
                        GridLocation.GRID_1_0,
                        GridLocation.GRID_1_2,
                        GridLocation.GRID_0_1,
                        GridLocation.GRID_2_1
                );
            case GRID_1_2:
                return new LinkedGrid(GridLocation.GRID_1_2,
                        GridLocation.GRID_1_0,
                        GridLocation.GRID_1_1,
                        GridLocation.GRID_0_2,
                        GridLocation.GRID_2_2
                );
            case GRID_2_0:
                return new LinkedGrid(GridLocation.GRID_2_0,
                        GridLocation.GRID_2_1,
                        GridLocation.GRID_2_2,
                        GridLocation.GRID_0_0,
                        GridLocation.GRID_1_0
                );
            case GRID_2_1:
                return new LinkedGrid(GridLocation.GRID_2_1,
                        GridLocation.GRID_2_0,
                        GridLocation.GRID_2_2,
                        GridLocation.GRID_0_1,
                        GridLocation.GRID_1_1
                );
            case GRID_2_2:
                return new LinkedGrid(GridLocation.GRID_2_2,
                        GridLocation.GRID_2_0,
                        GridLocation.GRID_2_1,
                        GridLocation.GRID_0_2,
                        GridLocation.GRID_1_2
                );
        }
        return null;
    }

    public ArrayList<LinkedGrid> getLinkedGrids() {
        return LINKED_GRIDS;
    }

    public LinkedGrid getLinkedGrid(GridLocation gridLocation) {
        if (LINKED_GRIDS.size() == 0) {
            System.err.println("getLinkedGrid: called before GridManager().");
        }
        return LINKED_GRIDS.get(gridLocation.getIndex());
    }
}
