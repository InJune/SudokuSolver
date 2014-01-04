package src;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A board is 9x9.
 * <p>
 * Created by Synchronised_ on 04/01/14.
 */
public class Board {

    private Cell[][] board;

    public Board() {
        //[Row][Column]
        board = new Cell[9][9];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Cell(0);
            }
        }

        // Default board setup.
        //board[0][2] = new src.Cell(7);
        //board[0][5] = new src.Cell(1);
        //board[0][6] = new src.Cell(3);

        board[0][0] = new Cell(1);
        board[0][1] = new Cell(0);
        board[0][2] = new Cell(4);
        board[0][3] = new Cell(2);
        board[0][4] = new Cell(7);
        board[0][5] = new Cell(6);
        board[0][6] = new Cell(0);
        board[0][7] = new Cell(8);
        board[0][8] = new Cell(9);


        board[1][1] = new Cell(1);
        board[2][1] = new Cell(2);
        board[3][1] = new Cell(3);
        board[4][1] = new Cell(4);
        board[5][1] = new Cell(0);
        board[6][1] = new Cell(6);
        board[7][1] = new Cell(7);
        board[8][1] = new Cell(8);

/*
        board[1][0] = new src.Cell(3);
        board[1][4] = new src.Cell(5);
        board[1][6] = new src.Cell(6);

        board[2][0] = new src.Cell(5);
        board[2][2] = new src.Cell(8);
        board[2][4] = new src.Cell(3);
        board[2][8] = new src.Cell(4);

        board[3][1] = new src.Cell(8);
        board[3][4] = new src.Cell(6);
        board[3][6] = new src.Cell(9);

        board[4][0] = new src.Cell(7);
        board[4][8] = new src.Cell(6);

        board[5][2] = new src.Cell(4);
        board[5][4] = new src.Cell(1);
        board[5][7] = new src.Cell(7);

        board[6][0] = new src.Cell(2);
        board[6][4] = new src.Cell(8);
        board[6][6] = new src.Cell(4);
        board[6][8] = new src.Cell(3);

        board[7][2] = new src.Cell(6);
        board[7][4] = new src.Cell(7);
        board[7][8] = new src.Cell(8);

        board[8][2] = new src.Cell(5);
        board[8][3] = new src.Cell(9);
        board[8][6] = new src.Cell(7);
        */
    }

    public void putValue(int row, int col, int value) {
        board[row][col] = new Cell(value);
    }

    public boolean checkRow(int row) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int col = 0; col < board[row].length; col++) {
            int value = board[row][col].getValue();
            if (value == 0) {
                // We skip 0s because that's the number I chose to represent an empty space.
                continue;
            }
            if (nums.contains(value)) {
                return false;
            }
            nums.add(value);
        }
        return nums.size() == 9;
    }

    public boolean checkCol(int col) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int row = 0; row < board[col].length; row++) {
            int value = board[row][col].getValue();
            if (value == 0) {
                // We skip 0s because that's the number I chose to represent an empty space.
                continue;
            }
            if (nums.contains(value)) {
                return false;
            }
            nums.add(value);
        }

        return nums.size() == 9;
    }

    public void printBoard() {
        for (int row = 0; row < board.length; row++) {
            System.out.println();
            if ((row) % 3 == 0 && row != 0) {
                System.out.println(" ----------------------- ");
            }
            for (int col = 0; col < board[row].length; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print(" | ");
                }
                System.out.print(" " + board[row][col].getValue());
            }
        }
        System.out.println();
    }

    public int getBestNumber(int row, int col) {
        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        // Check the parent 3x3 squares


        // Check the column for any number missing
        for (int tCol = 0; tCol < board[row].length; tCol++) {
            int value = board[row][tCol].getValue();
            if (value != 0) {
                if (numbers.contains(value)) {
                    numbers.remove((Integer) value); //Must cast to Integer to call remove(object) and not remove(index)
                }
            }
        }

        // Check the row for any number missing
        for (int tRow = 0; tRow < board.length; tRow++) {
            int value = board[tRow][col].getValue();
            System.out.println("value = " + value);
            if (value != 0) {
                if (numbers.contains(value)) {
                    numbers.remove((Integer) value);
                }
            }
        }

        return numbers.get(0); //Return the first number in the list.
    }

//    public void solve() {
//        int attempts = 0;
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board[row].length; col++) {
//                int value = board[row][col].getValue();
//
//                if (value == 0) { // empty slot
//
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();

        System.out.println(b.getBestNumber(0, 1));
    }
}
