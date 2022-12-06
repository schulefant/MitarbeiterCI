package mitarbeiter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SchichtArbeiter extends Mitarbeiter {
	private static final double MINDESTLOHN = 12.0;

	private double stundenSatz;
	private int anzahlStunden = 0;

	public SchichtArbeiter(SchichtArbeiter original) {
		super(original);
		this.stundenSatz = original.getStundenSatz();
		this.anzahlStunden = original.getAnzahlStunden();
	}
	public SchichtArbeiter(int id, String name, double stundenSatz) throws IllegalArgumentException {

		/*
		 * Konstruktoraufruf super immer an erster Stelle
		 */
		super(Math.abs(id) % 1000 + 3000, name);
		setStundenSatz(stundenSatz);
	}

	public double getStundenSatz() {
		return stundenSatz;
	}
	public void setStundenSatz(double stundenSatz) throws IllegalArgumentException {
		if (stundenSatz >= MINDESTLOHN)
			this.stundenSatz = stundenSatz;
		else
			throw new IllegalArgumentException("Stundensatz zu gering. " + MINDESTLOHN + " erforderlich.");
	}

	public int getAnzahlStunden() {
		return anzahlStunden;
	}
	public void setAnzahlStunden(int anzahlStunden) {
		if (anzahlStunden > 0)
			this.anzahlStunden = anzahlStunden;
	}

	@Override
	public void arbeite(int std) {
		if (std > 0)
			this.setAnzahlStunden(this.anzahlStunden + std);
	}

	@Override
	protected void setID(int id) {
		super.setID(Math.abs(id) % 1000 + 3000);
	}

	public double einkommen() {
		return anzahlStunden * stundenSatz;
	}

	@Override
	public String toString() {
		return super.toString()
				+ (" Einkommen: " + einkommen() + " bei " + this.anzahlStunden + " gearbeiteten Stunden.");
	}

	@Override
	public String toCSVString() {
		String result = super.toCSVString();
		result += this.stundenSatz + ";";
		result += this.anzahlStunden + ";";
		return result;
	}
	@Override
	protected MitarbeiterTyp getType() {
		return MitarbeiterTyp.SCHICHT;
	}
}
