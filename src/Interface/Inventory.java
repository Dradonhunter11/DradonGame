/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Proprio
 */
public class Inventory extends BasicGUI {
    public static ArrayList<Item> itemList = new ArrayList<>();
    public static Item currentItem;
    
    public Inventory() {
        super();
        panel.addMouseListener(initMouseList());
        panel.setBounds(0, 50, 180, 180);
        loadInventory();
    }

    private MouseListener initMouseList() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClickEvent(e);
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
        };
    }
    
    private void loadInventory() {
        String[] fileArray;
        int x = 16;
        int y = 0;
        try{
            File file = new File("./Texture/");
            fileArray = file.list();
            for(int i = 1; i < fileArray.length; i++) {
                
                itemList.add(new Item(fileArray[i], x, y));
                if(i%11==0) {
                    y+=17;
                    x=16;
                } else
                    x+=17;
                
            } 
        }catch(Exception e) {
            System.out.println("No texture have been found");
        }
    }
    
    private void mouseClickEvent(MouseEvent e) {
        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getRec().contains(e.getX()+16, e.getY()))
                currentItem = itemList.get(i);
        }
        System.out.println("Mouse click detected");
    }

    public void draw(Graphics2D buffer) {
        buffer.setPaint(Color.BLACK);
        buffer.drawRect(0, 50, 186, 186);
        buffer.setPaint(Color.GRAY);
        buffer.fillRect(1, 51, 185, 185);
        for(int i = 0; i < itemList.size(); i++) {

                buffer.setPaint(Color.BLACK);
                buffer.fillRect(itemList.get(i).getRec().x-18, 50+itemList.get(i).getRec().y, 18, 18);
                buffer.setPaint(Color.DARK_GRAY);
                buffer.fillRect(itemList.get(i).getRec().x+1-18, 51 + itemList.get(i).getRec().y, 16, 16);
                
                buffer.drawImage(itemList.get(i).getIt(), itemList.get(i).getRec().x+1-18, 51+itemList.get(i).getRec().y, null);
            
        }
    }

    @Override
    public void update() {
        
    }
    
}
