

package no.hist.aitel.chess.gui;

/**
 *
 * @author Vegard
 */
public class getRect {
    private static final int width = 80;
    private static final int height = 80;
    private static final int xIn = 13;
    private static final int yIn = 108;
    private int rectNumber;
    private int rectCoordX;
    private int rectCoordY;

    public int getRectNumber(int x, int y) { 
        int xNumber;
        int yNumber;
        xNumber = (x-xIn) / width;
        yNumber = (y-yIn) / height;
        if(yNumber == 0) {
            rectNumber = xNumber+(8*7);
        }
        else if(yNumber == 1) {
            rectNumber = xNumber+(8*6);
        }
        else if(yNumber == 2) {
            rectNumber = xNumber+(8*5);
        }
        else if(yNumber == 3) {
            rectNumber = xNumber+(8*4);
        }
        else if(yNumber == 4) {
            rectNumber = xNumber+(8*3);
        }
        else if(yNumber == 5) {
            rectNumber = xNumber+(8*2);
        }
        else if(yNumber == 6) {
            rectNumber = xNumber+(8*1);
        }
        else if(yNumber == 7) {
            rectNumber = xNumber+(8*0);
        }
        else {
            rectNumber = -1;
        }
        return rectNumber;
    }

    public int getRectCoordX(int rectNumber) {
        

        return rectCoordX;
    }

    public int getRectCoordY(int rectNumber) {
        int yNumber;
        yNumber = rectNumber / 8;
        rectCoordY = (yNumber*height)+yIn;
        return rectCoordY;
    }
    public static void main(String[] args) {
        getRect getRect = new getRect();
        System.out.println(getRect.getRectNumber(400, 400));
        System.out.println(getRect.getRectCoordY(4));
    }

}
