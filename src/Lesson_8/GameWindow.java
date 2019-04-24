package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameWindow extends JFrame {

    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 507;
    private static final int POS_X = 800;
    private static final int POS_Y = 300;

    private static StartNewGameWindow startNewGameWindow;
    private static Map field;

    public static Random rand = new Random();


    public GameWindow() {

        setTitle("TicTacToe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIN_WIDTH, WIN_HEIGHT);

        JButton btnStartGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        bottomPanel.add(btnStartGame);
        bottomPanel.add(btnExit);

        add(bottomPanel, BorderLayout.SOUTH);

        startNewGameWindow = new StartNewGameWindow(this);
        field = new Map();
        add(field, BorderLayout.CENTER);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
            }
        });

        setResizable(false);
        setVisible(true);
    }

    public void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        field.startNewGame(mode, fieldSizeX, fieldSizeY, winLen);

        // Me
        playGame(mode, fieldSizeX, fieldSizeY, winLen);
    }

    // Me
    void playGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {

        while (true) {
            humanStep(fieldSizeX, fieldSizeY);

            if (CheckWin(fieldSizeY, fieldSizeX, winLen, 1)) {
                System.out.println("HUMAN BRAINS WIN");
                break;
            }

            if (isMapFull(fieldSizeX, fieldSizeY)) {
                System.out.println("DRAW");
                break;
            }

            aiStep(fieldSizeX, fieldSizeY);
            if (CheckWin(fieldSizeY, fieldSizeX, winLen, -1)) {
                System.out.println("SkyNet WIN");
                break;
            }

            if (isMapFull(fieldSizeX, fieldSizeY)) {
                System.out.println("DRAW");
                break;
            }
        }

    }

    public static void humanStep(int fieldSizeX, int fieldSizeY) {
        int x, y;
        do {
            System.out.println("Поствьте нолик на пустую клетку");
            x = field.lastMouseClickCellX;
            y = field.lastMouseClickCellY;
        } while (!isCellValid(fieldSizeX, fieldSizeY,field.lastMouseClickCellX,field.lastMouseClickCellY));
        field.field[y][x] = 1;
    }

    public static void aiStep(int fieldSizeX, int fieldSizeY) {
        int x, y;
        do {
            x = rand.nextInt(fieldSizeX);
            y = rand.nextInt(fieldSizeY);
        } while (field.field[y][x] !=0);
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        field.field[y][x] = -1;
    }

    public static boolean isMapFull(int fieldSizeX, int fieldSizeY) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field.field[i][j] == 0) return false;
            }
        }
        return true;
    }

    public static boolean isCellValid(int fieldSizeX, int fieldSizeY, int x, int y) {
        if (x < 0 || x >= fieldSizeX || y < 0 || y >= fieldSizeY) return false;
        if (field.field[y][x] == 0) return true;
        return false;
    }

    public static boolean CheckWin(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        return CheckWinInRows(rowsNo, columnsNo, dotsToWin, figure)
                || CheckWinInColumns(rowsNo, columnsNo, dotsToWin, figure)
                || CheckWinInDiagonals(rowsNo, columnsNo, dotsToWin, figure);
    }

    // Method to check for Win in Rows
    public static boolean CheckWinInRows(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counter = 10;
        for (int i = 0; i < rowsNo; i++) {
            for (int shift = 0; shift <= columnsNo - dotsToWin; shift++) {
                for (int k = 0; k < dotsToWin; k++) {
                    if (k == 0) counter = 0;
                    if (field.field[i][k + shift] == figure) counter++;
                    if (counter == dotsToWin) return true;
                }
            }
        }
        return false;
    }

    // Method to check for Win in Columns
    public static boolean CheckWinInColumns(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counter = 10;
        for (int j = 0; j < columnsNo; j++) {
            for (int shift = 0; shift <= rowsNo - dotsToWin; shift++) {
                for (int k = 0; k < dotsToWin; k++) {
                    if (k == 0) counter = 0;
                    if (field.field[k + shift][j] == figure) counter++;
                    if (counter == dotsToWin) return true;
                }
            }
        }
        return false;
    }

    public static boolean CheckWinInDiagonals(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counterOne = 10;
        int counterTwo = 10;
        for (int n = 0; n <= 1; n++) {
            // When a = 0 and b = 1 we go by main diagonal. Otherwise we go by side diagonal.
            int a = n == 0 ? 0 : columnsNo - 1;
            int b = n == 0 ? 1 : -1;

            for (int i = 0; i <= rowsNo - dotsToWin; i++) {
                for (int shift = 0; shift <= rowsNo - i - dotsToWin; shift++) {
                    for (int k = 0; k < dotsToWin; k++) {
                        if (k == 0) counterOne = 0;
                        if (k == 0 && i != 0) counterTwo = 0;
                        if (field.field[i + k + shift][a + b * (k + shift)] == figure) counterOne++;
                        if (counterOne == dotsToWin) return true;
                        if (field.field[k + shift][a + b * (i + k + shift)] == figure) counterTwo++;
                        if (counterTwo == dotsToWin) return true;
                    }
                }
            }
        }
        return false;
    }


}
