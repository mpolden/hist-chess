package no.hist.aitel.java.chess.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Vegard
 */

public class testDrawBoard extends JFrame  {
    JLayeredPane layeredPane;
    drawBoard chessBoard;
    JButton newGame = new JButton("New game");    
    

    public testDrawBoard(String title) {
        super(title);
        Dimension boardSize = new Dimension(600, 600);
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        chessBoard = new drawBoard();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);                     
    }   
    
    public static void main(String[] args) {
        JFrame frame = new testDrawBoard("Chess");        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}