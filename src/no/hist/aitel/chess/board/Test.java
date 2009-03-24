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

//        b.movePiece(13, 21);
//        b.movePiece(53, 45);
//        b.movePiece(14, 30);
//        b.movePiece(48, 40);
//        b.movePiece(12, 20);
//        b.movePiece(49, 41);
//        b.movePiece(6, 12);
//        b.movePiece(51, 43);
//        b.movePiece(5, 23);
//        b.movePiece(52, 44);
//        b.movePiece(3, 34);
//        b.movePiece(4, 6);
//        b.movePiece(59, 16);
//        b.movePiece(58, 25);

//        System.out.println(b.toString());
        for (int i = 0; i < b.getBoard().length; i++) {
            System.out.println(b.getPiece(i).toString());
            System.out.println("");
        }

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

