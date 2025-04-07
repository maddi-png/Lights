// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a Onslow College 13DTC assignment.
// You may not distribute it in any other way without permission.

/* Exercise for WGC 13DT
 * Name:
 * Email:
 * Date:
 */

import ecs100.*;
import java.awt.Color;

/** Exercise for defining objects.
 *  This program contains methods for testing Lamp objects.
 *  It is all written for you, but you need to read it to see 
 *  what the Lamp class should do.
 */

public class ObjectsExercise {

    // the lamps: need to be in a field because two different methods need to access it.
    //private Lamp myLamp; 
    //private Lamp yrLamp;
    private final int STARTX = 300;
    private final int YPOS = 200;
    private final int DIFFX = 100;
    private final int MAXROWS = 3;
    private  int maxLamps = 1;
    private Lamp[][] lamps; // array of lamps

    public ObjectsExercise() {
        UI.setMouseListener(this::manageLamps);   // the mouse will switch on/off the lamps
    }

    /** Makes several Lamp objects */
    public void createLamps() {
        UI.clearPanes();
        lamps = new Lamp[MAXROWS][];
        for (int i = 0; i < MAXROWS; i++) {
            Lamp[] lampRow = new Lamp[maxLamps];
            maxLamps += 2;
            lamps[i] = lampRow;
            System.out.println(lamps);
        }
        maxLamps = 1;
        for (int i = 0; i < MAXROWS; i++) {
            for (int j = 0; j < maxLamps; j++) {
                lamps[i][j] = new Lamp(STARTX + DIFFX * (j-i), YPOS * (i + 1));
            }
            maxLamps += 2;
        }

        for (Lamp[] row : lamps) {
            for (Lamp lamp : row) {
                lamp.draw();
            }
        }
        UI.println("Click on the lamps to turn them on and off and change their color");
    }    

    /** Manages the Lamp objects */
    public void manageLamps(String action, double x, double y) {
        if (action.equals("released")){
            maxLamps = 1;
            for (int i = 0; i < MAXROWS; i++) {
                for (int j = 0; j < maxLamps; j++) {
                    if (lamps == null) {
                        UI.printMessage("Press Lamps button first to create some lamps");
                        return;  // the lamps haven't been constructed yet.
                    }
                    if (lamps[i][j].onBulb(x,y)){
                        lamps[i][j].changeColor();
                        lamps[i][j].draw();
                    }
                    else if (lamps[i][j].onStem(x,y)){
                        lamps[i][j].turnOff();
                        lamps[i][j].draw();
                    }
                }
                maxLamps += 2;
            }
        }
    }    

    public void clear() {
        UI.clearPanes();
        lamps = null;
    }
    
    public void runLights() {
        boolean running;
        while (running=true) {
            maxLamps = 1;
            for (int i = 0; i < MAXROWS; i++) {
                for (int j = 0; j < maxLamps; j++) {
                    lamps[i][j].changeColor();
                    lamps[i][j].draw();
                }
                maxLamps += 2;
            }
            UI.sleep(500);
        }
    }

    // Main
    /** Create a new ObjectExercise object and setup the interface */
    public static void main(String[] args) {
        ObjectsExercise oe = new ObjectsExercise();

        UI.addButton("Clear", oe::clear );
        UI.addButton("Lamps", oe::createLamps );
        UI.addButton("Quit", UI::quit );
        UI.addButton("Run lights", oe::runLights );

    }

}
