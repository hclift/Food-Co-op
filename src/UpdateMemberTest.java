import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;


/*
 * JUnit Test for Update Member
 * 
 */
public class UpdateMemberTest {
	
	@Test
	public void testUpdateMember()
	{
	Member original = new Member(1, "john", "doe", "jdoe@gmail.com",
			new Date(1234567890), 1, 2, 3, 4, 5.0, true);
	Model model = new Model();
	Controller controller = new Controller(model);
	controller.updateMember(original, "jason", "doe1", "jdoe1@gmail.com", 4, 3, 
		new Date(1234567890), 5, 2.0, true );
	
	DatabaseAbstraction.updateMember(original);
	assertEquals("jason", original.getFirstName() );
	assertEquals("doe1", original.getLastName() );
	assertEquals("jdoe1@gmail.com", original.getEmailAddress());
	assertEquals(4, original.getYearInSchool());
	assertEquals(3, original.getMembershipType());
	assertEquals(5, original.getAvailableDiscounts() );
	assertEquals(2.0, original.getIouAmount(), .1);
	assertEquals(true, original.getActive());
	}
}
