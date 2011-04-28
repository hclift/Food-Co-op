
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;


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
	}

}
