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
        int j=8;
        String number;
        for (int i = 0; i<8; i++) {            
            number = String.valueOf(j);
            g.setFont(arial);
            g.setColor(black);
            g.drawString(number, 2, ((i+2)*height)-8);
            j--;
        }
        String abcdefgh = "abcdefgh";
        for (int i = 0; i<8; i++) {
            String letter;
            letter = abcdefgh.substring(i, i+1);
            g.setFont(arial);
            g.setColor(black);
            g.drawString(letter, (width*i)+(width-15), (height*9)-14);
        }

    }  
}


    

    
 

                    

