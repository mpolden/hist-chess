/*
 * PieceConstants.java
 *
 * Created: 23.mar.2009 04:05:18
 */

package no.hist.aitel.chess.piece;

/**
 * Constants for types and colors
 * @author martin
 */

public final class PieceConstants {

    // Don't allow instances of this class
    private PieceConstants() {
    }

    // Constants
    public static final int UNDEFINED = -1;
    
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public static final int PAWN = 0;
    public static final int BISHOP = 1;
    public static final int KNIGHT = 2;
    public static final int ROOK = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;

}
