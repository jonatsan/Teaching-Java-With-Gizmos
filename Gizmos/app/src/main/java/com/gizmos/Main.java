package com.gizmos;

import com.gizmos.utility.Gizmos;
import com.gizmos.utility.TurtleWindow;

public class Main {
    private final TurtleWindow turtleWindow;

    public Main(TurtleWindow turtleWindow) {
        this.turtleWindow = turtleWindow;
    }

    public static void main(String[] args) {
        // Create a "Main" object and
        // store it in the variable called main.
        Main main = new Main(new TurtleWindow());

        // Create a tray icon that is
        // hooked up to send events to the main variable.
        Gizmos.createTrayIcon(main);

        // When the T.U.R.T.L.E. window is closed,
        // this exits the program.
        Gizmos.setupWindowClose(main);

        // Call the start method.
        // It's like sending a message to
        // the main variable, saying, "We're Ready!"
        main.start();
    }

    public TurtleWindow getTurtleWindow() {
        return turtleWindow;
    }

    /**
     * Called when your java program starts.
     */
    public void start() {
    }

    /**
     * Called once when your gizmo is activated.
     */
    public void gizmoInit() {
    }

    /**
     * Called repeatedly while your gizmo is on.
     */
    public void gizmoPeriodic() {
    }

    /**
     * Called once when your gizmo is turned off.
     */
    public void gizmoExit() {
    }
}
