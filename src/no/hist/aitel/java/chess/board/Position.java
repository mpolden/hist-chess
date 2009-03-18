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
    private Piece piece;
    private int position;

    public Position() {
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isValidMove() {
        switch (piece.getType()) {
            case 1: {
                if (piece.getPosition() - position == 8) {
//                    System.out.println("Moving piece from " + oldPosition + " to "
//                            + newPosition + ":\n");
//                    System.out.println(board[oldPosition].toString());
//                    if (board[newPosition] != null) {
//                        System.out.println("-- Piece was captured in " + newPosition + ":\n");
//                        System.out.println(board[newPosition].toString());
//                        System.out.println("");
//                    }
//                    board[newPosition] = board[oldPosition];
//                    board[oldPosition] = null;
                    return true;
                } else {
                    throw new IllegalPositionException("Invalid move from " + piece.getPosition() +
                            " to " + position);
                }
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
            case 6: {
                break;
            }
        }
        return false;
    }
}
