package Object;


import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import javax.swing.JFrame;

public class WinFrame {
    public int width;
    public int heigth;
    private boolean visible;
    public boolean isRunning = false;
    private Thread t;
    public static JFrame f;
   
    
    private Map m;
    
    public WinFrame(int width, int heigth, boolean isVisible) {
        this.width = width;
        this.heigth = heigth;
        this.visible = isVisible;
        initFrame(width, heigth);
    }

    private void initFrame(int width, int heigth) throws HeadlessException {
        f = new JFrame();
        f.setSize(width, heigth);
        f.setVisible(visible);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setState(Frame.NORMAL);
        f.setTitle("Dradoncraft 2D edition");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    
    
    
    public static RenderingHints rh() {
        RenderingHints map = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY); 
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        map.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        return map;
    } 
    
    public void run() {
        m = new Map(f);  
        f.add(m.getPanel());
        m.start();
    }

}
