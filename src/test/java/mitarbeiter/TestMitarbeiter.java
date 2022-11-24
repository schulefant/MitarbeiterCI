package mitarbeiter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestMitarbeiter {
	Mitarbeiter m;

	@Test
	void ifValidIDandNameIsPassedToConstructorValuesAreStoredInObject() {
		m = new Mitarbeiter(1000, "Jane Doe") {

			@Override
			protected MitarbeiterTyp getType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double einkommen() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		assertEquals(1000, m.getID());
		assertEquals("Jane Doe", m.getName());
	}
	@Test 
	void ifTooSamllIDIsPassedToConstructorIllegalArgumentExceptionIsThrown() {
		assertThrows(IllegalArgumentException.class,()-> new Mitarbeiter(0,"Jo"){
			@Override
			protected MitarbeiterTyp getType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double einkommen() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}, "Id 0 not caught.");

	}
	@Test 
	void ifnegativeIDIsPassedToConstructorIllegalArgumentExceptionIsThrown() {
		assertThrows(IllegalArgumentException.class,()-> new Mitarbeiter(-1,"Jane"){
			@Override
			protected MitarbeiterTyp getType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double einkommen() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}, "Id -1 not caught.");

	}
	@Test 
	void ifNamelessthan2LettersIsPassedToConstructorIllegalArgumentExceptionIsThrown() {
		IllegalArgumentException e = assertThrows(IllegalArgumentException.class,()-> new Mitarbeiter(1,"J"){
			@Override
			protected MitarbeiterTyp getType() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double einkommen() {
				// TODO Auto-generated method stub
				return 0;
			}
			
		}, "J as Name not caught");

	}
}
