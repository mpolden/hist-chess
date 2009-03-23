/*
 * IllegalTypeException.java
 *
 * Created: 22.mar.2009 20:54:16
 */

package no.hist.aitel.chess.piece;

/**
 * This exception is thrown when certain methods are called with an invalid piece type
 * @author martin
 */

public class IllegalTypeException extends IllegalArgumentException {

    public IllegalTypeException(String s) {
        super(s);
    }

}
