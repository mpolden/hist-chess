/*
 * Test2.java
 * 
 */

package no.hist.aitel.java.chess;

/**
 *
 * @author martin
 */
public class Test2 {
    public static void main(String[] args) {

        boolean inverse = false;
        for (int i = 1; i <= 64; i++) {

            if (!inverse) {
                if (i % 2 == 0) {
                    System.out.print(i + ":white ");
                } else {
                    System.out.print(i + ":black ");
                }
            } else {
                if (i % 2 == 0) {
                    System.out.print(i + ":black ");
                } else {
                    System.out.print(i + ":white ");
                }
            }

            if (i % 8 == 0) {
                inverse = !inverse;
                System.out.println("");
            }

        }
    }
}
