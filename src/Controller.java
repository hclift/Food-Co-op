import java.util.ArrayList;

/**
 * 
 * This is the Controller.java class
 */

public class Controller
{
	private Model model;
	private MainFrame mainFrame;

	public Controller (final Model model)
	{
		this.model = model;
		mainFrame = new MainFrame(this, model);
	}

	/**
	 * lookUpMember Method.
	 * Executes when a query is sent to the model and handles the response.
	 * View will call this method on lookUpMember(). 
	 * If an exception is made, it runs the createEmptyArrayList method as the return
	 * @param firstName
	 * @param lastName 
	 * @return searchResult ( an array list of members ) 
	 **/
	public ArrayList<Member> lookUpMember(final String firstName, final String lastName)
	{
		ArrayList<Member> searchResult = new ArrayList<Member>();

		try
		{
			searchResult = model.lookupMember(firstName,lastName);
		} 
		catch(Exception e)
		{
			mainFrame.displayException(e.getMessage());
		}

		model.setLastLookupMemberResults(searchResult);
		return searchResult;
	}

	/**
	 * 
	 * viewMember Method.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return searchResult
	 */
	public ArrayList<Member> viewMember(final String firstName, final String lastName)
	{
		ArrayList<Member> searchResult = new ArrayList<Member>();

		try
		{
			searchResult = model.lookupMember(firstName,lastName);
		} 
		catch(Exception e)
		{
			mainFrame.displayException(e.getMessage());
		}

		return searchResult;
	}

	/**
	 * 
	 * getMember Method.
	 * 
	 * @param index
	 * @return the member
	 */
	public Member getMember (final int index)
	{
		return model.getMember(index);
	}

}