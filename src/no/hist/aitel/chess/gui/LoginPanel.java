package no.hist.aitel.chess.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane.*;
import java.lang.Object.*;
import javax.swing.JLabel.*;
import javax.swing.JPanel.*;
import javax.sound.midi.*;
import java.io.*;

class MainWindow extends JFrame {

    private final static String NormalChess = "Normal Chess";
    private final static String StarWarsChess = "Star Wars Chess";
    private JRadioButton starWarsChess = new JRadioButton(StarWarsChess, false);
    private JRadioButton normalChess = new JRadioButton(NormalChess, false);
    ImageIcon yodaimage = new ImageIcon("./src/no/hist/aitel/chess/resources/Yoda.jpg");
    ImageIcon chessimage = new ImageIcon("./src/no/hist/aitel/chess/resources/Sjakk.jpg");
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JPanel yodaPanel = new JPanel(new BorderLayout());
    JPanel chessPanel = new JPanel(new BorderLayout());
    JPanel okButtonPanel = new JPanel(new BorderLayout());

    public MainWindow(String tittel) {

        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel yodai = new JLabel(yodaimage);
        JLabel chessi = new JLabel(chessimage);

        okButtonPanel.add(new ChoicePanel(), BorderLayout.NORTH);
        okButtonPanel.add(new OkButton(), BorderLayout.SOUTH);
        yodaPanel.add(yodai, BorderLayout.WEST);
        chessPanel.add(chessi, BorderLayout.EAST);


        add(okButtonPanel, BorderLayout.SOUTH);
        add(yodaPanel, BorderLayout.WEST);
        add(chessPanel, BorderLayout.EAST);

        pack();
    }

    private class ChoicePanel extends JPanel {

        public ChoicePanel() {
            normalChess.setForeground(Color.WHITE);
            starWarsChess.setForeground(Color.WHITE);
            ButtonGroup radioButtons = new ButtonGroup();
            radioButtons.add(normalChess);
            radioButtons.add(starWarsChess);
            add(starWarsChess);
            add(normalChess);

            RadioButtonListener listener = new RadioButtonListener();
            normalChess.addActionListener(listener);
            starWarsChess.addActionListener(listener);
        }
    }

    private class OkButton extends JPanel {

        public OkButton() {
            ButtonListener listener = new ButtonListener();

            JButton OkButton = new JButton("Nytt Spill");
            add(OkButton);

            OkButton.addActionListener(listener);
        }
    }

    private class RadioButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent listener) {
            String chessgame = listener.getActionCommand();

            if (chessgame.equals("Normal Chess")) {
                chessPanel.setVisible(true);
                yodaPanel.setVisible(false);

            } else if (chessgame.equals("Star Wars Chess")) {
                chessPanel.setVisible(false);
                yodaPanel.setVisible(true);
                yodaPanel.isFocusOwner();
            }
        }
    }

    public void MidiPlayer() {

        File midiFile = new File("./src/no/hist/aitel/chess/resources/sw.mid");

        if (!midiFile.exists()) {
            System.out.println("finner ikke fil");
        }
        // Play once
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();
            sequencer.start();
            while (true) {
                if (sequencer.isRunning()) {
                    try {
                        Thread.sleep(1000); // Check every second
                    } catch (InterruptedException ignore) {
                        break;
                    }
                } else {
                    break;
                }
            }
            // Close the MidiDevice & free resources
            sequencer.stop();
            sequencer.close();
        } catch (MidiUnavailableException mue) {
            System.out.println("Midi device unavailable!");
        } catch (InvalidMidiDataException imde) {
            System.out.println("Invalid Midi data!");
        } catch (IOException ioe) {
            System.out.println("I/O Error!");
        }

    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent listener1) {
            String kommando = listener1.getActionCommand();

          //  Settings.swBoard = true;


            if (kommando.equals("Nytt Spill")) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        Main.createAndShowGui();
                    }
                });
            } else {
            }


        }
    }
}

class LoginPanel {

    public static void main(String[] args) throws InterruptedException {

        JFrame frame = new MainWindow("Chess");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
