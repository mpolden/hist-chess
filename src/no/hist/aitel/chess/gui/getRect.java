

package no.hist.aitel.chess.gui;

import static no.hist.aitel.chess.gui.guiConstants.*;


/**
 *
 * @author Vegard
 */
public class getRect {
    private int rectNumber;
    private int rectCoordX;
    private int rectCoordY;    

    /**
     * Gets field number based on x and y coordinates
     * @param x
     * @param y
     * @return The field number
     */
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

    /**
     * Get x coordinate based on field number
     * @param rectNumber
     * @return The x coordinate
     */
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
        }else {
            rectCoordX = -1;
        }
        return rectCoordX;
    }

    /**
     * Get y coordinate based on field number
     * @param rectNumber
     * @return The y coordinate
     */
    public int getRectCoordY(int rectNumber) {
        if(rectNumber >= 0 && rectNumber <= 7) {
            rectCoordY = (height*8);
        }
        else if(rectNumber >= 8 && rectNumber <= 15) {
            rectCoordY = (height*7);
        }
        else if(rectNumber >= 16 && rectNumber <= 23) {
            rectCoordY = (height*6);
        }
        else if(rectNumber >= 24 && rectNumber <= 31) {
            rectCoordY = (height*5);
        }
        else if(rectNumber >= 32 && rectNumber <= 39) {
            rectCoordY = (height*4);
        }
        else if(rectNumber >= 40 && rectNumber <= 47) {
            rectCoordY = (height*3);
        }
        else if(rectNumber >= 48 && rectNumber <= 55) {
            rectCoordY = (height*2);
        }
        else if(rectNumber >= 56 && rectNumber <= 63) {
            rectCoordY = (height*1);
        }
        return rectCoordY;
    }
}
