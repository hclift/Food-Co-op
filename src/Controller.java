<<<<<<< HEAD

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

		model.setLastLookupMemberResults(searchResult);
		return searchResult;
	}

	public ArrayList<Member> viewMember(String firstName, String lastName)
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

	public Member getMember (int index)
	{
		return model.getMember(index);
	}

}
>>>>>>> controller
