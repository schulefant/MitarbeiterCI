package verwaltung;

import java.util.ArrayList;

import mitarbeiter.Manager;
import mitarbeiter.Mitarbeiter;

public class Abteilung implements Abteilungsteil {
	private String name;
	private ArrayList<Mitarbeiter> mitarbeiter = new ArrayList<>();
	private ArrayList<Abteilungsteil> abtTeile = new ArrayList<>();
	private Manager leiter;

	public Abteilung(String name, Manager leiter) {
		super();
		this.setName(name);
		this.changeLeiter(leiter); // Der Rueckgabewert muss hier nicht verwendet werden, weil leiter noch nie
									// gesetzt wurde
	}

	public void add(Mitarbeiter neuer) {
		if (!mitarbeiter.contains(neuer)) {
			mitarbeiter.add(neuer);
			abtTeile.add(neuer);
		}
	}

	public void add(Abteilung neue) {
		if (!abtTeile.contains(neue)) {
			abtTeile.add(neue);
		}
	}

	public void remove(Mitarbeiter welcher) {
		// wenn welcher nicht existiert passiert nichts - keine Exception
		mitarbeiter.remove(welcher);
	}

	public void remove(Abteilung welche) {
		// wenn welcher nicht existiert passiert nichts - keine Exception
		abtTeile.remove(welche);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Manager getLeiter() {
		return leiter;
	}

	public Manager changeLeiter(Manager leiter) {
		Manager alter = this.leiter;
		if (leiter != null) {
			if (mitarbeiter.contains(leiter))
				mitarbeiter.remove(leiter);
			this.leiter = leiter;
		}
		return alter;
	}

	public String gehaltsListe() {
		String liste = "Abteilung " + this.name + " geleitet von " + this.leiter.getName() + "\n";
		liste += this.leiter.getName() + ": " + leiter.einkommen() + "\n";

		for (Mitarbeiter m : mitarbeiter) {
			liste += m.getName();
			liste += ": ";
			liste += m.einkommen();
			liste += "\n";
		}
		double summe = personalkosten();
		liste += "Lohnkosten der Abteilung ohne Unterabteilungen: " + summe;
		liste += "\nDurchschnittsgehalt: " + summe / (mitarbeiter.size() + 1);
		return liste;
	}

	public double personalkosten() {
		double summe = leiter.einkommen();
		for (Mitarbeiter m : mitarbeiter) {
			summe += m.einkommen();
		}
		return summe;
	}

	@Override
	public int anzahlMitarbeiterInUndUnter() {
		int anzahl = 0;
		for (Abteilungsteil at : abtTeile)
			anzahl += at.anzahlMitarbeiterInUndUnter();
		return anzahl;
	}

	@Override
	public String toString() {
		String abtString = "";
		for (Abteilungsteil at : abtTeile)
			abtString += at.toString();
		return abtString;
	}

	@Override
	public String toCSVString() {
		return this.name + ";";
	}

}
