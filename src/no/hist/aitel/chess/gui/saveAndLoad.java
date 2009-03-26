

package no.hist.aitel.chess.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import no.hist.aitel.chess.board.Board;
import no.hist.aitel.chess.piece.Piece;

/**
 *
 * @author Vegard
 */
public class saveAndLoad {

    public void saveIntArray(String filename, int[] output_veld) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            //GZIPOutputStream gzos = new GZIPOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(output_veld);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public int[] loadIntArray(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            //GZIPInputStream gzis = new GZIPInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(fis);
            int[] gelezen_veld = (int[])in.readObject();
            in.close();
            return gelezen_veld;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public void saveBoard(String filename, Board output_veld) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            //GZIPOutputStream gzos = new GZIPOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(output_veld);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Board loadBoard(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            //GZIPInputStream gzis = new GZIPInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(fis);
            Board gelezen_veld = (Board)in.readObject();
            in.close();
            return gelezen_veld;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
