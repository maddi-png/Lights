    // This program is copyright VUW.
// You are granted permission to use it to construct your answer to a Onslow College 13DTC assignment.
// You may not distribute it in any other way without permission.

/* Exercise for WGC 13DT
 * Name: Maddi Homewood
 * Email: homewoodma@wgc.school.nz
 * Date: 31/03
 */

import ecs100.*;
import java.awt.Color;

/** A Lamp object represents a table lamp on the graphics pane. A lamp consists of a colored bulb and a stem.
 *   It remembers its current position, its color and whether it is on or off.
 *   Its initial position and its color are set when it is constructed.
 *   The diameter of a bulb should be 80.
 *   The height of the stem should be 80.
 *   The width of the stem should be 20.
 *   It has three methods:
 *     draw(),        which draws the lamp at its current position.
 *     onBulb(x,y),   which reports whether the point(x,y) is on the bulb.
 *     onStem(x,y),   which reports whether the point(x,y) is on the stem.
 *     turnOff(),     which turns the light off.
 *     changeColor(), which turns the light on if it is off; changes its color to a random bright colour if it is already on. 
 */

public class Lamp{

    public static final double SIZE = 80;  // diameter of the bulb and height of the stem
    // width of the stem is SIZE/4
    //fields
    public static final double STEMWIDTH = SIZE/4;
    private double left, top, bottom, stemBottom;
    private double xCoord, yCoord;
    private Color col = Color.black;
    private Color oldColor;
    

    /** Constructor: passed the initial position.
     * Initialises the fields
     */
    public Lamp(double x, double y){
        /*# YOUR CODE HERE */
        xCoord = x;
        yCoord = y;
        left = x - SIZE / 2.0;
        top = y - SIZE / 2.0;
        bottom = y + SIZE / 2.0;
        stemBottom = bottom + SIZE;
    }

    /**
     * draws the lamp at its current position:
     * - the bulb of the right colour.
     * - the stem darkGray
     * The height of the stem is the same as the diameter of the bulb
     * The width of the stem is a quater of its height
     */
    public void draw(){
        /*# YOUR CODE HERE */
        UI.setColor(col);
        UI.setLineWidth(1);
        UI.fillOval(this.left, this.top, SIZE, SIZE);
        
        UI.setLineWidth(STEMWIDTH);
        UI.setColor(Color.darkGray);
        UI.drawLine(this.xCoord, bottom, this.xCoord, stemBottom);
    }   

    /** 
     * Reports whether the point (x,y) is on the bulb.
     * (x and y represent the position where the mouse was released):
     */
    public boolean onBulb(double x, double y){
        // an easy approximation is to pretend it is the enclosing rectangle.
        // It is nicer to do a little bit of geometry and get it right
        /*# YOUR CODE HERE */
        if ((x >= this.left) && (x<=(this.left + SIZE) && (y >= this.top) && (y <= this.bottom))) {
            System.out.println("Bulb clicked");
            return true;
        } else {
            return false;
        }
    }   

    /**
     * Reports whether the point (x,y) is on the stem.
     * (x and y represent the position where the mouse was released):
     */
    public boolean onStem(double x, double y){
        /*# YOUR CODE HERE */
        if ((x >= this.xCoord - STEMWIDTH / 2.0) && (x <= this.xCoord + STEMWIDTH / 2.0) && (y >= this.bottom) && (y <= this.stemBottom)) {
            return true;
        } else {
            return false;
        }
    }   

    /**
     * Turns the light off.
     * Does not redraw
     */
    public void turnOff(){
        /*# YOUR CODE HERE */
        if (col == Color.black) {
            col = oldColor;
        } else {
            oldColor = col;
            col = Color.black;
        }
    }   

    /** changeColor method (no parameters):
     * Turns the light on (if it is off)
     * Changes its color to a random bright colour (if it is already on).
     * Does not redraw
     */
    public void changeColor(){
        /*# YOUR CODE HERE */
        col = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }   
}
