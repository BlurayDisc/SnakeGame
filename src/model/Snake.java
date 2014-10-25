/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 *
 * @author RuN
 */
public class Snake {
    
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    private LinkedList<Square> foodList;
    private LinkedList<Square> snakeBody;
    private Square head;
    private Square foodSquare;
    private boolean alive;
    private boolean moveIntoFood;
    
    private int facing;
    
    public Snake(int size) {
        facing = RIGHT;
        alive = true;
        foodList = new LinkedList<>();
        initSnake(size);
    }
    
    private void initSnake(int size) {
        snakeBody = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            snakeBody.add(new Square(210 - i * 10, 150));
        }
        head = snakeBody.getFirst();
    }
    
    public void moveUp() {
        head = new Square(head.x, head.y - head.length);
        applyMoveLogics();
    }
    
    public void moveDown() {
        head = new Square(head.x, head.y + head.length);
        applyMoveLogics();
    }
        
    public void moveLeft() {
        head = new Square(head.x - head.length, head.y);
        applyMoveLogics();
    }
            
    public void moveRight() {
        head = new Square(head.x + head.length, head.y);
        applyMoveLogics();
    }
    
    public void applyMoveLogics() {
        if (isBlocked()) {
            alive = false;
            moveIntoFood = false;
        } else if (isFood()) {
            alive = true;
            moveIntoFood = true;
            snakeBody.addFirst(head);
        } else {
            alive = true;
            moveIntoFood = false;
            snakeBody.addFirst(head);
            snakeBody.removeLast();
        }
    }
    
    public boolean faceUpPossible() {
        boolean possible = true;
        // faceUp
        if (head.x == snakeBody.get(1).x && head.y - head.length == snakeBody.get(1).y) {
            possible = false;
        }
        return possible;
    }
    
    public boolean faceDownPossible() {
        boolean possible = true;
        // faceDown
        if (head.x == snakeBody.get(1).x && head.y + head.length == snakeBody.get(1).y) {
            possible = false;
        }
        return possible;
    }
        
    public boolean faceLeftPossible() {
        boolean possible = true;
        // faceLeft
        if (head.x - head.length == snakeBody.get(1).x && head.y == snakeBody.get(1).y) {
            possible = false;
        }
        return possible;
    }
            
    public boolean faceRightPossible() {
        boolean possible = true;
        // faceRight
        if (head.x  + head.length == snakeBody.get(1).x && head.y == snakeBody.get(1).y) {
            possible = false;
        }
        return possible;
    }
    
    public boolean isBlocked() {
        if (head.x >= 400 || head.x < 0 || head.y >= 300 || head.y < 0) {
            return true;
        }
        for (int i = 3; i < snakeBody.size(); i++) {
            if (snakeBody.get(i).x == head.x && snakeBody.get(i).y == head.y) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFood() {
        if (foodList == null) {
            return false;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (head.x == foodList.get(i).x && head.y == foodList.get(i).y) {
                foodList.remove(i);
                // foodSquare = new Square(x, y);
                return true;
            }
        }
        return false;
    }    
    
    public int getLength() {
        return snakeBody.size();
    }
    
    public void draw(Graphics2D g2) {
        head.drawHead(g2);
        for (int i = 1; i < snakeBody.size(); i++) {
            snakeBody.get(i).draw(g2);
        }
    }
    
    public Square getHead() {
        return head;
    }
    
    public LinkedList<Square> getSnake() {
        return snakeBody;
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public boolean moveIntoFood() {
        return moveIntoFood;
    }
    
    public Square getFoodSquare() {
        return foodSquare;
    }
    
    public LinkedList<Square> getFoodList() {
        return foodList;
    }
    
    public void addFood(Square sq) {
        if (foodList == null) {
            foodList = new LinkedList<>();
        }
        foodList.add(sq);
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }
}
