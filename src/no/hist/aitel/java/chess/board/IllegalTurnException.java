/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.java.chess.board;

/**
 *
 * @author Martin
 */
public class IllegalTurnException extends IllegalArgumentException {

    public IllegalTurnException(String s) {
        super(s);
    }
    
}
