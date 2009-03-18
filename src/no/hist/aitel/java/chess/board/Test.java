/*
 * Main.java
 * 
 */

package no.hist.aitel.java.chess.board;

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

        Piece[] p = b.getBoard();

        // 56  57  58  59  60  61  62  63
        // 48  49  50  51  52  53  54  55
        // 40  41  42  43  44  45  46  47
        // 32  33  34  35  36  37  38  39
        // 24  25  26  27  28  29  30  31
        // 16  17  18  19  20  21  22  23
        // 8   9   10  11  12  13  14  15
        // 0   1   2   3   4   5   6   7

        System.out.println(p[8].toString());

        p[8].setPosition(16);

        System.out.println(p[8]);
        System.out.println(p[16].toString());


    }

}
