package no.hist.aitel.java.chess.gui;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Vegard
 */
public class drawBoard extends JPanel {
    static final int width = 80;
    static final int hight = 80;
    static final Color dunkel = new Color (0x999999);
    static final Color hell = new Color (0xFFFFCC);
    
    public drawBoard() {        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g = getGraphics();
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
            g.fillRect(x*width, y*hight, width, hight);
            if (i % 8 == 0) { // Står nå på den siste ruta i linja
                inverse = !inverse;
                y+=1;
            }
        }
        
    }
}
    
 

                    

