
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;

import org.junit.Test;


public class SignOutTest {
	Controller TempController;
	Model TempModel;
	ArrayList<Member> TestStoreMemberList;
	ArrayList<Member> TestKitchenMemberList;
	ArrayList<Member> testResults;
	Member testMember;
	@Before
	public void setUp() throws Exception {
		TempModel = new Model();
		testResults = new ArrayList<Member>();
		testMember = new Member(13132, "John", "Doe", "jdoe@binghamton.edu", 
				new Date(1234567890), 1, 1, 1, 2, 1, true);
		testResults.add(testMember);
		testMember = new Member(13902, "Raibi", "Jamila", "raibi1@binghamton.edu", 
				new Date(1234567890), 1, 3,1,2, 1, true);
		testResults.add(testMember);
		TempModel.setLastLookupMemberResults(testResults);
		TestStoreMemberList  = TempModel.signIntoStore(0);
		TestKitchenMemberList = TempModel.signIntoKitchen(1);

	}
    @Test
	public void testsignOutOfStore()
    {
		TestStoreMemberList.remove(0);
		TempModel.signOutOfStore(0);
		assertEquals(TestStoreMemberList,TempModel.getSignedIntoStore());
	}
    @Test
	public void testsignOutOfKitchen()
    {
		TestKitchenMemberList.remove(0);
		TempModel.signOutOfKitchen(0);
		assertEquals(TestKitchenMemberList,TempModel.getSignedIntoKitchen());
		
	}
}


