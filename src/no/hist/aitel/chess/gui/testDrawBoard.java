package no.hist.aitel.chess.gui;

import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */

public class testDrawBoard {
    
    private JFrame frame = new JFrame("Chess Board");
    private drawBoard boardPaint = new drawBoard();
    private drawStartPos startPos = new drawStartPos();
   


    public testDrawBoard() {
        
        frame.add(boardPaint);
        frame.add(startPos);
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);
        frame.setVisible(true);
    }
        
    public static void main(String[] args) {
        new testDrawBoard();
    }
}

