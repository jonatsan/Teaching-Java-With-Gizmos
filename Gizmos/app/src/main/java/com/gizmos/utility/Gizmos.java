package com.gizmos.utility;

import javax.swing.JOptionPane;

public class Gizmos {
    /**
     * Alerts the user with the message
     *
     * @param message The message to prompt the user with
     */
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Returns {@code true} when the key is currently pressed down
     * @param key The {@link Key} to check for.
     * @return Whether the key is pressed down.
     */
    public static boolean getKeyPressed(Key key) {
        return GizmosFramework.keyStates.getOrDefault(key.keycode, false);
    }
}
