
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class SignOutIntegrationTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	Member dummy;
	ArrayList<Member> searchResults = new ArrayList<Member>();
	
	@Before
	public void setUp() throws Exception {
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);
		
		dummy = new Member(12347, "Keith", "Hernandez", "khernandez@sny.com", new Date(123456789), 1, 1, 1, 1, 1, true);
		searchResults.add(dummy);
		dummy = new Member(12346, "Art", "Vandalay", "avandalay@lol.com", new Date(123456789), 2, 2, 2, 2, 2, true);
		searchResults.add(dummy);
		m.setLastLookupMemberResults(searchResults);
		m.signIntoStore(0);
		m.signIntoStore(1);
		
		searchResults.clear();
		dummy = new Member(12345, "Barack", "Obama", "president@whitehouse.gov", new Date(123456789), 1, 1, 1, 7, 500, true);
		searchResults.add(dummy);
		dummy = new Member(12348, "Samuel", "Jackson", "snakes@aplane.com", new Date(123456789), 3, 3, 3, 3, 3, true);
		searchResults.add(dummy);
		m.setLastLookupMemberResults(searchResults);
		m.signIntoKitchen(0);
		m.signIntoKitchen(1);
		
	
	}
	
	@Test
	public void testsignOutOfStore()
	{
		
		searchResults = m.getSignedIntoStore();
		int numSignedIn = searchResults.size();
		searchResults = m.signOutOfStore(1);
		assertEquals(numSignedIn-1, searchResults.size());
		assertEquals("Keith", searchResults.get(0).getFirstName());
		
		/**
		dummy = c.getSignedIntoStore();
		numSignedIn = dummy.size();
		dummy = c.signOutOfStore(0);
		assertEquals(numSignedIn-1, dummy.size());
		**/
		
	}
	
	@Test
	public void testsignOutOfKitchen()
	{
		
		searchResults = m.getSignedIntoKitchen();
		int numSignedIn = searchResults.size();
		searchResults = m.signOutOfKitchen(1);
		assertEquals(numSignedIn-1, searchResults.size());
		assertEquals("Barack", searchResults.get(0).getFirstName());
		
		/**
		dummy = c.getSignedIntoKitchen();
		numSignedIn = dummy.size();
		dummy = c.signOutOfKitchen(0);
		assertEquals(numSignedIn-1, dummy.size());
		**/
		
	}
	
	@Test
	public void testreconcileShiftLength()
	{
		dummy = m.getSignedIntoKitchen().get(0);
		dummy.setAvailableDiscounts(10);
	
		// Simulate Working for 72 minutes
		dummy.setLastSignIn(System.currentTimeMillis() - 4320000);
		searchResults = m.signOutOfKitchen(0);
		assertEquals(11, dummy.getAvailableDiscounts());	
		
	}
	

}
