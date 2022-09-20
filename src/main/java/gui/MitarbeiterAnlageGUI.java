package gui;

import mitarbeiter.*;
import verwaltung.*;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MitarbeiterAnlageGUI {
	private MitarbeiterVerwaltung mv;

	private JFrame f = new JFrame("Mitarbeiter Einstellen");
	private String[] attributsNamen = { "Name", "Gehalt", "Stundenlohn", "Fuehrerscheinklasse", "Bonusssatz" };
	private ArrayList<JTextField> entries = new ArrayList<>();
	private ArrayList<JLabel> labels = new ArrayList<>();
	private JComboBox<MitarbeiterTyp> typAuswahl = new JComboBox<>(MitarbeiterTyp.values());

	public MitarbeiterAnlageGUI(MitarbeiterVerwaltung mv) {
		this.mv = mv;
		init();
	}

	private void init() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel tit = new JLabel("Mitarbeiter Anlegen", JLabel.CENTER);
		tit.setFont(new Font("Calibri", Font.BOLD, 24));
		tit.setForeground(Color.RED);
		f.add(BorderLayout.NORTH, tit);

		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(8, 1));

		menu.add(typAuswahl);
		typAuswahl.addItemListener(e -> {
			showSelectedFields();
		});
		menu.add(new JLabel());// provides padding - quick an dirty :-(
/**
 * Speichern legt den Mitarbeiter in der Verwaltung an
 */
		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(e -> {
			MitarbeiterTyp typ = (MitarbeiterTyp) this.typAuswahl.getSelectedItem();
			ArrayList<String> attributes = new ArrayList<>();
			attributes.add(typ.toString());

			for (JTextField tf : entries) {
				if (tf.isVisible())
					attributes.add(tf.getText());
			}
			mv.addMitarbeiter(attributes);
		});
		menu.add(speichern);
		JButton reset = new JButton("Reset");
		reset.addActionListener((event)->{
			for(JTextField tf : entries)
				tf.setText("");
		});
		f.add(BorderLayout.WEST, menu);
		menu.add(reset);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		f.add(center);

		for (String s : attributsNamen) {
			JLabel l = new JLabel(s + ": ", SwingConstants.RIGHT);
			labels.add(l);
			JTextField tf = new JTextField();
			entries.add(tf);
			tf.setPreferredSize(new Dimension(300, 30));
			JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			p.add(l);
			p.add(tf);
			center.add(p);
		}
		f.pack();
		showSelectedFields();
		f.setVisible(true);
	}

	public void showSelectedFields() {
		MitarbeiterTyp typ = (MitarbeiterTyp) this.typAuswahl.getSelectedItem();
		switch (typ) {
		case BUERO:
			System.out.println("Buerohengst selected");
			activate(new boolean[] { true, true, false, false, false });
			break;
		case SCHICHT:
			System.out.println("Schichtarbeiter selected");
			activate(new boolean[] { true, false, true, false, false });
			break;
		case MANAGER:
			System.out.println("Manager selected");
			activate(new boolean[] { true, true, false, false, true });
			break;
		case FAHRER:
			System.out.println("Fahrer selected");
			activate(new boolean[] { true, false, true, true, false });
			break;
		}
	}

	private void activate(boolean[] attributesVisible) {
		for (int i = 0; i < attributsNamen.length; i++) {
			labels.get(i).setVisible(attributesVisible[i]);
			entries.get(i).setVisible(attributesVisible[i]);
		}
	}

}
