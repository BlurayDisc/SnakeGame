/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author RuN
 */
public class GamePanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private final GameController gc;
    
    public GamePanel(GameController gc) {
        super();
        this.gc = gc;
        
        setBounds(150, 0, 400, 300);
        setBackground(Color.WHITE);
        setOpaque(true);
        setFocusable(true);
        requestFocusInWindow();
        
        initKeyListeners();  
    }
    
    private void initKeyListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (gc.getSnake() != null) {
                    if (key == KeyEvent.VK_UP) {
                        gc.faceUp();
                    } else if (key == KeyEvent.VK_DOWN){
                        gc.faceDown();
                    } else if (key == KeyEvent.VK_LEFT){
                        gc.faceLeft();
                    } else if (key == KeyEvent.VK_RIGHT){
                        gc.faceRight();
                    } else if (key == KeyEvent.VK_SPACE) {
                        gc.moveSnake();
                    }
                }
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // draws the border
        g2.setStroke(new BasicStroke(2));
        g2.setPaint(Color.DARK_GRAY);
        g2.drawRect(1, 1, 398, 298);
                
        gc.drawSnake(g2);
        gc.drawFood(g2);
        
        if (gc.snakeIsAlive() == false) {
            gc.drawEndGame(g2);
        }
    }
}
