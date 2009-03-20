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
    private Position p = new Position(this);
    private int turn = 0; // White always begins

    /**
     * Creates the board and makes it ready for a new game
     */
    public Board() {
        fillBoard();
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
        
        // Check whos turn it is
        int color = getPiece(fromPos).getColor();
        if (color > -1 && !isValidTurn(color)) {
            throw new IllegalTurnException("It's not your turn!");
        }

        p.setFrom(fromPos);
        p.setTo(toPos);

        if (p.isValidMove()) {
//            System.out.println("-- " + board[fromPos].getColorStr() + " moved " +
//                    board[fromPos].getTypeStr() + " from " + fromPos + " to " + toPos);
            // Save captured piece
            if (!board[toPos].isEmpty()) {
                captured.add(board[toPos]);
//                System.out.println("** " + board[fromPos].getColorStr() + " captured " +
//                        board[toPos].getTypeStr() + " in " + toPos);
            }

            board[toPos] = board[fromPos]; // Move the piece in the table
            board[fromPos] = new Piece(fromPos, -1, -1); // Place empty piece in old position
            board[toPos].setPosition(toPos); // Update position in new piece
            switchTurn(); // Switch turn


        }
    }

    /**
     * Get the captured pieces
     * @return The captured pieces
     */
    public ArrayList<Piece> getCaptured() {
        return captured;
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
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    /**
     * Fills the board with pieces in their initial positions
     */
    private void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new Piece(i, getInitColor(i), getInitType(i));
        }
    }

    /**
     * Get the color for an initial position
     * @param position
     * @return The color
     */
    private int getInitColor(int position) {
        if (position <= 15) {
            return 0; // White
        } else if (position >= 48) {
            return 1; // Black
        }
        return -1; // Initially empty position
    }

    /**
     * Get the type for an initial position
     * @param position
     * @return The type
     */
    private int getInitType(int position) {
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
