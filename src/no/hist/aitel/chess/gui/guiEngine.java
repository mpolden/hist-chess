package no.hist.aitel.chess.gui;
/**
 *
 * @author Vegard
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.board.CheckException;
import no.hist.aitel.chess.board.CheckMateException;
import no.hist.aitel.chess.board.BoardException;
import no.hist.aitel.chess.piece.Piece;
import no.hist.aitel.chess.position.IllegalPositionException;
import static no.hist.aitel.chess.gui.guiConstants.*;
import static no.hist.aitel.chess.piece.PieceConstants.*;


public class guiEngine extends JFrame implements MouseListener, MouseMotionListener, ActionListener, Serializable {

    private boolean canPlay = true;
    private boolean canDrag = false;
    private Chessboard boardGui = new Chessboard();
    private Board board = new Board();
    private getRect getRect = new getRect();
    private saveAndLoad saveAndLoad = new saveAndLoad();
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
    private boolean timerCheckP1 = true;
    private boolean timerCheckP2 = false;
    private String player1 = null;
    private String player2 = null;
    private JLabel player1Label;
    private JLabel player2Label;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JTextArea topArea;
    private JTextArea miniMapArea;
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
    private Font miniMap = new Font("COURIER NEW", Font.PLAIN, 16);
    private Font centerArea = new Font("COURIER NEW", Font.PLAIN, 11);
    private promotionFrame promotionFrame;
    private checkMateFrame checkMateFrame;
    private String centerText = "";
    private String picked;
    private String notation = "";
    private String intro = "\n Welcome to Chess version 1.0 \n Created by team 9\n Last Updated: 28/04/09\n\n";
    private Buttonlistener listener = new Buttonlistener();

    /**
     * Creates the diffrent variables and gui
     * @param title
     */
    public guiEngine(String title) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        players();
        initNewGameSave();

        southPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        eastPanel = new JPanel(new BorderLayout());

        player1Label = new JLabel(player1, JLabel.LEFT);
        player2Label = new JLabel(player2, JLabel.LEFT);

        player1Label.setFont(player);
        player2Label.setFont(player);

        centerTextArea = new JTextArea(10, 25);
        centerTextArea.setFont(centerArea);
        centerTextArea.setEditable(false);
        centerTextArea.setBorder(BorderFactory.createLineBorder( Color.black));
        centerTextArea.setBackground(background);
        centerTextArea.setAutoscrolls(true);
        centerText+=intro;
        centerTextArea.setText(centerText);
        JScrollPane centerScrollPane = new JScrollPane(centerTextArea);
        centerScrollPane.createHorizontalScrollBar();

        topArea = new JTextArea(10, 25) {
            {
                setOpaque(false);
            }
            @Override
            public void paint(Graphics g) {
                g.drawImage(Chess, -40, -50, this);
                super.paint(g);
            }
        };
        topArea.setEditable(false);
        topArea.setBorder(BorderFactory.createLineBorder( Color.black));
        topArea.setBackground(background);

        miniMapArea = new JTextArea(10, 10);
        miniMapArea.setEditable(false);
        miniMapArea.setBorder(BorderFactory.createLineBorder( Color.black));
        miniMapArea.setBackground(background);
        miniMapArea.setFont(miniMap);
        miniMapArea.setText(board.toString());

        stopWatchP1.setFont(timer);
        stopWatchP1.setBorder(BorderFactory.createEmptyBorder());

        stopWatchP2.setFont(timer);
        stopWatchP2.setBorder(BorderFactory.createEmptyBorder());

        southPanel.add(player1Label, BorderLayout.SOUTH);
        southPanel.add(stopWatchP1, BorderLayout.NORTH);
        southPanel.setBackground(new Color(0xC0D9D9));

        northPanel.add(player2Label, BorderLayout.NORTH);
        northPanel.add(stopWatchP2, BorderLayout.SOUTH);
        northPanel.setBackground(new Color(0xFF2400));

        boardGui.setBackground(background);
        boardGui.setBorder(BorderFactory.createLineBorder(Color.black));

        westPanel.add(boardGui, BorderLayout.WEST);
        eastPanel.add(topArea, BorderLayout.NORTH);

        eastPanel.add(centerScrollPane, BorderLayout.CENTER);
        eastPanel.add(miniMapArea, BorderLayout.SOUTH);

        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        player1timer = new Runnable () {
            public void run () {
                while(timerCheckP1) {
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
                while(timerCheckP2) {
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
    /**
     * Initiates new game, so it equals a saved game when the game is started
     */
    private void initNewGameSave() {
        saveAndLoad.saveIntArray("./src/no/hist/aitel/chess/resources/new_game_x_coords.txt", getChessboard().getXcoords());
        saveAndLoad.saveIntArray("./src/no/hist/aitel/chess/resources/new_game_y_coords.txt", getChessboard().getYcoords());
        saveAndLoad.saveBoard("./src/no/hist/aitel/chess/resources/new_game_internal.txt", getBoardObj());
    }
    /**
     * Returns the saveAndLoad object
     * @return saveAndLoad
     */
    public saveAndLoad getSaveAndLoad() {
        return saveAndLoad;
    }
    /**
     * Returns the position of a captured piece
     * @return capturedPos
     */
    private int getCapturedPos() {
        return capturedPos;
    }
    /**
     * Stops the running player timers
     */
    public void stopTimers() {
        timerCheckP1 = false;
        timerCheckP2 = false;
    }
    /**
     * Sets the position of the captured piece to the given parameter
     * @param capturedPos
     */
    private void setCapturedPos(int capturedPos) {
        this.capturedPos = capturedPos;
    }
    /**
     * Sets the x coordinates
     * @param newCoords
     */
    public void setXcoords(int[] newCoords) {
        for(int i=0; i<64; i++) {
           x_coords[i] = newCoords[i];
        }
        this.repaint();
    }
    /**
     * Sets the y coordinates
     * @param newCoords
     */
    public void setYcoords(int[] newCoords) {
        for(int i=0; i<64; i++) {
           y_coords[i] = newCoords[i];
        }
        this.repaint();
    }
    /**
     * Returns the text of the center area
     * @return centerText
     */
    public String getCenterTextArea() {
        return centerText;
    }
    /**
     * Sets the text of the center area
     * @param newText
     */
    public void setCenterTextArea(String newText) {
        centerText = newText;
        centerTextArea.setText(centerText);
    }
    /**
     * cleans up before a new game
     */
    public void cleanup() {
        setTimeUsed(zero, zero);
        miniMapArea.setText(board.toString());
        getChessboard().getStartPos().resetPromoteImage();
        setCenterTextArea(intro);
        stopTimers();
    }    
    /**
     * Returns the current timer for P1
     * @return timeUsedP1
     */
    public int getTimeUsedP1() {
        return timeUsedP1;
    }
    /**
     * Returns the current timer for P2
     * @return timeUsedP2
     */
    public int getTimeUsedP2() {
        return timeUsedP2;
    }
    /**
     * Sets the timer value for each player
     * @param timeP1
     * @param timeP2
     */
    public void setTimeUsed(int timeP1, int timeP2) {
        timeUsedP1 = timeP1;
        stopWatchP1.setText("   " + timeUsedP1 + " sec");
        timeUsedP2 = timeP2;
        stopWatchP2.setText("   " + timeUsedP2 + " sec");        
    }
    /**
     * Sets the current board to the given parameter
     * @param board
     */
    public void setBoardObj(Board board) {
        this.board = board;
    }
    /**
     * Returns the current board
     * @return board
     */
    public Board getBoardObj() {
        return board;
    }
    /**
     * Returns the current chessboard gui
     * @return boardGui
     */
    public Chessboard getChessboard() {
        return boardGui;
    }
    /**
     * Sets player 1 name to the given parameter
     * @param newName
     */
    public void setP1name(String newName) {
        player1 = newName;
        player1Label.setText(player1);
    }
    /**
     * Sets player 2 name to the given parameter
     * @param newName
     */
    public void setP2name(String newName) {
        player2 = newName;
        player2Label.setText(player2);
    }
    /**
     * Returns the name of player1
     * @return player1
     */
    public String getP1name() {
        return player1;
    }
    /**
     * Returns the name of player2
     * @return player2
     */
    public String getP2name() {
        return player2;
    }
    /**
     * Initiates the player names
     */
    private void players() {
        player1 = "Player 1";
        player2 = "Player 2";
    }
    /**
     * Calculates which piece is being clicked on
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        if(canPlay) {
            x = e.getX();
            y = e.getY();
            try {
                if(y>=yIn && y <= (height*8)+yIn) {
                    for (int i = 0; i < 64; i++) {
                        if (x - xIn > x_coords[i] && x - xIn < x_coords[i] + (width) && y - yIn + y_calibrate > y_coords[i] && y - yIn + y_calibrate < y_coords[i] + (height)) {
                            movingPiece = i;
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
                    fromPos = getRect.getRectNumber(x_coordStartPos, y_coordStartPos);
                } else {
                    movingPiece = -1;
                    canDrag = false;
                }

            } catch (ArrayIndexOutOfBoundsException outOfBoundsException) {
            }
        }
        
    }
    /**
     * Moves the piece calculated by the mousePressed method
     * @param e
     */
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();       
        if (canDrag && canPlay) {
            x_coords[movingPiece] = x - dragFromX;
            y_coords[movingPiece] = y - dragFromY;

            if(x_coords[movingPiece] < -13 || x_coords[movingPiece] > getWidth() - 360) {              
                resetPosition();
                canDrag = false;
            }
            else if(y_coords[movingPiece] < 5 || y_coords[movingPiece] > 550) {              
                resetPosition();
                canDrag = false;
            }
        }       
        this.repaint();
    }
    /**
     * Tries to do the move indicated by the player, and checks if it is legal. Also removes a piece if it is capruted.
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        if (canDrag && canPlay) {
            int x_on_release = e.getX();
            int y_on_release = e.getY();
            toPos = getRect.getRectNumber(x_on_release, y_on_release);

            String from = board.getNotation(fromPos);
            String to = board.getNotation(toPos);
            try {
                if(board.getPiece(fromPos).getType() == PAWN) {
                    checkEnPassant();
                }
                capturedPiece = board.getPiece(toPos).getId();                
                board.movePiece(fromPos, toPos);                
                if(board.getPiece(toPos).getType() == PAWN) {
                    checkPromotion();
                }
                if(board.getPiece(toPos).getType() == KING) {
                    checkCastling();
                }
                setCapturedPos(board.getPiece(toPos).getId());
                changePosition();
                if(board.isInCheck() && !board.isCheckMate()) {
                    notation = "(Check)";
                }
                checkCheckMate();
                if(!from.equals(to)) {
                centerText += "\n "+board.getPiece(toPos).getColorStr() + " " + board.getPiece(toPos).getTypeStr()+" from "+from+" to "+to+notation;
                notation = "";                
            }
            } catch (BoardException exception) {               
                resetPosition();
                setCapturedPos(-1);
            } catch (IllegalPositionException exception) {
                resetPosition();
                setCapturedPos(-1);
            } catch (CheckException exception) {
                if(board.getPiece(fromPos).getType() == KING) {
                    centerText+="\n You can't put yourself in check";
                }                
                resetPosition();
                setCapturedPos(-1);
            } catch (CheckMateException exception) {               
            } catch (ArrayIndexOutOfBoundsException exception) {                
                resetPosition();
                setCapturedPos(-1);
            }
            // Moves the captured pieces
            if (getCapturedPos() > -1 && board.getPiece(fromPos).getId() != getCapturedPos()) {
                if (capturedPiece >= 48) {
                    try {
                        x_coords[capturedPiece] = (capturedBlackPieces * width/2) + (capturedBlackPieces * 7);
                        y_coords[capturedPiece] = height * 9 + 15;
                        capturedBlackPieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }

                } else if (capturedPiece <= 15) {
                    try {
                        x_coords[capturedPiece] = (capturedWhitePieces * width/2) + (capturedWhitePieces * 7);
                        y_coords[capturedPiece] = zero;
                        capturedWhitePieces++;
                        setCapturedPos(-1);
                    } catch (ArrayIndexOutOfBoundsException excep) {
                    }
                }
            }
            this.repaint();
            centerTextArea.setText(centerText);
            miniMapArea.setText(board.toString());
        }
    }
    /**
     * Resets the piece position if the move is invalid
     */
    private void resetPosition() {
        x_coords[movingPiece] = getRect.getRectCoordX(fromPos);
        y_coords[movingPiece] = getRect.getRectCoordY(fromPos);
    }

    /**
     * Changes the piece position and handles the timers
     */
    private void changePosition() {
        x_coords[movingPiece] = getRect.getRectCoordX(toPos);
        y_coords[movingPiece] = getRect.getRectCoordY(toPos);
        if(!timerCheckP1 && !timerCheckP2) {
            timerCheckP2 = true;
            new Thread(player2timer).start();
        }
        else if(timerCheckP1) {
            timerCheckP2 = true;
            timerCheckP1 = false;
            new Thread(player2timer).start();
            
        }
        else {
            timerCheckP1 = true;
            timerCheckP2 = false;
            new Thread(player1timer).start();
            
        }
    }
    /**
     * Checks if a player is trying to do castling, and if so, moves the rook to the right position
     */
    private void checkCastling() {
        if(!(board.getPiece(toPos).isMoved()) && fromPos == 4 && board.getPiece(toPos).getId() == 4) {
            if(toPos == 2) {
                x_coords[0] = getRect.getRectCoordX(3);
                y_coords[0] = getRect.getRectCoordY(3);
                notation = "(Castling)";
            }
            else if(toPos == 6) {
                x_coords[7] = getRect.getRectCoordX(5);
                y_coords[7] = getRect.getRectCoordY(5);
                notation = "(Castling)";
            }
        }
        else if(!(board.getPiece(toPos).isMoved()) && fromPos == 60 && board.getPiece(toPos).getId() == 60) {
            if(toPos == 62) {
                x_coords[63] = getRect.getRectCoordX(61);
                y_coords[63] = getRect.getRectCoordY(61);
                notation = "(Castling)";
            }
            else if(toPos == 58) {
                x_coords[56] = getRect.getRectCoordX(59);
                y_coords[56] = getRect.getRectCoordY(59);
                notation = "(Castling)";
            }
        }
        miniMapArea.setText(board.toString());
    }

    /**
     * Checks if a player can promote his pawn, and if so, creates a promotion promotionFrame so the player can choose
     */
    private void checkPromotion() {
        
       
        if(toPos >= 56 && toPos <=63) {
            canPlay = false;
            notation = "(Promotion)";
            promotionFrame = new promotionFrame("white");
            promotionFrame.getButton().addActionListener(listener);
        }        
        else if(toPos <=7 && toPos >=0) {
            canPlay = false;
            notation = "(Promotion)";
            promotionFrame = new promotionFrame("black");
            promotionFrame.getButton().addActionListener(listener);
        }
    }    
    /**
     * Checks if a player has done en passant, and if so, removes the captured piece
     */
    private void checkEnPassant() {
        Piece fromPiece = board.getPiece(fromPos);
        Piece toPiece = board.getPiece(toPos);
        if (fromPiece.getType() == PAWN && toPiece.isEmpty()) {
            if (fromPiece.getColor() == WHITE) {
                if (toPos == fromPos + 9 || toPos == fromPos + 7) {
                    Piece blackPawn = board.getPiece(toPos - 8);                    
                    if (blackPawn.getColor() == BLACK && board.getEnPassant()) {
                        try {
                            x_coords[board.getPiece(toPos-8).getId()] = (capturedBlackPieces * width/2) + (capturedBlackPieces * 7);
                            y_coords[board.getPiece(toPos-8).getId()] = height * 9 + 15;
                            capturedBlackPieces++;
                            notation = "(EnPassant)";
                        } catch (ArrayIndexOutOfBoundsException excep) {
                        }
                    }
                }
            } else if (fromPiece.getColor() == BLACK) {
                if (toPos == fromPos - 9 || toPos == fromPos - 7) {
                    Piece whitePawn = board.getPiece(toPos + 8);
                    if (whitePawn.getColor() == WHITE && board.getEnPassant()) {
                        try {
                            x_coords[board.getPiece(toPos+8).getId()] = (capturedWhitePieces * width/2) + (capturedWhitePieces * 7);
                            y_coords[board.getPiece(toPos+8).getId()] = zero;
                            capturedBlackPieces++;
                            notation = "(EnPassant)";
                        } catch (ArrayIndexOutOfBoundsException excep) {
                        }
                    }
                }
            }
        }
    }
    /**
     * Checks if a player is check mate, and if so, creates a checkmate frame
     */
    private void checkCheckMate() {
        if(board.isCheckMate()) {
            canPlay = false;
            stopTimers();
            checkMateFrame = new checkMateFrame();            
            checkMateFrame.getNewGameButton().addActionListener(listener);
            checkMateFrame.getQuitButton().addActionListener(listener);
            int player = board.getTurn() ^ 1;
            if(player == zero) {
                checkMateFrame.setMessage(player1+" Wins!");
            }
            else if(player == 1) {
                checkMateFrame.setMessage(player2+" Wins!");
            }
        }       
    }
    private class Buttonlistener implements ActionListener {
        /**
         * Sets the pawn, who is able to promote, to the chosen type
         * @param event
         */
        public void actionPerformed(ActionEvent event) {
            
            if(event.getActionCommand().equals("Ok")) {
                canPlay = true;
                promotionFrame.setVisible(false);
                picked = promotionFrame.getPicked();
                BufferedImage[] images = boardGui.getStartPos().getImages();
                if(toPos >= 56 && toPos <= 63) {
                    if(picked.equals("queen")) {
                        boardGui.getStartPos().setPromotedImage(images[3], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(QUEEN);
                    }
                    else if(picked.equals("rook")) {
                        boardGui.getStartPos().setPromotedImage(images[0], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(ROOK);
                    }
                    else if(picked.equals("knight")) {
                        boardGui.getStartPos().setPromotedImage(images[1], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(KNIGHT);
                    }
                    else if(picked.equals("bishop")) {
                        boardGui.getStartPos().setPromotedImage(images[2], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(BISHOP);
                    }
                }

                else if(toPos <= 7 && toPos >= 0) {
                    if(picked.equals("queen")) {
                        boardGui.getStartPos().setPromotedImage(images[59], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(QUEEN);
                    }
                    else if(picked.equals("rook")) {
                        boardGui.getStartPos().setPromotedImage(images[56], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(ROOK);
                    }
                    else if(picked.equals("knight")) {
                        boardGui.getStartPos().setPromotedImage(images[57], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(KNIGHT);
                    }
                    else if(picked.equals("bishop")) {
                        boardGui.getStartPos().setPromotedImage(images[58], board.getPiece(toPos).getId());
                        board.getPiece(toPos).setType(BISHOP);
                    }
                }
                repaint();
                miniMapArea.setText(board.toString());
            }

            else if(event.getActionCommand().equals("New game")) {
                checkMateFrame.setVisible(false);
                try {                    
                    setXcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/new_game_x_coords.txt"));
                    setYcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/new_game_y_coords.txt"));
                    setBoardObj(saveAndLoad.loadBoard("./src/no/hist/aitel/chess/resources/new_game_internal.txt"));
                    cleanup();
                    canPlay = true;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(event.getActionCommand().equals("Quit")) {
                System.exit(0);
            }
            
        }        
    }    
    /**
     * Sets the variable canDrag to false, meaning the player is unable to drag a piece before one is clicked.
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        canDrag = false;
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}
    
    public class Chessboard extends JPanel {

        private JLayeredPane layeredPane;
        private drawBoard chessBoard;
        private drawPos drawPos = new drawPos();
        private int[] x_coords = drawPos.getXcoords();
        private int[] y_coords = drawPos.getYcoords();
        private int xSize = 480+5;
        private int ySize = 480+yIn;

        /**
         * Creates the gui
         */
        public Chessboard() {
            Dimension boardSize = new Dimension(xSize, ySize);
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            chessBoard = new drawBoard();
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setPreferredSize(boardSize);
            drawPos.initDrawPos();
            drawPos.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setBounds(5, 0, boardSize.width, boardSize.height);
            chessBoard.setOpaque(true);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            drawPos.setOpaque(false);
            layeredPane.add(drawPos, JLayeredPane.PALETTE_LAYER);
            add(layeredPane);

        }
        /**
         * Returns the drawPos object
         * @return drawPos
         */
        public drawPos getStartPos() {
            return drawPos;
        }
        /**
         * Returns the drawBoard object
         * @return chessBoard
         */
        public drawBoard getDrawBoard() {
            return chessBoard;
        }  
        /**
         * Returns the array of x coordinates of the pieces
         * @return x_coords
         */
        public int[] getXcoords() {
            return x_coords;
        }
        /**
         * Returns the array of y coordinates of the pieces
         * @return y_coords
         */
        public int[] getYcoords() {
            return y_coords;
        }        
    }    
}