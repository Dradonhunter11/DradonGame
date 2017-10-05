package Object;

import Interface.Inventory;
import ScreenOperation.ScreenOperation;
import World.BasicWorld;
import World.Tree;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JApplet implements Runnable {
    private JFrame frame;
    private Graphics2D g;
    private BufferedImage im = new BufferedImage(800,1000, BufferedImage.TYPE_INT_RGB);
    private JPanel panel;
    private final RenderingHints map = WinFrame.rh();
    private BasicWorld world;
    private Thread th;
    private long before = 0;
    private ScreenOperation so;
    public static Inventory inv; 
    
    public JPanel getPanel() {
        return panel;
    }
    
    public Map(JFrame frame) {
        this.frame = frame;
        panel = new JPanel();
        activateProperty();
        world = new BasicWorld(panel);
        frame.setFocusable(true);
        so = new ScreenOperation(frame, world.getG());
        inv = new Inventory();
        so.start();
        frame.add(inv.panel());
    }
    
    public final void activateProperty() {
        panel.setBackground(Color.red);
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        panel.setDoubleBuffered(true);
        panel.setVisible(true);
        setBounds(0, 0, frame.getWidth(), frame.getHeight());
        setVisible(true);
    }
    
    

    @Override
    public void start() {
        if(th == null) {
            th = new Thread(this);
            th.start();
        }
    }
    
    @Override
    public void stop() {
        if(th != null) {
            th.stop();
            th = null;
            
        }
    }
    
    @Override
    public void run() {
        while(th != null) {
            try {
                im = new BufferedImage(1200,800, BufferedImage.TRANSLUCENT);
                g = im.createGraphics();
                map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                map.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.addRenderingHints(map);
                update();
                drawInBuffer();
                drawOnScreen();
                
                before = System.currentTimeMillis();
                Thread.sleep(25);
            } catch(Exception e) {
            }
        }
    }
    
    private void update() throws IOException {
        world.update();
    }

    private void drawInBuffer() throws IOException {
        
        world.drawBuffer(g);
        so.drawInBuffer(g);
        if(ScreenOperation.inventoryOpen) {
            inv.draw(g);
        } else
            inv.panel().setVisible(false);
        g.setPaint(Color.WHITE);
        g.drawString(String.valueOf("FPS: " + (System.currentTimeMillis() - before)), 5, 15);
        //g.drawImage(im, 0, 0, null);
        
    }

    private void drawOnScreen() throws IOException {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(im, 0, 0, panel); 
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }
}
