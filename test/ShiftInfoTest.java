import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShiftInfoTest {
	private ShiftInfo testShift;

	@Before
	public void setUp() throws Exception {
		testShift = new ShiftInfo(6, 8, 2011, 120);  // (month, day, year, minWorked)
	}

	@Test
	public void shiftMonthTest(){
		assertEquals(6, testShift.getShiftMonth());
	}

	@Test
	public void shiftDayTest(){
		assertEquals(8, testShift.getShiftDay());
	}

	@Test
	public void shiftMonthYear(){
		assertEquals(2011, testShift.getShiftYear());
	}

	@Test
	public void shiftMinWorkedTest(){
		assertEquals(120, testShift.getMinWorked());
	}
}
