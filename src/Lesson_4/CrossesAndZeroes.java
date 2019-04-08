package Lesson_4;

import java.util.Random;
import java.util.Scanner;

public class CrossesAndZeroes {
    public static char[][] field;
    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 3;
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

            if (checkWinThreeByThreeViaCycles(DOT_PLAYER)) {
                System.out.println("HUMAN BRAINS WIN");
                break;
            }

            if (isMapFull()) {
                System.out.println("DRAW");
                break;
            }

            aiStep();
            printField();
            if (checkWinThreeByThreeViaCycles(DOT_AI)) {
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
        // P.S., it's turn of the diagonal \.
        for (int k = 0; k < DOTS_TO_WIN; k++) {
            if (k == 0) counter = 0;
            if (field[k][k] == symb) counter++;
            if (counter == DOTS_TO_WIN) return true;
        }
        // P.S.S., it's turn of the diagonal /.
        for (int k = 0; k < DOTS_TO_WIN; k++) {
            if (k == 0) counter = 0;
            if (field[k][SIZE_X - 1 - k] == symb) counter++;
            if (counter == DOTS_TO_WIN) return true;
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

}

