

package no.hist.aitel.java.chess.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */
public class testDrawBoard {
    public static void main(String[] args) {
        Window window = new Window("Chess");
        window.setVisible(true);
    }
}

class Window extends JFrame {
    //private drawBoard chessBoard = new drawBoard();
    private JButton button = new JButton("New game");
    
    public Window(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);        

        //setBackground (Color.lightGray);
        setLayout (new BorderLayout (10,10));

        //add("Center", chessBoard);
        add("South", button);
        pack();
    }

}
