/*
 * Board.java
 * 
 */

package no.hist.aitel.java.chess.board;

/**
 *
 * @author martin
 */
public class Board {
    final private int size = 64;
    private Piece[] board = new Piece[size];
    private Position p = new Position();

    public Board() {
        fillBoard();
    }

    /**
     * Returns the current board
     * @return An Piece[] array which represents the current board
     */
    public Piece[] getBoard() {
        return board;
    }

    public Piece getPiece(int index) {
        return board[index];
    }

    public void movePiece(int index, int position) {
        p.setPiece(board[index]);
        p.setPosition(position);
    }

    // Fill board with pieces
    private void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = new Piece(i, getColor(i), getType(i));
        }
    }

    // Get color from (initial) position
    private int getColor(int pos) {
        if (pos <= 15) {
            return 0;
        } else {
            return 1;
        }
    }

    // Get piece type from (initial) position
    private int getType(int pos) {
        // Empty = 0
        // Pawn = 1
        // Bishop = 2
        // Knight = 3
        // Rook = 4
        // Queen = 5
        // King = 6

        switch (pos) {
            // 0   1   2   3   4   5   6   7
            // 56  57  58  59  60  61  62  63
            // = Rook (4) Knight (3) Bishop(2) Queen (5) King (6) Bishop(2) Knight(3) Rook(4)
            case 0:
            case 56: {
                return 4;
            }
            case 1:
            case 57: {
                return 3;
            }
            case 2:
            case 58: {
                return 2;
            }
            case 3:
            case 59: {
                return 5;
            }
            case 4:
            case 60: {
                return 6;
            }
            case 5:
            case 61: {
                return 2;
            }
            case 6:
            case 62: {
                return 3;
            }
            case 7:
            case 63: {
                return 4;
            }
            // 8   9   10  11  12  13  14  15
            // 48  49  50  51  52  53  54  55
            // = Pawn (1)
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55: {
                return 1;
            }
        }
        return 0; // Empty position
    }

    @Override
    public String toString() {
        String out = "";

        for (int i = board.length - 1; i >= 0; i--) {
            if ((i + 1) % 8 == 0) {
                out = out.trim() + "\n";
            }
            if (board[i] != null) {
                out += board[i].getType() + " ";
            } else {
                out += 0 + " ";
            }
        }

        return out;
    }
}
