/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.java.chess.board;

/**
 *
 * @author Martin
 */
public class Position {
    private Piece oldPiece;
    private Piece newPiece;

    public Position() {
    }

    /**
     * Sets the destination piece.
     * @param Destination piece object.
     */
    public void setNewPiece(Piece newPiece) {
        this.newPiece = newPiece;
    }

    /**
     * Sets the piece that should be moved.
     * @param Piece object that should be moved.
     */
    public void setOldPiece(Piece oldPiece) {
        this.oldPiece = oldPiece;
    }

    /**
     * Checks if a move is valid
     * @return True if the move is valid and false otherwise.
     */
    public boolean isValidMove() {
        // Get position of old and new position
        int oldPos = oldPiece.getPosition();
        int newPos = newPiece.getPosition();

        // Can't move empty piece
        if (oldPiece.isEmpty()) {
            throw new IllegalPositionException("Can't move empty piece.\n" +
                    "From: " + oldPos +
                    "\nTo: " + newPos);
        }

        // Can't capture piece of same color
        if (oldPiece.getColor() == newPiece.getColor()) {
            throw new IllegalPositionException("Can't capture piece of same type.\n" +
                    "From: " + oldPos +
                    "\nTo: " + newPos);
        }

        // Check if new position is outside board
        if (newPos < 0 || newPos > 63) {
            throw new IllegalPositionException("Can't move piece outside of board.\n" +
                    "From: " + oldPos +
                    "\nTo: " + newPos);
        }

        // Difference between new position and old position
        int diffPos = newPos - oldPos;

        // Type specific rules
        switch (oldPiece.getType()) {
            // Pawn.. garbage follows :|
            case 0: {
                if (newPiece.isEmpty()) { // New position is empty, pawn can then only move forward
                    if (oldPiece.getColor() == 0) { // White piece
                        if ((oldPos >= 8 && oldPos <= 15)) { // If the pawn is in its original position, it can move 1 or 2 fields forward
                            if (diffPos != 8 && diffPos != 16) {
                                throw new IllegalPositionException("Can only move one or two fields forward.\n" +
                                        "From: " + oldPos +
                                        "\nTo: " + newPos);
                            } else {
                                return true;
                            }
                        } else if (diffPos != 8) { // Pawn can always move 1 field forward
                            throw new IllegalPositionException("Can only move one field forward.\n" +
                                    "From: " + oldPos +
                                    "\nTo: " + newPos);
                        } else {
                            return true; // Valid move
                        }
                    } else if (oldPiece.getColor() == 1) { // Black piece
                        if (oldPos >= 48 && oldPos <= 55) { // Same as above
                            if (diffPos != -8 && diffPos != -16) {
                                throw new IllegalPositionException("Can only move one or two fields forward.\n" +
                                        "From: " + oldPos +
                                        "\nTo: " + newPos);
                            } else {
                                return true;
                            }
                        } else if (diffPos != -8) { // Same as above
                            throw new IllegalPositionException("Can only move one field forward.\n" +
                                    "From: " + oldPos +
                                    "\nTo: " + newPos);
                        } else {
                            return true; // Valid move
                        }
                    } else {
                        throw new IllegalPositionException("Can't move empty piece. First check failed? :(.\n" +
                                "From: " + oldPos +
                                "\nTo: " + newPos);
                    }
                } else {
                    if (oldPiece.getColor() == 0) {
                        if (diffPos != 9 && diffPos != 17) {
                            throw new IllegalPositionException("Can't move forward because 'to' position isn't empty.\n" +
                                    "From: " + oldPos +
                                    "\nTo: " + newPos);
                        } else {
                            return true; // Valid move
                        }
                    } else if (oldPiece.getColor() == 1) {
                        if (diffPos != -9 && diffPos != -17) {
                            throw new IllegalPositionException("Can't move forward because 'to' position isn't empty.\n" +
                                    "From: " + oldPos +
                                    "\nTo: " + newPos);
                        } else {
                            return true; // Valid move
                        }
                    } else {
                        throw new IllegalPositionException("Can't move empty piece. First check failed? :(.\n" +
                                "From: " + oldPos +
                                "\nTo: " + newPos);
                    }
                }
            }
            // Bishop
            case 1: {
                break;
            }
            // Knight
            case 2: {
                break;
            }
            // Rook
            case 3: {
                break;
            }
            // Queen
            case 4: {
                break;
            }
            // King
            case 5: {
                break;
            }
        }
        return false;
    }
}
