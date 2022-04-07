package com.gizmos.utility;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

import com.gizmos.Main;

public class GizmosFramework {
    /**
     * The number of periodic ticks per second.
     */
    private static final int PERIODIC_RATE = 60;

    private static volatile GizmoState gizmoState = GizmoState.DISABLED;
    /** package-private */
    static final TurtleWindow turtleWindow = new TurtleWindow();

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
                        turtleWindow.setVisible(true);
                        main.gizmoInit();
                        break;
                    case ENABLED:
                        gizmoState = GizmoState.DISABLED;
                        trayIcon.setToolTip("Gizmo Turned OFF");
                        turtleWindow.setVisible(false);
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
                            turtleWindow.repaint();
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
        turtleWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    /** package-private */
    static Map<Integer, Boolean> keyStates = new HashMap<>();

    public static void setupKeypressEvents(Main main) {
        turtleWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyStates.put(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyStates.put(e.getKeyCode(), false);
            }
        });
    }
}
