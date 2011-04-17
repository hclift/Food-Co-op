/*
 * Model.java:
 * This file contains the model portion of the model/view/controller design.
 */
import java.util.ArrayList;

/*
 * This class provides the model portion of the MVC framework.
 */
public class Model 
{
	/*
	 * This method provides functionality for looking up members based on name.
	 * It services requests from the view. It does this by calling the
	 * lookupMember method in the database abstraction layer and passing this
	 * return value its own return value.
	 * 
	 * @param firstName First name of the member being looked up.
	 * 
	 * @param lastName Last name of the member being looked up.
	 * 
	 * @return An ArrayList of members matching the first name and last name
	 * given.
	 */
	public ArrayList<Member> lookupMember(String firstName, String lastName)
			throws Exception 
	{
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}
}