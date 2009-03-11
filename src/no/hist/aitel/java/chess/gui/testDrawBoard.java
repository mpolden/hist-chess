

package no.hist.aitel.java.chess.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Vegard
 */

public class testDrawBoard extends JFrame {
    drawBoard board;

    public testDrawBoard(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        board = new drawBoard();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(board,BorderLayout.CENTER);
        

    }
    public static void main(String[] args) {
        testDrawBoard drawedBoard = new testDrawBoard("Chess");
        drawedBoard.setVisible(true);
    }
}