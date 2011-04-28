
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;


public class UpdateMemberIntegrationTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	private Member dummy;
	@Before
	public void setUp() throws Exception {
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);
		dummy = new Member(1, "john", "doe", "jdoe@gmail.com",
				new Date(1234567890), 1, 2, 3, 4, 5.0, false, true);
	}
	
	@Test
	// We must physically look at the database itself to make sure the member has been updated.
	public void UpdateMemberTestDBA()
	{
		boolean isSuccessful;
		isSuccessful = DatabaseAbstraction.updateMember(dummy);
		assertEquals(true, isSuccessful);
		
	}
	
	@Test
	public void UpdateMemberTestModeltoController()
	{
		m.updateMember(dummy, "Jane", "Doe", "janedoe@gmail.com", 4, 2, 
				new Date(1234567899), 7, 10.0, true, true);
		assertEquals("Jane", dummy.getFirstName());
		assertEquals("Doe", dummy.getLastName());
		assertEquals("janedoe@gmail.com", dummy.getEmailAddress());
		assertEquals(4, dummy.getYearInSchool());
		assertEquals(2, dummy.getMembershipType());
		assertEquals(7, dummy.getAvailableDiscounts() );
		assertEquals(10.0, dummy.getIouAmount(), .1);
		assertEquals(true, dummy.getActive());
		assertEquals(true, dummy.getReceiveEmail());
		
	}
	
	@Test
	public void ControllerToMainFrame()
	{
		
		//new Member(1, "john", "doe", "jdoe@gmail.com",
		//new Date(1234567890), 1, 2, 3, 4, 5.0, false, true);
		c.updateMember(dummy, "Jane", "Doe", "janedoe@gmail.com", 4, 2, 
				new Date(1234567899), 7, 10.0, true, true);
		assertEquals("John", dummy.getFirstName());
		assertEquals("Doe", dummy.getLastName());
		assertEquals("jdoe@gmail.com", dummy.getEmailAddress());
		assertEquals(3, dummy.getYearInSchool());
		assertEquals(2, dummy.getMembershipType());
		assertEquals(4, dummy.getAvailableDiscounts() );
		assertEquals(5.0, dummy.getIouAmount(), .1);
		assertEquals(true, dummy.getActive());
		assertEquals(false, dummy.getReceiveEmail());
	}
	
	
}
