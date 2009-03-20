

package no.hist.aitel.chess.gui;

import javax.swing.ImageIcon;
import no.hist.aitel.chess.board.Piece;

/**
 *
 * @author Vegard
 */
public class Images {
    final private int size = 64;
    private Piece[] pieces = new Piece[size];
    private ImageIcon[] pieceIcons = new ImageIcon[12];

    public Images() {
        pieceIcons[0] = new ImageIcon("resources/pawnw.gif", "white pawn");
        pieceIcons[1] = new ImageIcon("resources/knightw.gif", "white knight");
        pieceIcons[2] = new ImageIcon("resources/bishopw.gif", "white bishop");
        pieceIcons[3] = new ImageIcon("resources/rookw.gif", "white rook");
        pieceIcons[4] = new ImageIcon("resources/queenw.gif", "white queen");
        pieceIcons[5] = new ImageIcon("resources/kingw.gif", "white king");
        pieceIcons[6] = new ImageIcon("resources/pawnb.gif", "black pawn");
        pieceIcons[7] = new ImageIcon("resources/knightb.gif", "black knight");
        pieceIcons[8] = new ImageIcon("resources/bishopb.gif", "black bishop");
        pieceIcons[9] = new ImageIcon("resources/rookb.gif", "black rook");
        pieceIcons[10] = new ImageIcon("resources/queenb.gif", "black queen");
        pieceIcons[11] = new ImageIcon("resources/kingb.gif", "black king");
    }

    public Piece[] getPieces() {
        return pieces;
    }
    public ImageIcon getPieceIcon(int i) {
        return pieceIcons[i];
    }
}
