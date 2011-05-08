import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * This class provides the controller portion of the MVC framework.
 **/
public class Controller
{
	// Contains the model associated with the controller instance.
	private Model model;
	// Contains the MainFrame associated with the controller instance.
	private MainFrame mainFrame;

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Explicit Value Constructor for the controller, that takes in a model to be associated.
	 * @param model
	 *            The model to link to the controller
	 **/
	public Controller(final Model model)
	{
		this.model = model;
		mainFrame = new MainFrame(this, model);
		model.setControllerReference(this);
	}

	/**
	 * Try Catch Method. Executes when a query is sent to the model and handles
	 * the response. View will call this method on lookUpMember(). If an
	 * exception is made, it runs the createEmptyArrayList method as the return
	 * 
	 * @param firstName
	 *            First name of the member being looked up
	 * @param lastName
	 *            Last name of the member being looked up
	 * @return searchResult
	 * 			  An array list of members found from the search
	 **/
	public ArrayList<Member> lookUpMember(final String firstName, final String lastName)
	{
		// ArrayList to hold returned search results
		ArrayList<Member> searchResult = new ArrayList<Member>();

		try
		{
			searchResult = model.lookupMember(firstName, lastName);
		}
		catch (Exception e)
		{
			mainFrame.displayException(e.getMessage());
		}
	
		return searchResult;
	}
	
	/**
	* Adds a member to the database.  Uses a PreparedStatement.
	* @param first_name			First name of the member to look for
	* @param last_name			Last name of the member to look for
	* @param membership_length	Length of member's membership, 0 for
									half semester, 1 for full ??
	* @param membership_type	Membership type ??
	* @param year_in_school		Member's year in school 0 for freshman
									1 for sophomore, 2 for junior,
									3 for senior, 4 for graduate,
									5 for faculty ??
	* @param receive_email		Can the member receive emails from the
									Food Co-op?
	* @param is_active			Is this member active?
	*/
	public void addMember(String first_name, 
		String last_name,
		String email_address,
		int membership_length,
		int membership_type,
		int year_in_school,
		int is_active)
	{
		try
		{
			model.addMember(first_name, last_name, email_address, membership_length, membership_type, year_in_school, is_active);
		} 
		catch (Exception e)
		{
			mainFrame.displayException(e.getMessage());
		}
	}
	
	/**
	 * Try Catch Method.
	 * Executes when a member is being signed into the kitchen
	 * View will call this method on signIntoKitchen(). 
	 * If an exception is made, it runs the createEmptyArrayList method as the return
	 * @param index
	 * @return array list of members 
	 **/
	public ArrayList<Member> signIntoKitchen(int index)
	{
		ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
		try
		{
			signedIntoKitchen = model.signIntoKitchen(index);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			signedIntoKitchen = model.getSignedIntoKitchen();
		}
		
		return signedIntoKitchen;
	}
	
	/**
	 * Try Catch Method.
	 * Executes when a member is being signed into the kitchen
	 * View will call this method on signIntoStore(). 
	 * If an exception is made, it runs the createEmptyArrayList method as the return
	 * @param index
	 * @return array list of members 
	 **/
	public ArrayList<Member> signIntoStore(int index)
	{
		ArrayList<Member> signedIntoStore = new ArrayList<Member>();
		try
		{
			signedIntoStore = model.signIntoStore(index);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			signedIntoStore = model.getSignedIntoStore();
		}
		
		return signedIntoStore;
	}
	
	/**
	 * The getSignedIntoKitchen method is used to return 
	 * an array of members signed into the kitchen.
	 * 
	 * @returns ArrayList of members signed into kitchen
	 */
	public ArrayList<Member> getSignedIntoKitchen() {
		return model.getSignedIntoKitchen();
	}
	
	/**
	 * The getSignedIntoStore method is used to return 
	 * an array of members signed into the store.
	 * 
	 * @returns ArrayList of members signed into store
	 */
	public ArrayList<Member> getSignedIntoStore() {
		return model.getSignedIntoStore();
	}
	
	public boolean updateMember(Member member, String firstName, String lastName, String emailAddress, int yearInSchool, int membershipType, 
			Date expirationDate, int availableDiscounts, double iouAmount, boolean status)
	{
				
		boolean retVal = model.updateMember(member, firstName, lastName, emailAddress, yearInSchool, membershipType, expirationDate, availableDiscounts, iouAmount, status);
		
		return retVal;
	}
	
	public Member getMember (int index)
	{
		return model.getMember(index);
	}
	
	public boolean validateMembershipType(double doubleIOUAmount)
	{
		model.validateMembershipType(doubleIOUAmount);
		
		return true;
	}
	
	public double addToIou(int currentYear, int membershipType, double oldAmount, double adjustment)
	{
		double newAmount = 0;
		try
		{
			newAmount = model.addToIou(currentYear, membershipType, oldAmount, adjustment);
		}
		catch (Exception e)
		{
			mainFrame.displayException(e.getMessage());
			return oldAmount;
		}
		return newAmount;
	}
	
	public double subtractFromIou(int currentYear, int membershipType, double oldAmount, double adjustment)
	{
		double newAmount = 0;
		try
		{
			newAmount = model.subtractFromIou(currentYear, membershipType, oldAmount, adjustment);
		}
		catch(Exception e)
		{
			mainFrame.displayException(e.getMessage());
			return oldAmount;
		}
		return newAmount;
	}

	/** @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Calls model's signOutOfStore() method, so it will eventually be
	 *          passed to the view.
	 * @param designated
	 *            index of the member in the array list
	 * @return modified array list of currently working members in the store
	 */
	public ArrayList<Member> signOutOfStore(final int index)
	{
		return model.signOutOfStore(index);
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Calls model's signOutOfKitchen() method, so it will eventually
	 *          be passed to the view.
	 * @param designated
	 *            index of the member in the array list
	 * @return modified array list of currently working members in the kitchen
	 */

    public ArrayList<Member> signOutOfKitchen(final int index)
    {
        return model.signOutOfKitchen(index);
    }
    
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Calls reconcileShiftLength() method in the view, so it will
	 *          eventually be passed to the model.
	 * @param shfitLength
	 *            shift length in minutes
	 * @return New shift length after reconciliation.
	 */
    public long reconcileShiftLength(long reconciledShiftLength)
    {
    	long newShiftLength = reconciledShiftLength * 60000;
    	model.setShiftLength(newShiftLength);
    	return newShiftLength;    
    }
    
    public ArrayList<YearsInSchool> getYearsInSchool(){
		return model.getYearsInSchool();
	}
    
    public ArrayList<MembershipTypes> getMembershipTypes(){
		return model.getMembershipTypes();
	}
}
