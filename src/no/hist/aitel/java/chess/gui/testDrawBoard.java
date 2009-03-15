package no.hist.aitel.java.chess.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Vegard
 */

public class testDrawBoard {
    private JLabel wpawn = new JLabel();
    private Images newBoard = new Images();
    private JFrame frame = new JFrame("Chess Board");
    private drawBoard boardPaint = new drawBoard();

    public testDrawBoard() {
        wpawn.setIcon(newBoard.getPieceIcon(0));        
        frame.add(boardPaint);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
        
    public static void main(String[] args) {
        new testDrawBoard();
    }
}

