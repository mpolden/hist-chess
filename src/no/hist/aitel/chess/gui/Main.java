

package no.hist.aitel.chess.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Vegard
 */
public class Main implements ActionListener, ItemListener {
    private static guiEngine mainWindow = new guiEngine("Chess");
    private static JFrame frame = mainWindow; 
    

    private static void createAndShowGui() {
        
        Main gui = new Main();
        frame.setJMenuBar(gui.addJMenu());        
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }

    private JMenuBar addJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File"), submenu = new JMenu("Set player names"), submenu2 = new JMenu("Set resolution");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Test");
        
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_T);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menuItem = new JMenuItem("Save game");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menuItem = new JMenuItem("Load game");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menu.addSeparator();
        submenu2.setMnemonic(KeyEvent.VK_S);
        menuItem = new JMenuItem("Classic Chess");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
        submenu2.add(menuItem);

        menuItem = new JMenuItem("Star Wars Chess");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu2.add(menuItem);

        menu.add(submenu2);

        menu.addSeparator();
        submenu.setMnemonic(KeyEvent.VK_S);
        menuItem = new JMenuItem("Player 1");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Player 2");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menu.add(submenu);

        return menuBar;

    }

    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    private void saveArray(String filename, int[] output_veld) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            GZIPOutputStream gzos = new GZIPOutputStream(fos);
            ObjectOutputStream out = new ObjectOutputStream(gzos);
            out.writeObject(output_veld);
            out.flush();
            out.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private int[] loadArray(String filename) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            GZIPInputStream gzis = new GZIPInputStream(fis);
            ObjectInputStream in = new ObjectInputStream(gzis);
            int[] gelezen_veld = (int[])in.readObject();
            in.close();
            return gelezen_veld;
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }


    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                + "\n"
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")";
        System.out.println(s);
        if(e.getActionCommand().equals("Save game")) {
            saveArray("./src/no/hist/aitel/chess/resources/io.txt", mainWindow.getChessboard().getXcoords());
        }
        else if(e.getActionCommand().equals("Load game")) {
            try {
                mainWindow.setXcoords(loadArray("./src/no/hist/aitel/chess/resources/io.txt"));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand().equals("Player 1")) {
            String newP1name = JOptionPane.showInputDialog(null, "Player 1 name:");
            if(!newP1name.equals("")) {
                mainWindow.setP1name(newP1name);
            }
        }
        else if(e.getActionCommand().equals("Player 2")) {
            String newP2name = JOptionPane.showInputDialog(null, "Player 2 name:");
            if(!newP2name.equals("")) {
                mainWindow.setP2name(newP2name);
            }
        }
        else if(e.getActionCommand().equals("Classic Chess")) {
            
        }
        else if(e.getActionCommand().equals("Star Wars Chess")) {
            
        }
        else if(e.getActionCommand().equals("New game")) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game?\nUnsaved progress will be lost.")) {
                mainWindow.newgame();
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
