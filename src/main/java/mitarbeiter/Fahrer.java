package mitarbeiter;

public class Fahrer extends SchichtArbeiter {
	private String fuehrerSchein;
	private static String[] gueltigeKlassen = { "B", "C", "D" };

	public Fahrer(int id, String name, double satz, String fuehrerSchein) throws IllegalArgumentException {
		super(id, name, satz);
		this.setFuehrerSchein(fuehrerSchein);
	}

	public String getFuehrerSchein() {
		return fuehrerSchein;
	}
	public void setFuehrerSchein(String fuehrerSchein) {
		boolean gueltig = false;
		for (int i = 0; i < gueltigeKlassen.length; i++) {
			if (gueltigeKlassen[i].equals(fuehrerSchein)) {
				this.fuehrerSchein = fuehrerSchein;
				gueltig = true;
			}
		}
		if (!gueltig)
			throw new IllegalArgumentException(fuehrerSchein + "-Klasse nicht gueltig.");
	}
	@Override
	public String toString() {
		return super.toString() + " hat Fuehrerscheinklasse " + this.fuehrerSchein;
	}

	@Override
	public String toCSVString() {
		String result = super.toCSVString();
		result += this.fuehrerSchein + ";";
		return result;
	}
	@Override
	protected MitarbeiterTyp getType() {
		return MitarbeiterTyp.FAHRER;
	}
}
