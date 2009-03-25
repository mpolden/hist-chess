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

    /**
     * The number which undefined attributes are represented by
     */
    public static final int UNDEFINED = -1;

    /**
     * The number which white is represented by
     */
    public static final int WHITE = 0;
    /**
     * The number which black is represented by
     */
    public static final int BLACK = 1;

    /**
     * The number a pawn is represented by
     */
    public static final int PAWN = 0;

    /**
     * The number a bishop is represented by
     */
    public static final int BISHOP = 1;

    /**
     * The number a knight is represented by
     */
    public static final int KNIGHT = 2;

    /**
     * The number a rook is represented by
     */
    public static final int ROOK = 3;

    /**
     * The number a queen is represented by
     */
    public static final int QUEEN = 4;

    /**
     * The number a king is represented by
     */
    public static final int KING = 5;

}
