
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class AddShiftIntegrationTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	private Member dummy;
	private DatabaseAbstraction dba;
	private ShiftInfo testShift;
	private ArrayList<ShiftInfo> testResults = new ArrayList<ShiftInfo>();
	private ArrayList<ShiftInfo> results = new ArrayList<ShiftInfo>();
	@Before
	public void setUp() throws Exception {
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);
		dummy = new Member(12345, "Barack", "Obama", "president@whitehouse.gov", 
				new Date(123456789), 1, 1, 1, 7, 500, true);
		testShift = new ShiftInfo(05, 07, 2011, 63);
		testResults.add(testShift);
	}
	
	@Test
	public void testAddShifts()
	{

		DatabaseAbstraction.addShift(dummy, 05, 07, 2011, 63);
		results = c.getShifts(dummy, 05, 2011);
		assertEquals(testResults, results);
		
		
	}
	

}
