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
        int oldPosition = oldPiece.getPosition();
        int newPosition = newPiece.getPosition();

        // Can't capture piece of same color
        if (oldPiece.getColor() == newPiece.getColor()) {
            throw new IllegalPositionException("Invalid move from " + oldPosition +
                    " to " + newPosition);
        }

        // Make it a bit easier to write rules which apply to both colors
        int diffPosition;
        if (oldPiece.getColor() == 0) {
            diffPosition = newPosition - oldPosition;
        } else {
            diffPosition = oldPosition - newPosition;
        }


        // Type specific rules
        switch (oldPiece.getType()) {
            case 0: {
                if (newPiece.isEmpty()) {
                    if ((oldPosition >= 8 && oldPosition <= 15) || (oldPosition >= 48 && oldPosition <= 55)) {
                        if (diffPosition == 8 || diffPosition == 16) {
                            return true;
                        }
                    } else if (diffPosition == 8) {
                        return true;
                    }
                } else if (diffPosition == 7 || diffPosition == 9) {
                    return true;
                } else {
                    throw new IllegalPositionException("Invalid move from " + oldPosition +
                            " to " + newPosition);
                }
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
        }
        return false;
    }
}
