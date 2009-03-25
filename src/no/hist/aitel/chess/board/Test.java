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

//        b.movePiece(8, 24);
//        b.movePiece(55, 47);
//        b.movePiece(24, 32);
//        b.movePiece(49, 33);
//        b.movePiece(32, 41);
//        b.movePiece(49, 41);
//        b.movePiece(6, 12);
//        b.movePiece(51, 43);
//        b.movePiece(5, 23);
//        b.movePiece(52, 44);
//        b.movePiece(3, 34);
//        b.movePiece(4, 6);
//        b.movePiece(59, 16);
//        b.movePiece(58, 25);
        b.movePiece(8, 16);
        b.movePiece(52, 44);
        b.movePiece(9, 17);
        b.movePiece(61, 52);
        b.movePiece(10, 18);
        b.movePiece(62, 47);
        b.movePiece(11, 19);
        b.movePiece(60, 62);


        System.out.println(b.toString());


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

