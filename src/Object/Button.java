/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author 1635598
 */
public class Button extends java.awt.Component {
    private BufferedImage img;
    public BufferedImage getImg() {
        return img;
    }
    
    public Button(String link) {
        try {
            img = ImageIO.read(new File(link));
        } catch (IOException ex) { }
    }
    
    public void setProperty() {
    
    setBounds(0, 0, img.getWidth(), img.getHeight());
    addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.drawImage(img, 0, 0, this);  
    }
}
