
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class SignOutIntegrationTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	ArrayList<Member> dummy = new ArrayList<Member>();
	Member testMember;
	@Before
	public void setUp() throws Exception {
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);
		dummy = m.lookupMember("John", "Smith");
		m.signIntoStore(0);
		dummy = m.lookupMember("Jane", "Dane");
		m.signIntoStore(0);
		dummy = m.lookupMember("Keith", "Hernandez");
		m.signIntoKitchen(0);
		dummy = m.lookupMember("Art", "Vandalay");
		m.signIntoKitchen(0);
	
	}
	
	@Test
	public void testsignOutOfStore()
	{
		
		dummy = m.getSignedIntoStore();
		int numSignedIn = dummy.size();
		dummy = m.signOutOfStore(1);
		assertEquals(numSignedIn-1, dummy.size());
		
		dummy = c.getSignedIntoStore();
		numSignedIn = dummy.size();
		dummy = c.signOutOfStore(0);
		assertEquals(numSignedIn-1, dummy.size());
		
	}
	
	@Test
	public void testsignOutOfKitchen()
	{
		
		dummy = m.getSignedIntoKitchen();
		int numSignedIn = dummy.size();
		dummy = m.signOutOfKitchen(1);
		assertEquals(numSignedIn-1, dummy.size());
		assertEquals("John", dummy.get(0).getFirstName());
		
		dummy = c.getSignedIntoKitchen();
		numSignedIn = dummy.size();
		dummy = c.signOutOfKitchen(0);
		assertEquals(numSignedIn-1, dummy.size());
		assertEquals("Keith", dummy.get(0).getFirstName());
		
	}
	
	@Test
	public void testreconcileShiftLength()
	{
		m.getSignedIntoKitchen().clear();
		testMember = new Member(13132, "Barack", "Obama", "president@whitehouse.gov", 
						new Date(1234567890), 1, 1,1,7, 1, false, true);
		dummy = m.signIntoKitchen(testMember);
		int newShiftLength = c.reconcileShiftLength(51);
		if (newShiftLength >= 90)
		{
			m.getSignedIntoKitchen().get(0).setAvailableDiscounts(testMember.getAvailableDiscounts()+2);
			assertEquals(9, m.getSignedIntoKitchen().get(0).getAvailableDiscounts());
		}
		else
		{
			m.getSignedIntoKitchen().get(0).setAvailableDiscounts(testMember.getAvailableDiscounts()+1);
			assertEquals(8, testMember.getAvailableDiscounts());
		}
	}
	
}
