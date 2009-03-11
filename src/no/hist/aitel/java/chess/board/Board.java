/*
 * Board.java
 * 
 */

package no.hist.aitel.java.chess.board;

import javax.swing.ImageIcon;
import no.hist.aitel.java.chess.pieces.Piece;

/**
 *
 * @author martin
 */
public class Board {
    final private int size = 64;
    private Piece[] pieces = new Piece[size];
    private ImageIcon[] pieceIcons = new ImageIcon[12];
    
    public Board() {
        pieceIcons[0] = new ImageIcon("images/pawnw.gif", "white pawn");
        pieceIcons[1] = new ImageIcon("images/knightw.gif", "white knight");
        pieceIcons[2] = new ImageIcon("images/bishopw.gif", "white bishop");
        pieceIcons[3] = new ImageIcon("images/rookw.gif", "white rook");
        pieceIcons[4] = new ImageIcon("images/queenw.gif", "white queen");
        pieceIcons[5] = new ImageIcon("images/kingw.gif", "white king");
        pieceIcons[6] = new ImageIcon("images/pawnb.gif", "black pawn");
        pieceIcons[7] = new ImageIcon("images/knightb.gif", "black knight");
        pieceIcons[8] = new ImageIcon("images/bishopb.gif", "black bishop");
        pieceIcons[9] = new ImageIcon("images/rookb.gif", "black rook");
        pieceIcons[10] = new ImageIcon("images/queenb.gif", "black queen");
        pieceIcons[11] = new ImageIcon("images/kingb.gif", "black king");
    }

    public Piece[] getPieces() {
        return pieces;
    }
    public ImageIcon getPieceIcon(int i) {
        return pieceIcons[i];
    }
}
