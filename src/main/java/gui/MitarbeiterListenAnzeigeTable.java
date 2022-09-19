package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import mitarbeiter.Mitarbeiter;
import verwaltung.MitarbeiterVerwaltung;

public class MitarbeiterListenAnzeigeTable {
	// Das Model:
	private MitarbeiterVerwaltung mv;

	private JFrame f = new JFrame("Personal- und Kostenliste");
	JPanel center;

	public MitarbeiterListenAnzeigeTable(MitarbeiterVerwaltung mv) {
		this.mv = mv;
		init();
	}

	private void init() {
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel tit = new JLabel("Liste aller Mitarbeiter", JLabel.CENTER);
		tit.setFont(new Font("Calibri", Font.BOLD, 24));
		tit.setForeground(Color.BLUE);
		f.add(BorderLayout.NORTH, tit);

		JButton refresh = new JButton("Neu Laden");
		refresh.addActionListener(e -> {
			center.removeAll();
			center =this.fillTableOutput();
			center.revalidate();
		});
		JPanel west = new JPanel();
		west.add(refresh);
		f.add(BorderLayout.WEST, west);
		center = fillTableOutput();
		f.add(center);
		f.validate();
		f.setSize(800,800);
		f.setVisible(true);
	}

	private JPanel fillTableOutput() {
		JPanel panel = new JPanel();
		GridLayout gl = new GridLayout(this.mv.getAlleMitarbeiter().size() + 2, 3);
		panel.setLayout(gl);

		JLabel pk = new JLabel(" ");
		pk.setBackground(Color.green);
		pk.setOpaque(true);
		panel.add(pk);
		pk = new JLabel("Personalkosten: ");
		pk.setBackground(Color.green);
		pk.setOpaque(true);
		panel.add(pk);
		pk = new JLabel("" + mv.personalkosten() + " Euro ");
		pk.setBackground(Color.green);
		pk.setOpaque(true);
		pk.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(pk);

		pk = new JLabel("ID");
		pk.setHorizontalAlignment(JLabel.CENTER);
		pk.setBackground(Color.BLACK);
		pk.setForeground(Color.white);
		pk.setOpaque(true);
		panel.add(pk);
		pk = new JLabel("Name");
		pk.setHorizontalAlignment(JLabel.CENTER);
		pk.setBackground(Color.BLACK);
		pk.setForeground(Color.white);
		pk.setOpaque(true);
		panel.add(pk);
		pk = new JLabel("Einkommen");
		pk.setHorizontalAlignment(JLabel.CENTER);
		pk.setBackground(Color.BLACK);
		pk.setForeground(Color.white);
		pk.setOpaque(true);
		panel.add(pk);

		for (Mitarbeiter m : mv.getAlleMitarbeiter()) {
			pk = new JLabel("" + m.getID());
			pk.setHorizontalAlignment(JLabel.CENTER);
			panel.add(pk);
			panel.add(new JLabel(m.getName()));
			pk = new JLabel("" + Math.round(m.einkommen() * 100) / 100.0 + " Euro ");
			pk.setHorizontalAlignment(JLabel.RIGHT);
			panel.add(pk);
		}
		return panel;
	}

}
