package foxesandrabbits;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testPlaceObjectIntInt() {
		Field veldje = new Field(10,10);
		Location locatie = new Location(2,2);
		Fox vosje = new Fox(false, veldje, locatie);
		
		field.place(vosje, new Location(1,2));
		
		
		assertEquals("De locatie is niet aangemaakt", 2, fox.getLocation().getRow());
	}

}
