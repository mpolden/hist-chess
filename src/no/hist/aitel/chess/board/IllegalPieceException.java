/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author Martin
 */
public class IllegalPieceException extends IllegalArgumentException {

    public IllegalPieceException(String s) {
        super(s);
    }
    
}
