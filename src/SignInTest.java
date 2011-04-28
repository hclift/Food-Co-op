
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class SignInTest {
	
	private ArrayList<Member> testResults;
	private Member testMember;
	private Model model;

	@Before
	public void setUp() throws Exception {
		testResults = new ArrayList<Member>();
		testMember = new Member(13132, "John", "Doe", "jdoe@binghamton.edu", 
				new Date(1234567890), 1, 3,1,2, 1, false, true);
		testResults.add(testMember);
		model = new Model();
		model.setLastLookupMemberResults(testResults);
	}
	
	@Test
	public void testSignIntoKitchen(){
		System.out.println(testResults.size());
		try{
			ArrayList<Member> signInResults;// = new ArrayList<Member>();
			signInResults = model.signIntoKitchen(0);
			System.out.println(signInResults.size());
			assertEquals(1, signInResults.size());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
