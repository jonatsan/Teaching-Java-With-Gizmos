package com.gizmos.utility;

import java.awt.*;
import javax.swing.*;

public class TurtleWindow extends JFrame {
    public class TurtleCanvas extends Canvas {
        private final Image TURTLE_IMG;
        private final static int TURTLE_LENGTH = 20;

        TurtleCanvas() {
            var iconURL = Gizmos.class.getResource("Turtle.jpg");
            TURTLE_IMG = Toolkit.getDefaultToolkit().createImage(iconURL);
        }

        @Override
        public void paint(Graphics g) {
            g.drawImage(TURTLE_IMG, turtleX, turtleY, TURTLE_LENGTH, TURTLE_LENGTH, null);
        }
    }

    public int turtleX = 0;
    public int turtleY = 0;

    private TurtleCanvas canvas;

    public TurtleWindow() {
        super("Gizmos T.U.R.T.L.E.");

        canvas = new TurtleCanvas();
        canvas.setBackground(Color.LIGHT_GRAY);
        add(canvas);

        setSize(500, 400);
    }

    public void move(int dx, int dy) {
        turtleX += dx;
        turtleY += dy;
    }

    @Override
    public void repaint() {
        canvas.repaint();
    }
}
