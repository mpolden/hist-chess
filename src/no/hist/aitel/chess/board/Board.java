/*
 * Board.java
 * 
 */

package no.hist.aitel.chess.board;

import no.hist.aitel.chess.position.Position;
import no.hist.aitel.chess.piece.Piece;
import no.hist.aitel.chess.piece.IllegalPieceException;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Board {

    final private int size = 64;
    private Piece[] board = new Piece[size];
    private Piece[] captured = new Piece[size];
    private int turn = WHITE;
    private Position p = new Position(this);
    
    /**
     * Creates the board and makes it ready for a new game
     */
    public Board() {
        board = new BoardInit().getInitBoard();
    }

    /**
     * Get board
     * @return The current board
     */
    public Piece[] getBoard() { // Not needed?
        return board;
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
     * Move a piece from an old position to a new position
     * @param from
     * @param to
     */
    public void movePiece(int from, int to) {

        // Check if any of the positions are outside the board
        if ((from < 0 || from > 63) || (to < 0 || from > 63)) {
            throw new IllegalPieceException("Can't move pieces outside of the board.\n"
                    + "\nFrom: " + from
                    + "\nTo: " + to);
        }

        // Check if piece in 'from' position is empty
        if (getPiece(from).isEmpty()) {
            throw new IllegalPieceException("Can't move empty piece.\n" +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }
        
        // Check whos turn it is
        int color = getPiece(from).getColor();
        if (!isValidTurn(color)) {
            throw new IllegalTurnException("Color " + color + " is not allowed to move now.");
        }

//        System.out.println(isCastling(from, to));
        if (isCastling(from, to)) {
            doCastling(from, to);
        } else {
            p.setPositions(from, to);
            p.verifyPositions();

            if (!getPiece(to).isEmpty()) {
                addCaptured(getPiece(to));
            }

            board[to] = getPiece(from);
            getPiece(to).setInitPosition(false);
            board[from] = new Piece(); // Empty piece
        }

        switchTurn();
    }

    /**
     * Get the captured pieces
     * @return The captured pieces
     */
    public Piece[] getCaptured() {
        return captured;
    }

    /**
     * Adds a piece to the captured array
     * @param piece
     */
    private void addCaptured(Piece piece) {
        for (int i = 0; i < captured.length; i++) {
            if (captured[i] == null) {
                captured[i] = piece;
                break;
            }
        }
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
     * Get current turn
     */
    public int getTurn() {
        return turn;
    }

    private void doCastling(int from, int to) {
        if (from != 4 && from != 60) {
            throw new IllegalSpecialMoveException("Castling can only be performed from position 4 or 60");
        }
        int rookTo = -1, rookFrom = -1;
        if (from == 4 || from == 60) {
            if (to == (from + 2)) {
                rookTo = from + 1;
                rookFrom = from + 3;
            } else if (to == (from - 2)) {
                rookTo = from - 2;
                rookFrom = from - 4;
            }
            board[to] = getPiece(from);
            board[from] = new Piece();
            board[rookTo] = getPiece(rookFrom);
            board[rookFrom] = new Piece();
        }
    }

    private boolean isCastling(int from, int to) {
        Piece fromPiece = getPiece(from);
        
        if (fromPiece.getType() == KING && fromPiece.isInitPosition()) {
            if (fromPiece.getColor() == WHITE) {
                int rookPos;
                if (to == 6 && getPiece(5).isEmpty() && getPiece(6).isEmpty()) {
                    rookPos = 7;
                } else if (to == 2 && getPiece(1).isEmpty() && getPiece(2).isEmpty() && getPiece(3).isEmpty()) {
                    rookPos = 0;
                } else {
                    rookPos = -1;
                }
                Piece rook;
                if (rookPos > -1) {
                    rook = getPiece(rookPos);
                } else {
                    return false;
                }
                if (rook.getColor() == WHITE && rook.getType() == ROOK && rook.isInitPosition()) {
                    return true;
                }
            } else if (fromPiece.getColor() == BLACK) {
                int rookPos;
                if (to == 62 && getPiece(61).isEmpty() && getPiece(62).isEmpty()) {
                    rookPos = 63;
                } else if (to == 58 && getPiece(57).isEmpty() && getPiece(58).isEmpty() && getPiece(59).isEmpty()) {
                    rookPos = 56;
                } else {
                    rookPos = -1;
                }
                Piece rook;
                if (rookPos > -1) {
                    rook = getPiece(rookPos);
                } else {
                    return false;
                }
                if (rook.getColor() == BLACK && rook.getType() == ROOK && rook.isInitPosition()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmptyRange(int from, int to) {
        if (to < from) {
            for (int i = from; i >= to; i--) {
                if (!getPiece(i).isEmpty()) {
                    return false;
                }
            }
        } else {
            for (int i = from; i <= to; i++) {
                if (!getPiece(i).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
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
