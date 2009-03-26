/*
 * Board.java
 * 
 */

package no.hist.aitel.chess.board;

import java.io.Serializable;
import no.hist.aitel.chess.piece.Piece;
import no.hist.aitel.chess.piece.IllegalPieceException;
import no.hist.aitel.chess.position.IllegalSpecialPositionException;
import no.hist.aitel.chess.position.Position;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Board implements Serializable {

    final private int size = 64;
    private Piece[] board = new Piece[size];
    private Position p = new Position(this);
    private int turn = WHITE;
    
    /**
     * Creates the board and makes it ready for a new game
     */
    public Board() {
        board = new BoardInit().getInitBoard();
    }

    /**
     * Get piece
     * @param position
     * @return The piece in the given position
     */
    public Piece getPiece(int position) {
        return board[position];
    }

    /**
     * Set piece
     * @param position
     * @param piece
     */
    private void setPiece(int position, Piece piece) {
        board[position] = piece;
    }

    /**
     * Checks if in check
     * @return True if in check and false otherwise
     */
//    public boolean inCheck() {
//        for (int position = 0; position < board.length; position++) {
//            if (boardgetColor() == WHITE) {
//                try {
//
//                }
//            }
//        }
//        return false;
//    }

    /**
     * Move a piece from an old position to a new position
     * @param from
     * @param to
     */
    public void movePiece(int from, int to) throws BoardException {

        // Check if any of the positions are outside the board
        if ((from < 0 || from > 63) || (to < 0 || from > 63)) {
            throw new BoardException("Can't move pieces outside of the board.\n"
                    + "\nFrom: " + from
                    + "\nTo: " + to);
        }

        // Check if piece in 'from' position is empty
        if (getPiece(from).isEmpty()) {
            throw new BoardException("Can't move empty piece.\n" +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }
        
        // Check whos turn it is
        if (!isValidTurn(getPiece(from).getColor())) {
            throw new BoardException("Not allowed to move now.");
        }

        // Check if we're in check
//        if (p.inCheck() && getPiece(from).getType() != KING) { need to move king; }

        // Set our positions
        p.setPositions(from, to);

        // Check if we're doing a special move
        if (p.isCastling()) {
            doCastling(from, to);
        } else if (p.isEnPassant()) {
            doEnPassant(from, to);
        } else {

            // Regular move
            p.verifyPositions();
            doRegularMove(from, to);

        }

        // Switch turn
        switchTurn();
    }

    /**
     * Perform a regular move
     * @param from
     * @param to
     */
    private void doRegularMove(int from, int to) {
        setPiece(to, getPiece(from));
        getPiece(to).setMoved(true);
        setPiece(from, new Piece()); // Empty piece
    }

    /**
     * Perform castling move
     * @param from
     * @param to
     */
    private void doCastling(int from, int to) throws IllegalSpecialPositionException {
        int rookTo = -1, rookFrom = -1;
        if (from == 4 || from == 60) {
            if (to == (from + 2)) {
                rookTo = from + 1;
                rookFrom = from + 3;
            } else if (to == (from - 2)) {
                rookTo = from - 1;
                rookFrom = from - 4;
            } else {
                throw new IllegalSpecialPositionException("Castling is allowed, but method was " +
                        "called with invalid positions.\nFrom: " + from + "\nTo: " + to);
            }
            setPiece(to, getPiece(from));
            setPiece(from, new Piece());
            setPiece(rookTo, getPiece(rookFrom));
            setPiece(rookFrom, new Piece());
        }
    }
    /**
     * Perform en passant move
     * @param from
     * @param to
     */
    private void doEnPassant(int from, int to) throws IllegalSpecialPositionException {
        int pawn = -1;
        if (to == from + 9 || to == from + 7) {
            pawn = to - 8;
        } else if (to == from - 9 || to == from - 7) {
            pawn = to + 8;
        } else {
            throw new IllegalSpecialPositionException("En passant is allowed, but method was " +
                    "called with invalid positions.\nFrom: " + from + "\nTo: " + to);
        }
        setPiece(to, getPiece(from));
        setPiece(from, new Piece());
        setPiece(to - 8, new Piece());
    }

    /**
     * Check whos turn it is
     * @param color
     * @return True if color can move and false otherwise
     */
    private boolean isValidTurn(int color) {
        return (color == turn);
    }

    /**
     * Switches turn
     */
    private void switchTurn() {
        turn ^= 1; // Bitwise flip between 0 and 1
    }

    /**
     * Produces a string representation of the chess board
     * @return Fancy ascii drawing of the board, colors and types
     */
    @Override
    public String toString() {
        String out = ""; 
        for (int i = 0; i < board.length; i++) {
            if (i % 8 == 0) {
                out = out + "\n";
            }
            int type = board[i].getType();
            int color = board[i].getColor();
            if (color == WHITE) {
                out += "W";
            } else if (color == BLACK) {
                out += "B";
            } else {
                out += "x";
            }
            if (type == UNDEFINED) {
                out += "x ";
            } else {
                out += type + " ";
            }
        }
        // Reverse output
        String[] outArr = out.split("\n");
        out = "+-------------------------+\n";
        for (int i = outArr.length - 1; i > 0; i--) {
            out += "| " + outArr[i] + "|\n";
        }
        out += "+-------------------------+";
        return out;
    }
}
