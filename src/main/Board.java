package main;

import ragdoll.Body;
import ragdoll.Limb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

public class Board extends Canvas implements ActionListener {

    private boolean repaintInProgress;
    private Body body;

    private int mouseX;
    private int mouseY;

    public Board() {

        body = new Body();
        body.build(Display.WIDTH, Display.HEIGHT);

        setIgnoreRepaint(true);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        new Timer(16, this).start();
    }

    public void myRepaint() {
        if(repaintInProgress) {
            return;
        }
        repaintInProgress = true;
        BufferStrategy bufferStrategy = getBufferStrategy();
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        //clean screen
        g.setColor(Color.white);
        g.fill(new Rectangle2D.Double(0,0,Display.WIDTH, Display.HEIGHT));


        //update
        body.update(mouseX, mouseY);

        //draw
        g.setColor(Color.BLACK);
        for(Limb limb : body.limbs) {
            g.fill(new Rectangle2D.Float(limb.start[0] - 5, limb.start[1], 10, 10));
            g.fill(new Rectangle2D.Float(limb.end[0] - 5, limb.end[1], 10, 10));
            g.draw(new Line2D.Float(limb.start[0], limb.start[1], limb.end[0], limb.end[1]));
        }

        g.dispose();
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
        repaintInProgress = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myRepaint();
    }
}
