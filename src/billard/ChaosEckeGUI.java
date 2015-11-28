package billard;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 * Diese Klasse generiert die graphische Oberflaeche des ChaosEckeProgramms (Sektion Billard), liest die Werte ein und gibt die Loesungen aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class ChaosEckeGUI {
	
	private JFrame frame1 = new JFrame("ChaosEcke");
	private NumberFormat format1 = NumberFormat.getInstance(); 
	private NumberFormat format2 = NumberFormat.getInstance();
	private NumberFormat format3 = NumberFormat.getInstance();
	private NumberFormat format4 = NumberFormat.getInstance();
	private NumberFormat format5 = NumberFormat.getInstance();
	private NumberFormat format6 = NumberFormat.getInstance();
	private NumberFormatter formatter1 = new NumberFormatter(format1);
    private NumberFormatter formatter2 = new NumberFormatter(format2);
    private NumberFormatter formatter3 = new NumberFormatter(format3);
    private NumberFormatter formatter4 = new NumberFormatter(format4);
    private NumberFormatter formatter5 = new NumberFormatter(format5);
    private NumberFormatter formatter6 = new NumberFormatter(format6);
    private JLabel labelBreite = new JLabel("Breite");
	private JLabel labelHoehe = new JLabel("Höhe");
	private JLabel labelStartX = new JLabel("Start(x)");
	private JLabel labelStartY = new JLabel("Start(y)");
	private JLabel labelVectX = new JLabel("Richtung(x)");
	private JLabel labelVectY = new JLabel("Richtung(y)");
	private JFormattedTextField feldBreite = new JFormattedTextField(formatter1);
	private JFormattedTextField feldHoehe = new JFormattedTextField(formatter2);
	private JFormattedTextField feldStartX = new JFormattedTextField(formatter3);
	private JFormattedTextField feldStartY = new JFormattedTextField(formatter4);
	private JFormattedTextField feldVectX = new JFormattedTextField(formatter5);
	private JFormattedTextField feldVectY = new JFormattedTextField(formatter6);
	private JButton buttonRechnen = new JButton("Berechne");
	private int loesung;
	
	private ChaosEckeGUI() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(200,260));
		frame1.setMinimumSize(new Dimension(200,260));
		frame1.setMaximumSize(new Dimension(400,520));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridLayout(7,1));
		
		JPanel paneBreite = new JPanel();
		paneBreite.setLayout(new BorderLayout());
		paneBreite.add(labelBreite,BorderLayout.WEST);
		paneBreite.add(feldBreite,BorderLayout.CENTER);
		
		JPanel paneHoehe = new JPanel();
		paneHoehe.setLayout(new BorderLayout());
		paneHoehe.add(labelHoehe,BorderLayout.WEST);
		paneHoehe.add(feldHoehe,BorderLayout.CENTER);
		
		JPanel paneStartX = new JPanel();
		paneStartX.setLayout(new BorderLayout());
		paneStartX.add(labelStartX,BorderLayout.WEST);
		paneStartX.add(feldStartX,BorderLayout.CENTER);
		
		JPanel paneStartY = new JPanel();
		paneStartY.setLayout(new BorderLayout());
		paneStartY.add(labelStartY,BorderLayout.WEST);
		paneStartY.add(feldStartY,BorderLayout.CENTER);
		
		JPanel paneVectX = new JPanel();
		paneVectX.setLayout(new BorderLayout());
		paneVectX.add(labelVectX,BorderLayout.WEST);
		paneVectX.add(feldVectX,BorderLayout.CENTER);
		
		JPanel paneVectY = new JPanel();
		paneVectY.setLayout(new BorderLayout());
		paneVectY.add(labelVectY,BorderLayout.WEST);
		paneVectY.add(feldVectY,BorderLayout.CENTER);
		
		frame1.add(paneBreite);
		frame1.add(paneHoehe);
		frame1.add(paneStartX);
		frame1.add(paneStartY);
		frame1.add(paneVectX);
		frame1.add(paneVectY);
		frame1.add(buttonRechnen);
		
		buttonRechnen.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) {
	    		try {
	    			loesung = new ChaosEcke().berechne(Double.valueOf(feldBreite.getText()),Double.valueOf(feldHoehe.getText()), Double.valueOf(feldStartX.getText()), Double.valueOf(feldStartY.getText()), Double.valueOf(feldVectX.getText()), Double.valueOf(feldVectY.getText()));
	    			//14+28+6+4+2+3
		    		if(loesung==-1) {
		    			throw new NumberFormatException();
		    		} else if(loesung==-2) {
		    			JOptionPane.showMessageDialog(null, "Bei der Feldgröße "+Double.valueOf(feldBreite.getText())+"x"+Double.valueOf(feldHoehe.getText())+" gibt es keine Lösung unter 1.000.000 Berührungen."+System.getProperty("line.separator")+"Das Programm wurde abgebrochen.", "Abgebrochen", JOptionPane.WARNING_MESSAGE);
		    		} else if(loesung==-3) {
		    			JOptionPane.showMessageDialog(null, "Deine Eingabe ist ungültig."+System.getProperty("line.separator")+"Bitte achte darauf, dass der Startpunkt im Feld liegt.", "Eingabe ungültig", JOptionPane.ERROR_MESSAGE);
	    			} else {
		    			JOptionPane.showMessageDialog(null, "Bei der Feldgröße "+Double.valueOf(feldBreite.getText())+"x"+Double.valueOf(feldHoehe.getText())+" gibt es insgesamt "+loesung+" Berührungen.", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
		    		}
	    		} catch(Exception nfe) {
	    			JOptionPane.showMessageDialog(null, "Deine Eingabe ist ungültig."+System.getProperty("line.separator")+"Bitte benutze nur ganzzahlige positive Zahlen.", "Eingabe ungültig", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}	
	    });
		feldBreite.setHorizontalAlignment(SwingConstants.RIGHT);
		feldHoehe.setHorizontalAlignment(SwingConstants.RIGHT);
		feldStartX.setHorizontalAlignment(SwingConstants.RIGHT);
		feldStartY.setHorizontalAlignment(SwingConstants.RIGHT);
		feldVectX.setHorizontalAlignment(SwingConstants.RIGHT);
		feldVectY.setHorizontalAlignment(SwingConstants.RIGHT);
		format1.setGroupingUsed(false);
	    format2.setGroupingUsed(false);
	    format3.setGroupingUsed(false);
	    format4.setGroupingUsed(false);
	    format5.setGroupingUsed(false);
	    format6.setGroupingUsed(false);
	    formatter1.setAllowsInvalid(false);
	    formatter2.setAllowsInvalid(false);
	    formatter3.setAllowsInvalid(false);
	    formatter4.setAllowsInvalid(false);
	    formatter5.setAllowsInvalid(false);
	    formatter6.setAllowsInvalid(false);
	    
	    frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}

	public static void main(String[] args) {
		new ChaosEckeGUI();
	}
}