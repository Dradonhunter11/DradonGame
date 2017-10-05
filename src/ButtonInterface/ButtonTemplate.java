package ButtonInterface;

import java.awt.Rectangle;

public abstract class ButtonTemplate {
    private String text;
    protected Rectangle bound;
    private int x, y;
    
    public abstract void function();
    
    public Rectangle getRectangle()  {
        return bound;
    }
    
    public ButtonTemplate(int x, int y, int width,int heigth, String text) {
        this.text = text;
        bound = new Rectangle(x, y, width, heigth);
    }
}
