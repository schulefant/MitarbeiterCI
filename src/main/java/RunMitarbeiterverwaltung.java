import gui.MitarbeiterAnlageGUI;
import gui.MitarbeiterListenAnzeigeTable;
import gui.MitarbeiterListenAnzeigeTextArea;
import verwaltung.MitarbeiterVerwaltung;

public class RunMitarbeiterverwaltung {

	public static void main(String[] args) {
		MitarbeiterVerwaltung mv = new MitarbeiterVerwaltung();
		mv.loadMitarbeiter();
		MitarbeiterAnlageGUI gui = new MitarbeiterAnlageGUI(mv);
		MitarbeiterListenAnzeigeTextArea la = new MitarbeiterListenAnzeigeTextArea(mv);
		//Die Anzeige als Tabelle aktualisiert sich nicht richtig:-(
//		MitarbeiterListenAnzeigeTable lat = new MitarbeiterListenAnzeigeTable(mv); 
	}

}
