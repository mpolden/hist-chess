

package no.hist.aitel.chess.gui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private saveAndLoad saveAndLoad = new saveAndLoad(); 
    private String[] savedString = new String[5];
    private String test = "Vegard";

    public static void createAndShowGui() {
        
        Main gui = new Main();
        frame.setJMenuBar(gui.addJMenu());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }

    private JMenuBar addJMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File"), submenu = new JMenu("Set player names"), submenu2 = new JMenu("Change pieces");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Test");
        
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New game", KeyEvent.VK_N);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menuItem = new JMenuItem("Save game", KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menuItem = new JMenuItem("Load game", KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menu.addSeparator();
        submenu2.setMnemonic(KeyEvent.VK_C);
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

        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Test");
        menu.add(menuItem);

        menu.add(menuItem);
        
        return menuBar;       
    }    

    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }   


    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Action event detected."
                + "\n"
                + "    Event source: " + source.getText()
                + " (an instance of " + getClassName(source) + ")";
        System.out.println(s);
        if(e.getActionCommand().equals("Save game")) {
             if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to save the game?\nPrevious save data will be lost.")) {
                 saveAndLoad.saveIntArray("./src/no/hist/aitel/chess/resources/x_coords.txt", mainWindow.getChessboard().getXcoords());
                 saveAndLoad.saveIntArray("./src/no/hist/aitel/chess/resources/y_coords.txt", mainWindow.getChessboard().getYcoords());
                 saveAndLoad.saveBoard("./src/no/hist/aitel/chess/resources/internal.txt", mainWindow.getBoardObj());                 
                 savedString[0] = Integer.toString(mainWindow.getTimeUsedP1());
                 savedString[1] = Integer.toString(mainWindow.getTimeUsedP2());
                 savedString[2] = mainWindow.getCenterTextArea();
                 savedString[3] = mainWindow.getP1name();
                 savedString[4] = mainWindow.getP2name();                 
                 saveAndLoad.saveStringArray("./src/no/hist/aitel/chess/resources/stringArray.txt", savedString);
             }
        }
        else if(e.getActionCommand().equals("Load game")) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to load game?\nUnsaved progress will be lost.")) {
                try {
                    mainWindow.setXcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/x_coords.txt"));
                    mainWindow.setYcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/y_coords.txt"));
                    mainWindow.setBoardObj(saveAndLoad.loadBoard("./src/no/hist/aitel/chess/resources/internal.txt"));
                    savedString = saveAndLoad.loadStringArray("./src/no/hist/aitel/chess/resources/stringArray.txt");
                    mainWindow.setTimeUsed(Integer.parseInt(savedString[0]), Integer.parseInt(savedString[1]));
                    mainWindow.setCenterTextArea(savedString[2]);
                    mainWindow.setP1name(savedString[3]);
                    mainWindow.setP2name(savedString[4]);
                    mainWindow.stopTimers();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            mainWindow.getChessboard().getStartPos().setClassicImages();
        }
        else if(e.getActionCommand().equals("Star Wars Chess")) {
            mainWindow.getChessboard().getStartPos().setSwImages();
        }
        else if(e.getActionCommand().equals("New game")) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to create a new game?\nUnsaved progress will be lost.")) {
                try {
                    mainWindow.setXcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/new_game_x_coords.txt"));
                    mainWindow.setYcoords(saveAndLoad.loadIntArray("./src/no/hist/aitel/chess/resources/new_game_y_coords.txt"));
                    mainWindow.setBoardObj(saveAndLoad.loadBoard("./src/no/hist/aitel/chess/resources/new_game_internal.txt"));                    
                    mainWindow.cleanup();
                   
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(e.getActionCommand().equals("Exit")) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?\nUnsaved progress will be lost.")) {
               System.exit(0);
            }
        }
    }
    public void itemStateChanged(ItemEvent e) {}
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}
