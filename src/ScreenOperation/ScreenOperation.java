package ScreenOperation;

import Interface.Inventory;
import Object.Map;
import Object.WinFrame;
import World.Background;
import World.BasicWorld;
import World.Chunk;
import World.Tree;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 1635598
 */
public class ScreenOperation extends JApplet implements Runnable {
    public static int x, y;
    private int rx, ry;
    private BufferedImage camera;
    private Graphics2D buffer;
    private final RenderingHints map = WinFrame.rh();
    private Thread t;
    private JFrame frame;
    private boolean wallMode = false;
    private JPanel pane = new JPanel();
    public static int currentChunk = 0;
    public static boolean inventoryOpen = false;
    

    public JPanel getPane() {
        return pane;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public BufferedImage getCamera() {
        return camera;
    }
    
    
    
    public ScreenOperation(JFrame frame, Graphics2D g) {
        this.frame = frame;
        
        buffer = g;
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setFocusable(true);
        WinFrame.f.addKeyListener(initKeyEvent());
        pane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        this.add(pane);
        
    }

    private KeyAdapter initKeyEvent() {
        return new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                try{
                    if(evt.getKeyCode() == 37) {
                        
                        if(rx <= 0 && x != 0) {
                            rx = 256;
                        }
                        
                        if(rx <= 0 && x == 0) {
                            rx = 0;
                        }
                        else {
                            rx -= 16; x+=16;
                        }
                            
                        if(x > 0)
                            x = 0;
                    }
                    if(evt.getKeyCode() == 38)
                        y -= 16;
                    if(evt.getKeyCode() == 39) {
                        rx += 16;
                        x -= 16;
                        if(rx >= 256) {
                            rx = 0;
                            BasicWorld.chunkList.add(new Chunk(ScreenOperation.currentChunk));
                        }
                    }
                    if(evt.getKeyCode() == 40)
                        y += 16;
                    
                    ScreenOperation.currentChunk = -x/256;
                    //System.out.println(currentChunk);
                    
                    if(evt.getKeyCode() == 69) {
                        if(!inventoryOpen) {
                            inventoryOpen = true;
                           Map.inv.panel().setVisible(true);                            
                        } 
                        else
                            inventoryOpen = false;
                    }
                    
                    if(evt.getKeyCode() == 49) {
                        BasicWorld.chunkList.get(ScreenOperation.currentChunk).getChunk().createGraphics().drawImage(Background.background.getSubimage(rx-(rx%16), y-(y%16), 16, 16), rx%256, y-(y%16), null);
                    }
                    
                    
                    
                    if(evt.getKeyCode() == 50) {
                        BasicWorld.chunkList.get(ScreenOperation.currentChunk).getChunk().createGraphics()
                                .drawImage(Inventory.currentItem.getIt(), rx%256, y-(y%16), null); 
                    }
                    
                    
                    if(evt.getKeyCode() == 83) {
                        /*for(int i = 0; i < BasicWorld.chunkList.size(); i++)
                            ImageIO.write(BasicWorld.chunkList.get(i).getChunk(), "png", new File("./chunkImage/chunk_" + String.valueOf(i) + ".png"));*/
                        ImageIO.write(BasicWorld.world, "png", new File("./chunkImage/world.png"));
                    }
                    
                    if(evt.getKeyCode() == 65) {
                        if(wallMode)
                            wallMode = false;
                        else
                            wallMode = true;
                    }
                    //System.out.println(evt.getKeyCode());
                }catch(Exception e) {}
            }
        };
    }
    
    public void keyPressed(KeyEvent evt) {
    }
    
    @Override
    public void start() {
        if(t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        while(t != null) {
            try {
                camera = ImageIO.read(new File("./Texture/pointer.png"));
                
            } catch (IOException ex) {
                
            }
        }
    }
    
    public void drawInBuffer(Graphics2D buffer) throws IOException {
        buffer.setPaint(Color.RED);
        buffer.drawRect(rx%256, y, 16,16);
        buffer.setPaint(Color.WHITE);
        buffer.drawString("X: " + x + ", Y: " + y + ", Real X: " + rx , 5, 25);
        buffer.drawString("Current chunk: " + currentChunk , 5, 35);
    }
}
