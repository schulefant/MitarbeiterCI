package mitarbeiter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBueroArbeiter {

	BueroArbeiter bmGood, bmBad;

	@BeforeEach
	void setUp() throws Exception {
		bmGood = new BueroArbeiter(5234, "Erna", 2000);
	}

	@Test
	void testIfIDisAdaptedCorrectly() {
		bmBad = new BueroArbeiter(1111, "Al", 3000);
		assertEquals(5111, bmBad.getID(), "ID 1111 was not adapted correctly");
		bmBad = new BueroArbeiter(0, "Al", 3000);
		assertEquals(5000, bmBad.getID(), "ID 0 was not adapted correctly");
	}

	@Test
	void testIfMinimumWage300IsGuaranteed() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
				() -> bmBad = new BueroArbeiter(5111, "Al", 299),
				"The wage of 299 was accepted. It should be greater than 300.");
		assertEquals("Gehalt ist zu gering.", e.getMessage(), "Exception Message is not correct.");
		bmBad = new BueroArbeiter(0, "Al", 3000);
		bmBad.setFestgehalt(300);
		assertEquals(300, bmBad.getFestgehalt(), "The wage of 300 was not accepted, but it should have been.");
		assertThrows(IllegalArgumentException.class, () -> bmBad.setFestgehalt(299),
				"The wage of 299 was accepted. It should be greater than 300.");
	}
}
