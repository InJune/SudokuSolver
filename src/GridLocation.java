package src;

/**
 * Created by Synchronised on 05/01/14.
 */
public enum GridLocation {
    GRID_0_0(0),
    GRID_0_1(1),
    GRID_0_2(2),
    GRID_1_0(3),
    GRID_1_1(4),
    GRID_1_2(5),
    GRID_2_0(6),
    GRID_2_1(7),
    GRID_2_2(8);

    private int index;

    private GridLocation(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static GridLocation getGridByIndex(int index) {
        switch (index) {
            case 0:
                return GRID_0_0;
            case 1:
                return GRID_0_1;
            case 2:
                return GRID_0_2;
            case 3:
                return GRID_1_0;
            case 4:
                return GRID_1_1;
            case 5:
                return GRID_1_2;
            case 6:
                return GRID_2_0;
            case 7:
                return GRID_2_1;
            case 8:
                return GRID_2_2;
            default:
                System.err.println("What the fuck?");
                break;
        }
        return null;
    }
}
