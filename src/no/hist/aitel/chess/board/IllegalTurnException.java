/*
 * IllegalTurnException.java
 *
 */

package no.hist.aitel.chess.board;

/**
 * This exception is thrown when a player attempts to move without having the current turn
 * @author martin
 */

public class IllegalTurnException extends IllegalArgumentException {

    /**
     * This exception is thrown the current color isn't allowed to move
     * @param s
     */
    public IllegalTurnException(String s) {
        super(s);
    }
    
}
