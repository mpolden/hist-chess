/*
 * BoardException.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */
public class CheckException extends IllegalArgumentException {

    /**
     * Thrown when player is in check or tries to move a piece that will put the player in check
     * @param s
     */
    public CheckException(String s) {
        super(s);
    }

}
