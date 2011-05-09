
import org.junit.BeforeClass;
import org.junit.Test;


public class ShftInfoTest {
	
	private ShiftInfo testShift;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Sets up test shiftInfo(int month,int day, int year, int timeWorkedInMinutes);
		testShift = new ShiftInfo(6,8,2011,120);
	}
	
	@Test
	public void shiftMonthTest(){
		assertEquals(6,testShift.getShiftMonth());
	}
	
	@Test
	public void shiftDayTest(){
		assertEquals(8,testShift.getShiftDay());
	}
	
	@Test
	public void shiftMonthYear(){
		assertEquals(2011,testShift.getShiftYear());
	}
	
	@Test
	public void shiftMinWorkedTest(){
		assertEquals(120,testShift.getminWorked());
	}

}
