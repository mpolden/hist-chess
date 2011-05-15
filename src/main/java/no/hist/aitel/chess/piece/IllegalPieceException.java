/*
 * IllegalPieceException.java
 *
 */

package no.hist.aitel.chess.piece;

/**
 *
 * @author martin
 */

public class IllegalPieceException extends IllegalArgumentException {

    /**
     * This exception is thrown when a invalid piece operation happens
     * @param s
     */
    public IllegalPieceException(String s) {
        super(s);
    }
    
}
