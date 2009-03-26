/*
 * BoardException.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */
public class BoardException extends IllegalArgumentException {

    /**
     * Thrown when movePiece is called with invalid positions etc.
     * @param s
     */
    public BoardException(String s) {
        super(s);
    }

}
