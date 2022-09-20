package mitarbeiter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBueroArbeiter {

	@Test
	void testIfIDIsAdaptedCorrectly() {
		BueroArbeiter bm = new BueroArbeiter(1111, "Erwin", 2000);
		assertEquals(5111, bm.getID(), "ID adapted correctly");
	}

}
