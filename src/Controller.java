<<<<<<< HEAD
import java.sql.Date;
=======
/**
 * Controller.java:
 * This file contains the controller portion 
 * of the model/view/controller design.
 **/
>>>>>>> refs/heads/sign_out
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
	public Controller(Model model)
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
	 * @return array list of members
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
<<<<<<< HEAD
	
	public boolean updateMember(Member member, String firstName, String lastName, String emailAddress, int yearInSchool, int membershipType, 
			Date expirationDate, int availableDiscounts, double iouAmount, boolean recieveEmail, boolean status)
	{
		
		
		boolean retVal = model.updateMember(member, firstName, lastName, emailAddress, yearInSchool, membershipType, expirationDate, availableDiscounts, iouAmount, recieveEmail, status);
		
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
	
	
}
=======

	/**
	 * @author Ashley Chin
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
    public int reconcileShiftLength(final int reconciledShiftLength)
    {
    	return mainFrame.reconcileShiftLength(reconciledShiftLength);
         
    }

}
>>>>>>> refs/heads/sign_out
