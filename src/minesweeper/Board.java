package minesweeper;

import java.util.Random;

public class Board {
    final private int mines;
    private final Cell[][] field = new Cell[9][9];
    private final static Random random = new Random();

    public Board(int mines) {

        // It's 9x9 board, so number of mines can't be lower than 1 and can't exceed 80 (at least 1 empty cell)
        if (mines < 1) {
            this.mines = 1;
        } else {
            this.mines = Math.min(mines, 80);
        }

        // Filling a board with Cell objects, but we are not inserting mines yet, as we want first move to be mine-free
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    // Placing mines on the board randomly
    public void insertMines() {
        int counter = 0;
        while (counter < this.mines) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (field[x][y].isChecked()) {
                continue;
            }
            if (!field[x][y].isBomb()) {
                field[x][y].setBomb(true);
                counter++;
            }
        }

        //Setting int minesAround parameter to every Cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j].setMinesAround(checkMinesAroundCell(i, j));
            }
        }
    }

    // Printing the board using toString method for every Cell
    public void printField() {
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        int row = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(row++ + "|");
            for (int j = 0; j < 9; j++) {
                System.out.print(field[i][j].toString());
            }
            System.out.print("| \n");
        }
        System.out.println("-|---------|");
    }

    // Counting number of mines around particular cell
    private int checkMinesAroundCell(int x, int y) {
        int minRow = Math.max(x - 1, 0);
        int maxRow = Math.min(x + 1, 8);
        int minCol = Math.max(y - 1, 0);
        int maxCol = Math.min(y + 1, 8);

        int counter = 0;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (field[i][j].isBomb()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    // Mark/unmark a cell
    public void markCell(int x, int y) {
        this.field[x][y].setFlag(!this.field[x][y].isFlag());
    }

    // Checks the cell, returns true if game has been lost and false otherwise
    // if the cell has 0 bombs around, function automatically calls checkEmptyCells()
    public boolean checkCell(int x, int y) {
        if (field[x][y].isBomb()) {
            gameLost();
            return true;
        } else if (field[x][y].getMinesAround() != 0) {
            field[x][y].setChecked(true);
        } else if (field[x][y].getMinesAround() == 0) {
            field[x][y].setChecked(true);
            checkEmptyCells(x, y);
        }
        return false;
    }

    // If the cell is empty and has no mines around, all the cells around are checked. If next to that cell there is
    // another empty cell with no mines around, it is further explored using recursive call. It is done automatically
    // until no more cells can be checked
    public void checkEmptyCells(int x, int y) {
        int minRow = Math.max(x - 1, 0);
        int maxRow = Math.min(x + 1, 8);
        int minCol = Math.max(y - 1, 0);
        int maxCol = Math.min(y + 1, 8);

        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (field[i][j].isChecked()) {
                    continue;
                } else {
                    field[i][j].setChecked(true);
                }
                if (field[i][j].getMinesAround() == 0) {
                    checkEmptyCells(i, j);
                }
            }
        }
    }

    // Game has been lost, printing the board with all mines revealed
    public void gameLost() {
        System.out.println();
        System.out.println(" |123456789|");
        System.out.println("-|---------|");
        int row = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(row++ + "|");
            for (int j = 0; j < 9; j++) {
                if (field[i][j].isBomb()) {
                    System.out.print("X");
                } else {
                    System.out.print(field[i][j].toString());
                }
            }
            System.out.print("| \n");
        }
        System.out.println("-|---------|");
        System.out.println("You stepped on a mine and failed!\n");
    }

    // Checks if the game is won
    public boolean checkIfWon() {
        if (checkIfWon2()) {
            return true;
        }
        // If all mines are flagged, the game is won
        int bombCounter = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (field[i][j].isBomb() && field[i][j].isFlag()) {
                    bombCounter++;
                }
            }
        }
        return bombCounter == this.mines;
    }

    // If all empty cells are checked, the game is won
    public boolean checkIfWon2() {
        int counter = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (field[i][j].isChecked()) {
                    counter++;
                }
            }
        }
        return counter == 81 - this.mines;
    }
}
