/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.java.chess.board;

/**
 *
 * @author Martin
 */
public class IllegalPositionException extends IllegalArgumentException {

    public IllegalPositionException(String s) {
        super(s);
    }

}
