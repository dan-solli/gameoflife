package com.sollisar.app;

/** Simple class to handle a coordinate (x, y) 
 * In future versions, this class may handle state of a coordinate as well as 
 * methods for distances to other points or references to bounding boxes of patterns
 * to optimize passes and drawing. It could also contain its number of neighbours and
 * methodology to keep that count updated over generations.
*/
public class MyPoint {
    /** The coordinates x and y
     * 
     */
    protected int x, y;

    /** Constructor for MyPoint
     * @param xx Sets the x coordinate
     * @param yy Sets the y coordinate
     */
    public MyPoint(int xx, int yy) {
        x = xx;
        y = yy;
    }
        
    /** Returns a string representing the coordinate.
     * @return String
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
