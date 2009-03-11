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

    /**
     * Eksempel på utskriving av et sjakkbrett
     * @param args
     */
    public static void main(String[] args) {

        boolean inverse = false;
        boolean evenNum;
        for (int i = 1; i <= 64; i++) {

            if (!inverse) { // Sjekker om vi har byttet om på rekkefølgen
                evenNum = (i % 2 == 0);
            } else {
                evenNum = !(i % 2 == 0);
            }

            if (evenNum) {
                System.out.print(i + ":white ");
            } else {
                System.out.print(i + ":black ");
            }

            if (i % 8 == 0) { // Står nå på den siste ruta i linja
                inverse = !inverse; // Bytter rekkefølge på hvit og svart
                System.out.println("");
            }

        }
    }
}
