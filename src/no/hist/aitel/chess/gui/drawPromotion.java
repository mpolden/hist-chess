

package no.hist.aitel.chess.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import static no.hist.aitel.chess.gui.guiConstants.*;

/**
 *
 * @author Vegard
 */
public class drawPromotion extends JPanel {
    private BufferedImage img;
    private int x;
    private int y;

    public drawPromotion(BufferedImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        g = getGraphics();
        
        //paintComponent(g);
        g.setColor(pink);
        g.fillRect(x, y, width, height);
            
        g.drawImage(img, x, y, width, height, this);
        //g.fillRect(x+80, y, width, height);
    
    }
    
}
