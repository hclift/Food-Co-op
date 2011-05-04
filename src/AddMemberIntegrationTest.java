
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;


public class AddMemberIntegrationTest {
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
	/* We must physically look at the database itself to make sure the member has been created
	 * 
	 */
	public void AddMemberTestDBA()
	{
		try{
			boolean isSuccessful;
			isSuccessful = DatabaseAbstraction.addMember(dummy);
			assertEquals(true, isSuccessful);
		}
		catch(Exception e)
		{
			System.out.println("Error adding member to database"  + e.getMessage());
		}
		
	}
	
	@Test
	public void AddMemberTestModeltoController()
	{
		try
		{
			boolean isSuccesful;
			isSuccesful =m.addMember("John","Doe","jdoe@bing.edu","junior 1", "Core", "one");
		
			Member testMember;
			testMember = //don't know how the member is stored in model yet
			assertEquals("John", testMember.getFirstName() );
			assertEquals("Doe", testMember.getLastName() );
			assertEquals("jdoe@bing.edu", testMember.getEmailAddress());
			assertEquals(4,getYearsInSchool());
		}
		catch(Exception e)
		{
			System.out.println("Error adding member at model to controller"  + e.getMessage());
		}
	}
	
	@Test
	public void ControllerToMainFrame()
	{
		try{
			boolean isSuccesful;
			isSuccessful = c.addMember("John","Doe","jdoe@bing.edu","junior 1", "Core", "one");
			assertEquals(true, isSuccessful);
		}
		catch(Exception e)
		{
			System.out.println("Error adding member at controller to view"  + e.getMessage());
		}

	}
	
	
}
