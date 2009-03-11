

package no.hist.aitel.java.chess.gui;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */
public class testDrawBoard {
    public static void main(String[] args) {
        Window window = new Window();
        window.setVisible(true);
    }
}

class Window extends JFrame  {    
    
    drawBoard draw = new drawBoard();
    private JButton button = new JButton("New game");

    public Window() {
        super("My Simple Frame");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container con = this.getContentPane();
        //con.add(draw);
        con.add(button);
               
    }

}
