

package no.hist.aitel.java.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */

public class testDrawBoard extends JFrame {
    drawBoard board;
    JButton newGame = new JButton("New game");    
    

    public testDrawBoard(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 1000);
        setBackground (Color.lightGray);
        board = new drawBoard();
        setLayout(new BorderLayout());
        
        add("Center", board);
        
        
        

    }
    public Insets insets () {
        return new Insets (10,10,10,10);
    }

    public static void main(String[] args) {
        testDrawBoard drawedBoard = new testDrawBoard("Chess");
        drawedBoard.setVisible(true);
    }
}