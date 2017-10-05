/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import Object.Map;
import Object.WinFrame;
import ScreenOperation.ScreenOperation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 *
 * @author 1635598
 */
public class BasicWorld  {
    public static BufferedImage world = new BufferedImage(1216, 800, BufferedImage.TYPE_INT_RGB);
    private BufferedImage actualWorld = world;
    private Graphics2D g = actualWorld.createGraphics();
    public static ArrayList<Chunk> chunkList = new ArrayList<>();
    private int x, y;
    private Random rng = new Random();
    public static ArrayList<Tree> t = new ArrayList<>();

    public Graphics2D getG() {
        return g;
    }
    
    
    
    public BufferedImage getWorld() {
        return actualWorld;
    }
    
    /**
     *
     * @param panel
     */
    public BasicWorld(JPanel panel) {
        world = new BufferedImage(1216, 800, BufferedImage.TYPE_INT_RGB);
        g.drawImage(Background.background,0, 0, 1200, 800, null);
        g = world.createGraphics();
        g.drawImage(Background.background,0, 0, 1200, 800, null);
        for(int i = 0;i<10;i++) {
            try {
                chunkList.add(new Chunk(i));
            } catch (IOException ex) {
            }
        }
    } 
    
    public void update() throws IOException {
        world = new BufferedImage(1216, 800, BufferedImage.TYPE_INT_RGB);
        g = world.createGraphics();
        g.drawImage(Background.background,0, 0, 1200, 800, null);
        for(int i = 0; i < t.size(); i++) {
            t.get(i).Update();
            if(t.get(i).isMature)
                t.remove(i);
        }
        
        
        for(int i = ScreenOperation.currentChunk, ic = 0; ic < 7; i++, ic++) {
            g.drawImage(BasicWorld.chunkList.get(i).getChunk(), ic*256, 0, null);
        }
        g.dispose();
    }
    
    public void drawBuffer(Graphics2D g) throws IOException {
        g.drawImage(world, 0, 0, null);
        for(int i = 0; i < t.size(); i++)
            g.drawImage(t.get(i).getIm(), t.get(i).getX(), t.get(i).getY(), 96, 96, null);
    }

}
