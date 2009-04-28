

package no.hist.aitel.chess.gui;

import static no.hist.aitel.chess.gui.guiConstants.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Panel;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Vegard
 */
public class checkMateFrame extends JFrame {
    private JButton newGame;
    private JButton quit;
    private JTextArea topField;
    private Font text = new Font("VERDANA", Font.BOLD, 20);
    private String message;

    /**
     * Creates the check mate frame
     */
    public checkMateFrame() {
        try {
            Chess = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/Sjakk.jpg"));
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
        setTitle("Check mate!");
        newGame = new JButton("New game");
        quit = new JButton("Quit");
        Panel buttons = new Panel();
        buttons.add(newGame);
        buttons.add(quit);
        buttons.setBackground(Color.BLACK);
        add(buttons, BorderLayout.SOUTH);

        topField = new JTextArea(18, 32) {
            {
                setOpaque(false);
            }
            @Override
            public void paint(Graphics g) {
                g.drawImage(Chess, 0, -5, this);
                g.setFont(text);
                g.drawString(message, 90, 25);
                super.paint(g);
            }
        };      
        add(topField, BorderLayout.CENTER);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
    }

    /**
     * Set the message shown in checkMateFrame()
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
        this.repaint();
    }

    /**
     * Returns the new game button
     * @return newGame
     */
    public JButton getNewGameButton() {
        return newGame;
    }
    
    /**
     * Returns the quit button
     * @return quit
     */
    public JButton getQuitButton() {
        return quit;
    }
}
