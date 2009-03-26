/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.hist.aitel.chess.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Neerow
 */
public class MessageBox {
	 public static void main(String[]args){
		 TekstVindu etVindu = new TekstVindu("Chess Team 9");
		 etVindu.setVisible(true);
	 }

}

 class TekstVindu extends JFrame{
	 private static final String standardtekst =
	 "> Velkommen til Chess av Team 9"
	 + "\n> Spillernavn1(Hvit) Spillenavn2(Svart)"
	 + "\n> MESSAGE"
	 + "\n> HVIT VANT"
	 + "\n> TEST2"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST"
	 + "\n> TEST";
	// private JTextField brukernavnFelt = new JTextField(15);
	// private JPasswordField passordFelt = new JPasswordField(15);
	 private JTextArea tekstfelt = new JTextArea(15,30);
	// private JButton skriveknapp = new JButton("Skriv");
	// private JLabel meldingsfelt = new JLabel("Her kommer meldinger.....");

	 public TekstVindu(String tittel){
		 setTitle(tittel);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(new BorderLayout(5,5));
	//	 add(new InnloggingsPanel(), BorderLayout.NORTH);
		 add(new TekstomraadePanel(), BorderLayout.CENTER);
		 pack();
	 }

	 /* Nordre panel */
	 private class InnloggingsPanel extends JPanel{
		 public InnloggingsPanel(){
			 setLayout(new GridLayout(2,2,5,5));
			 add(new JLabel("Brukernavn:", JLabel.RIGHT));
	//		 add(brukernavnFelt);
	//		 brukernavnFelt.setInputVerifier(new BrukernavnKontroll());

			 add(new JLabel("Passord:", JLabel.RIGHT));
	//		 add(passordFelt);
	//		 passordFelt.setInputVerifier(new PassordKontroll());
	//		 passordFelt.addFocusListener(new Fokuslytter());
		 }
	 }

	 /* Mitre panel */
	 private class TekstomraadePanel extends JPanel{
		 public TekstomraadePanel(){
			 tekstfelt.setAutoscrolls(true); //scroller automatisk
		//	 tekstfelt.setBorder(BorderFactory.createLineBorder(Color.black)); // lager svart strek rundt boksen
			 tekstfelt.setLineWrap(true);
			 tekstfelt.setWrapStyleWord(true);
			 tekstfelt.setEditable(false);
			 tekstfelt.setText(standardtekst);
			 JScrollPane rullefelt = new JScrollPane(tekstfelt);
			 add(rullefelt);
		 }
	 }


	 private class Fokuslytter implements FocusListener{
		 public void focusLost(FocusEvent hendelse){
	//		 skriveknapp.setEnabled(true);
			 tekstfelt.setEditable(true);
			 tekstfelt.requestFocus();
		 }
		 public void focusGained(FocusEvent hendelse){
		 }
	 }

	 /* Beskriver objekter som lytter etter knappetrykk */
	 private class KnappeLytter implements ActionListener{
		 public void actionPerformed(ActionEvent hendelse){
			 String tekst = tekstfelt.getText();
			 System.out.println("Teksten ser slik ut: ");
			 System.out.println(tekst);
		 }
	 }
 }
