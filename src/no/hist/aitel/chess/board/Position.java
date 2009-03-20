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
    private Board board;
    private int fromPos, toPos;

    /**
     * Creates a position object which is used to validate moves
     */
    public Position(Board board) {
        this.board = board;
    }

    /**
     * Sets the destination piece
     * @param to
     */
    public void setTo(int to) {
        this.toPos = to;
    }

    /**
     * Sets the piece that should be moved
     * @param from
     */
    public void setFrom(int from) {
        this.fromPos = from;
    }

    /**
     * Checks if a move is valid
     * @return True if the move is valid and false otherwise
     */
    public boolean isValidMove() {
        // Pieces
        Piece from = board.getPiece(this.fromPos);
        Piece to = board.getPiece(this.toPos);

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
        
        // Difference between new position and old position
        int diffPos = toPos - fromPos;

        // Piece type
        int type = from.getType();

        // Direction
        int direction = getDirection(type, diffPos);

        // Check if path is clear
        if (type != 2 && !isClearPath(direction)) {
            throw new IllegalPositionException("A piece is blocking my path.\n" +
                    "Type: " + from.getType() +
                    "\nFrom: " + fromPos +
                    "\nTo: " + toPos);
        }

        // Type specific rules
        switch (type) {
            // Pawn .. garbage follows :|
            case 0: {
                if (to.isEmpty()) { // New position is empty, pawn can then only move forward
                    if (from.getColor() == 0) { // White piece
                        if ((fromPos >= 8 && fromPos <= 15)) { // If the pawn is in its original position, it can move 1 or 2 fields forward
                            if (diffPos != 8 && diffPos != 16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + from.getType() +
                                        "\nFrom: " + fromPos +
                                        "\nTo: " + toPos);
                            }
                        } else if (diffPos != 8) { // Pawn can always move 1 field forward
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + from.getType() +
                                    "\nFrom: " + fromPos +
                                    "\nTo: " + toPos);
                        }
                    } else if (from.getColor() == 1) { // Black piece
                        if (fromPos >= 48 && fromPos <= 55) { // Same as above
                            if (diffPos != -8 && diffPos != -16) {
                                throw new IllegalPositionException("Pawn can only move one or two fields forward when in initial position.\n" +
                                        "Type: " + from.getType() +
                                        "\nFrom: " + fromPos +
                                        "\nTo: " + toPos);
                            }
                        } else if (diffPos != -8) {
                            throw new IllegalPositionException("Pawn can only move one field forward when not in initial position.\n" +
                                    "Type: " + from.getType() +
                                    "\nFrom: " + fromPos +
                                    "\nTo: " + toPos);
                        }
                    }
                } else {
                    if (from.getColor() == 0 && diffPos != 9 && diffPos != 7) {
                        throw new IllegalPositionException("Pawn can't move forward because 'to' position isn't empty.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    } else if (from.getColor() == 1 && diffPos != -9 && diffPos != -7) {
                        throw new IllegalPositionException("Pawn can't move forward because 'to' position isn't empty.\n" +
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
                    throw new IllegalPositionException("Bishop can only move diagonally.\n" +
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
                        throw new IllegalPositionException("Knight can only move one field diagonally + one forward.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                }
            }
            // Rook
            case 3: {
                if (diffPos % 7 == 0 || diffPos % 9 == 0) {
                    throw new IllegalPositionException("Rook can't move diagonally.\n" +
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
                        throw new IllegalPositionException("Queen can't move one field diagonally + one forward.\n" +
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
                        throw new IllegalPositionException("King can only move one field in any direction.\n" +
                                "Type: " + from.getType() +
                                "\nFrom: " + fromPos +
                                "\nTo: " + toPos);
                    }
                }
            }
            default: {
                throw new IllegalPositionException("Method was called with invalid positions (should never happen)");
            }
        }
    }

    /**
     * Get the current direction
     * @param type
     * @param diffPos
     * @return
     */
    private int getDirection(int type, int diffPos) {

        int[] directions = getDirections(type);
        switch (type) {
            case 0: // Pawn
            case 1: // Bishop
            case 3: // Rook
            case 4: // Queen
            case 5: // King
                for (int direction : directions) {
                    if (diffPos % direction == 0) {
                        return direction;
                    }
                }
                return -1;
            default: {
                    return -1;
            }
        }
    }

    /**
     * Get possible directions for a type
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
                return new int[] {8, 1};
            }
            case 4:
            case 5: {
                return new int[] {7, 8, 9, 1};
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
    private boolean isClearPath(int direction) {
        if (direction == -1) {
            return true;
        }
        if (toPos < fromPos) { // Black moves in a negative direction
            for (int i = fromPos - direction; i > toPos; i -= direction) {
                if (!board.getPiece(i).isEmpty()) {
                    return false;
                }
            }
        } else { // White moves in a positive direction
            for (int i = fromPos + direction; i < toPos; i += direction) {
                if (!board.getPiece(i).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
}
