package com.sollisar.app;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

/** Handles the Swing UI */
public class DisplayEngineSwing implements DisplayEngineInterface {
    JFrame ui;
    DisplayEngineSwingPanel drawBoard;

    /** Constructor, requires the size of the board
     * @param boardSize The size of the board.
     */
    public DisplayEngineSwing(final int boardSize) {

        drawBoard = new DisplayEngineSwingPanel(boardSize);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeEngine(boardSize);
            }
        });            
    }

    
    /** Sets up the drawing board for what entities should be drawn.
     * @param b The game board to be drawn
     */
    @Override
    public void drawGameBoard(GameBoard b) {
        ArrayList<MyPoint> life = new ArrayList<MyPoint>();
        int boardSize = b.getBoardSize();

        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                MyPoint p = new MyPoint(x, y);
                if (b.getEntity(p)) {
                    life.add(p);
                }
            }
        }
        if (drawBoard != null) {
            drawBoard.setCurrentLife(life);
        }
    }

    /** Does nothing. Clearing the drawing area is done as the area is being drawn anew. */
    @Override
    public void clearDrawingArea() {
        // Do nothing.
    }
    
    
    /** Sets up the application ui. 
     * @param boardSize The size of the board.
     */
    public void initializeEngine(int boardSize) {
        ui = new JFrame("Game of Life");
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setSize(boardSize * 3, boardSize * 3);

        ui.add(drawBoard);
        ui.pack();

        ui.setVisible(true);
    }
}
