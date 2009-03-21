/*
 * Board.java
 * 
 */

package no.hist.aitel.chess.board;

import no.hist.aitel.chess.position.Position;
import no.hist.aitel.chess.piece.Piece;
import no.hist.aitel.chess.piece.IllegalPieceException;

/**
 *
 * @author martin
 */

public class Board {

    final private int size = 64;
    private Piece[] board = new Piece[size];
    private Piece[] captured = new Piece[size];
    private int turn = 0;
    private Position p = new Position(this);
    
    /**
     * Creates the board and makes it ready for a new game
     */
    public Board() {
        // Using the BoardInit class to get an initial board
        BoardInit boardInit = new BoardInit();
        board = boardInit.getInitBoard();
    }

    /**
     * Get board
     * @return The current board
     */
    public Piece[] getBoard() {
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
     * @param fromPos
     * @param toPos
     */
    public void movePiece(int fromPos, int toPos) {

        // Check if any of the positions are outside the board
        if ((fromPos < 0 || fromPos > 63) || (toPos < 0 || fromPos > 63)) {
            throw new IllegalPieceException("Can't move pieces outside of the board.\n"
                    + "\nFrom: " + fromPos
                    + "\nTo: " + toPos);
        }

        // Check if piece in 'from' position is empty
        if (getPiece(fromPos).isEmpty()) {
            throw new IllegalPieceException("Can't move empty piece.\n" +
                    "\nFrom: " + fromPos +
                    "\nTo: " + toPos);
        }
        
        // Check whos turn it is
        if (!isValidTurn(getPiece(fromPos).getColor())) {
            throw new IllegalTurnException("It's not your turn!");
        }

        p.setPositions(fromPos, toPos);

        if (p.isValidMove()) {
            if (!getPiece(toPos).isEmpty()) {
                addCaptured(getPiece(toPos));
            }

            board[toPos] = getPiece(fromPos);
            board[fromPos] = new Piece();            
            switchTurn();
        }
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
    public void switchTurn() {
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
            if (color == 0) {
                out += "W";
            } else if (color == 1) {
                out += "B";
            } else {
                out += "x";
            }
            if (type == -1) {
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
