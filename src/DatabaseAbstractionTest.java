import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


/**
 * @author
 *
 */
public class DatabaseAbstractionTest{

	/**
	 * Test method for {@link DatabaseAbstraction#lookupMember(String, String)}.
	 */
	@Test
	public void testLookupMember() {
		//fail("Not yet implemented");
		if(DatabaseAbstraction.lookupMember("john", "doe").isEmpty()){
			assertEquals("Empty List should return 0", 0, DatabaseAbstraction.lookupMember("john", "doe").size());
		}else{
			ArrayList<Member> testList = DatabaseAbstraction.lookupMember("john","doe");
			for(Member m: testList){
				assertEquals("john", m.getFirstName());
				assertEquals("doe", m.getLastName());				
			}
		}		
	} 
	
	@Test
	public void testAddMember() {
		//fail("Not yet implemented");
		/*DatabaseAbstraction.addMember("abc", "efg", "a@aol.com", 0, 0, 3, 1);

		ArrayList<Member> testList = DatabaseAbstraction.lookupMember("abc","efg");
		if (testList.size() > 0) {
			for (Member m : testList) {
				assertEquals("abc", m.getFirstName());
				assertEquals("efg", m.getLastName());
			}
		}else{
			fail("Failed to add Member");
		}
*/
	} 
}
