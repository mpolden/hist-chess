package no.hist.aitel.java.chess.gui;

/**
 *
 * @author lgreger_89
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Mainwindow extends JFrame {

    public Mainwindow(String title) {
        setTitle(title);
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