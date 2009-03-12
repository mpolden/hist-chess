package no.hist.aitel.java.chess.gui;

import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */

public class testDrawBoard {
    JFrame frame = new JFrame("Chess Board");
    drawBoard chesspanel = new drawBoard();

    public testDrawBoard() {
        frame.getContentPane().add(chesspanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
        
    public static void main(String[] args) {
        new testDrawBoard();
    }
}