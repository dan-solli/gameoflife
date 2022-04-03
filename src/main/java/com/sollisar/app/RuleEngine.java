package com.sollisar.app;

import java.util.ArrayList;

/**
 * The rule engine determines whether wrapping on edges are allowed or not, 
 * what counts as a neighbour or not to a single cell 
 * and how to determine if a entity should be alive or not based on its number
 * of neighbours.
 */
public class RuleEngine {
    private boolean edgeWrapping = true;

    /** Constructor with the option to set edgewrapping to true or false.
     * @param wrap Whether wrapping at edges should be on (true) or off (false)
     */ 
    public RuleEngine(boolean wrap) {
        edgeWrapping = wrap;
    }

    
    /** The method returns whether an entity on a specific coordinate with its number 
     * of neighbours should be alive or dead on the next iteration.
     * @param numberOfNeighbours The number of neighbours an entity has.
     * @param currentState Whether the entity currently is alive or not.
     * @return boolean Whether the entity is alive (true) or dead (false) during next iteration.
     */
    public boolean deadOrAlive(int numberOfNeighbours, boolean currentState) {
        if (!currentState && numberOfNeighbours == 3)
            return true;
        else if (currentState && numberOfNeighbours >= 2 && numberOfNeighbours <= 3)
            return true;
        else {
            return false;
        }
    }

    
    /** The method returns an ArrayList of MyPoint with absolute coordinates of neighbours to the point origin.
     * @param origin The location of the entity
     * @param boardSize The size of the board
     * @return ArrayList of MyPoints A list of coordinates to the neighbours of origin. 
     * The return value can vary in size depending on the ruleset being used (with general ruleset, without wrapping and origin on an edge, 
     * the list will only show max 5 neighbour coordinates, max 3 if the origin is in a corner).
     */
    public ArrayList<MyPoint> getNeighbourCoordinates(MyPoint origin, int boardSize) {
        ArrayList<MyPoint> neighbours = new ArrayList<MyPoint>();

        for (int i = -1; i < 2; i++) {
            int local_x = resolveRelativeCoord(origin.x + i, boardSize), 
                local_y = -1;

            for (int j = -1; j < 2; j++) {

                // Skip origin.
                if (i == 0 && j == 0) {
                    continue;
                }

                local_y = resolveRelativeCoord(origin.y + j, boardSize);
                
                if (local_x != -1 && local_y != -1) {
                    neighbours.add(new MyPoint(local_x, local_y));
                }
            }
        }
        return neighbours;
    }

    
    /** The method returns a single number, part of a coordinate based on location in grid compared
     * to board size as well as ruleset for wrapping.
     * @param coord A number, part of a coordinate.
     * @param boardSize Size of board.
     * @return int The modified number.
     */
    private int resolveRelativeCoord(int coord, int boardSize) {
        if (coord < 0) {
            return edgeWrapping ? boardSize - 1 : -1;
        }
        else if (coord >= boardSize) {
            return edgeWrapping ? 0 : -1;
        } 
        return coord;
    }

    
    /** Returns whether wrapping around edges should be done or not.
     * @return boolean
     */
    public boolean getWrapping() {
        return edgeWrapping;
    }
}
