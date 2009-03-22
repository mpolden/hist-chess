package no.hist.aitel.chess.gui;

/**
 *
 * @author Vegard
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.board.IllegalTurnException;
import no.hist.aitel.chess.position.IllegalPositionException;
import no.hist.aitel.chess.piece.IllegalPieceException;
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;


class Mainwindow extends JFrame implements MouseListener, MouseMotionListener {
    private boolean canDrag = true;
    private Chessboard boardGui = new Chessboard();
    private Board board = new Board();
    private getRect getRect = new getRect();
    private int[] x_coords = boardGui.getXcoords();
    private int[] y_coords = boardGui.getYcoords();    
    private int dragFromX = 0;
    private int dragFromY = 0;  
    private int movingPiece = -1;
    private int x_coordStartPos = -1;
    private int y_coordStartPos = -1;
    private static final int xIn = 13;
    private static final int yIn = 108;
    private int x;
    private int y;
    private int fromPos;
    private int toPos;
    
    public Mainwindow(String title) {        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new Buttons(), BorderLayout.SOUTH);
        add(boardGui, BorderLayout.CENTER);        
        pack();
    }

    public Chessboard getBoard() {
        return boardGui;
    }

    public void undoMove() {
        try {            
            x_coords[movingPiece] = x - dragFromX;
            y_coords[movingPiece] = y - dragFromY;            
            this.repaint();
            //board.switchTurn(); // Ikke nødvendig, Board-klassen tar seg av dette
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("test");
        }        
    }
    
    public void newgame() {        
        //
        this.repaint();
    }
    
    public void mousePressed(MouseEvent e) {        
        x = e.getX();
        y = e.getY();

        try {
            for(int i=0; i<64; i++){
            if(x-xIn>x_coords[i] && x-xIn<x_coords[i]+(80) && y-27> y_coords[i] && y-27<y_coords[i]+(80)) {
                movingPiece = i;
                canDrag=true;
                dragFromX = x - x_coords[i];
                dragFromY = y - y_coords[i];
                break;
            }
            else {
                movingPiece = -1;
                canDrag=false;
            }
        }
            x_coordStartPos = x;
            y_coordStartPos = y;
        }catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
            System.out.println(outOfBoundsException);
        }        
    }
          
    public void mouseDragged(MouseEvent e) {        
        if(canDrag) {            
            x_coords[movingPiece] = e.getX() - dragFromX;
            y_coords[movingPiece] = e.getY() - dragFromY;

            x_coords[movingPiece] = Math.max(x_coords[movingPiece], 0);
            x_coords[movingPiece] = Math.min(x_coords[movingPiece], getWidth() - 450);

            y_coords[movingPiece] = Math.max(y_coords[movingPiece], 50);
            y_coords[movingPiece] = Math.min(y_coords[movingPiece], getWidth() - 400);            

            this.repaint();            
        }        
    }

    public void mouseReleased(MouseEvent e) {
        if(canDrag) {
        int x_on_release = e.getX();
        int y_on_release = e.getY();        
        fromPos = getRect.getRectNumber(x_coordStartPos, y_coordStartPos);
        toPos = getRect.getRectNumber(x_on_release, y_on_release);
        
        try {
            board.movePiece(fromPos, toPos);
            x_coords[movingPiece] = getRect.getRectCoordX(toPos);
            y_coords[movingPiece] = getRect.getRectCoordY(toPos);
        } catch(IllegalPositionException posE) {
            System.out.println(posE);
            try{
                x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
                y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
            } catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
                System.out.println(outOfBoundsException);
            }
        } catch(IllegalTurnException turnE) {
            System.out.println(turnE);
            try{
                x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
                y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
            } catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
                System.out.println(outOfBoundsException);
            }
        } catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
            x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
            y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
            System.out.println(outOfBoundsException);
        } catch(IllegalPieceException turnException) {
            System.out.println(turnException);
            try{
                x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
                y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
            } catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
                System.out.println(outOfBoundsException);
            }
        }
        if(board.getCapturedPos() != -1) {
            System.out.println("test2");
            int capturedPos = board.getCapturedPos();
            x_coords[capturedPos] = 0;
            y_coords[capturedPos] = 0;
            System.out.println(toPos+" "+capturedPos);       
            
        }
        this.repaint();
        //movingPiece = -1;
        System.out.println(board.toString());  
        }             
    }
    public void mouseEntered(MouseEvent e) {        
    }
    public void mouseExited(MouseEvent e) {        
        canDrag = false;        
    }
    public void mouseClicked(MouseEvent e) {        
    }
    public void mouseMoved(MouseEvent e) {        
    }

    public class Buttons extends JPanel {

        public Buttons() {
            Buttonlistener listener = new Buttonlistener();
            
            setLayout(new FlowLayout());

            JButton buttonOne = new JButton("New game");
            add(buttonOne);
            JButton buttonTwo = new JButton("Undo move");
            add(buttonTwo);
            JButton buttonThree = new JButton("Highscore");
            add(buttonThree);
            JButton buttonFour = new JButton("Save game");
            add(buttonFour);

            buttonOne.addActionListener(listener);
            buttonTwo.addActionListener(listener);
            buttonThree.addActionListener(listener);
            buttonFour.addActionListener(listener);
        }
    }

    public class Chessboard extends JPanel {

        private JLayeredPane layeredPane;
        private drawBoard chessBoard;
        private drawStartPos startPos = new drawStartPos();
        private int[] x_coords = startPos.getXcoords();
        private int[] y_coords = startPos.getYcoords();
        

        public Chessboard() {
            Dimension boardSize = new Dimension(1024, 768);
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            chessBoard = new drawBoard();            
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setPreferredSize(boardSize);
            startPos.initStartCoords();            
            startPos.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setOpaque(true);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            startPos.setOpaque(false);    
            layeredPane.add(startPos, JLayeredPane.PALETTE_LAYER);
            add(layeredPane);    
                    
        }       
        
        public int[] getXcoords() {
            return x_coords;
        }
        public int[] getYcoords() {
            return y_coords;
        }
        
    }

    private class Buttonlistener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            
            String commando = event.getActionCommand();

            if (commando.equals("New game")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game?\nUnsaved progress will be lost.")) {
                    newgame();
                }
            } else if (commando.equals("Undo move")) {
                try{
                    undoMove();
                } catch(ArrayIndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("test");
                }                
                //JOptionPane.showMessageDialog(null, "Du angret et trekk");
            } else if (commando.equals("Highscore")) {
                JOptionPane.showMessageDialog(null, "Highscore:");
            } else if (commando.equals("Save game")) {
                JOptionPane.showMessageDialog(null, "Game saved");
            }
        }        
    }
}

class testGui {

    public static void main(String[] args) {
        JFrame frame = new Mainwindow("Chess");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}