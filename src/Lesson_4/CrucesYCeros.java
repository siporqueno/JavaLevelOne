package Lesson_4;

import java.util.Random;
import java.util.Scanner;

public class CrucesYCeros {
    public static char[][] field;
    public static final int SIZE_X = 4;
    public static final int SIZE_Y = 4;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '\u2022'; //  bullet point \u2022 , middle point \u00B7
    public static final char DOT_PLAYER = 'X';
    public static final char DOT_AI = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            humanStep();
            printField();

            if (CheckWin(SIZE_Y, SIZE_X, DOTS_TO_WIN, DOT_PLAYER)) {
                System.out.println("HUMAN BRAINS WIN");
                break;
            }

            if (isMapFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (CheckWin(SIZE_Y, SIZE_X, DOTS_TO_WIN, DOT_AI)) {
                System.out.println("SkyNet WIN");
                break;
            }

            if (isMapFull()) {
                System.out.println("DRAW");
                break;
            }


        }
        System.out.println("GAME OVER");
    }

    public static void initField() {
        field = new char[SIZE_X][SIZE_X];
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printField() {
        System.out.println("---------");
        for (int i = 0; i <= SIZE_X; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void humanStep() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        field[y][x] = DOT_PLAYER;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_X) return false;
        if (field[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void aiStep() {
        int x, y;
        do {
            x = rand.nextInt(SIZE_X);
            y = rand.nextInt(SIZE_X);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        field[y][x] = DOT_AI;
    }

    public static boolean checkWinThreeByThree(char symb) {
        if (field[0][0] == symb && field[0][1] == symb && field[0][2] == symb) return true;
        if (field[1][0] == symb && field[1][1] == symb && field[1][2] == symb) return true;
        if (field[2][0] == symb && field[2][1] == symb && field[2][2] == symb) return true;
        if (field[0][0] == symb && field[1][0] == symb && field[2][0] == symb) return true;
        if (field[0][1] == symb && field[1][1] == symb && field[2][1] == symb) return true;
        if (field[0][2] == symb && field[1][2] == symb && field[2][2] == symb) return true;
        if (field[0][0] == symb && field[1][1] == symb && field[2][2] == symb) return true;
        if (field[0][2] == symb && field[1][1] == symb && field[2][0] == symb) return true;
        return false;
    }

    // Home work of Lesson 4, task 2.
    public static boolean checkWinThreeByThreeViaCycles(char symb) {
        int counter = 10;
        // Let's check the rows first.
        for (int i = 0; i < SIZE_Y; i++) {
            for (int k = 0; k < DOTS_TO_WIN; k++) {
                if (k == 0) counter = 0;
                if (field[i][k] == symb) counter++;
                if (counter == DOTS_TO_WIN) return true;
            }

        }
        // Now let's check the columns.
        for (int j = 0; j < SIZE_X; j++) {
            for (int k = 0; k < DOTS_TO_WIN; k++) {
                if (k == 0) counter = 0;
                if (field[k][j] == symb) counter++;
                if (counter == DOTS_TO_WIN) return true;
            }
        }
        /*// P.S., it's turn of the main diagonal \.
        for (int k = 0; k < DOTS_TO_WIN; k++) {
            if (k == 0) counter = 0;
            if (field[k][k] == symb) counter++;
            if (counter == DOTS_TO_WIN) return true;
        }
        // P.S.S., it's turn of the side diagonal /.
        for (int k = 0; k < DOTS_TO_WIN; k++) {
            if (k == 0) counter = 0;
            if (field[k][SIZE_X - 1 - k] == symb) counter++;
            if (counter == DOTS_TO_WIN) return true;
        }*/

        // Let's check main and side diagonals simultaneously
        for (int n = 0; n <= 1; n++) {
            // When a = 0 and b = 1 we go by main diagonal. Otherwise we go by side diagonal.
            int a = n == 0 ? 0 : SIZE_X - 1;
            int b = n == 0 ? 1 : -1;
            for (int k = 0; k < DOTS_TO_WIN; k++) {
                if (k == 0) counter = 0;
                if (field[k][a + b * k] == symb) counter++;
                if (counter == DOTS_TO_WIN) return true;
            }
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    // Home work of Lesson 4, task 3.
    public static boolean CheckWin(int rowsNo, int columnsNo, int dotsToWin, char symb) {
        return CheckWinInRows(rowsNo, columnsNo, dotsToWin, symb)
                || CheckWinInColumns(rowsNo, columnsNo, dotsToWin, symb)
                || CheckWinInDiagonals(rowsNo, columnsNo, dotsToWin, symb);
    }

    // Method to check for Win in Rows
    public static boolean CheckWinInRows(int rowsNo, int columnsNo, int dotsToWin, char symb) {
        int counter = 10;
        for (int i = 0; i < rowsNo; i++) {
            for (int shift = 0; shift <= columnsNo - dotsToWin; shift++) {
                for (int k = 0; k < DOTS_TO_WIN; k++) {
                    if (k == 0) counter = 0;
                    if (field[i][k + shift] == symb) counter++;
                    if (counter == DOTS_TO_WIN) return true;
                }
            }
        }
        return false;
    }

    // Method to check for Win in Columns
    public static boolean CheckWinInColumns(int rowsNo, int columnsNo, int dotsToWin, char symb) {
        int counter = 10;
        for (int j = 0; j < columnsNo; j++) {
            for (int shift = 0; shift <= rowsNo - dotsToWin; shift++) {
                for (int k = 0; k < DOTS_TO_WIN; k++) {
                    if (k == 0) counter = 0;
                    if (field[k + shift][j] == symb) counter++;
                    if (counter == DOTS_TO_WIN) return true;
                }
            }
        }
        return false;
    }

    // Method to check for Win in main diagonals \
    /*public static boolean CheckWinInMainDiagonals(int rowsNo, int columnsNo, int dotsToWin, char symb) {
        int counterOne = 10;
        int counterTwo = 10;
        for (int i = 0; i <= rowsNo - dotsToWin; i++) {
            for (int shift = 0; shift <= rowsNo - i - dotsToWin; shift++) {
                for (int k = 0; k < DOTS_TO_WIN; k++) {
                    if (k == 0) counterOne = 0;
                    if (k == 0 && i != 0) counterTwo = 0;
                    if (field[i + k + shift][k + shift] == symb) counterOne++;
                    if (counterOne == DOTS_TO_WIN) return true;
                    if (field[k + shift][i + k + shift] == symb) counterTwo++;
                    if (counterTwo == DOTS_TO_WIN) return true;
                }
            }
        }
        return false;
    }*/

    // Method to check for Win in both main and side diagonals
    public static boolean CheckWinInDiagonals(int rowsNo, int columnsNo, int dotsToWin, char symb) {
        int counterOne = 10;
        int counterTwo = 10;
        for (int n = 0; n <= 1; n++) {
            // When a = 0 and b = 1 we go by main diagonal. Otherwise we go by side diagonal.
            int a = n == 0 ? 0 : SIZE_X - 1;
            int b = n == 0 ? 1 : -1;

            for (int i = 0; i <= rowsNo - dotsToWin; i++) {
                for (int shift = 0; shift <= rowsNo - i - dotsToWin; shift++) {
                    for (int k = 0; k < DOTS_TO_WIN; k++) {
                        if (k == 0) counterOne = 0;
                        if (k == 0) counterTwo = 0;
//                        if (k == 0 && i != 0) counterTwo = 0;
                        if (field[i + k + shift][a + b * (k + shift)] == symb) counterOne++;
                        if (counterOne == DOTS_TO_WIN) return true;
                        if (field[k + shift][a + b * (i + k + shift)] == symb) counterTwo++;
                        if (counterTwo == DOTS_TO_WIN) return true;
                    }
                }
            }
        }
        return false;
    }

}

