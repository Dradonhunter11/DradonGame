/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Proprio
 */
public class Item {
    private BufferedImage it;
    private final Rectangle rec;

    public BufferedImage getIt() {
        return it;
    }

    public Rectangle getRec() {
        return rec;
    }
    
    
    
    public Item(String itemName, int x, int y) {
        rec = new Rectangle(x, y, 16, 16);
        try{
            it = ImageIO.read(new File("./Texture/" + itemName));
        }catch(Exception e) {
        
        }
    }
}
