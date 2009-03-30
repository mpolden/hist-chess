

package no.hist.aitel.chess.gui;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private BufferedImage[] images;
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
    private Graphics g = getGraphics();

//    private BufferedImage sw_pawnb;
//    private BufferedImage sw_pawnw;
//    private BufferedImage sw_kingb;
//    private BufferedImage sw_kingw;
//    private BufferedImage sw_queenb;
//    private BufferedImage sw_queenw;
//    private BufferedImage sw_knightb;
//    private BufferedImage sw_knightw;
//    private BufferedImage sw_rookb;
//    private BufferedImage sw_rookw;
//    private BufferedImage sw_bishopb;
//    private BufferedImage sw_bishopw;
    


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
    public void initStartCoords() {
        for (int i=8; i<16; i++) {
            x_coords[i] = width*j;
            y_coords[i] = height*7;
            j++;
        }
        j=0;
        for (int i=48; i<56; i++) {
            x_coords[i] = width*j;
            y_coords[i] = height*2;
            j++;
        }
        x_coords[60] = width*4;
        y_coords[60] = height;
        x_coords[4] = width*4;
        y_coords[4] = height*8;

        x_coords[59] = width*3;
        y_coords[59] = height;
        x_coords[3] = width*3;
        y_coords[3] = height*8;

        j=1;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height*8;
            j=6;
        }
        j=1;
        x_coords[57] = width*j;
        y_coords[57] = height;

        j=6;
        x_coords[62] = width*j;
        y_coords[62] = height;
        
        j=0;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height*8;
            j=7;
        }

        j=0;
        x_coords[56] = width*j;
        y_coords[56] = height;

        j=7;
        x_coords[63] = width*j;
        y_coords[63] = height;
        
        j=2;
        for(int i=0; i<2; i++) {
            x_coords[j] = width*j;
            y_coords[j] = height;
            j=5;
        }

        j=2;
        x_coords[58] = width*j;
        y_coords[58] = height*8;

        j=5;
        x_coords[61] = width*j;
        y_coords[61] = height*8;        

    }
    
    @Override
    public void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);        
        try {
            File file_pawnb = new File("./src/no/hist/aitel/chess/resources/pawnb.gif");
            File file_pawnw = new File("./src/no/hist/aitel/chess/resources/pawnw.gif");
            File file_kingb = new File("./src/no/hist/aitel/chess/resources/kingb.gif");
            File file_kingw = new File("./src/no/hist/aitel/chess/resources/kingw.gif");
            File file_queenb = new File("./src/no/hist/aitel/chess/resources/queenb.gif");
            File file_queenw = new File("./src/no/hist/aitel/chess/resources/queenw.gif");
            File file_knightb = new File("./src/no/hist/aitel/chess/resources/knightb.gif");
            File file_knightw = new File("./src/no/hist/aitel/chess/resources/knightw.gif");
            File file_rookb = new File("./src/no/hist/aitel/chess/resources/rookb.gif");
            File file_rookw = new File("./src/no/hist/aitel/chess/resources/rookw.gif");
            File file_bishopb = new File("./src/no/hist/aitel/chess/resources/bishopb.gif");
            File file_bishopw = new File("./src/no/hist/aitel/chess/resources/bishopw.gif");            

            try  {
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

            pawnb = ImageIO.read(file_pawnb);
            pawnw = ImageIO.read(file_pawnw);
            kingb = ImageIO.read(file_kingb);
            kingw = ImageIO.read(file_kingw);
            queenb = ImageIO.read(file_queenb);
            queenw = ImageIO.read(file_queenw);
            knightb = ImageIO.read(file_knightb);
            knightw = ImageIO.read(file_knightw);
            rookb = ImageIO.read(file_rookb);
            rookw = ImageIO.read(file_rookw);
            bishopb = ImageIO.read(file_bishopb);
            bishopw = ImageIO.read(file_bishopw);       



        }

        catch (IOException e) {
            System.out.println("Image could not be read\n"+e);
        }

        //draw pawns
        
        for(int i=8; i<16; i++) {
            g.drawImage(sw_pawnw, x_coords[i], y_coords[i], width, height, this);
        }        
        for(int i=48; i<56; i++) {
            g.drawImage(sw_pawnb, x_coords[i], y_coords[i], width, height, this);
        }
        //draw kings
        g.drawImage(sw_kingb, x_coords[60], y_coords[60], width, height, this);
        g.drawImage(sw_kingw, x_coords[4], y_coords[4], width, height, this);
        //draw queens
        g.drawImage(sw_queenb, x_coords[59], y_coords[59], width, height, this);
        g.drawImage(sw_queenw, x_coords[3], y_coords[3], width, height, this);
        //draw knights
        j=1;
        for(int i=0; i<2; i++) {            
            g.drawImage(sw_knightw, x_coords[j], y_coords[j], width, height, this);
            j=6;
        }                
        g.drawImage(sw_knightb, x_coords[57], y_coords[57], width, height, this);
        g.drawImage(sw_knightb, x_coords[62], y_coords[62], width, height, this);
        //draw rooks
        j=0;
        for(int i=0; i<2; i++) {
            g.drawImage(sw_rookw, x_coords[j], y_coords[j], width, height, this);
            j=7;
        }        
        g.drawImage(sw_rookb, x_coords[56], y_coords[56], width, height, this);
        g.drawImage(sw_rookb, x_coords[63], y_coords[63], width, height, this);
        //draw bishops
        j=2;
        for(int i=0; i<2; i++) {
            g.drawImage(sw_bishopb, x_coords[j], y_coords[j], width, height, this);
            j=5;
        }        
        g.drawImage(sw_bishopw, x_coords[58], y_coords[58], width, height, this);
        g.drawImage(sw_bishopw, x_coords[61], y_coords[61], width, height, this);
    }
    

    public void drawPromotion(BufferedImage img, int x, int y) {
        g = getGraphics();
        sw_pawnw = img;
        
        g.drawImage(img, x, y, width, height, this);
        //g.fillRect(x, y, width, height);
    }

    public static void main(String[] args ){
        drawPos test = new drawPos();
        test.initStartCoords();
        System.out.println(test.getXcoords()[8]);
        System.out.println(test.getYcoords()[8]);
        
    }
}
