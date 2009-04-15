/*
 * BoardException.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */
public class CheckMateException extends IllegalArgumentException {

    /**
     * Thrown when player is in check and can't move, check mate!
     * @param s
     */
    public CheckMateException(String s) {
        super(s);
    }

}
