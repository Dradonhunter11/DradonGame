/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import ScreenOperation.ScreenOperation;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Proprio
 */
public class Tree {
    private int x, y;
    private int timeLeft = 50;
    private int height = (int) Math.floor(Math.random() * 5) + 2;
    private int currentChunk;
    private BufferedImage im = new BufferedImage(96, 96, BufferedImage.TRANSLUCENT);
    private Graphics2D b = im.createGraphics();
    public boolean isMature = false;
    private int[][] leaf = {{0, 1, 1, 1, 0},
                            {1, 1, 1, 1, 1},
                            {1, 1, 2, 1, 1},
                            {1, 1, 2, 1, 1}};
    
    public BufferedImage getIm() {
        return im;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Tree() {
        x = 50*16;
        y = 800-(33*16);
    }
    
    public Tree(int x, int y, int currentChunk) {
        this.x = x;
        this.y = y;
        this.currentChunk = currentChunk;
    }
    
    public void Update() throws IOException {
        if(!isMature) {
            timeLeft--;
            b.drawImage(ImageIO.read(new File("./Texture/Sapling.png")), x, y, 16, 16, null);
        }
        if(timeLeft == 0) {
            drawTreeInBuffer();
            isMature = true;
        }
    }
    
    public void drawBuffer(Graphics2D b) throws IOException {
        b.drawImage(im, x, y, 96, 96, null);
    }
    
    public void drawTreeInBuffer() throws IOException {
        for(int posY = y; posY >= y-(height*16); posY-=16) {
            BasicWorld.chunkList.get(currentChunk).getChunk().getGraphics().drawImage(ImageIO.read(new File("./Texture/Tree.png")), x, posY, 16, 16, null);
        }
        
        for(int posX = 0; posX < 5; posX++) {
            for(int posY = 0; posY < 4; posY++) {
                String block = detectBlock(leaf[posY][posX]);
                if(!block.equals("air")) {
                    BasicWorld.chunkList.get(currentChunk).getChunk().getGraphics().drawImage(ImageIO.read(new File("./Texture/"+block+".png")), (x-32)+(posX*16), (y-64)+(16*(posY-height)), 16, 16, null);
                }
            }
        }
    }
    
    public String detectBlock(int blockID) {
        switch(blockID) {
            case 1 : 
                return "leave";
            case 2 : 
                return "Tree";
        }
        return "air";
    }
}
