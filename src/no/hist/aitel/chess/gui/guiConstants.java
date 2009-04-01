

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

    public static final int width = 60;
    public static final int height = 60;
    public static final int xIn = 17;
    public static final int yIn = 155;
    public static final int boardSize = 64;
    public static final int zero = 0;
    
    public static final Color kewl = new Color (0xD8D8BF);
    public static final Color dunkel = new Color (0x999999);
    public static final Color hell = new Color (0xFFFFCC);
    public static final Color black = new Color (0x000000);
    public static final Color pink = new Color (0xFFC0CB);
    public static final Color white = new Color (0xFFFFFF);

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
