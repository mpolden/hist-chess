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

        public Chessboard() {
            Dimension boardSize = new Dimension(400, 500);
            layeredPane = new JLayeredPane();
            getContentPane().add(layeredPane);
            layeredPane.setPreferredSize(boardSize);
            chessBoard = new drawBoard();
            layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
            chessBoard.setLayout(new GridLayout(8, 8));
            chessBoard.setPreferredSize(boardSize);
            chessBoard.setBounds(10, 0, boardSize.width, boardSize.height);
            add(chessBoard);
        }
    }
    /*
    private void knappEn() {

            btnNewGame.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseReleased(java.awt.event.MouseEvent e) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you wish to end this game?")) {
                        newGame();
                    }
                }
            });
                    return btnNewGame;

        }
    */

    private class Buttonlistener implements ActionListener {

        public void actionPerformed(ActionEvent hendelse) {
        }
    }
}

class testGui {

    public static void main(String[] args) {

        JFrame frame = new Mainwindow("Chess");
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.pack();


    }
}