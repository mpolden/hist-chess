/*
 * IllegalPositionException.java
 *
 */

package no.hist.aitel.chess.position;

/**
 *
 * @author martin
 */

public class IllegalPositionException extends IllegalArgumentException {

    /**
     * This exception is thrown when an illegal move is attempted
     * @param s
     */
    public IllegalPositionException(String s) {
        super(s);
    }

}
