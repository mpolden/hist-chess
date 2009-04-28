

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
    /**
     * Serializes an int array and stores it to the given file
     * @param filename
     * @param input
     */    
    public void saveIntArray(String filename, int[] input) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);           
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(input);
            out.flush();
            out.close();
        }
        catch (IOException e) {            
        }
    }    
    /**
     * Load a serialized int array from file
     * @param filename
     * @return The int array
     * @throws java.lang.ClassNotFoundException
     */
    public int[] loadIntArray(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);            
            ObjectInputStream in = new ObjectInputStream(fis);
            int[] read = (int[])in.readObject();
            in.close();
            return read;
        }
        catch (IOException e) {            
        }
        return null;
    }
    /**
     * Serializes a String array and stores it to the given file
     * @param filename
     * @param save
     */    
    public void saveStringArray(String filename, String[] save) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(save);
            out.flush();
            out.close();
        }
        catch (IOException e) {            
        }
    }    
    /**
     * Load a serialized string array from file
     * @param filename
     * @return A string array
     * @throws java.lang.ClassNotFoundException
     */
    public String[] loadStringArray(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fis);
            String[] read = (String[])in.readObject();
            in.close();
            return read;
        }
        catch (IOException e) {            
        }
        return null;
    }
    /**
     * Serializes a given board object and stores it to the given file
     * @param filename
     * @param save
     */  
    public void saveBoard(String filename, Board save) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);            
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(save);
            out.flush();
            out.close();
        }
        catch (IOException e) {            
        }
    }
    /**
     * Loads a serialized board object
     * @param filename
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public Board loadBoard(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);            
            ObjectInputStream in = new ObjectInputStream(fis);
            Board load = (Board)in.readObject();
            in.close();
            return load;
        }
        catch (IOException e) {            
        }
        return null;
    }

}
