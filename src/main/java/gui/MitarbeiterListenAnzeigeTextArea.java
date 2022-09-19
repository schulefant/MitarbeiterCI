package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mitarbeiter.Mitarbeiter;
import verwaltung.MitarbeiterVerwaltung;

public class MitarbeiterListenAnzeigeTextArea {
	//Das Model:
	private MitarbeiterVerwaltung mv;

	private JFrame f = new JFrame("Personal- und Kostenliste");
	private JTextArea ta = new JTextArea();
	private JScrollPane output= new JScrollPane(ta);

	public MitarbeiterListenAnzeigeTextArea(MitarbeiterVerwaltung mv) {
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
			this.refreshOutput();
		});
		
		JPanel west = new JPanel();
		west.add(refresh);
		f.add(BorderLayout.WEST,west );
		this.refreshOutput();
		f.add(output);
		f.pack();
		f.setVisible(true);
	}
	private void refreshOutput() {
		ta.selectAll();
		ta.replaceSelection("");
//		String text = "";
//		text +="Gesamte Personalkosten: "+mv.personalkosten();
//		for (Mitarbeiter m : mv.getAlleMitarbeiter() ){
//			text += m.toString();
//		}
		ta.append("\nGESAMTE PERSONALKOSTEN: "+mv.personalkosten()+"\n");

		for (Mitarbeiter m : mv.getAlleMitarbeiter() ){
			ta.append(m.toString());
		}
		ta.repaint();
	}

}
