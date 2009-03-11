

package no.hist.aitel.java.chess;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Insets;

/**
 *
 * @author Vegard
 */
public class test4 extends Applet {
    board board;
    Button newgame = new Button ("start new game");

    public boolean action (Event evt, Object arg) {
        if ( ((String) arg).equals ("start new game"))
            board.newgame ();
        return true;
    }

    public void init() {
        super.init();

        board = new board(this);


        setBackground (Color.lightGray);
        setLayout (new BorderLayout (10,10));

        add ("Center", board);
        add ("South", newgame);

    }

    public Insets insets () {
        return new Insets (10,10,10,10);
    }
}
