/*
 * Board.java
 * 
 */

package no.hist.aitel.chess.board;

import java.io.Serializable;
import no.hist.aitel.chess.piece.IllegalTypeException;
import no.hist.aitel.chess.piece.Piece;
import no.hist.aitel.chess.position.IllegalPositionException;
import no.hist.aitel.chess.position.IllegalSpecialPositionException;
import no.hist.aitel.chess.position.Position;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Board implements Serializable, Cloneable {

    final private int size = 64;
    private Piece[] board = new Piece[size];
    private int turn = WHITE;
    private boolean inCheck = false;
    private boolean checkMate = false;
    private Position p = new Position(this);
    private boolean fake = false;
    
    /**
     * Creates the board and makes it ready for a new game
     */
    public Board() {
        board = new BoardInit().getInitBoard();
    }

    /**
     * Reset board
     */
    public void reset() {
        board = new BoardInit().getInitBoard();
        turn = WHITE;
        inCheck = false;
        checkMate = false;
        p = new Position(this);
    }

    /**
     * Get piece
     * @param position
     * @return The piece in the given from
     */
    public Piece getPiece(int position) {
        return board[position];
    }

    /**
     * Set piece
     * @param position
     * @param piece
     */
    public void setPiece(int position, Piece piece) {
        board[position] = piece;
    }

    /**
     * Set board
     * @param board
     */
    public void setBoard(Piece[] board) {
        this.board = board;
    }

    /**
     * Get enPassant from Position object
     * @return True if previous move was a pawn moving two fields forward (from initial position)
     */
    public boolean getEnPassant() {
        return p.getEnPassant();
    }

    /**
     * Move a piece
     * @param from
     * @param to
     */
    public void movePiece(int from, int to) throws BoardException {

        // Check if piece in 'from' is empty
        if (getPiece(from).isEmpty()) {
            throw new BoardException("Can't move empty piece.\n" +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }
        
        // Check whos turn it is
        if (!isValidTurn(getPiece(from).getColor())) {
            throw new BoardException("Not allowed to move now.");
        }

        // Save the pieces we're moving, in case we need to revert the move
        Piece fromPiece = getPiece(from);
        Piece toPiece = getPiece(to);

        // Set our positions
        p.setPositions(from, to);

        // Check if we're doing a special move
        if (p.isCastling()) {
            doCastling(from, to);
        } else if (p.isEnPassant()) {
            doEnPassant(from, to);
        } else {

            // Regular move
            p.verifyPositions(false);
            doRegularMove(from, to);

        }

        // Check if player is in check after move, but wasn't initially in check
        if (!isInCheck() && inCheckAfterMove()) {
            // Undo move
            setPiece(from, fromPiece);
            setPiece(to, toPiece);
            throw new CheckException("You can't put yourself in check.\n" +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Check if player is initially in check and is still in check after move
        if (isInCheck() && inCheckAfterMove() && !isCheckMate()) {
            // Undo move
            setPiece(from, fromPiece);
            setPiece(to, toPiece);
            throw new CheckException("Still in check! Move another piece.\n" +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Check if player is check mate
        if (isCheckMate()) {
            throw new CheckMateException("Game over");
        }

        // Update check and check mate state
        updateInCheck();
        if (!fake) { // Calling updateCheckMate() on fake boards will cause a StackOverflow
            updateCheckMate();
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
        setPiece(pawn, new Piece());
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
     * Update current check state
     */
    private void updateInCheck() {
        int opponent = turn ^ 1;
//        if (fake) {
//            System.out.println("checking if any of player " + opponent + " can put my king in check");
//        }
        // Check any of the current players pieces has put opponent in check
        for (int position = 0; position < board.length; position++) {
            if (getPiece(position).getColor() == turn) {
                try {
                    p.setPositions(position, getKing(opponent));
                    p.verifyPositions(true);
//                    if (fake) {
//                        System.out.println(position + " can get my king in " + getKing(opponent));
//                    }
                    inCheck = true;
                    break;
                } catch (IllegalPositionException e) {
                    inCheck = false;
                }
            }
        }
    }

    /**
     * Check if player is in check after move
     * @return True if player is in check after move and false otherwise
     */
    private boolean inCheckAfterMove() {
        int opponent = turn ^ 1;
        for (int position = 0; position < board.length; position++) {
            if (getPiece(position).getColor() == opponent) {
                try {
                    // Check if any opponent piece can move to my king
                    p.setPositions(position, getKing(turn));
                    p.verifyPositions(true);
                    return true;
                } catch (IllegalPositionException e) {
                }
            }
        }
        return false;
    }

    /**
     * Clone current board
     * @return The board
     */
    private Board getFakeBoard() {
        Board fakeBoard;
        try {
            fakeBoard = (Board)this.clone();
            fakeBoard.setBoard(board.clone());
            fakeBoard.p = new Position(fakeBoard);
            fakeBoard.fake = true;
        } catch (CloneNotSupportedException e) {
            fakeBoard = null;
        }
        return fakeBoard;
    }

    /**
     * Update check mate state
     */
    private void updateCheckMate() {
        if (!isInCheck()) {
            checkMate = false;
        } else {
            // Clone Board object so we can simulate moves
            Board fakeBoard = getFakeBoard();
            fakeBoard.switchTurn();
            out: {
                for (int from = 0; from < fakeBoard.board.length; from++) {
                    if (fakeBoard.getPiece(from).getColor() == fakeBoard.turn) {
                        for (int to = 0; to < fakeBoard.board.length; to++) {
                            try {
                                fakeBoard.movePiece(from, to);
                                if (!fakeBoard.isInCheck()) {
                                    checkMate = false;
                                    break out;
                                }
                                fakeBoard = getFakeBoard();
                            } catch (BoardException e) {
                            } catch (IllegalPositionException e) {
                            } catch (IllegalTypeException e) {
                            } catch (CheckException e) {
                            }
                            checkMate = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if player is in check
     * @return True if in check and false otherwise
     */
    private boolean isInCheck() {
        return inCheck;
    }

    /**
     * Check if player is check mate
     * @return True if check mate and false otherwise
     */
    private boolean isCheckMate() {
        return checkMate;
    }

    /**
     * Get the position of a king
     * @param color
     * @return The position
     */
    private int getKing(int color) {
        for (int position = 0; position < board.length; position++) {
            if (getPiece(position).getColor() == color && getPiece(position).getType() == KING) {
                return position;
            }
        }
        return -1;
    }

    /**
     * Switches turn
     */
    private void switchTurn() {
        turn ^= 1; // Bitwise flip between 0 and 1
    }

    public String getNotation(int position) {
        int rank = 0;
        for (int i = 0; i <= position; i++) {
            if (i % 8 == 0) {
                rank++;
            }
        }

        char file;

        switch (position) {
            case 0:
            case 8:
            case 16:
            case 24:
            case 32:
            case 40:
            case 48:
            case 56: {
                file = 'A';
                break;
            }
            case 1:
            case 9:
            case 17:
            case 25:
            case 33:
            case 41:
            case 49:
            case 57: {
                file = 'B';
                break;
            }
            case 2:
            case 10:
            case 18:
            case 26:
            case 34:
            case 42:
            case 50:
            case 58: {
                file = 'C';
                break;
            }
            case 3:
            case 11:
            case 19:
            case 27:
            case 35:
            case 43:
            case 51:
            case 59: {
                file = 'D';
                break;
            }
            case 4:
            case 12:
            case 20:
            case 28:
            case 36:
            case 44:
            case 52:
            case 60: {
                file = 'E';
                break;
            }
            case 5:
            case 13:
            case 21:
            case 29:
            case 37:
            case 45:
            case 53:
            case 61: {
                file = 'F';
                break;
            }
            case 6:
            case 14:
            case 22:
            case 30:
            case 38:
            case 46:
            case 54:
            case 62: {
                file = 'G';
                break;
            }
            case 7:
            case 15:
            case 23:
            case 31:
            case 39:
            case 47:
            case 55:
            case 63: {
                file = 'H';
                break;
            }
            default: {
                file = '_';
            }
        }

        return file + "" + rank;
    }

    /**
     * Get a string format of the board state
     * @return Values of turn, inCheck and checkMate
     */
    public String getStateStr() {
        String out = "turn: " + turn + "\ninCheck: " + inCheck +
                "\ncheckMate: " + checkMate + "\nfake: " + fake;
        return out;
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
