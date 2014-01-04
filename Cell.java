/**
 * Contains a number between 1 - 9.
 * <p>
 * Created by Synchronised_ on 04/01/14.
 */
public class Cell {
    private int value;

    public Cell(int value) {
        if (value >= 1 || value <= 9) {
            this.value = value;
        } else {
            System.err.println("Value must be between 1 and 9. Defaulted to 0 (Empty)");
            this.value = 0;
        }
    }

    public int getValue() {
        return value;
    }
}
