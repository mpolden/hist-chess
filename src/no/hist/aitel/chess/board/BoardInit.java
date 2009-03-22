/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

import no.hist.aitel.chess.piece.Piece;

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
            board[position] = new Piece(getInitColor(), getInitType());
        }
    }

    /**
     * Get the color for an initial position
     * @return The color
     */
    private int getInitColor() {
        if (position <= 15) {
            return 0; // White
        } else if (position >= 48) {
            return 1; // Black
        }
        return -1; // Empty piece
    }

    /**
     * Get the type for an initial position
     * @return The type
     */
    private int getInitType() {
        switch (position) {
            // 0   1   2   3   4   5   6   7
            // 56  57  58  59  60  61  62  63
            // = Rook (3) Knight (2) Bishop(1) Queen (4) King (5) Bishop(1) Knight(2) Rook(3)
            case 0:
            case 56: {
                return 3;
            }
            case 1:
            case 57: {
                return 2;
            }
            case 2:
            case 58: {
                return 1;
            }
            case 3:
            case 59: {
                return 4;
            }
            case 4:
            case 60: {
                return 5;
            }
            case 5:
            case 61: {
                return 1;
            }
            case 6:
            case 62: {
                return 2;
            }
            case 7:
            case 63: {
                return 3;
            }
            // 8   9   10  11  12  13  14  15
            // 48  49  50  51  52  53  54  55
            // = Pawn (0)
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
                return 0;
            }
            default: {
                return -1; // Empty piece
            }
        }
    }
}
