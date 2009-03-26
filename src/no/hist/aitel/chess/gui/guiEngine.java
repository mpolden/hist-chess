package no.hist.aitel.chess.gui;

/**
 *
 * @author Vegard
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.board.BoardException;
import no.hist.aitel.chess.position.IllegalPositionException;
import static no.hist.aitel.chess.gui.guiConstants.*;
import static no.hist.aitel.chess.piece.PieceConstants.*;

public class guiEngine extends JFrame implements MouseListener, MouseMotionListener {

    private boolean canDrag = true;
    private Chessboard boardGui = new Chessboard();
    private Board board = new Board();
    private getRect getRect = new getRect();
    private int[] x_coords = boardGui.getXcoords();
    private int[] y_coords = boardGui.getYcoords();
    
    private int dragFromX = zero;
    private int dragFromY = zero;
    private int movingPiece = -1;
    private int capturedPiece = -1;
    private int x_coordStartPos = -1;
    private int y_coordStartPos = -1;
    private int capturedWhitePieces = zero;
    private int capturedBlackPieces = zero;
    private int x;
    private int y;
    private int fromPos;
    private int toPos;
    private boolean timerCheck = true;    
    private String player1 = null;
    private String player2 = null;
    private JLabel player1Label;
    private JLabel player2Label;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JTextArea p1textArea;
    private JTextArea p2textArea;
    private JTextArea centerTextArea;
    private int capturedPos = -1;
    private JLabel stopWatchP1 = new JLabel("", JLabel.LEFT);
    private JLabel stopWatchP2 = new JLabel("", JLabel.LEFT);
    private Runnable player1timer;
    private Runnable player2timer;
    private int timeUsedP1 = zero;
    private int timeUsedP2 = zero;
    private Font player = new Font("VERDANA", Font.BOLD, 20);
    private Font timer = new Font("VERDANA", Font.ITALIC, 14);  

    private int getCapturedPos() {
        return capturedPos;
    }

    private void setCapturedPos(int capturedPos) {
        this.capturedPos = capturedPos;
    }

    public void setXcoords(int[] newCoords) {
        for(int i=0; i<64; i++) {
           x_coords[i] = newCoords[i];
        }
        this.repaint();
    }

    public void setYcoords(int[] newCoords) {
        for(int i=0; i<64; i++) {
           y_coords[i] = newCoords[i];
        }
        this.repaint();
    }    

    public guiEngine(String title) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        players();

        southPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        eastPanel = new JPanel(new BorderLayout());
        

        player1Label = new JLabel(player1, JLabel.LEFT);
        player2Label = new JLabel(player2, JLabel.LEFT);

        player1Label.setFont(player);
        player2Label.setFont(player);

        centerTextArea = new JTextArea(10, 25);
        p1textArea = new JTextArea(10, 25);
        p2textArea = new JTextArea(10, 10);
        p1textArea.setEditable(false);
        p2textArea.setEditable(false);
        centerTextArea.setEditable(false);
        p1textArea.setBorder(BorderFactory.createLineBorder( Color.black));
        p2textArea.setBorder(BorderFactory.createLineBorder( Color.black));
        centerTextArea.setBorder(BorderFactory.createLineBorder( Color.black));
        p2textArea.setBackground(new Color(0xD8D8BF));
        p1textArea.setBackground(new Color(0xD8D8BF));
        centerTextArea.setBackground(new Color(0xD8D8BF));
        p2textArea.setFont(timer);
        new JScrollPane(p1textArea);

        stopWatchP1.setFont(timer);
        stopWatchP1.setBorder(BorderFactory.createEmptyBorder());

        stopWatchP2.setFont(timer);
        stopWatchP2.setBorder(BorderFactory.createEmptyBorder());        
        //westPanel.setBackground(new Color(0x97694F));
        
        southPanel.add(player1Label, BorderLayout.SOUTH);
        southPanel.add(stopWatchP1, BorderLayout.NORTH);
        southPanel.setBackground(new Color(0xC0D9D9));

        northPanel.add(player2Label, BorderLayout.NORTH);
        northPanel.add(stopWatchP2, BorderLayout.SOUTH);
        northPanel.setBackground(new Color(0xFF2400));

        boardGui.setBackground(new Color(0xD8D8BF));
        westPanel.add(boardGui, BorderLayout.WEST);
        

        
        eastPanel.add(p1textArea, BorderLayout.NORTH);
        eastPanel.add(centerTextArea, BorderLayout.CENTER);
        eastPanel.add(p2textArea, BorderLayout.SOUTH);

        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
        
        
        player1timer = new Runnable () {
            public void run () {                
                while(timerCheck) {
                    try {
                        Thread.currentThread().sleep(1000);
                        stopWatchP1.setText("   " + timeUsedP1 + " sec");
                        timeUsedP1++;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        stopWatchP1.setText("   " + timeUsedP1 + " sec");
        

        player2timer = new Runnable () {
            public void run () {                
                while(!timerCheck) {
                    try {
                        Thread.currentThread().sleep(1000);
                        stopWatchP2.setText("   " + timeUsedP2 + " sec");
                        timeUsedP2++;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        stopWatchP2.setText("   " + timeUsedP2 + " sec");
        pack();
    }

    
    public void setBoardObj(Board board) {
        this.board = board;
    }

    public Board getBoardObj() {
        return board;
    }

    public Chessboard getChessboard() {
        return boardGui;
    }

    public void setP1name(String newName) {
        player1Label.setText(newName);
    }

    public void setP2name(String newName) {
        player2Label.setText(newName);
    }

    private void players() {
        player1 = "Player 1";
        player2 = "Player 2";
    }

    public void undoMove() {
        //
    }

    public void newgame() {
        //
        this.repaint();
    }

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        //System.out.println(northPanel.getHeight());
        try {
            for (int i = 0; i < 64; i++) {
                if (x - xIn > x_coords[i] && x - xIn < x_coords[i] + (width) && y - yIn + 61 > y_coords[i] && y - yIn + 61 < y_coords[i] + (height)) {
                    movingPiece = i;
                    System.out.println(i);
                    System.out.println(movingPiece);
                    canDrag = true;
                    dragFromX = x - x_coords[i];
                    dragFromY = y - y_coords[i];
                    break;
                } else {
                    movingPiece = -1;
                    canDrag = false;
                }
            }
            x_coordStartPos = x;
            y_coordStartPos = y;
        } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            System.out.println(outOfBoundsException);
        }
    }

    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (canDrag) {
            x_coords[movingPiece] = x - dragFromX;
            y_coords[movingPiece] = y - dragFromY;

            x_coords[movingPiece] = Math.max(x_coords[movingPiece], zero);
            x_coords[movingPiece] = Math.min(x_coords[movingPiece], getWidth() - 400);

            y_coords[movingPiece] = Math.max(y_coords[movingPiece], 50);
            y_coords[movingPiece] = Math.min(y_coords[movingPiece], getWidth() - 340);


        }

        //System.out.println(x + " " + y);
        this.repaint();
    }

    /**
     * Resets the piece position if the move is invalid
     */
    private void resetPosition() {
        x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
        y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
    }

    /**
     * Changes the piece position
     */
    private void changePosition() {
        x_coords[movingPiece] = getRect.getRectCoordX(toPos);
        y_coords[movingPiece] = getRect.getRectCoordY(toPos);
        if(timerCheck) {
            timerCheck = false;
            new Thread(player2timer).start();
            
        }
        else {
            timerCheck = true;
            new Thread(player1timer).start();
            
        }
    }

    private void checkCastling() {
        if(!(board.getPiece(toPos).isMoved()) && fromPos == 4 && board.getPiece(toPos).getId() == 4) {
            if(toPos == 2) {
                x_coords[0] = getRect.getRectCoordX(3);
                y_coords[0] = getRect.getRectCoordY(3);
            }
            else if(toPos == 6) {
                x_coords[7] = getRect.getRectCoordX(5);
                y_coords[7] = getRect.getRectCoordY(5);
            }
        }
        else if(!(board.getPiece(toPos).isMoved()) && fromPos == 60 && board.getPiece(toPos).getId() == 60) {
            if(toPos == 62) {
                x_coords[63] = getRect.getRectCoordX(61);
                y_coords[63] = getRect.getRectCoordY(61);
            }
            else if(toPos == 58) {
                x_coords[56] = getRect.getRectCoordX(59);
                y_coords[56] = getRect.getRectCoordY(59);
            }

        }

    }

    public void mouseReleased(MouseEvent e) {
        if (canDrag) {
            int x_on_release = e.getX();
            int y_on_release = e.getY();
            fromPos = getRect.getRectNumber(x_coordStartPos, y_coordStartPos);
            toPos = getRect.getRectNumber(x_on_release, y_on_release);
            System.out.println(fromPos+" "+toPos);
            try {
                capturedPiece = board.getPiece(toPos).getId();
                System.out.println(capturedPiece);
                System.out.println(board.getPiece(fromPos).getType());                
                board.movePiece(fromPos, toPos);
                if(board.getPiece(toPos).getType() == KING) {
                    checkCastling();
                }
                setCapturedPos(board.getPiece(toPos).getId());
                changePosition();
            } catch (BoardException exception) { 
                System.out.println(exception.getMessage());
                resetPosition();
                setCapturedPos(-1);
            } catch (IllegalPositionException exception) {
                System.out.println(exception.getMessage());
                resetPosition();
                setCapturedPos(-1);
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println(exception.getMessage());
                resetPosition();
                setCapturedPos(-1);
            }

            // Fin shø, andre betingelsen hindrer at brikken "capture"-er seg selv
            if (getCapturedPos() > -1 && board.getPiece(fromPos).getId() != getCapturedPos()) {
//            System.out.println("Captured pos: " + getCapturedPos());
//            System.out.println("toPos ID: " + board.getPiece(toPos).getId());
//            System.out.println("fromPos ID: " + board.getPiece(fromPos).getId());
                System.out.println(capturedPiece);
                if (capturedPiece == 61) {
                    capturedPiece = 5;
                    try {
                        x_coords[capturedPiece] = capturedBlackPieces * width;
                        y_coords[capturedPiece] = height * 9;
                        capturedBlackPieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                } else if (capturedPiece == 5) {
                    capturedPiece = 61;
                    try {
                        x_coords[capturedPiece] = capturedWhitePieces * width;
                        y_coords[capturedPiece] = zero;
                        capturedWhitePieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                } else if (capturedPiece == 58) {
                    capturedPiece = 2;
                    try {
                        x_coords[capturedPiece] = capturedBlackPieces * width;
                        y_coords[capturedPiece] = height * 9;
                        capturedBlackPieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                } else if (capturedPiece == 2) {
                    capturedPiece = 58;
                    try {
                        x_coords[capturedPiece] = capturedWhitePieces * width;
                        y_coords[capturedPiece] = zero;
                        capturedWhitePieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                } else if (capturedPiece >= 48) {
                    try {
                        x_coords[capturedPiece] = capturedBlackPieces * width;
                        y_coords[capturedPiece] = height * 9;
                        capturedBlackPieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }

                } else if (capturedPiece <= 15) {
                    try {
                        x_coords[capturedPiece] = capturedWhitePieces * width;
                        y_coords[capturedPiece] = zero;
                        capturedWhitePieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                }
            }
            this.repaint();
            System.out.println(board.toString());
            p2textArea.setText(board.toString());
        }
    }

    public void mouseExited(MouseEvent e) {
        canDrag = false;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
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

            buttonOne.addActionListener(listener);
            buttonTwo.addActionListener(listener);
            buttonThree.addActionListener(listener);

        }
    }

    public class Chessboard extends JPanel {

        private JLayeredPane layeredPane;
        private drawBoard chessBoard;
        private drawPos startPos = new drawPos();
        private int[] x_coords = startPos.getXcoords();
        private int[] y_coords = startPos.getYcoords();
        private int xSize = 480;
        private int ySize = 480+yIn;

        public Chessboard() {
            Dimension boardSize = new Dimension(xSize, ySize);
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

        public drawBoard getDrawBoard() {
            return chessBoard;
        }

        public void setXsize(int size) {
            xSize = size;

        }

        public void setYsize(int size) {
            ySize = size;

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
                try {
                    undoMove();
                } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("test");
                }
            //JOptionPane.showMessageDialog(null, "Du angret et trekk");
            } else if (commando.equals("Highscore")) {
                JOptionPane.showMessageDialog(null, "Highscore:");
            }
        }
    }
}

