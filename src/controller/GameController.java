/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import model.Snake;
import model.Square;
import view.MainFrame;

/**
 *
 * @author RuN
 */
public class GameController {
    private final Random random ;
    private final MainFrame frame;
    public final int length;
    private Snake snake;
    private Square randomFood;
    private Timer randomSpawnTimer;
    private ActionListener randomListener;
    private Timer autoMoveTimer;
    private ActionListener autoListener;
    
    public GameController(MainFrame frame) {
        this.frame = frame;
        random = new Random();
        length = 5;     // default length
    }
    
    public void faceUp() {
        if (snake.faceUpPossible()) {
            snake.setFacing(Snake.UP);
        }
    }
    public void faceDown() {
        if (snake.faceDownPossible()) {
            snake.setFacing(Snake.DOWN);
        }
    }
    
    public void faceLeft() {
        if (snake.faceLeftPossible()) {
            snake.setFacing(Snake.LEFT);
        }
    }
    
    public void faceRight() {
        if (snake.faceRightPossible()) {
            snake.setFacing(Snake.RIGHT);
        }
    }
    
    public void moveSnake() {
        if (snake.getFacing() == Snake.UP) {
            snake.moveUp();
        } else if (snake.getFacing() == Snake.DOWN) {
            snake.moveDown();
        } else if (snake.getFacing() == Snake.LEFT) {
            snake.moveLeft();
        } else if (snake.getFacing() == Snake.RIGHT) {
            snake.moveRight();
        }
        
        frame.repaint();
        
        checkIsFood();
        checkIsAlive();
    }
    
    private void checkIsFood() {
        if (snake.moveIntoFood() == true) {
            frame.updatePoints();
            changeSpeed();
        }
    }
    
    private void checkIsAlive() {
        if (snake.isAlive() == false){
            randomSpawnTimer.stop();
            randomSpawnTimer.removeActionListener(randomListener);
            autoMoveTimer.stop();
            autoMoveTimer.removeActionListener(autoListener);
            frame.gamePanelRepaint();
        }
    }
    
    public void initSnake() {
        if (snake == null) {
            snake = new Snake(length);
            frame.gamePanelRepaint();
        }
    }
    
    public int getPoints() {
        return snake.getLength();
    }
    
    public void changeSpeed() {
        if (snake.getLength() >= 80) {
            autoMoveTimer.setDelay(15);
        } else if (snake.getLength() >= 70) {
            autoMoveTimer.setDelay(25);
        } else if (snake.getLength() >= 60) {
            autoMoveTimer.setDelay(50);
        } else if (snake.getLength() >= 50) {
            autoMoveTimer.setDelay(100);
        } else if (snake.getLength() >= 40) {
            autoMoveTimer.setDelay(200);
        } else if (snake.getLength() >= 30) {
            autoMoveTimer.setDelay(400);
        } else if (snake.getLength() >= 20) {
            autoMoveTimer.setDelay(600);
        } else if (snake.getLength() >= 10) {
            autoMoveTimer.setDelay(800);
        }
    }
    
    public boolean snakeIsAlive() {
        if (snake == null) {
            return true;
        }
        return snake.isAlive();
    }
    
    public Square generateRandomFood() {

        int x = random.nextInt(40) * 10;
        int y = random.nextInt(30) * 10;
        
        return new Square(x, y);
    }
        
    public void drawSnake(Graphics2D g2) {
        if (snake != null) {
            snake.draw(g2);
        }
    }
    
    public void drawFood(Graphics2D g2) {
        if (snake != null) {
            for (int i = 0; i < snake.getFoodList().size(); i++) {
                snake.getFoodList().get(i).drawFood(g2);
            }
        }
    }
    
    public void drawEndGame(Graphics2D g2) {
        g2.setPaint(Color.RED);
        g2.drawRect(147, 112, 100, 60);
        g2.drawString("Game Over!", 165, 150);
        snake = null;
    }
  
    private void initTimers() {        
        randomListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (snake.getFoodList().size() < 2) {
                    randomFood = generateRandomFood();
                    snake.addFood(randomFood);
                    frame.repaint();
                }
            }
        };
        randomSpawnTimer = new Timer(3000, randomListener);             // spawn every 3 seconds
        
        autoListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                moveSnake();
            }
        };
        autoMoveTimer = new Timer(1000, autoListener);             // move every 1 second
    }
    
    public void startTimers() {
        randomSpawnTimer.start();
        autoMoveTimer.start();
    }
    
    public void restartGame() {
        frame.dispose();
        new MainFrame().setVisible(true);
    }
    
    public void startGame() {
        initSnake();
        initTimers();
        startTimers();
    }
    
    public Snake getSnake() {
        return snake;
    }
}
