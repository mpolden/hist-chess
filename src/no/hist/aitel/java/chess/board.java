

package no.hist.aitel.java.chess;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Vegard
 */
public class board extends java.awt.Canvas  {
    Color dunkel = new Color (0x999999);
	Color hell = new Color (0xFFFFCC);

    public board(java.applet.Applet ref) {
        super();
    }
    
    public void newgame () {
        return;
    }

    public void paintBoard(Graphics g) {
        g = getGraphics();
        g.fillRect (3, 5, 40, 40);
        int x = 1;
        int y = 1;
        boolean inverse = false;
        for (int i = 1; i <= 64; i++) {
            if (!inverse) {
                if (i % 2 == 0) {
                    g.setColor(dunkel);

                } else {
                    g.setColor(hell);
                }
            } else {
                if (i % 2 == 0) {
                    g.setColor(hell);
                } else {
                    g.setColor(dunkel);
                }
            }

            if (i % 8 == 0) {
                inverse = !inverse;                
            }
        }
        g.fillRect (x, y, 40, 40);
        x++;
        y++;
    }

    public void run() {        
    }
}
