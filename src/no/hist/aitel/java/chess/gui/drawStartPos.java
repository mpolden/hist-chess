

package no.hist.aitel.java.chess.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Vegard
 */
public class drawStartPos extends JPanel {
    private BufferedImage pawnb;
    private BufferedImage pawnw;
    private BufferedImage kingb;
    private BufferedImage kingw;
    private BufferedImage queenb;
    private BufferedImage queenw;
    private BufferedImage rookb;
    private BufferedImage rookw;
    static final int width = 117;
    static final int height = 117;

    public drawStartPos() {        
    }

    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            URL url_pawnb = new URL("http://www.chessvariants.com/d.pieces/pawnb.gif");
            URL url_pawnw = new URL("http://www.chessvariants.com/d.pieces/pawnw.gif");
            URL url_kingb = new URL("http://www.chessvariants.com/d.pieces/kingb.gif");
            URL url_kingw = new URL("http://www.chessvariants.com/d.pieces/kingw.gif");
            URL url_queenb = new URL("http://www.chessvariants.com/d.pieces/queenb.gif");
            URL url_queenw = new URL("http://www.chessvariants.com/d.pieces/queenw.gif");
            pawnb = ImageIO.read(url_pawnb);
            pawnw = ImageIO.read(url_pawnw);
            kingb = ImageIO.read(url_kingb);
            kingw = ImageIO.read(url_kingw);
            queenb = ImageIO.read(url_queenb);
            queenw = ImageIO.read(url_queenw);

        }

        catch (IOException e) {
            System.out.println("Image could not be read");
        }

        //draw pawns
        for(int i=0; i<8; i++) {
            g.drawImage(pawnb, width*i, height*2, this);
            g.drawImage(pawnw, width*i, height*7, this);

        }
        //draw kings
        g.drawImage(kingb, width*4, height, this);
        g.drawImage(kingw, width*4, height*8, this);
        //draw queens
        g.drawImage(queenb, width*3, height, this);
        g.drawImage(queenw, width*3, height*8, this);

    }
}
