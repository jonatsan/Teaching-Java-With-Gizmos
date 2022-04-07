package com.gizmos.utility;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import com.gizmos.Main;

public class Gizmos {
    /**
     * The number of periodic ticks per second.
     */
    private static final int PERIODIC_RATE = 60;

    private static volatile GizmoState gizmoState = GizmoState.DISABLED;

    public static void createTrayIcon(Main main) {
        SystemTray tray = SystemTray.getSystemTray();

        var iconURL = Gizmos.class.getResource("Alert.png");
        Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);

        TrayIcon trayIcon = new TrayIcon(icon, "Gizmo Turned OFF");
        trayIcon.setImageAutoSize(true);
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                switch (gizmoState) {
                    case DISABLED:
                        gizmoState = GizmoState.ENABLED;
                        trayIcon.setToolTip("Gizmo Turned ON");
                        main.getTurtleWindow().setVisible(true);
                        main.gizmoInit();
                        break;
                    case ENABLED:
                        gizmoState = GizmoState.DISABLED;
                        trayIcon.setToolTip("Gizmo Turned OFF");
                        main.getTurtleWindow().setVisible(false);
                        main.gizmoExit();
                        break;
                    default:
                        throw new IllegalStateException("Unreachable!");
                }
            }
        });

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        new Thread() {
            public void run() {
                while (true) {
                    switch (gizmoState) {
                        case DISABLED:
                            // No-Op
                            break;
                        case ENABLED:
                            main.getTurtleWindow().repaint();
                            main.gizmoPeriodic();
                    }

                    try {
                        Thread.sleep(1000 / PERIODIC_RATE);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
        }.start();
    }

    public static void setupWindowClose(Main main) {
        main.getTurtleWindow().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Alerts the user with the message
     *
     * @param message The message to prompt the user with
     */
    public static void alert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
