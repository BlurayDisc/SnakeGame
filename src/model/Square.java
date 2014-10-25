/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

;

/**
 *
 * @author RuN
 */
public class Square {
    
    public final int x, y;
    private final Rectangle2D.Double rect;
    private final Rectangle2D.Double headRect;
    // private final Ellipse2D.Double leftEye, rightEye;
    public final int length;
    private final Color[] colors;
    private final Random r;
    private final int colorIndex;
    
    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        length = 10;
        r = new Random();
        colorIndex = r.nextInt(8);
        rect = new Rectangle2D.Double(x, y, length, length);
        headRect = new Rectangle2D.Double(x - 1, y - 1, length + 2, length + 2);
        // leftEye = new Ellipse2D.Double(x, y + 2, length - 6, length - 6);
        // rightEye = new Ellipse2D.Double(x + 6, y + 2, length - 6, length - 6);
        
        colors = new Color[8];
        colors[0] = Color.BLUE;
        colors[1] = Color.CYAN;
        colors[2] = Color.DARK_GRAY;
        colors[3] = Color.GREEN;
        colors[4] = Color.ORANGE;
        colors[5] = Color.PINK;
        colors[6] = Color.BLACK;
        colors[7] = Color.RED;        
    }
    
    public void drawHead(Graphics2D g2) {
        g2.setPaint(Color.BLACK);
        g2.fill(rect);
        g2.setPaint(Color.WHITE);
        g2.draw(headRect);
        g2.setPaint(Color.RED);
        // g2.draw(leftEye);
        // g2.draw(rightEye);
    }
    
    public void draw(Graphics2D g2) {
        g2.setPaint(Color.BLACK);
        g2.fill(rect);
        g2.setPaint(Color.WHITE);
        g2.draw(rect);
    }
    
    public void drawFood(Graphics2D g2) {
        g2.setPaint(colors[colorIndex]);
        g2.fill(rect);
        g2.setPaint(Color.WHITE);
        g2.draw(rect);
    }
}
