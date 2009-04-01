

package no.hist.aitel.chess.gui;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static no.hist.aitel.chess.gui.guiConstants.*;


/**
 *
 * @author Vegard
 */
public class drawPos extends JPanel {
    private int[] x_coords;
    private int[] y_coords;
    private BufferedImage[] sw_images = new BufferedImage[64];
    private BufferedImage[] classic_images = new BufferedImage[64];
    private BufferedImage[] images = new BufferedImage[64];    
    private BufferedImage[] promoteImage = new BufferedImage[64];
    private boolean classic = true;

    public BufferedImage[] getImages() {
        return images;
    }

    public void setPromotedImage(BufferedImage img, int i) {
        promoteImage[i] = img;
    }   

    public void setSwImages() {
        images = sw_images;
        classic = false;
        this.repaint();
    }

    public void setClassicImages() {
        images = classic_images;
        classic = true;
        this.repaint();
    }

    public boolean getClassic() {
        return classic;
    }

    public drawPos() {
        x_coords = new int[boardSize];
        y_coords = new int[boardSize];
        // 56  57  58  59  60  61  62  63
        // 48  49  50  51  52  53  54  55
        // 40  41  42  43  44  45  46  47
        // 32  33  34  35  36  37  38  39
        // 24  25  26  27  28  29  30  31
        // 16  17  18  19  20  21  22  23
        // 8   9   10  11  12  13  14  15
        // 0   1   2   3   4   5   6   7
    }    

    public int[] getXcoords() {
        return x_coords;
    }
    public int[] getYcoords() {
        return y_coords;
    }    
        
    int j=0;
    public void initDrawPos() {

        try {
            sw_pawnb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/storm_trooper.gif"));
            sw_pawnw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/rebel_guard.gif"));
            sw_kingb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/emperor.gif"));
            sw_kingw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/yoda_star_wars.gif"));
            sw_queenb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/darth_vader.gif"));
            sw_queenw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/princes_leia.gif"));
            sw_knightb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/boba_fett.gif"));
            sw_knightw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/chewbaca.gif"));
            sw_rookb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/royal_guard_.gif"));
            sw_rookw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/c3po.gif"));
            sw_bishopb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/darth_maul.gif"));
            sw_bishopw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/obi_wan_kenobi.gif"));
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
        try {
            pawnb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/pawnb.gif"));
            pawnw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/pawnw.gif"));
            kingb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/kingb.gif"));
            kingw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/kingw.gif"));
            queenb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/queenb.gif"));
            queenw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/queenw.gif"));
            knightb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/knightb.gif"));
            knightw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/knightw.gif"));
            rookb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/rookb.gif"));
            rookw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/rookw.gif"));
            bishopb = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/bishopb.gif"));
            bishopw = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("./no/hist/aitel/chess/resources/bishopw.gif"));
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        } 

        for (int i=8; i<16; i++) {
            x_coords[i] = width*j;
            y_coords[i] = height*7;            
            classic_images[i] = pawnw;
            sw_images[i] = sw_pawnw;            
            j++;
        }
        j=0;
        for (int i=48; i<56; i++) {
            x_coords[i] = width*j;
            y_coords[i] = height*2;            
            classic_images[i] = pawnb;
            sw_images[i] = sw_pawnb;
            j++;
        }
        x_coords[60] = width*4;
        y_coords[60] = height;        
        classic_images[60] = kingb;
        sw_images[60] = sw_kingb;
        x_coords[4] = width*4;
        y_coords[4] = height*8;        
        classic_images[4] = kingw;
        sw_images[4] = sw_kingw;

        x_coords[59] = width*3;
        y_coords[59] = height;        
        classic_images[59] = queenb;
        sw_images[59] = sw_queenb;
        x_coords[3] = width*3;
        y_coords[3] = height*8;        
        classic_images[3] = queenw;
        sw_images[3] = sw_queenw;

        j=1;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height*8;            
            classic_images[j] = knightw;
            sw_images[j] = sw_knightw;
            j=6;
        }
        j=1;
        x_coords[57] = width*j;
        y_coords[57] = height;        
        classic_images[57] = knightb;
        sw_images[57] = sw_knightb;

        j=6;
        x_coords[62] = width*j;
        y_coords[62] = height;       
        classic_images[62] = knightb;
        sw_images[62] = sw_knightb;
        
        j=0;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height*8;            
            classic_images[j] = rookw;
            sw_images[j] = sw_rookw;
            j=7;
        }

        j=0;
        x_coords[56] = width*j;
        y_coords[56] = height;        
        classic_images[56] = rookb;
        sw_images[56] = sw_rookb;

        j=7;
        x_coords[63] = width*j;
        y_coords[63] = height;        
        classic_images[63] = rookb;
        sw_images[63] = sw_rookb;
        
        j=2;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height*8;            
            classic_images[j] = bishopw;
            sw_images[j] = sw_bishopw;
            j=5;
        }

        j=2;
        x_coords[58] = width*j;
        y_coords[58] = height;        
        classic_images[58] = bishopb;
        sw_images[58] = sw_bishopb;

        j=5;
        x_coords[61] = width*j;
        y_coords[61] = height;        
        classic_images[61] = bishopb;
        sw_images[61] = sw_bishopb;

        images = classic_images;
    }
    
    @Override
    public void paintComponent(Graphics g) {        
        //draw pawns        
        for(int i=8; i<16; i++) {
            if(promoteImage[i] == null) {
                g.drawImage(images[i], x_coords[i], y_coords[i], width, height, this);
            }
            else {
                g.drawImage(promoteImage[i], x_coords[i], y_coords[i], width, height, this);
            }
            
        }        
        for(int i=48; i<56; i++) {
            if(promoteImage[i] == null) {
                g.drawImage(images[i], x_coords[i], y_coords[i], width, height, this);
            }
            else {
                g.drawImage(promoteImage[i], x_coords[i], y_coords[i], width, height, this);
            }            
        }
        
        //draw kings
        g.drawImage(images[60], x_coords[60], y_coords[60], width, height, this);
        g.drawImage(images[4], x_coords[4], y_coords[4], width, height, this);
        //draw queens
        g.drawImage(images[59], x_coords[59], y_coords[59], width, height, this);
        g.drawImage(images[3], x_coords[3], y_coords[3], width, height, this);
        //draw knights
        j=1;
        for(int i=0; i<2; i++) {            
            g.drawImage(images[j], x_coords[j], y_coords[j], width, height, this);
            j=6;
        }                
        g.drawImage(images[57], x_coords[57], y_coords[57], width, height, this);
        g.drawImage(images[62], x_coords[62], y_coords[62], width, height, this);
        //draw rooks
        j=0;
        for(int i=0; i<2; i++) {
            g.drawImage(images[j], x_coords[j], y_coords[j], width, height, this);
            j=7;
        }        
        g.drawImage(images[56], x_coords[56], y_coords[56], width, height, this);
        g.drawImage(images[63], x_coords[63], y_coords[63], width, height, this);
        //draw bishops
        j=2;
        for(int i=0; i<2; i++) {
            g.drawImage(images[j], x_coords[j], y_coords[j], width, height, this);
            j=5;
        }        
        g.drawImage(images[58], x_coords[58], y_coords[58], width, height, this);
        g.drawImage(images[61], x_coords[61], y_coords[61], width, height, this);
        
    }    
    

    public static void main(String[] args ){
        drawPos test = new drawPos();
        test.initDrawPos();
        System.out.println(test.getXcoords()[8]);
        System.out.println(test.getYcoords()[8]);
        
    }
}
