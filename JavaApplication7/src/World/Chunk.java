/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World;

import static World.BasicWorld.world;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Proprio
 */
public class Chunk {
    private BufferedImage chunk = new BufferedImage(256, 900, BufferedImage.TRANSLUCENT);
    private Graphics2D g = chunk.createGraphics();
    private int x, y;
    private int chunkID;
    private Random rng = new Random();

    public BufferedImage getChunk() {
        return chunk;
    }

    public int getChunkID() {
        return chunkID;
    }
    
    
    
    
    public Chunk(int id) throws IOException {
        this.chunkID = id;
        placeBlock();
        generateCavern();
        generateLake();
        genOre();
        
    }
    
    public void placeBlock() throws IOException {       
        for(y = 0; y < 25; y++) {
            for(x = 0; x < 16; x++) {
                g.drawImage(ImageIO.read(new File("./Texture/Stone.png")), x*16, (800 - 16*y), 16, 16, null);
            }
        }
        
        for(; y < 27; y++) {
            for(x = 0; x < 16; x++) {
                g.drawImage(ImageIO.read(new File("./Texture/Dirt.png")), x*16, (800 - 16*y), 16, 16, null);
            }
        }
        
        for(x = 0; x < 16; x++) {
            g.drawImage(ImageIO.read(new File("./Texture/Grass.png")), x*16, (800 - 16*y), 16, 16, null);
        }
    }
    
    public void generateCavern() throws IOException {
        if(rng.nextBoolean()) {
            rng = new Random();
            boolean caveGen = false;
            int cx, cy;
            int heigth = rng.nextInt(5) + 3;
            int width[] = determineCaveSize(heigth);
            while(!caveGen) {
                cx = rng.nextInt(16 - rng.nextInt(10));
                cy = rng.nextInt((8 - rng.nextInt(4)));
                g.setPaint(Color.BLACK);
                drawCavern(cx, cy, heigth, width);
                caveGen = true;
            }
        }
    }
    
    public int[] determineCaveSize(int height) {
        int[] width = new int[height];
        for(int i = 0; i < height; i++) {
            width[i] = (int) (12 * Math.random() + 1);
        }
        return width;
    }
    
    public void drawCavern(int cx, int cy, int heigth, int width[]) throws IOException {
        int i = 0;
        for(int genY = 0; genY < heigth; genY++, i++) {
            for(int genX = 0; genX < width[i]; genX++) {
                g.drawImage(ImageIO.read(new File("./Texture/StoneCave.png")),(int)(cx+genX - width[i]/2)*16, 800-16*((genY + heigth)), 16, 16, null);
            }
        }
    }
    
    public void generateLake() {
        if(rng.nextInt(10) == 0) {
            int depth = rng.nextInt(3) + 1;
            int width = rng.nextInt(6) + 3;
            int lx = rng.nextInt(16 - rng.nextInt(5)) + 2;
            g.setPaint(Color.BLUE);
            for(int genY = 27, i = 0; genY > 15 - depth;genY--, width/=2, i++) {
                for(int genX = (int)(lx-width/2); genX < (int)(lx+width/2);genX++)
                    g.fillRect((int)(genX)*16, 800-16*((27 - i)), 16, 16);
            }
        }
    }
    
    public void genOre() throws IOException {
        for(int oy = 0; oy < 20; oy++) {
            for(int ox = 0; ox < 16; ox++) {
                if(generateOre()) {
                    determineOre(oy, ox);
                }
            }
        }
    }
    
    public boolean generateOre() {
        if(rng.nextInt(10) == 0)
            return true;
        return false;
    }
    
    public void determineOre(int y, int x) throws IOException {
        try{
            if(rng.nextInt(2) == 0 && y < 10) {
                if(world.getRGB((int)(x)*16, 800-16*(y)) == -10592674)
                    g.drawImage(ImageIO.read(new File("./Texture/GoldOreCave.png")), (int)(x)*16, 800-16*(y), 16, 16, null);
                else
                    g.drawImage(ImageIO.read(new File("./Texture/GoldOre.png")), (int)(x)*16, 800-16*(y), 16, 16, null);
            }
            else {
                if(world.getRGB((int)(x)*16, 800-16*(y)) == -10592674)
                    g.drawImage(ImageIO.read(new File("./Texture/IronOreCave.png")), (int)(x)*16, 800-16*(y), 16, 16, null);
                else g.drawImage(ImageIO.read(new File("./Texture/IronOre.png")), (int)(x)*16, 800-16*(y), 16, 16, null);
            }
        }catch(Exception e) {}
    }
}
