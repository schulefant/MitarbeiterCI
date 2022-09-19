package verwaltung;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import mitarbeiter.*;

public class MitarbeiterVerwaltung {

	private static int idCounter = 0;

	public static int getIdCounter() {
		return idCounter;
	}

	private Path fileToSave = Paths.get("mitarbeiter.csv");
	private ArrayList<Mitarbeiter> alleMitarbeiter = new ArrayList<>();
	private ArrayList<SchichtArbeiter> malocher = new ArrayList<>();
	private ArrayList<Fahrer> fahrer = new ArrayList<>();
	private ArrayList<BueroArbeiter> buerohengste = new ArrayList<>();
	private ArrayList<Manager> chefs = new ArrayList<>();

	public void saveAll() throws IOException {

		try (BufferedWriter bw = Files.newBufferedWriter(fileToSave, StandardOpenOption.CREATE)) {
			for (Mitarbeiter m : alleMitarbeiter) {
				bw.write(m.toCSVString());
				bw.write("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addToCSV(Mitarbeiter m) throws IOException {

		if (Files.notExists(fileToSave)) {
			Files.createFile(fileToSave);
		}

		try (BufferedWriter bw = Files.newBufferedWriter(fileToSave, StandardOpenOption.APPEND)) {
			bw.write(m.toCSVString());
			bw.write("\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadMitarbeiter() {

		try {
			List<String> mitarbeiterRecords = Files.readAllLines(fileToSave);
			for (String s : mitarbeiterRecords) {
				createMitarbeiter(s.split(";"));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addMitarbeiter(ArrayList<String> attributeValues) {
		attributeValues.add(1, "" + (++idCounter));
		Mitarbeiter m = this.createMitarbeiter(attributeValues
				.toArray(new String[0])); /*
											 * Der Typ des Arrays wird mit new String[0] mitgeteilt. Verständlicher,
											 * aber nicht notwendig, wäre: new String[attributeValues.size()]
											 */
		try {
			this.addToCSV(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Mitarbeiter createMitarbeiter(String[] attributeValues) {
		MitarbeiterTyp typ = MitarbeiterTyp.valueOf(attributeValues[0]);
		int id = Integer.parseInt(attributeValues[1]);
		String name = attributeValues[2];
		Mitarbeiter m = null;
		idCounter++;

		switch (typ) {
		case BUERO:
			double gehalt = Double.parseDouble(attributeValues[3]);
			BueroArbeiter b = new BueroArbeiter(id, name, gehalt);
			m = b;
			buerohengste.add(b);
			break;
		case SCHICHT:
			double stundenlohn = Double.parseDouble(attributeValues[3]);
			SchichtArbeiter sa = new SchichtArbeiter(id, name, stundenlohn);
			if (attributeValues.length == 5) {
				int std = Integer.parseInt(attributeValues[4]);
				sa.setAnzahlStunden(std);
			} else
				sa.arbeite((int) Math.random() * 40); //hier als Platzhalter
			m = sa;
			malocher.add(sa);
			break;
		case MANAGER:
			gehalt = Double.parseDouble(attributeValues[3]);
			double bonus = Double.parseDouble(attributeValues[4]);
			Manager ma = new Manager(id, name, gehalt, bonus);
			m = ma;
			chefs.add(ma);
			break;
		case FAHRER:
			stundenlohn = Double.parseDouble(attributeValues[3]);
			Fahrer f;
			if (attributeValues.length == 6) {
				f = new Fahrer(id, name, stundenlohn, attributeValues[5]);
				int std = Integer.parseInt(attributeValues[4]);
				f.setAnzahlStunden(std);
			} else
				f = new Fahrer(id, name, stundenlohn, attributeValues[4]);
				
			m = f;
			fahrer.add(f);
			break;
		}
		this.alleMitarbeiter.add(m);
		return m;
	}

	public double personalkosten() {
		double lohnsumme = 0;
		for (Mitarbeiter m : alleMitarbeiter) {
			lohnsumme += m.einkommen();
		}
		return lohnsumme;
	}

	public void testBasics() throws IOException {

		try {

			System.out.println("Test Methode toString()");
			for (int i = 0; i < alleMitarbeiter.size(); i++) {
				System.out.print(alleMitarbeiter.get(i).toString());
			}
			System.out.println("\n\nTest Methode einkommen()");
			for (Mitarbeiter m : alleMitarbeiter) {
				System.out.print(m.getName() + " vom Typ \"");
				System.out.print(m.getClass() + "\" hat ");
				System.out.println("Einkommen: " + m.einkommen());
			}
			double durchschnitt = personalkosten() / alleMitarbeiter.size();
			System.out.println("Lohnsumme: " + personalkosten());
			System.out.println("Durchschnittlicher Lohn: " + durchschnitt);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void testWithAbteilung() {
//		Abteilung it = new Abteilung("IT-Abteilung", new Manager(444444, "Paula", 10000, 0.05));
//		it.add(new Manager(333333, "Pia", 10000, 0.3));
//		it.add(new BueroArbeiter(456789, "Horst", 1300));
//		SchichtArbeiter mal = new SchichtArbeiter(121212, "Yan", 15);
//		mal.arbeite(43 * 4);
//		it.add(mal);
//		Fahrer fred = new Fahrer(456, "Fred", 23.5, "C");
//		fred.arbeite(60 * 4);
//		it.add(fred);
//
//		System.out.println("Test gehaltsListe");
//		System.out.println(it.gehaltsListe());
//
//	}
//
	public final ArrayList<Mitarbeiter> getAlleMitarbeiter() {
		return alleMitarbeiter;
	}

	public final ArrayList<SchichtArbeiter> getMalocher() {
		return malocher;
	}

	public final ArrayList<Fahrer> getFahrer() {
		return fahrer;
	}

	public final ArrayList<BueroArbeiter> getBuerohengste() {
		return buerohengste;
	}

	public final ArrayList<Manager> getChefs() {
		return chefs;
	}

}
