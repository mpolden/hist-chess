

package no.hist.aitel.chess.gui;

import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author Vegard
 */
public class internalFrame extends JFrame {
    JButton openButton, macButton, javaButton, motifButton, winButton;
    JLayeredPane desktop;
    JInternalFrame internalFrame;
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
    

    public internalFrame() {
        super("internal frame");
        setSize(300, 300);
        setLocation(xOffset, yOffset);
        openButton = new JButton("Open");
        macButton = new JButton("Mac");
        javaButton = new JButton("Metal");
        motifButton = new JButton("Motif");
        winButton = new JButton("Windows");
        Panel p = new Panel();
        p.add(openButton);
        p.add(macButton);
        p.add(javaButton);
        p.add(motifButton);
        p.add(winButton);
        add(p, BorderLayout.SOUTH);

        desktop = new JDesktopPane();
        desktop.setOpaque(true);
        add(desktop, BorderLayout.CENTER);
        
    }

}
