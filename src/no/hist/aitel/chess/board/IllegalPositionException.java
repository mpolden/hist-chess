/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author Martin
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
