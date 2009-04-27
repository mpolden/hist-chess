

package no.hist.aitel.chess.gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import no.hist.aitel.chess.board.Board;

/**
 *
 * @author Vegard
 */
public class saveAndLoad {

    public void saveIntArray(String filename, int[] input) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);           
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(input);
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
            ObjectInputStream in = new ObjectInputStream(fis);
            int[] read = (int[])in.readObject();
            in.close();
            return read;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public void saveGuiEngine(String filename, guiEngine save) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(save);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public guiEngine loadGuiEngine(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            guiEngine read = (guiEngine)in.readObject();
            in.close();
            return read;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public void saveBoard(String filename, Board save) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);            
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(save);
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
            ObjectInputStream in = new ObjectInputStream(fis);
            Board load = (Board)in.readObject();
            in.close();
            return load;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}
