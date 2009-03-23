/*
 * IllegalPieceException.java
 *
 */

package no.hist.aitel.chess.piece;

/**
 * This exception is thrown when a invalid piece operation happens
 * @author martin
 */

public class IllegalPieceException extends IllegalArgumentException {

    public IllegalPieceException(String s) {
        super(s);
    }
    
}
