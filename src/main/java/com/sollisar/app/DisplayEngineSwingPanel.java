package com.sollisar.app;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Class responsible for the actually drawing of the game board 
 */
public class DisplayEngineSwingPanel extends JPanel {
    /** Variable to hold the size of the side of the gameboard 
     * 
    */
    private int boardSize = 0;
    /** ArrayList of MyPoint to hold the board after a tick, before the redrawing 
     * 
    */
    private ArrayList<MyPoint> currentLife = new ArrayList<MyPoint>();

    /**
     * Constructor for the class, sets border and size of drawing area.
     * @param size The size of the board
     */
    public DisplayEngineSwingPanel(int size) {
        boardSize = size;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    
    /** Returns the preferred dimension to make orderly repaints.
     * @return Dimension The dimension, normalized to the chosen "pixel"-size.
     */
    public Dimension getPreferredSize() {
        return new Dimension(boardSize * 3 + 2, boardSize * 3 + 2);
    }

    
    /** Sets the list of entities to be drawn at next generation and requests a redraw.
     * @param dots An ArrayList of MyPoints with alive entities.
     */
    public void setCurrentLife(ArrayList<MyPoint> dots) {
        currentLife = dots;
        repaint();
    }

    
    /** Overloaded method for drawing the actual pixels on the drawing surface.
     * @param g The graphics context to draw on.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, boardSize * 3 + 2, boardSize * 3 + 2);
        
        g.setColor(Color.WHITE);
        for (int i = 0; i < currentLife.size(); i++) {
            MyPoint p = currentLife.get(i);
            g.drawRect(p.x * 3, p.y * 3, 2, 2);
        }
    }
}
