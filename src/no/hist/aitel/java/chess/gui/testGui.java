package no.hist.aitel.java.chess.gui;

/**
 *
 * @author lgreger_89
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Mainwindow extends JFrame {

    public Mainwindow(String tittel) {
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new Buttons(), BorderLayout.SOUTH);
        add(new Chessboard(), BorderLayout.CENTER);

        pack();
    }

    public class Buttons extends JPanel {

        public Buttons() {
            Buttonlistener listener = new Buttonlistener();

            setLayout(new FlowLayout());

            JButton buttonOne = new JButton("Nytt spill");
            add(buttonOne);
            JButton buttonTwo = new JButton("Angre trekk");
            add(buttonTwo);
            JButton buttonThree = new JButton("Highscore");
            add(buttonThree);
            JButton buttonFour = new JButton("Lagre spill");
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
        private drawStartPos startPos;

        public Chessboard() {
            Dimension boardSize = new Dimension(1200, 1050);
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            chessBoard = new drawBoard();
            startPos = new drawStartPos();
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setPreferredSize(boardSize);
            startPos.setBounds(10, 0, boardSize.width, boardSize.height);
            chessBoard.setBounds(10, 0, boardSize.width, boardSize.height);
            chessBoard.setOpaque(true);
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            startPos.setOpaque(false);
            layeredPane.add(startPos, JLayeredPane.PALETTE_LAYER);
            add(layeredPane);
                      

        }
        public void newgame() {            
            new Mainwindow("Chess");
        }
    }

    private class Buttonlistener implements ActionListener {

        public void actionPerformed(ActionEvent hendelse) {
            String kommando = hendelse.getActionCommand();

            if (kommando.equals("Nytt spill")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil avslutte?")) {
                    Chessboard newboard = new Chessboard();
                    newboard.newgame();
                }
            } else if (kommando.equals("Angre trekk")) {
                JOptionPane.showMessageDialog(null, "Du angret et trekk");
            } else if (kommando.equals("Highscore")) {
                JOptionPane.showMessageDialog(null, "Her er highscore");
            } else if (kommando.equals("Lagre spill")) {
                JOptionPane.showMessageDialog(null, "Du lagret spill");
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