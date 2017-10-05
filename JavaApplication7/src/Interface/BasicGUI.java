/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Proprio
 */
public abstract class BasicGUI {
    private BufferedImage im;
    public final JPanel panel;

    public BasicGUI() {
        panel = new JPanel();
    }
    
    public JPanel panel() {
        return panel;
    }
    
    //public abstract void draw(Graphics2D buffer);
    public abstract void update();
    
}
