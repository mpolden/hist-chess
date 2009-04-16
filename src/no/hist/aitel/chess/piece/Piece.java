/*
 * Piece.java
 * 
 */

package no.hist.aitel.chess.piece;

import java.io.Serializable;
import static no.hist.aitel.chess.piece.PieceConstants.*;

/**
 *
 * @author martin
 */

public class Piece implements Serializable {

    final private int color;
    final private int id;
    private int type;
    private boolean moved = false;

    /**
     * Check if piece has been moved
     * @return True if piece has been moved from its original position and false otherwise
     */
    public boolean isMoved() {
        return moved;
    }

    /**
     * Set if piece has been moved
     * @param moved
     */
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    /**
     * Creates a new piece of the given color, type and id
     * @param color
     * @param type
     * @param id
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
        this.id = UNDEFINED;
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
     * Set piece type
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Check if piece is empty
     * @return True if piece is empty and false otherwise
     */
    public boolean isEmpty() {
        return (type == UNDEFINED && color == UNDEFINED && id == UNDEFINED);
    }

    /**
     * Get a string representation of this piece
     * @return A string containing piece position, color and type
     */
    @Override
    public String toString() {
        String out = "Color: " + getColor() + " (" + getColorStr() +
                ")\nType: " + getType() + " (" + getTypeStr() + ")\nID: " + getId();
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
    public String getTypeStr() {
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
