

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

        paintField();
    }
    

    public void paintField() {
        Graphics g = getGraphics();
        int y = 1;
        
      
        boolean inverse = false;
        boolean evenNum;
        for (int i = 1; i<=64; i++) {
            int x = i%8;
            

            if (!inverse) { // Sjekker om vi har byttet om på rekkefølgen
                    evenNum = (i % 2 == 0);
                } else {
                    evenNum = !(i % 2 == 0);
                }

                if (evenNum) {
                    g.setColor(dunkel);

                } else {
                    g.setColor(hell);

                }
                g.fillRect(x*80, y*80, 80, 80);

                if (i % 8 == 0) { // Står nå på den siste ruta i linja
                    inverse = !inverse;
                    y+=1;
                    //x+=1;

                }

            
            }
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
