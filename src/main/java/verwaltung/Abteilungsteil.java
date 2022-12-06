package verwaltung;

public interface Abteilungsteil {
	public String toString();

	public default int anzahlMitarbeiterInUndUnter() {
		return 1;
	}

	public String toCSVString();

}
