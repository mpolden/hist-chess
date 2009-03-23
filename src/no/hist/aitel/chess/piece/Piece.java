/*
 * Piece.java
 * 
 */

package no.hist.aitel.chess.piece;

import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Piece {

    final private int color;
    final private int type;
    final private int id;

    /**
     * Creates a new piece of the given color and type
     * @param color
     * @param type
     */
    public Piece(int color, int type, int id) {
        this.color = color;
        this.type = type;
        this.id = id;
    }

    /**
     * Creates an empty piece
     */
    public Piece() {
        this.color = UNDEFINED;
        this.type = UNDEFINED;
        this.id = -1;
    }

    /**
     * Get piece color
     * @return The piece color
     */
    public int getColor() {
        return color;
    }

    /**
     * Get piece type
     * @return The piece type
     */
    public int getType() {
        return type;
    }

    /**
     * Get piece id
     * @return The piece id
     */
    public int getId() {
        return id;
    }

    /**
     * Check if piece is empty
     * @return True if piece is empty and false otherwise
     */
    public boolean isEmpty() {
        if (type == UNDEFINED && color == UNDEFINED) {
            return true;
        }
        return false;
    }

    /**
     * Get a string representation of this piece
     * @return A string containing piece position, color and type
     */
    @Override
    public String toString() {
        String out = "Color: " + getColor() + " (" + getColorStr() +
                ")\nType: " + getType() + " (" + getTypeStr() + ")";
        return out;
    }

    /**
     * Get piece color
     * @return The piece color
     */
    private String getColorStr() {
        switch (color) {
            case UNDEFINED: {
                return "Undefined";
            }
            case WHITE: {
                return "White";
            }
            case BLACK: {
                return "Black";
            }
            default: {
                return null;
            }
        }
    }

    /**
     * Get piece type
     * @return The piece type
     */
    private String getTypeStr() {
        switch (type) {
            case UNDEFINED: {
                return "Undefined";
            }
            case PAWN: {
                return "Pawn";
            }
            case BISHOP: {
                return "Bishop";
            }
            case KNIGHT: {
                return "Knight";
            }
            case ROOK: {
                return "Rook";
            }
            case QUEEN: {
                return "Queen";
            }
            case KING: {
                return "King";
            }
            default: {
                return null;
            }
        }
    }

}
