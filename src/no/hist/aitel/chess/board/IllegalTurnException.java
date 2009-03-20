/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author Martin
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
