/*
 * Board.java
 * 
 */

package no.hist.aitel.java.chess.board;

import no.hist.aitel.java.chess.pieces.Piece;

/**
 *
 * @author martin
 */
public class Board {
    final private int size = 64;
    private Piece[] pieces = new Piece[size];
    
    public Board() {
        
    }

    public Piece[] getPieces() {
        return pieces;
    }
    
}
