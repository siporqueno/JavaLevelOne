package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {

    static final int MODE_H_V_A = 0;
    static final int MODE_H_V_H = 1;

    private static final int FIGURE_EMPTY = 0;
    private static final int FIGURE_HUMAN = 1;
    private static final int FIGURE_AI = -1;

    private static final int RESULT_DRAW = 0;
    private static final int RESULT_HUMAN_WIN = 1;
    private static final int RESULT_AI_WIN = -1;
    private int gameResult;

    private static final Font font = new Font("Arial", Font.BOLD, 40);

    int[][] field;
    int fieldSizeX;
    int fieldSizeY;

    int winLen;

    int cellHeight;
    int cellWidth;

    boolean isInitialized = false;
    boolean gameOver = false;

    private static final Color COLOR_CEROS = Color.RED;
    private static final Color COLOR_CRUCES = Color.BLUE;


    private static Random rand = new Random();


    Map() {
        setBackground(Color.ORANGE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }

    void update(MouseEvent e) {
        int CellX = e.getX() / cellWidth;
        int CellY = e.getY() / cellHeight;

        if (gameOver) return;

        if (!isCellValid(fieldSizeX, fieldSizeY, CellX, CellY)) return;
        field[CellY][CellX] = FIGURE_HUMAN;

        if (checkWin(fieldSizeY, fieldSizeX, winLen, FIGURE_HUMAN)) {
//            System.out.println("Human victory!!!");
            repaint();
            gameResult = RESULT_HUMAN_WIN;
            gameOver = true;
            return;
        }

        if (isMapFull(fieldSizeX, fieldSizeY)) {
//            System.out.println("DRAW");
            repaint();
            gameResult = RESULT_DRAW;
            gameOver = true;
            return;
        }

        aiStep(fieldSizeX, fieldSizeY);

        repaint();

        if (checkWin(fieldSizeY, fieldSizeX, winLen, FIGURE_AI)) {
//            System.out.println("SkyNet win!!!");
            repaint();
            gameResult = RESULT_AI_WIN;
            gameOver = true;
            return;
        }

        if (isMapFull(fieldSizeX, fieldSizeY)) {
//            System.out.println("DRAW");
            repaint();
            gameOver = true;
            gameResult = RESULT_DRAW;
        }
    }


    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLen;
        field = new int[fieldSizeY][fieldSizeX];
        isInitialized = true;
        gameOver = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    void render(Graphics g) {
        if (!isInitialized) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWidth / fieldSizeY;
        cellHeight = panelHeight / fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == 1) putCero(g, COLOR_CEROS, i, j);
                if (field[i][j] == -1) fillCruz(g, COLOR_CRUCES, i, j);
            }
        }
        if (gameOver) {
            showMessageGameOver(g);
        }
    }

    void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 80);
        g.setColor(Color.ORANGE);
        g.setFont(font);
        switch (gameResult) {
            case RESULT_HUMAN_WIN:
                g.drawString("HUMAN BRAINS WON!", 30, getHeight() / 2);
                break;
            case RESULT_AI_WIN:
                g.drawString("SKYNET WON!", 100, getHeight() / 2);
                break;
            case RESULT_DRAW:
                g.drawString("DRAW!", 180, getHeight() / 2);
                break;
            default:
                System.out.println("switch default");
        }
    }

    void putCero(Graphics graphics, Color colorCeros, int rowNo, int columnNo) {
        // It's a method to paint zero of color COLOR_CEROS in row rowNo and column columnNo.
        graphics.setColor(colorCeros);
        graphics.fillOval(cellWidth * columnNo + cellWidth / 8, cellHeight * rowNo, 3 * cellWidth / 4, cellHeight);
        graphics.setColor(Color.ORANGE);
        graphics.fillOval(cellWidth * columnNo + cellWidth / 4, cellHeight * rowNo + cellHeight / 8, cellWidth / 2, 3 * cellHeight / 4);
    }

    /*void putCruz(Graphics graphics, Color colorCruces, int rowNo, int columnNo) {
        // It's a method to paint cross of color COLOR_CRUCES in row rowNo and column columnNo.
        graphics.setColor(colorCruces);
        graphics.drawLine(cellWidth * columnNo, cellHeight * rowNo, cellWidth * (columnNo + 1), cellHeight * (rowNo + 1));
        graphics.drawLine(cellWidth * (columnNo + 1), cellHeight * rowNo, cellWidth * columnNo, cellHeight * (rowNo + 1));
    }*/

    void fillCruz(Graphics graphics, Color colorCruces, int rowNo, int columnNo) {
        graphics.setColor(colorCruces);
        // main diagonal of cruz (left top to right bottom)
        int[] xOne = {cellWidth / 10 + columnNo * cellWidth, cellWidth * (columnNo + 1), 9 * cellWidth / 10 + cellWidth * columnNo, cellWidth * columnNo};
        int[] yOne = {cellHeight * rowNo, 9 * cellHeight / 10 + cellHeight * rowNo, cellHeight * (rowNo + 1), cellHeight / 10 + cellHeight * rowNo};
        graphics.fillPolygon(xOne, yOne, 4);
        int[] xTwo = {9 * cellWidth / 10 + columnNo * cellWidth, cellWidth * (columnNo + 1), cellWidth / 10 + cellWidth * columnNo, cellWidth * columnNo};
        int[] yTwo = {cellHeight * rowNo, cellHeight / 10 + cellHeight * rowNo, cellHeight * (rowNo + 1), 9 * cellHeight / 10 + cellHeight * rowNo};
        graphics.fillPolygon(xTwo, yTwo, 4);
    }

    public void aiStep(int fieldSizeX, int fieldSizeY) {
        int x, y;
        do {
            x = rand.nextInt(fieldSizeX);
            y = rand.nextInt(fieldSizeY);
        } while (field[y][x] != FIGURE_EMPTY);
//        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        field[y][x] = FIGURE_AI;
    }

    public boolean isMapFull(int fieldSizeX, int fieldSizeY) {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isCellValid(int fieldSizeX, int fieldSizeY, int x, int y) {
        if (x < 0 || x >= fieldSizeX || y < 0 || y >= fieldSizeY) return false;
        if (field[y][x] == 0) return true;
        return false;
    }

    public boolean checkWin(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        return checkWinInRows(rowsNo, columnsNo, dotsToWin, figure)
                || checkWinInColumns(rowsNo, columnsNo, dotsToWin, figure)
                || checkWinInDiagonals(rowsNo, columnsNo, dotsToWin, figure);
    }

    // Method to check for Win in Rows
    public boolean checkWinInRows(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counter = 10;
        for (int i = 0; i < rowsNo; i++) {
            for (int shift = 0; shift <= columnsNo - dotsToWin; shift++) {
                for (int k = 0; k < dotsToWin; k++) {
                    if (k == 0) counter = 0;
                    if (field[i][k + shift] == figure) counter++;
                    if (counter == dotsToWin) return true;
                }
            }
        }
        return false;
    }

    // Method to check for Win in Columns
    public boolean checkWinInColumns(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counter = 10;
        for (int j = 0; j < columnsNo; j++) {
            for (int shift = 0; shift <= rowsNo - dotsToWin; shift++) {
                for (int k = 0; k < dotsToWin; k++) {
                    if (k == 0) counter = 0;
                    if (field[k + shift][j] == figure) counter++;
                    if (counter == dotsToWin) return true;
                }
            }
        }
        return false;
    }

    public boolean checkWinInDiagonals(int rowsNo, int columnsNo, int dotsToWin, int figure) {
        int counterOne = 10;
        int counterTwo = 10;
        for (int n = 0; n <= 1; n++) {
            // When a = 0 and b = 1 we go by main diagonal. Otherwise we go by side diagonal.
            int a = n == 0 ? 0 : columnsNo - 1;
            int b = n == 0 ? 1 : -1;

            for (int i = 0; i <= rowsNo - dotsToWin; i++) {
                for (int shift = 0; shift <= rowsNo - i - dotsToWin; shift++) {
                    for (int k = 0; k < dotsToWin; k++) {
                        if (k == 0) {
                            counterOne = 0;
                            counterTwo = 0;
                        }
//                        if (k == 0 && i != 0) counterTwo = 0; Tt was a bug!!! Don't uncomment!!!
                        if (field[i + k + shift][a + b * (k + shift)] == figure) counterOne++;
                        if (counterOne == dotsToWin) return true;
                        if (field[k + shift][a + b * (i + k + shift)] == figure) counterTwo++;
                        if (counterTwo == dotsToWin) return true;
                    }
                }
            }
        }
        return false;
    }


}
