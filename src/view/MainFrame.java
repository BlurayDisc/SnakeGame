/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GameController;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author RuN
 */
public class MainFrame extends JFrame {
    
    private static final int MainFrame_WIDTH_INT = 550;
    private static final int MainFrame_HEIGHT_INT = 300;
    private static GamePanel gp;
    private static ScorePanel sp;
    private static GameController gc;
    private static final long serialVersionUID = 1L;
    
    public MainFrame() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(MainFrame_WIDTH_INT, MainFrame_HEIGHT_INT);
        init();
        setWindowLocation();
    }
    
    private void init() {
        gc = new GameController(this);
        
        gp = new GamePanel(gc);
        this.add(gp);
        
        sp = new ScorePanel(gc);
        this.add(sp);
    }
    
    public void updatePoints() {
        sp.updatePoints();
    }
    
    public void gamePanelRepaint() {
        gp.repaint();
    }
    
    private void setWindowLocation(){
        setUndecorated(true);
        Dimension windowSize = this.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth() - windowSize.getWidth()) / 2, (int)(screenSize.getHeight() - windowSize.getHeight()) / 2);
    }
}
