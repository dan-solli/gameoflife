package com.sollisar.app;

import java.util.BitSet;
import java.util.ArrayList;
import java.util.HashMap;

/** The gameboard contains the states of dead or alive entities. The gameboard is a square with a determined size, which is the number of entities in a row or column.
 */
public class GameBoard {
    private ArrayList<BitSet> board = new ArrayList<BitSet>();
    private int boardSize = 80;

    /** Create a new square gameboard with default size.
     * 
     */
    public GameBoard() {
        initializeBoard();
    }

    /** Create a new square gameboard with size * size entities.
     * 
     * @param size The size of the square representing entities.
     */
    public GameBoard(int size) {
        if (size < 10) {
            System.err.println("Spelbrädet måste vara minst 10x10. Sätter brädet till default: " + boardSize + "x" + boardSize);
        } else {
            boardSize = size;
        }
        initializeBoard();
    }

    /** Sets up the bitsets (rows) in the gameboard grid. */
    private void initializeBoard() {
        for (int y = 0; y < boardSize; y++) {
            board.add(new BitSet());
        }
    }
    
    /** randomizeBoard takes a number between 0 and 100, interpreted as the percentage coverage of the gameBoard, ie 50 means every other entity is alive, on average.
     * @param percentage The amount of coverage.
     */
    public void randomizeBoard(int percentage) {
        if (percentage < 0 || percentage > 100) {
            System.err.println("Procent anges från 1-100%. Använder 20%.");
            percentage = 20;
        }
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                int randomNumber = 1 + (int)(Math.random() * 100);

                if (randomNumber <= percentage) {
                    board.get(y).set(x);
                }
            }
        }
    }
    
    /** The method returns the length of the gameboard's side.
     * @return int
     */
    public int getBoardSize() {
        return boardSize;
    }
    
    /** The method flips the state of a single entity described by the coordinates in the MyPoint object.
     * @param p Holds a MyPoint object which state will be flipped.
     */
    public void flipEntity(MyPoint p) {
        board.get(p.y).flip(p.x);
    }
    
    /** Gets the state of a coordinate in the gameboard. 
     * @param x coordinate on x axis
     * @param y coordinate on y axis
     * @return boolean The state of the entity (true = alive, false = dead)
     */
    public boolean getEntity(int x, int y) {
        return board.get(y).get(x);
    }
    
    /** Gets the state of a coordinate in the gameboard. 
     * @param p The MyPoint identifying a coordinate
     * @return boolean
     */
    public boolean getEntity(MyPoint p) {
        return getEntity(p.x, p.y);
    }
    
    /** Updates the gameboard with changes for the next generation.
     * @param rules The rule engine to be used to determine state changes.
     */
    public void tick(RuleEngine rules) {
        HashMap<MyPoint, Boolean> updates = new HashMap<MyPoint, Boolean>();
            
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                MyPoint p = new MyPoint(x, y);
                boolean entityValue = getEntity(p);
                int neighbourCount = countNeighbours(p, rules);
                boolean newState = rules.deadOrAlive(neighbourCount, entityValue);

                if (newState != entityValue) {
                    updates.put(p, newState);
                }
            }
        }
        if (updates.size() > 0) {
            ArrayList<MyPoint> points = new ArrayList<>(updates.keySet());

            for (int i = 0; i < points.size(); i++) {
                flipEntity(points.get(i));
            }
        }
    }
    
    /** Counts the number of alive entities around the point p. 
     * @param p The point whose neighbours need to be counted.
     * @param rules The rule engine which determines how to identify a neighbour.
     * @return int The number of alive neighbours the entity has.
     */
    public int countNeighbours(MyPoint p, RuleEngine rules) {
        ArrayList<MyPoint> neighbours = rules.getNeighbourCoordinates(p, boardSize);
        int neighbourCount = 0;

        for (int i = 0; i < neighbours.size(); i++) {
            if (getEntity(neighbours.get(i))) {
                neighbourCount++;
            }
        }
        return neighbourCount;
    }
}
