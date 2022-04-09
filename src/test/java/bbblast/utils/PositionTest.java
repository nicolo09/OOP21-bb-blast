package bbblast.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class PositionTest {
	
	@Test
	public void testPositionMethods() {
		final Position pos = new PositionImpl(0,0);
		final Position check = pos.getCopy();
		assertEquals(pos,check,"check is the copy of pos so they are the same position");
		check.translate(1, 1);
		assertFalse(pos.equals(check),"check now represents another position");
		check.translate(1, 1);
		pos.setCoords(2, 2);
		assertEquals(pos, check,"check and pos should now represent the same position again");
	}

}
