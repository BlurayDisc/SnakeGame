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
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RuN
 */
public class ScorePanel extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private final GameController gc;
    private JLabel scoreLabel;
    private JLabel titleLabel;
    private JButton exitButton;
    private JButton startButton;
    private JButton restartButton;
    
    public ScorePanel(GameController gc) {
        super();
        setBounds(0, 0, 150, 300);
        setBackground(Color.BLACK);
        setOpaque(true);
        setVisible(true);
        setLayout(null);
        
        this.gc = gc;
        
        initLabels();
        initButtons();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        g2.setPaint(Color.DARK_GRAY);
        g2.drawRect(1, 1, 150, 298);
    }
    
    private void initLabels() {
        titleLabel = new JLabel();
        titleLabel.setText("Game of Snakes");
        titleLabel.setBounds(30, 20, 100, 25);
        titleLabel.setForeground(Color.white);
        add(titleLabel);
        
        scoreLabel = new JLabel();
        scoreLabel.setText("Points: " + gc.length);
        scoreLabel.setBounds(30, 55, 100, 25);
        scoreLabel.setForeground(Color.white);
        add(scoreLabel);
    }
    
    private void initButtons() {
        // Exit Button
        exitButton = new JButton();
        exitButton.setText("Quit");
        exitButton.setBounds(25, 240, 110, 50);
        exitButton.setBackground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener((ActionEvent evt) -> {
            exitButtonClicked(evt);
        });
        add(exitButton);
        
        // Start Button
        startButton = new JButton();
        startButton.setText("Start");
        startButton.setBounds(25, 120, 110, 50);
        startButton.setBackground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.addActionListener((ActionEvent evt) -> {
            startButtonClicked(evt);
        });
        add(startButton);
        
        // Pause Button
        restartButton = new JButton();
        restartButton.setText("Restart");
        restartButton.setBounds(25, 180, 110, 50);
        restartButton.setBackground(Color.WHITE);
        restartButton.setFocusable(false);
        restartButton.addActionListener((ActionEvent evt) -> {
            restartButtonClicked(evt);
        });
        add(restartButton);
    }
    
    private void exitButtonClicked(ActionEvent evt) {
        System.exit(0);
    }
    
    private void startButtonClicked(ActionEvent evt) {
        gc.startGame();
        startButton.setVisible(false);
    }
    
    private void restartButtonClicked(ActionEvent evt) {
        gc.restartGame();
    }
    
    public void updatePoints() {
        scoreLabel.setText("Points: " + gc.getPoints());
    }
    
}
