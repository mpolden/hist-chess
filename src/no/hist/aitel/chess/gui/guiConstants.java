

package no.hist.aitel.chess.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Vegard
 */
public final class guiConstants {

    private guiConstants() {
    }
    /**
     * The constant width determinates the width of the chessboard squares and the pieces
     */
    public static final int width = 60;
    /**
     * The constant height determinates the height of the chessboard squares and the pieces
     */
    public static final int height = 60;
    /**
     * The constant xIn determinates the amount of pixels into the screen, seen from the left, the chessboard starts
     */
    public static final int xIn = 17;
    /**
     * The constant yIn determinates the amount of pixels into the screen, seen from the top, the chessboard starts
     */
    public static final int yIn = 155;
    /**
     * The constant y_calibrate determinates the calibration of the pieces, seen from the top, into the chessboard
     */
    public static final int y_calibrate = 61;
    /**
     * The constant boardSize determinates how many squares there are in the board
     */
    public static final int boardSize = 64;
    /**
     * The constant zero determinates the value 0
     */
    public static final int zero = 0;
    
    public static final Color background = new Color (0xD8D8BF);
    public static final Color dunkel = new Color (0x999999);    
    public static final Color black = new Color (0x211E1E);    
    public static final Color white = new Color (0xFFFFFF);   

    public static BufferedImage Chess;
    
    public static BufferedImage pawnb;
    public static BufferedImage pawnw;
    public static BufferedImage kingb;
    public static BufferedImage kingw;
    public static BufferedImage queenb;
    public static BufferedImage queenw;
    public static BufferedImage knightb;
    public static BufferedImage knightw;
    public static BufferedImage rookb;
    public static BufferedImage rookw;
    public static BufferedImage bishopb;
    public static BufferedImage bishopw;

    public static BufferedImage sw_pawnb;
    public static BufferedImage sw_pawnw;
    public static BufferedImage sw_kingb;
    public static BufferedImage sw_kingw;
    public static BufferedImage sw_queenb;
    public static BufferedImage sw_queenw;
    public static BufferedImage sw_knightb;
    public static BufferedImage sw_knightw;
    public static BufferedImage sw_rookb;
    public static BufferedImage sw_rookw;
    public static BufferedImage sw_bishopb;
    public static BufferedImage sw_bishopw;
}
