/*
 * IllegalPieceException.java
 *
 */

package no.hist.aitel.chess.piece;

/**
 *
 * @author Martin
 */

public class IllegalPieceException extends IllegalArgumentException {

    public IllegalPieceException(String s) {
        super(s);
    }
    
}
