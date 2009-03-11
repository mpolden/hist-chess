/*
 * Board.java
 * 
 */

package no.hist.aitel.java.chess.board;

import java.awt.Image;
import no.hist.aitel.java.chess.pieces.Piece;

/**
 *
 * @author martin
 */
public class Board {
    final private int size = 64;
    private Piece[] board = new Piece[size];
    private Image[] pieces = new Image[18];
    
    public Board() {
        
    }

  /*public Piece[] getPieces() {
        return pieces;
    }*/
    
}
