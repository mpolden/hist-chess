/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

import no.hist.aitel.chess.piece.Piece;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author Martin
 */
public class BoardInit {

    final private int size = 64;
    final private Piece[] board = new Piece[size];
    private int position;

    /**
     * Creates an initial board
     */
    public BoardInit() {
        fillInitBoard();
    }

    /**
     * Get a "fresh" board with pieces in their initial positions
     * @return The board
     */
    public Piece[] getInitBoard() {
        return board;
    }

    /**
     * Fills the initial board with pieces
     */
    private void fillInitBoard() {
        for (position = 0; position < board.length; position++) {
            board[position] = new Piece(getInitColor(), getInitType(), position);
        }
    }

    /**
     * Get the color for an initial position
     * @return The color
     */
    private int getInitColor() {
        if (position <= 15) {
            return WHITE;
        } else if (position >= 48) {
            return BLACK;
        }
        return UNDEFINED;
    }

    /**
     * Get the type for an initial position
     * @return The type
     */
    private int getInitType() {
        switch (position) {
            // 0   1   2   3   4   5   6   7
            // 56  57  58  59  60  61  62  63
            // = Rook Knight Bishop Queen King Bishop Knight Rook
            case 0:
            case 56: {
                return ROOK;
            }
            case 1:
            case 57: {
                return KNIGHT;
            }
            case 2:
            case 58: {
                return BISHOP;
            }
            case 3:
            case 59: {
                return QUEEN;
            }
            case 4:
            case 60: {
                return KING;
            }
            case 5:
            case 61: {
                return BISHOP;
            }
            case 6:
            case 62: {
                return KNIGHT;
            }
            case 7:
            case 63: {
                return ROOK;
            }
            // 8   9   10  11  12  13  14  15
            // 48  49  50  51  52  53  54  55
            // = Pawn
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55: {
                return PAWN;
            }
            default: {
                return UNDEFINED;
            }
        }
    }
}
