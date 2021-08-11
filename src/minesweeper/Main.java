package minesweeper;

import java.util.Scanner;

public class Main {
    final static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many mines do you want on the field?");
        System.out.println();
        final int bombsNumber = scan.nextInt();

        Board board = new Board(bombsNumber);
        board.printField();

        // First move is free of mine
        boolean firstMove = false;
        while (!firstMove) {
            System.out.println("Set/unset mines marks or claim a cell as free:");
            int y = scan.nextInt() - 1;
            int x = scan.nextInt() - 1;
            String command = scan.next();
            if ("free".equals(command)) {
                board.getCell(x, y).setChecked(true);
                firstMove = true;

                // Randomly inserting mines after first move
                board.insertMines();
                if (board.getCell(x, y).getMinesAround() == 0) {
                    board.checkEmptyCells(x, y);
                }
                board.printField();
            } else if ("mine".equals(command)) {
                board.markCell(x, y);
                board.printField();
            }
        }

        // Play until game is won or lost
        while (!board.checkIfWon()) {
            if (userInput(board)) {
                board.gameLost();
                break;
            } else {
                board.printField();
            }
        }

        if (board.checkIfWon()) {
            System.out.println("Congratulations! You found all mines!");
        }

    }

    // Handle user inputs
    public static boolean userInput(Board board) {
        System.out.println("Set/unset mines marks or claim a cell as free:");
        int y = scan.nextInt() - 1;
        int x = scan.nextInt() - 1;
        String command = scan.next();
        if ("free".equals(command)) {
            return board.checkCell(x, y);
        } else if ("mine".equals(command)) {
            board.markCell(x, y);
        }
        return false;
    }
}