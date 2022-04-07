package com.gizmos;

import com.gizmos.utility.GizmosFramework;

public class Main {
    public static void main(String[] args) {
        // Create a "Main" object and
        // store it in the variable called main.
        Main main = new Main();

        // Create a tray icon that is
        // hooked up to send events to the main variable.
        GizmosFramework.createTrayIcon(main);

        // When the T.U.R.T.L.E. window is closed,
        // this exits the program.
        GizmosFramework.setupWindowClose(main);

        // Register keypress listeners so that we
        // know when a key is pressed or not.
        GizmosFramework.setupKeypressEvents(main);

        // Call the start method.
        // It's like sending a message to
        // the main variable, saying, "We're Ready!"
        main.start();
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
