

package no.hist.aitel.chess.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

    public drawPromotion(int x, int y) {
        Graphics g = getGraphics();
        try {
                        img = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/princes_leia.gif"));
                    } catch (IOException ex) {
                        Logger.getLogger(guiEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        this.x = x;
        this.y = y;
        g.drawImage(img, x, y, width, height, this);
    }
}
