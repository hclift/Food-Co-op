import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class SignOutTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	ArrayList<Member> dummy = new ArrayList<Member>();
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
		int numSignedIn = dummy.size();
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
		
		dummy = c.getSignedIntoKitchen();
		int numSignedIn = dummy.size();
		dummy = c.signOutOfKitchen(0);
		assertEquals(numSignedIn-1, dummy.size());
		
	}
}
