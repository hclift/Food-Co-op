import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SignInIntegrationTest {
	private Model m;
	private Controller c;
	private MainFrame mf;
	private Member dummyMember;
	ArrayList<Member>dummyArrayList = new ArrayList<Member>();
	private int numSignedIntoKitchen;
	private int numSignedIntoStore;

	@Before
	public void setUp() throws Exception{
		m = new Model();
		c = new Controller(m);
		mf = new MainFrame(c, m);

		try{
			//Sign in 2 members into the kitchen
			dummyArrayList = c.lookUpMember("Vinny", "Chase");
			m.signIntoKitchen(0);
			dummyArrayList = c.lookUpMember("Eric", "Murphy");
			m.signIntoKitchen(0);
			numSignedIntoKitchen = dummyArrayList.size();
		}
		catch(Exception e){
			System.out.println("Error in signing a member into the kitchen: " + e.getMessage());
		}

		try{
			//Sign in 2 members into the store
			dummyArrayList = c.lookUpMember("Henry", "Hill");
			m.signIntoStore(0);
			dummyArrayList = c.lookUpMember("George", "Costanza");
			m.signIntoStore(0);
			numSignedIntoStore = dummyArrayList.size() - 2;	//minus two since the first two members in the arrayList are in the kitchen
		}
		catch(Exception e){
			System.out.println("Error in signing a member into the store: " + e.getMessage());
		}

	}

	@Test
	public void testSignIntoKitchen{
		try{
			//getSignedIntoKitchen returns an arrayList of members currently signed into kitchen
			//The size of dummyArrayList in both methods should be 2 (number of members signed in)
			//dummyArrayList will now contain members signed into kitchen
			dummyArrayList = m.getSignedIntoKitchen();
			assertEquals(numSignedIntoKitchen, dummyArrayList.size());
		}
		catch(Exception e){
			System.out.println("Error in signIntoKitchen, size of array list is wrong: " + e.getMessage());
		}
	}

	@Test
	public void testSignIntoStore{
		try{
			//dummyArrayList will now contain members signed into store
			//not completely sure this is accurate
			dummyArrayList = m.getSignedIntoStore();
			assertEquals(numSignedIntoStore, dummyArrayList.size());
		}
		catch(Exception e){
			System.out.println("Error in signIntoStore, size of array list is wrong: " + e.getMessage());
		}
	}

}