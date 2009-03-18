package no.hist.aitel.java.chess.gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


/**
 *
 * @author Vegard
 */
public class drawBoard extends JPanel {
    private BufferedImage pawnb;
    private BufferedImage pawnw;
    static final int width = 117;
    static final int height = 117;
    static final Color dunkel = new Color (0x999999);
    static final Color hell = new Color (0xFFFFCC);
    
    
    public drawBoard() {        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
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
            g.fillRect(x*width, y*height, width, height);
            if (i % 8 == 0) { // Står nå på den siste ruten i linja
                inverse = !inverse;
                y+=1;
            }
        }
        for (int i = 0; i<8; i++) {
            g.drawString("1", i*width, (y+1)*height);
        }
    }  
}


    

    
 

                    

