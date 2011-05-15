/*
 * IllegalSpecialPositionException.java
 *
 * Created: 25.mar.2009 16:26:23
 */

package no.hist.aitel.chess.position;

/**
 *
 * @author Martin
 */

public class IllegalSpecialPositionException extends IllegalArgumentException {

    /**
     * This exception is thrown when special move methods are called with invalid positions
     * @param s
     */
    public IllegalSpecialPositionException(String s) {
        super(s);
    }

}
