package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    int[][] field;
    int fieldSizeX;
    int fieldSizeY;

    int winLen;

    int cellHeight;
    int cellWidth;

    boolean isInitialized = false;

    Color colorCeros = Color.RED;
    Color colorCruces = Color.BLUE;

    int lastMouseClickCellX = 100;
    int lastMouseClickCellY = 100;

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
        lastMouseClickCellX = e.getX() / cellWidth;
        lastMouseClickCellY = e.getY() / cellHeight;

        System.out.println("x: " + lastMouseClickCellX + " y: " + lastMouseClickCellY);
        repaint();
    }

   /* void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        System.out.println("x: " + cellX + " y: " + cellY);
        repaint();
    }*/


    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLen = winLen;
        field = new int[fieldSizeY][fieldSizeX];
        isInitialized = true;
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
                if (field[i][j] == 1) putCero(g, colorCeros, i, j);
                if (field[i][j] == -1) putCruz(g, colorCruces, i, j);
            }
        }
//        putCero(g, colorCeros, 0, 0);
//        putCruz(g, colorCruces, 2, 2);

    }

    void putCero(Graphics graphics, Color colorCeros, int rowNo, int columnNo) {
        // It's a method to paint zero of color colorCeros in row rowNo and column columnNo.
        graphics.setColor(colorCeros);
        graphics.fillOval(cellWidth * columnNo + cellWidth / 8, cellHeight * rowNo, 3 * cellWidth / 4, cellHeight);
        graphics.setColor(Color.ORANGE);
        graphics.fillOval(cellWidth * columnNo + cellWidth / 4, cellHeight * rowNo + cellHeight / 8, cellWidth / 2, 3 * cellHeight / 4);
    }

    void putCruz(Graphics graphics, Color colorCruces, int rowNo, int columnNo) {
        // It's a method to paint cross of color colorCruces in row rowNo and column columnNo.
        graphics.setColor(colorCruces);
        graphics.drawLine(cellWidth * columnNo, cellHeight * rowNo, cellWidth * (columnNo + 1), cellHeight * (rowNo + 1));
        graphics.setColor(colorCruces);
        graphics.drawLine(cellWidth * (columnNo + 1), cellHeight * rowNo, cellWidth * columnNo, cellHeight * (rowNo + 1));
    }

}
