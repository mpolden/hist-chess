/*
 * Piece.java
 * 
 */

package no.hist.aitel.java.chess.board;

/**
 *
 * @author martin
 */
public class Piece {

    private int position;
    final private int color;
    final private int type;

    public Piece(int position, int color, int type) {
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public int getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String out = "Position: " + position + "\nColor: " + getColorStr() + "\nType: "
                + getTypeStr();
        return out;
    }

    // Helper method for toString()
    private String getColorStr() {
        switch (color) {
            case 0: {
                return "White";
            }
            case 1: {
                return "Black";
            }
        }
        return null;
    }

    // Helper method for toString()
    private String getTypeStr() {
        switch (type) {
            case 1: {
                return "Pawn";
            }
            case 2: {
                return "Bishop";
            }
            case 3: {
                return "Knight";
            }
            case 4: {
                return "Rook";
            }
            case 5: {
                return "Queen";
            }
            case 6: {
                return "King";
            }
        }
        return null;
    }

}
