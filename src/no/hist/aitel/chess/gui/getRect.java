

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
        if(rectNumber == 0 || rectNumber == 8 || rectNumber == 16 || rectNumber == 24 || rectNumber == 32 || rectNumber == 40 || rectNumber == 48 || rectNumber == 56) {
            rectCoordX = (width*0);
        }
        else if(rectNumber == 1 || rectNumber == 9 || rectNumber == 17 || rectNumber == 25 || rectNumber == 33 || rectNumber == 41 || rectNumber == 49 || rectNumber == 57) {
            rectCoordX = (width*1);
        }
        else if(rectNumber == 2 || rectNumber == 10 || rectNumber == 18 || rectNumber == 26 || rectNumber == 34 || rectNumber == 42 || rectNumber == 50 || rectNumber == 58) {
            rectCoordX = (width*2);
        }
        else if(rectNumber == 3 || rectNumber == 11 || rectNumber == 19 || rectNumber == 27 || rectNumber == 35 || rectNumber == 43 || rectNumber == 51 || rectNumber == 59) {
            rectCoordX = (width*3);
        }
        else if(rectNumber == 4 || rectNumber == 12 || rectNumber == 20 || rectNumber == 28 || rectNumber == 36 || rectNumber == 44 || rectNumber == 52 || rectNumber == 60) {
            rectCoordX = (width*4);
        }
        else if(rectNumber == 5 || rectNumber == 13 || rectNumber == 21 || rectNumber == 29 || rectNumber == 37 || rectNumber == 45 || rectNumber == 53 || rectNumber == 61) {
            rectCoordX = (width*5);
        }
        else if(rectNumber == 6 || rectNumber == 14 || rectNumber == 22 || rectNumber == 30 || rectNumber == 38 || rectNumber == 46 || rectNumber == 54 || rectNumber == 62) {
            rectCoordX = (width*6);
        }
        else if(rectNumber == 7 || rectNumber == 15 || rectNumber == 23 || rectNumber == 31 || rectNumber == 39 || rectNumber == 47 || rectNumber == 55 || rectNumber == 63) {
            rectCoordX = (width*7);
        }
        return rectCoordX;
    }

    public int getRectCoordY(int rectNumber) {
        if(rectNumber == 0 || rectNumber == 1 || rectNumber == 2 || rectNumber == 3 || rectNumber == 4 || rectNumber == 5 || rectNumber == 6 || rectNumber == 7) {
            rectCoordY = (height*8);
        }
        else if(rectNumber == 8 || rectNumber == 9 || rectNumber == 10 || rectNumber == 11 || rectNumber == 12 || rectNumber == 13 || rectNumber == 14 || rectNumber == 15) {
            rectCoordY = (height*7);
        }
        else if(rectNumber == 16 || rectNumber == 17 || rectNumber == 18 || rectNumber == 19 || rectNumber == 20 || rectNumber == 21 || rectNumber == 22 || rectNumber == 23) {
            rectCoordY = (height*6);
        }
        else if(rectNumber == 24 || rectNumber == 25 || rectNumber == 26 || rectNumber == 27 || rectNumber == 28 || rectNumber == 29 || rectNumber == 30 || rectNumber == 31) {
            rectCoordY = (height*5);
        }
        else if(rectNumber == 32 || rectNumber == 33 || rectNumber == 34 || rectNumber == 35 || rectNumber == 36 || rectNumber == 37 || rectNumber == 38 || rectNumber == 39) {
            rectCoordY = (height*4);
        }
        else if(rectNumber == 40 || rectNumber == 41 || rectNumber == 42 || rectNumber == 43 || rectNumber == 44 || rectNumber == 45 || rectNumber == 46 || rectNumber == 47) {
            rectCoordY = (height*3);
        }
        else if(rectNumber == 48 || rectNumber == 49 || rectNumber == 50 || rectNumber == 51 || rectNumber == 52 || rectNumber == 53 || rectNumber == 54 || rectNumber == 55) {
            rectCoordY = (height*2);
        }
        else if(rectNumber == 56 || rectNumber == 57 || rectNumber == 58 || rectNumber == 59 || rectNumber == 60 || rectNumber == 61 || rectNumber == 62 || rectNumber == 63) {
            rectCoordY = (height*1);
        }
        return rectCoordY;
    }
    public static void main(String[] args) {
        getRect getRect = new getRect();
        System.out.println(getRect.getRectNumber(400, 400));
        System.out.println(getRect.getRectCoordX(8));
        System.out.println(getRect.getRectCoordY(8));
    }

}
