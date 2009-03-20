/*
 * Board.java
 * 
 */

package no.hist.aitel.chess.board;

import java.util.ArrayList;

/**
 *
 * @author martin
 */
public class Board {

    final private int size = 64;
    private Piece[] board = new Piece[size];
    private ArrayList<Piece> captured = new ArrayList<Piece>();
    private Position p = new Position();
    private int turn = 0; // White always begins

    public Board() {
        fillBoard();
    }

    /**
     * Returns the current board
     * @return An Piece[] array which represents the current board
     */
    public Piece[] getBoard() {
        return board;
    }

    public Piece getPiece(int index) {
        return board[index];
    }

    /**
     * Moves a piece from an old position to a new position
     * @param oldPosition
     * @param newPosition
     */
    public void movePiece(int fromPos, int toPos) {
        
        // Check whos turn it is
        int color = getPiece(fromPos).getColor();
        if (color > -1 && !isValidTurn(color)) {
            throw new IllegalTurnException("It's not your turn!");
        }

        p.setFrom(board[fromPos]);
        p.setTo(board[toPos]);

        if (p.isValidMove()) {
            // Save captured piece
            if (!board[toPos].isEmpty()) {
                captured.add(board[toPos]);
            }

            board[toPos] = board[fromPos]; // Move the piece in the table
            board[fromPos] = new Piece(fromPos, -1, -1); // Place empty piece in old position
            board[toPos].setPosition(toPos); // Update position in new piece
            switchTurn(); // Switch turn
        }
    }

    public Piece getCaptured(int index) {
        return captured.get(index);
    }

    // Check if color can move pieces
    private boolean isValidTurn(int color) {
        return (color == turn);
    }

    // Switch turn
    private void switchTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    // Fill board with pieces
    private void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new Piece(i, getColor(i), getType(i));
        }
    }

    // Get color from (initial) position
    private int getColor(int pos) {
        if (pos <= 15) {
            return 0; // White
        } else if (pos >= 48) {
            return 1; // Black
        }
        return -1; // Initially empty position
    }

    // Get piece type from (initial) position
    private int getType(int position) {
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
                return -1; // Initially empty position
            }
        }
    }

    /**
     * Produces a string representation of the chess board.
     * @return A string containing the board and types.
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
