

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
    private BufferedImage[] sw_images = new BufferedImage[boardSize];
    private BufferedImage[] classic_images = new BufferedImage[boardSize];
    private BufferedImage[] images = new BufferedImage[boardSize];
    private BufferedImage[] promoteImages = new BufferedImage[boardSize];
    private int line=0;

   

    /**
     * Returns an array of the current images
     * @return images
     */
    public BufferedImage[] getImages() {
        return images;
    }

    /**
     * Resets the images of the promoted pawns to the initial images
     */
    public void resetPromoteImage() {
        for (int i=0; i<64; i++) {
            promoteImages[i] = null;
        }
    }

    /**
     * Sets the promoteImages array to the image at the given index
     * @param img
     * @param i
     */
    public void setPromotedImage(BufferedImage img, int i) {
        promoteImages[i] = img;
    }   

    /**
     * Sets the current used images to star wars images
     */
    public void setSwImages() {
        images = sw_images;
        
        this.repaint();
    }

    /**
     * Sets the current used images to classic images
     */
    public void setClassicImages() {
        images = classic_images;
        
        this.repaint();
    }    

    /**
     *
     */
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
    /**
     * Returns the current x coordinates
     * @return x_coords
     */
    public int[] getXcoords() {
        return x_coords;
    }
    /**
     * Returns the current y coordinates
     * @return y_coords
     */
    public int[] getYcoords() {
        return y_coords;
    }    
        
    /**
     * Initiates the images, the start coordinates and the images arrays
     */
    public void initDrawPos() {
        try {
            sw_pawnb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/storm_trooper.gif"));
            sw_pawnw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/rebel_guard.gif"));
            sw_kingb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/emperor.gif"));
            sw_kingw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/yoda_star_wars.gif"));
            sw_queenb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/darth_vader.gif"));
            sw_queenw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/princes_leia.gif"));
            sw_knightb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/boba_fett.gif"));
            sw_knightw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/chewbaca.gif"));
            sw_rookb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/royal_guard_.gif"));
            sw_rookw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/c3po.gif"));
            sw_bishopb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/darth_maul.gif"));
            sw_bishopw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/obi_wan_kenobi.gif"));
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        }
        try {
            pawnb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/pawnb.gif"));
            pawnw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/pawnw.gif"));
            kingb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/kingb.gif"));
            kingw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/kingw.gif"));
            queenb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/queenb.gif"));
            queenw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/queenw.gif"));
            knightb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/knightb.gif"));
            knightw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/knightw.gif"));
            rookb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/rookb.gif"));
            rookw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/rookw.gif"));
            bishopb = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/bishopb.gif"));
            bishopw = ImageIO.read(getClass().getResource("/no/hist/aitel/chess/resources/bishopw.gif"));
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        } 

        for (int i=8; i<16; i++) {
            x_coords[i] = width*line;
            y_coords[i] = height*7;            
            classic_images[i] = pawnw;
            sw_images[i] = sw_pawnw;            
            line++;
        }
        line=0;
        for (int i=48; i<56; i++) {
            x_coords[i] = width*line;
            y_coords[i] = height*2;            
            classic_images[i] = pawnb;
            sw_images[i] = sw_pawnb;
            line++;
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

        line=1;
        for(int i=0; i<2; i++) {
            x_coords[line] = width*line;
            y_coords[line] = height*8;
            classic_images[line] = knightw;
            sw_images[line] = sw_knightw;
            line=6;
        }
        line=1;
        x_coords[57] = width*line;
        y_coords[57] = height;        
        classic_images[57] = knightb;
        sw_images[57] = sw_knightb;

        line=6;
        x_coords[62] = width*line;
        y_coords[62] = height;       
        classic_images[62] = knightb;
        sw_images[62] = sw_knightb;
        
        line=0;
        for(int i=0; i<2; i++) {
            x_coords[line] = width*line;
            y_coords[line] = height*8;
            classic_images[line] = rookw;
            sw_images[line] = sw_rookw;
            line=7;
        }

        line=0;
        x_coords[56] = width*line;
        y_coords[56] = height;        
        classic_images[56] = rookb;
        sw_images[56] = sw_rookb;

        line=7;
        x_coords[63] = width*line;
        y_coords[63] = height;        
        classic_images[63] = rookb;
        sw_images[63] = sw_rookb;
        
        line=2;
        for(int i=0; i<2; i++) {
            x_coords[line] = width*line;
            y_coords[line] = height*8;
            classic_images[line] = bishopw;
            sw_images[line] = sw_bishopw;
            line=5;
        }

        line=2;
        x_coords[58] = width*line;
        y_coords[58] = height;        
        classic_images[58] = bishopb;
        sw_images[58] = sw_bishopb;

        line=5;
        x_coords[61] = width*line;
        y_coords[61] = height;        
        classic_images[61] = bishopb;
        sw_images[61] = sw_bishopb;

        images = classic_images;
    }
    /**
     * Keeps track of the drawn graphics
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {        
        //draw pawns        
        for(int i=8; i<16; i++) {
            if(promoteImages[i] == null) {
                g.drawImage(images[i], x_coords[i], y_coords[i], width, height, this);
            }
            else {
                g.drawImage(promoteImages[i], x_coords[i], y_coords[i], width, height, this);
            }
            
        }        
        for(int i=48; i<56; i++) {
            if(promoteImages[i] == null) {
                g.drawImage(images[i], x_coords[i], y_coords[i], width, height, this);
            }
            else {
                g.drawImage(promoteImages[i], x_coords[i], y_coords[i], width, height, this);
            }            
        }
        
        //draw kings
        g.drawImage(images[60], x_coords[60], y_coords[60], width, height, this);
        g.drawImage(images[4], x_coords[4], y_coords[4], width, height, this);
        //draw queens
        g.drawImage(images[59], x_coords[59], y_coords[59], width, height, this);
        g.drawImage(images[3], x_coords[3], y_coords[3], width, height, this);
        //draw knights
        line=1;
        for(int i=0; i<2; i++) {            
            g.drawImage(images[line], x_coords[line], y_coords[line], width, height, this);
            line=6;
        }                
        g.drawImage(images[57], x_coords[57], y_coords[57], width, height, this);
        g.drawImage(images[62], x_coords[62], y_coords[62], width, height, this);
        //draw rooks
        line=0;
        for(int i=0; i<2; i++) {
            g.drawImage(images[line], x_coords[line], y_coords[line], width, height, this);
            line=7;
        }        
        g.drawImage(images[56], x_coords[56], y_coords[56], width, height, this);
        g.drawImage(images[63], x_coords[63], y_coords[63], width, height, this);
        //draw bishops
        line=2;
        for(int i=0; i<2; i++) {
            g.drawImage(images[line], x_coords[line], y_coords[line], width, height, this);
            line=5;
        }        
        g.drawImage(images[58], x_coords[58], y_coords[58], width, height, this);
        g.drawImage(images[61], x_coords[61], y_coords[61], width, height, this);
        
    } 
}
