/*
 * Test.java
 * 
 */

package no.hist.aitel.chess.board;

/**
 *
 * @author martin
 */

public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b  = new Board();

        // Putting myself in check
//        b.movePiece(11, 27);
//        b.movePiece(48, 40);
//        b.movePiece(3, 19);
//        b.movePiece(49, 41);
//        b.movePiece(14, 22);
//        b.movePiece(53, 45);
//        b.movePiece(19, 46);
//
//        System.out.println(b.getStateStr());
//        System.out.println(b.toString());

        // In check, but trying to move piece which won't me out of check
//        b.movePiece(11, 27);
//        b.movePiece(48, 40);
//        b.movePiece(3, 19);
//        b.movePiece(49, 41);
//        b.movePiece(14, 22);
//        b.movePiece(53, 45);
//        b.movePiece(19, 46);
//        b.movePiece(55, 46); // Not allowed
//        System.out.println(b.getStateStr());
//        System.out.println(b.toString());

        // Check mate in 4 moves
//        b.reset();
//        b.movePiece(13, 21);
//        b.movePiece(52, 36);
//        b.movePiece(14, 30);
//        b.movePiece(59, 31);
//
//        System.out.println(b.getStateStr());
//        System.out.println(b.toString());

        // Check mate in 6 moves
//        b.reset();
//        b.movePiece(12, 28);
//        b.movePiece(52, 36);
//        b.movePiece(3, 39);
//        b.movePiece(48, 40);
//        b.movePiece(5, 26);
//        b.movePiece(40, 32);
//        b.movePiece(39, 53);

//White Pawn from B2 to B4
// Black Pawn from C7 to C5
// White Pawn from E2 to E3
// Black Pawn from C5 to C4
// White Pawn from F2 to F3
// Black Pawn from C4 to C3
// White Pawn from D2 to D4
// Black Pawn from B7 to B5(Check)

        // Possible bug
        b.movePiece(9, 25);
        b.movePiece(50, 34);
        b.movePiece(12, 20);
        b.movePiece(34, 26);
        b.movePiece(13, 21);
        b.movePiece(26, 18);
        b.movePiece(11, 27);
        b.movePiece(49, 33);

        System.out.println(b.getStateStr());
        System.out.println(b.toString());
       

        

        // En passant
//        b.movePiece(10, 26);
//        b.movePiece(48, 40);
//        b.movePiece(26, 34);
//        b.movePiece(51, 35);
//        b.movePiece(34, 43);
//        b.movePiece(52, 45);

//        System.out.println(b.getStateStr());
//        System.out.println(b.toString());

        // 56  57  58  59  60  61  62  63
        // 48  49  50  51  52  53  54  55
        // 40  41  42  43  44  45  46  47
        // 32  33  34  35  36  37  38  39
        // 24  25  26  27  28  29  30  31
        // 16  17  18  19  20  21  22  23
        // 8   9   10  11  12  13  14  15
        // 0   1   2   3   4   5   6   7

    }


}

