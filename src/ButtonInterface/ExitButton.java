/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ButtonInterface;

/**
 *
 * @author 1635598
 */
public class ExitButton extends ButtonTemplate {

    public ExitButton(int x, int y, int width, int heigth, String text) {
        super(x, y, width, heigth, text);
    }
    
    @Override
    public void function() {
        System.exit(0);
    }
    
}
