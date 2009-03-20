/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author Martin
 */
public class Position {
    private Piece from;
    private Piece to;

    /**
     * Creates a position object which is used to validate moves
     */
    public Position() {
    }

    /**
     * Sets the destination piece
     * @param to
     */
    public void setTo(Piece to) {
        this.to = to;
    }

    /**
     * Sets the piece that should be moved
     * @param from
     */
    public void setFrom(Piece from) {
        this.from = from;
    }

    /**
     * Checks if a move is valid
     * @return True if the move is valid and false otherwise
     */
    public boolean isValidMove() {
        // Get positions
        int fromPos = from.getPosition();
        int toPos = to.getPosition();

        // Can't move empty piece
        if (from.isEmpty()) {
            throw new IllegalPositionException("Can't move empty piece.\n" +
                    "Type: " + from.getType() +
                    "\nFrom: " + fromPos +
                    "\nTo: " + toPos);
        }

        // Can't capture piece of same color
        if (from.getColor() == to.getColor()) {
            throw new IllegalPositionException("Can't capture piece of same type.\n" +
                    "Type: " + from.getType() +
                    "\nFrom: " + fromPos +
                    "\nTo: " + toPos);
        }

        // Check if new position is outside board
        if (toPos < 0 || toPos > 63) {
            throw new IllegalPositionException("Can't move piece outside of board.\n" +
                    "Type: " + from.getType() +
                    "\nFrom: " + fromPos +
                    "\nTo: " + toPos);
        }

        // Difference between new position and old position
        int diffPos = toPos - fromPos;

        // Type specific rules
        switch (from.getType()) {
            // Pawn .. garbage follows :|
            case 0: {
                if (to.isEmpty()) { // New position is empty, pawn can then only move forward
                    if (from.getColor() == 0) { // White piece
                        if ((fromPos >= 8 && fromPos <= 15)) { // If the pawn is in its original position, it can move 1 or 2 fields forward
                            if (diffPos != 8 && diffPos != 16) {
                                throw new IllegalPositionException("Can only move one or two fields forward.\n" +
                                        "Type: " + from.getType() +
                                        "\nFrom: " + fromPos +
                                        "\nTo: " + toPos);
                            }
                        } else if (diffPos != 8) { // Pawn can always move 1 field forward
                            throw new IllegalPositionException("Can only move one field forward.\n" +
                                    "Type: " + from.getType() +
                                    "\nFrom: " + fromPos +
                                    "\nTo: " + toPos);
                        }
                    } else if (from.getColor() == 1) { // Black piece
                        if (fromPos >= 48 && fromPos <= 55) { // Same as above
                            if (diffPos != -8 && diffPos != -16) {
                                throw new IllegalPositionException("Can only move one or two fields forward.\n" +
                                        "Type: " + from.getType() +
                                        "\nFrom: " + fromPos +
                                        "\nTo: " + toPos);
                            }
                        } else if (diffPos != -8) {
                            throw new IllegalPositionException("Can only move one field forward.\n" +
                                    "Type: " + from.getType() +
                                    "\nFrom: " + fromPos +
                                    "\nTo: " + toPos);
                        }
                    }
                } else {
                    if (from.getColor() == 0 && diffPos != 9 && diffPos != 7) {
                        throw new IllegalPositionException("Can't move forward because 'to' position isn't empty.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    } else if (from.getColor() == 1 && diffPos != -9 && diffPos != -7) {
                        throw new IllegalPositionException("Can't move forward because 'to' position isn't empty.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                }
                return true;
            }
            // Bishop
            case 1: {
                if (diffPos % 7 != 0 && diffPos % 9 != 0) {
                    throw new IllegalPositionException("Can only move diagonally.\n" +
                            "Type: " + from.getType() +
                            "\nFrom: " + fromPos +
                            "\nTo: " + toPos);
                }
                return true;
            }
            // Knight
            case 2: {
                switch (diffPos) {
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
                        throw new IllegalPositionException("Can only move one field diagonally + one forward.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                }
            }
            // Rook
            case 3: {
                if (diffPos % 7 == 0 || diffPos % 9 == 0) {
                    throw new IllegalPositionException("Can't move diagonally.\n" +
                            "Type: " + from.getType() +
                            "\nFrom: " + fromPos +
                            "\nTo: " + toPos);
                }
                return true;
            }
            // Queen
            case 4: {
                switch (diffPos) {
                    case -10:
                    case -17:
                    case -15:
                    case -6:
                    case 10:
                    case 17:
                    case 15:
                    case 6: {
                        throw new IllegalPositionException("Can't move one field diagonally + one forward.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                    default: {
                        return true;
                    }
                }
            }
            // King
            case 5: {
                switch (diffPos) {
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
                        throw new IllegalPositionException("Can only move one field in any direction.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                }
            }
            default: {
                throw new IllegalPositionException("Move not implemented.");
            }
        }
    }
}
