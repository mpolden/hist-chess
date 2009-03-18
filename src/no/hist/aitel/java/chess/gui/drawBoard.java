package no.hist.aitel.java.chess.gui;
import java.awt.Color;
import java.awt.Font;
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
    private static final int width = 117;
    private static final int height = 117;
    static final Color dunkel = new Color (0x999999);
    static final Color hell = new Color (0xFFFFCC);
    static final Color black = new Color (0x000000);
    private Font arial = new Font("Arial", Font.PLAIN, 18);


    
    
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
        for (int i = 1; i<=8; i++) {
            String number ="";
            number = number.valueOf(i);
            g.setFont(arial);
            g.setColor(black);
            g.drawString(number, (i-1)*width, (y*height)-4);
        }
    }  
}


    

    
 

                    

