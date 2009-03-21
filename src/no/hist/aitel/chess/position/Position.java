/*
 * Position.java
 *
 */

package no.hist.aitel.chess.position;

import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.piece.Piece;

/**
 *
 * @author martin
 */

public class Position {
    private Board board;
    private int from, to, diff;

    /**
     * Creates a position object which is used to validate moves
     */
    public Position(Board board) {
        this.board = board;
    }

    /**
     * Sets the from and to positions
     * @param from
     * @param to
     */
    public void setPositions(int from, int to) {
        this.from = from;
        this.to = to;
        this.diff = to - from;
    }

    /**
     * Checks if a move is valid
     * @return True if the move is valid otherwise an exception is thrown
     */
    public boolean isValid() {
        // Get destionation piece
        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);

        // Can't capture piece of same color
        if (fromPiece.getColor() == toPiece.getColor()) {
            throw new IllegalPositionException("Can't capture piece of same type.\n" +
                    "Type: " + fromPiece.getType() +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Piece type
        int type = fromPiece.getType();

        // Direction
        int direction = getDirection(type);

        // Check if path is clear, not checking for type == 2 (Knight) since it can jump over pieces
        if (type != 2 && !isPathClear(direction)) {
            throw new IllegalPositionException("A piece is blocking my path.\n" +
                    "Type: " + fromPiece.getType() +
                    "\nFrom: " + from +
                    "\nTo: " + to);
        }

        // Type specific rules
        switch (type) {
            // Pawn .. garbage follows :|
            case 0: {
                if (toPiece.isEmpty()) { // New position is empty, pawn can then only move forward
                    if (fromPiece.getColor() == 0) { // White piece
                        if (from >= 8 && from <= 15) { // If the pawn is in its original position, it can move 1 or 2 fields forward
                            if (diff != 8 && diff != 16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + fromPiece.getType() +
                                        "\nFrom: " + from +
                                        "\nTo: " + to);
                            }
                        } else if (diff != 8) { // Pawn can always move 1 field forward
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + fromPiece.getType() +
                                    "\nFrom: " + from +
                                    "\nTo: " + to);
                        }
                    } else if (fromPiece.getColor() == 1) { // Black piece
                        if (from >= 48 && from <= 55) { // Same as above
                            if (diff != -8 && diff != -16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + fromPiece.getType() +
                                        "\nFrom: " + from +
                                        "\nTo: " + to);
                            }
                        } else if (diff != -8) {
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + fromPiece.getType() +
                                    "\nFrom: " + from +
                                    "\nTo: " + to);
                        }
                    }
                } else {
                    if (fromPiece.getColor() == 0 && diff != 9 && diff != 7) {
                        throw new IllegalPositionException("Pawn can't move forward because field isn't empty.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    } else if (fromPiece.getColor() == 1 && diff != -9 && diff != -7) {
                        throw new IllegalPositionException("Pawn can't move forward because field isn't empty.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
                return true;
            }
            // Bishop
            case 1: {
                if (diff % 7 != 0 && diff % 9 != 0) {
                    throw new IllegalPositionException("Bishop can only move diagonally.\n" +
                            "Type: " + fromPiece.getType() +
                            "\nFrom: " + from +
                            "\nTo: " + to);
                }
                return true;
            }
            // Knight
            case 2: {
                switch (diff) {
                    case -10:
                    case -17:
                    case -15:
                    case -6:
                    case 10:
                    case 17:
                    case 15:
                    case 6: {
                        return true;
                    }
                    default: {
                        throw new IllegalPositionException("Knight can only move one field diagonally + one forward.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
            }
            // Rook
            case 3: {
                if (diff % 7 == 0 || diff % 9 == 0) {
                    throw new IllegalPositionException("Rook can't move diagonally.\n" +
                            "Type: " + fromPiece.getType() +
                            "\nFrom: " + from +
                            "\nTo: " + to);
                }
                return true;
            }
            // Queen
            case 4: {
                switch (diff) {
                    case -10:
                    case -17:
                    case -15:
                    case -6:
                    case 10:
                    case 17:
                    case 15:
                    case 6: {
                        throw new IllegalPositionException("Queen can't move one field diagonally + one forward.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                    default: {
                        return true;
                    }
                }
            }
            // King
            case 5: {
                switch (diff) {
                    case -1:
                    case -7:
                    case -8:
                    case -9:
                    case 1:
                    case 7:
                    case 8:
                    case 9: {
                        return true;
                    }
                    default: {
                        throw new IllegalPositionException("King can only move one field in any direction.\n" +
                                "Type: " + fromPiece.getType() +
                                "\nFrom: " + from +
                                "\nTo: " + to);
                    }
                }
            }
            default: {
                throw new IllegalPositionException("Tried to move empty piece " +
                        "(should never happen)");
            }
        }
    }

    /**
     * Get the current direction for a type
     * @param type
     * @return The direction or -1 if something bad happens
     */
    private int getDirection(int type) {

        int[] directions = getDirections(type);
        switch (type) {
            case 0: // Pawn
            case 1: // Bishop
            case 3: // Rook
            case 4: // Queen
            case 5: // King
                for (int direction : directions) {
                    if (diff % direction == 0) {
                        return direction;
                    }
                }
                return -1; // Piece is moving one field to the left or right (1 % (n!=1) != 0)
            default: {

                return -1;
            }
        }
    }


    /**
     * Get possible directions for a type
     * @param type
     * @return The possible directions for the type or -1 if something bad happens
     */
    private int[] getDirections(int type) {
        switch (type) {
            case 0: {
                return new int[] {7, 8, 9, 16};
            }
            case 1: {
                return new int[] {7, 9};
            }
            case 2: {
                return new int[] {6, 10, 15, 17};
            }
            case 3: {
                return new int[] {8}; // Not including 1 as n % 1 == 0 and that causes an invalid
                                      // warning to be displayed
            }
            case 4:
            case 5: {
                return new int[] {7, 8, 9}; // Not including 1 as n % 1 == 0 and that causes an
                                            // invalid warning to be displayed

            }
            default: {
                return null;
            }
        }
    }

    /**
     * Checks if path in a direction is clear
     * @param direction
     * @return True if the path is clear and false otherwise
     */
    private boolean isPathClear(int direction) {
        if (direction == -1) { // Happens when a pieces moves one field to the left or right,
                               // which is always valid since no piece can exist between only two
                               // fields
            return true;
        }
        if (to < from) { // Black moves in a negative direction
            for (int position = from - direction; position > to; position -= direction) {
                if (!board.getPiece(position).isEmpty()) {
                    return false;
                }
            }
        } else { // White moves in a positive direction
            for (int position = from + direction; position < to; position += direction) {
                if (!board.getPiece(position).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
