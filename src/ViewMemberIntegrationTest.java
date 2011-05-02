
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class ViewMemberIntegrationTest {
	private Controller c;
	private Model m;
	private MainFrame mf;
	
	
	@Before
	public void setUp() throws Exception {
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);
		ArrayList<Member> dummy = new ArrayList<Member>();
		dummy = c.lookUpMember("John", "Smith");
		
	}
	
	@Test
	public void testModeltoControllerViewMember()
	{
		Member testMember;
		testMember = m.getMember(0);
		assertEquals("John", testMember.getFirstName() );
		assertEquals("Smith", testMember.getLastName() );
		assertEquals("jsmith2@example.com", testMember.getEmailAddress());
	
	
	}
	
	@Test
	public void testControllertoMainFrame()
	{
		Member testMember;
		testMember = c.getMember(0);
		assertEquals("John", testMember.getFirstName() );
		assertEquals("Smith", testMember.getLastName() );
		assertEquals("jsmith2@example.com", testMember.getEmailAddress());	
		
	}
}
