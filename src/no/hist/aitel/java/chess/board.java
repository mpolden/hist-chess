

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

    @Override
    public void paint(Graphics g) {
        for ( int i = 1; i <= 64; i++)
        {
            paintField(i);
        }
    }

    public void paintField(int index) {
        Graphics g = getGraphics(); 
        int x = index % 10;
        int y = index / 10;
        boolean inverse = false;
        
            if (!inverse) {
                if (index % 2 == 0) {
                    g.setColor(dunkel);
                } else {
                    g.setColor(hell);
                }
            } else {
                if (index % 2 == 0) {
                    g.setColor(hell);                    
                }
                 else {
                    g.setColor(dunkel);
                 }
            }

            if (index % 8 == 0) {
                inverse = !inverse;
            }
        g.fillRect ( x * 40, y * 40, 40, 40);
        }
    public void run() {
        
    }
    
    }



   /* public void paintBoard(Graphics g) {
        g = getGraphics();        
        
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
*/
