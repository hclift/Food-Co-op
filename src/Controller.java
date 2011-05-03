import java.sql.Date;
import java.util.ArrayList;

public class Controller
{
	private Model model;
	private MainFrame mainFrame;

	public Controller (Model model)
	{
		this.model = model;
		mainFrame = new MainFrame(this, model);
	}
	
	/**
	 * Try Catch Method.
	 * Executes when a query is sent to the model and handles the response.
	 * View will call this method on lookUpMember(). 
	 * If an exception is made, it runs the createEmptyArrayList method as the return
	 * @param first name, last name
	 * @return array list of members 
	 **/
	public ArrayList<Member> lookUpMember(String firstName, String lastName)
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
	
	public boolean updateMember(Member member, String firstName, String lastName, String emailAddress, int yearInSchool, int membershipType, 
			Date expirationDate, int availableDiscounts, double iouAmount, boolean status)
	{
		
		
		boolean retVal = model.updateMember(member, firstName, lastName, emailAddress, yearInSchool, membershipType, expirationDate, availableDiscounts, iouAmount, status);
		
		return retVal;
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
			signedIntoStore = model.signIntoKitchen(index);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		return signedIntoStore;
	}
	
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 * Calls model's signOutOfStore() method, so it will eventually be passed to the view.
	 * @param designated index of the member in the array list
	 * @return modified array list of currently working members in the store
	 */
	public ArrayList<Member> signOutOfStore(int index)
    {
        return model.signOutOfStore(index);
    }
    
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 * Calls model's signOutOfKitchen() method, so it will eventually be passed to the view.
	 * @param designated index of the member in the array list
	 * @return modified array list of currently working members in the kitchen
	 */
    public ArrayList<Member> signOutOfKitchen(int index)
    {
        return model.signOutOfKitchen(index);
    }
    
    /**
     * @author Ashley Chin
     * @version 4/14/11
     * 
     * Calls view's reconcileShiftLength() method, so it will eventually be passed to the model.
     * @param shift length in minutes
     * @return shift length
     */
    public void reconcileShiftLength(int shiftLength)
    {
        
    	//return mainFrame.reconcileShiftLength(shiftLength);
        /*----------------------------------------------------------------
        * Update-member User Interface reference?  Need this method in the View somehow
        *
        *-----------------------------------------------------------------
        */
        
    }
}