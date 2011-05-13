import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class SignInTest {
	
	private ArrayList<Member> testResults;
	private Member testMember;
	private Model model;

	@Before
	public void setUp() throws Exception 
	{
		testResults = new ArrayList<Member>();

		testMember = new Member(13132, "John", "Doe", "jdoe@binghamton.edu", 
				new Date(1234567890), 1,1,2, 1, true);

		testResults.add(testMember);
		testMember = new Member(13902, "Kevin", "Hannon", "kbh1@binghamton.edu", 
				new Date(1234567890), 1,1,2, 1, true);
		testResults.add(testMember);
		testMember = new Member(13902, "Bob", "Smith", "bsmith@binghamton.edu", 
				new Date(1234567890), 0,1,2, 1, true);
		testResults.add(testMember);
		model = new Model();
		///model.setLastLookupMemberResults(testResults);
	}
	
	@Test
	public void testSetlastSignIn()
	{
		long time = System.currentTimeMillis();
		testMember.setLastSignIn(time);
		assertEquals(time, testMember.getLastSignIn());
	}
	
	@Test
	public void testSignIntoKitchen()
	{
		ArrayList<Member> signInResults = new ArrayList<Member>();
		String message = "Blank";
		try
		{
			signInResults = model.signIntoKitchen(0);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			signInResults = model.signIntoStore(0);
		}
		catch(Exception e)
		{
			message = e.getMessage();
		}
		assertEquals(1, signInResults.size());
		assertEquals("This member is already signed into the kitchen", message);
	}
	
	@Test
	public void testSignIntoStore()
	{
		ArrayList<Member> signInResults = new ArrayList<Member>();
		String message = "Blank";
		try
		{
			signInResults = model.signIntoStore(1);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try
		{
			signInResults = model.signIntoStore(1);
		}
		catch(Exception e)
		{
			message = e.getMessage();
		}
		assertEquals(1, signInResults.size());
		assertEquals("This member is already signed into the store", message);
	}
	
	@Test
	public void testForAlreadySignedIntoOtherList()
	{
		String message1 = "Blank";
		String message2 = "Blank";
		ArrayList<Member> storeList = new ArrayList<Member>();
		ArrayList<Member> kitchenList = new ArrayList<Member>();
		try{
			storeList = model.signIntoStore(1);
			kitchenList = model.signIntoKitchen(0);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try{
			storeList = model.signIntoStore(0);
		}
		catch(Exception e)
		{
			message1 = e.getMessage();
		}
		try{
			storeList = model.signIntoStore(1);
		}
		catch(Exception e)
		{
			message2 = e.getMessage();
		}
		assertEquals(1, storeList.size());
		assertEquals(1, kitchenList.size());
		assertEquals("This member is already signed into the kitchen", message1);
		assertEquals("This member is already signed into the store", message2);
	}
	
	@Test
	public void cannotBeSignedIn()
	{
		String message1 = "Blank";
		String message2 = "Blank";
		try{
			model.signIntoStore(2);
		}
		catch(Exception e)
		{
			message1 = e.getMessage();
		}
		try{
			model.signIntoStore(2);
		}
		catch(Exception e)
		{
			message2 = e.getMessage();
		}
		assertEquals("This member cannot be signed in", message1);
		assertEquals("This member cannot be signed in", message2);
	}
}
