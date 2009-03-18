

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
    private int boardSize = 64;
    private Object[] pieces;
    private BufferedImage pawnb;
    private BufferedImage pawnw;
    private BufferedImage kingb;
    private BufferedImage kingw;
    private BufferedImage queenb;
    private BufferedImage queenw;
    private BufferedImage knightb;
    private BufferedImage knightw;
    private BufferedImage rookb;
    private BufferedImage rookw;
    private BufferedImage bishopb;
    private BufferedImage bishopw;
    static final int width = 117;
    static final int height = 117;    

    public drawStartPos() {
        pieces = new Object[boardSize];
        // 56  57  58  59  60  61  62  63
        // 48  49  50  51  52  53  54  55
        // 40  41  42  43  44  45  46  47
        // 32  33  34  35  36  37  38  39
        // 24  25  26  27  28  29  30  31
        // 16  17  18  19  20  21  22  23
        // 8   9   10  11  12  13  14  15
        // 0   1   2   3   4   5   6   7
    }

    public Object[] getPieces() {
        return pieces;
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
            URL url_knightb = new URL("http://www.chessvariants.com/d.pieces/knightb.gif");
            URL url_knightw = new URL("http://www.chessvariants.com/d.pieces/knightw.gif");
            URL url_rookb = new URL("http://www.chessvariants.com/d.pieces/rookb.gif");
            URL url_rookw = new URL("http://www.chessvariants.com/d.pieces/rookw.gif");
            URL url_bishopb = new URL("http://www.chessvariants.com/d.pieces/bishopb.gif");
            URL url_bishopw = new URL("http://www.chessvariants.com/d.pieces/bishopw.gif");
            pawnb = ImageIO.read(url_pawnb);
            pawnw = ImageIO.read(url_pawnw);
            kingb = ImageIO.read(url_kingb);
            kingw = ImageIO.read(url_kingw);
            queenb = ImageIO.read(url_queenb);
            queenw = ImageIO.read(url_queenw);
            knightb = ImageIO.read(url_knightb);
            knightw = ImageIO.read(url_knightw);
            rookb = ImageIO.read(url_rookb);
            rookw = ImageIO.read(url_rookw);
            bishopb = ImageIO.read(url_bishopb);
            bishopw = ImageIO.read(url_bishopw);
        }

        catch (IOException e) {
            System.out.println("Image could not be read");
        }

        //draw pawns
        int j=0;
        for(int i=8; i<16; i++) {
            pieces[i] = g.drawImage(pawnw, width*j, height*7, this);
            j++;
        }
        j=0;
        for(int i=48; i<56; i++) {
            pieces[i] = g.drawImage(pawnb, width*j, height*2, this);
            j++;

        }
        //draw kings
        pieces[60] = g.drawImage(kingb, width*4, height, this);
        pieces[4] = g.drawImage(kingw, width*4, height*8, this);
        //draw queens
        pieces[59] = g.drawImage(queenb, width*3, height, this);
        pieces[3] = g.drawImage(queenw, width*3, height*8, this);
        //draw knights
        j=1;
        for(int i=0; i<2; i++) {            
            pieces[j] = g.drawImage(knightw, width*j, height*8, this);
            j=6;
        }
        j=1;        
        pieces[57] = g.drawImage(knightb, width*j, height, this);
        j=6;
        pieces[62] = g.drawImage(knightb, width*j, height, this);
        
        //draw rooks
        j=0;
        for(int i=0; i<2; i++) {
            pieces[j] = g.drawImage(rookw, width*j, height*8, this);
            j=7;
        }
        j=0;
        pieces[56] = g.drawImage(rookb, width*j, height, this);
        j=7;
        pieces[63] = g.drawImage(rookb, width*j, height, this);
        //draw bishops
        j=2;
        for(int i=0; i<2; i++) {
            pieces[j] = g.drawImage(bishopb, width*j, height, this);
            j=5;
        }
        j=2;
        pieces[58] = g.drawImage(bishopw, width*j, height*8, this);
        j=5;
        pieces[61] = g.drawImage(bishopw, width*j, height*8, this);
    }
}
