package main;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Display() {
        Canvas canvas = new Board();
        add(canvas, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        canvas.createBufferStrategy(2);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> {
            new Display();
        });
    }
}
