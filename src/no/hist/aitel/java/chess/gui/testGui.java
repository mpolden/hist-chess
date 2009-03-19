package no.hist.aitel.java.chess.gui;

/**
 *
 * @author lgreger_89
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Mainwindow extends JFrame implements MouseListener, MouseMotionListener {
    private boolean canDrag = true;
    private Chessboard board = new Chessboard();
    private int[] x_coords = board.getXcoords();
    private int[] y_coords = board.getYcoords();
    private int dragFromX = 0;
    private int dragFromY = 0;
    private int movingPiece;
    
    public Mainwindow(String title) {        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new Buttons(), BorderLayout.SOUTH);
        add(board, BorderLayout.CENTER);        
        
        pack();
    }

    //test
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for(int i=0; i<64; i++){
            if(x>=x_coords[i] && x<=x_coords[i]+79 && y>= y_coords[i] && y<=y_coords[i]+79){
                movingPiece = i;
                canDrag=true;
                dragFromX = x - x_coords[i];
                dragFromY = y - y_coords[i];
            }
            
        }        
        
        System.out.println(x+" "+y);
        
    }
    public void mouseDragged(MouseEvent e) {
        if(canDrag) {
            x_coords[movingPiece] = e.getX() - dragFromX;
            y_coords[movingPiece] = e.getY() - dragFromY;

            x_coords[movingPiece] = Math.max(x_coords[movingPiece], 0);
            x_coords[movingPiece] = Math.min(x_coords[movingPiece], getWidth());
            
            this.repaint();
            
        }
        int x = e.getX();
        System.out.println(x);
    }

    public void mouseReleased(MouseEvent e) {        
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
            startPos.setBounds(5, 5, boardSize.width, boardSize.height);
            chessBoard.setBounds(5, 5, boardSize.width, boardSize.height);
            chessBoard.setOpaque(true);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            startPos.setOpaque(false);        
            
            layeredPane.add(startPos, JLayeredPane.PALETTE_LAYER);
            add(layeredPane);          
                    

        }
        public void newgame() {            
            new Mainwindow("Chess");
        }
        public int[] getXcoords() {
            return x_coords;
        }
        public int[] getYcoords() {
            return y_coords;
        }
    }

    private class Buttonlistener implements ActionListener {
        public void actionPerformed(ActionEvent hendelse) {
            String kommando = hendelse.getActionCommand();

            if (kommando.equals("New game")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game?\nUnsaved progress will be lost.")) {
                    Chessboard newboard = new Chessboard();
                    newboard.newgame();
                }
            } else if (kommando.equals("Undo move")) {

                //JOptionPane.showMessageDialog(null, "Du angret et trekk");
            } else if (kommando.equals("Highscore")) {
                JOptionPane.showMessageDialog(null, "Highscore:");
            } else if (kommando.equals("Save game")) {
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