import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * The Model class represents the current state of the program,
 * including lookup member, sign-in, and sign out features.
 * 
 * The Model class interacts with the DatabaseAbstraction to get
 * and save member information.
 * 
 */
public class Model 
{
	
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();

	public enum yearInSchool {Freshman1, Freshman, Sophmore1, Sophmore2, Junior1, Junior2, Senior1, Senior2, Graduate, Faculty}
	private ArrayList<Member> matches = new ArrayList<Member>();
	
	/**
	 * Update the model with matching members from the database
	 * 
	 * @param firstName		the first name of the member to search for
	 * @param lastName		the last name of the member to search for
	 * 
	 * @return an ArrayList of matching members
	 * 
	 * FIXME: @throws
	 */
	public ArrayList<Member> lookupMember(String firstName, 
			String lastName) throws Exception
	{
		//throw new Exception();  // Is this suppose to do anything?
		
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}
	
	/**
	 * The getSignedIntoKitchen method is used to return 
	 * an array of members signed into the kitchen.
	 * 
	 * @returns ArrayList of members signed into kitchen
	 */
	public ArrayList<Member> getSignedIntoKitchen() {
		return signedIntoKitchen;
	}
	
	/**
	 * The getSignedIntoStore method is used to return 
	 * an array of members signed into the store.
	 * 
	 * @returns ArrayList of members signed into store
	 */
	public ArrayList<Member> getSignedIntoStore() {
		return signedIntoStore;
	}
	
	/**
	 * Signs a member into the kitchen
	 * 
	 * @param index the index of the member (in the matches) to be signed into the kitchen
	 * @returns ArrayList of members signed into kitchen
	 * @throws Exception when member is already signed into the kitchen or store or
	 * if invalid index is passed in.
	 */
	public ArrayList<Member> signIntoKitchen(int index) throws Exception
	{
		if(index >= matches.size())
		{
			throw new Exception("Invalid Index");
		}
		else if(matches.get(index).canSignIn())
		{
			for(int i = 0; i < signedIntoKitchen.size(); i++)
			{
				if(signedIntoKitchen.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the kitchen");
				}
			}
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the store");
				}
			}
			long time = 0;
			matches.get(index).setLastSignIn(new Time(time));
			signedIntoKitchen.add(matches.get(index));
		}
		else
		{
			throw new Exception("This member cannot be signed in");
		}
		
		return signedIntoKitchen;
	}
	
	/**
	 * Signs a member into the store
	 * 
	 * @param index 	the index of the member (in the matches) to be signed into the store
	 * @returns ArrayList of members signed into store
	 * @throws Exception when member is already signed into the kitchen or store or
	 * if invalid index is passed in.
	 */
	public ArrayList<Member> signIntoStore(int index) throws Exception
	{
		if(index >= matches.size())
		{
			throw new Exception("Invalid Index");
		}
		else if(matches.get(index).canSignIn())
		{
			for(int i = 0; i < signedIntoKitchen.size(); i++)
			{
				if(signedIntoKitchen.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the kitchen");
				}
			}
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the store");
				}
			}
			long time = 0;
			matches.get(index).setLastSignIn(new Time(time));
			signedIntoStore.add(matches.get(index));
			
		}
		else
		{
			throw new Exception("This member cannot be signed in");
		}
		return signedIntoStore;
	}
	
	/**
	 * Update all the values of a member.  See Member class.
	 * 
	 * @param member 				the member to update
	 * @param firstName				the member's first name
	 * @param lastname				the member's last name
	 * @param emailAddress			the member's email address
	 * @param yearInSchool			the index of the YEAR_IN_SCHOOL global variable 
	 * 								that the member belongs to
	 * @param membershipType		the membership type
	 * @param expirationDate		the date the membership expires
	 * @param availableDiscounts	the percent discount available to the member
	 * @param iouAmount				the IOU amount the member owes
	 * @param receiveEmail			if the member opts to receive email
	 * @param status				if member is active		
	 * 
	 * @returns	boolean if database updates completed successfully	// TODO: turn this into an exception 
	 * 
	 */
	public boolean updateMember(Member member, String firstName, String lastName, String emailAddress, int yearInSchool, int membershipType, 
			Date expirationDate, int availableDiscounts, double iouAmount,boolean receiveEmail, boolean status)
	{
		
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(emailAddress);
		member.setYearInSchool(yearInSchool);
		member.setMembershipType(membershipType);
		member.setAvailableDiscounts(availableDiscounts);
		member.setIouAmount(iouAmount);
		member.setReceiveEmail(receiveEmail);
		member.setActive(status);
		
		boolean retVal = DatabaseAbstraction.updateMember(member);
		
		return retVal;
	}
	
	// FIXME: this always returns true
	public boolean validateMembershipType(double doubleIOUAmount)
	{
		return true;
	}
	
	/**
	 * The addToIou() method validates and calculates a new IOU value based on:
	 * 
	 * @param currentYear 		the current year of the member
	 * @param membershipType 	the type of member
	 * @param oldAmount 		the current (or old) IOU amount
	 * @param adjustment 		the amount to increase
	 * 
	 * @returns oldAmount+adjustments if no exceptions thrown, return the new IOU amount.
	 * 
	 * @throws Exception (variable, based on conditions set by client and outlined in specs)
	 */
	public double addToIou(int currentYear, int membershipType, double oldAmount, double adjustment) throws Exception
	{
		if(adjustment <= 0)
			throw new Exception("Adjustment must be a positive number");
		else if(membershipType == 0)
			throw new Exception("Must be a working member");
		else if(adjustment + oldAmount > 100)
			throw new Exception("IOU cannot exceed $100");
		else if(currentYear == 7 && oldAmount + adjustment > 50)
			throw new Exception("Second semester seniors cannot exceed an IOU of $50");
		
		return oldAmount + adjustment;
	}
	
	/**
	 * Validates and calculates a new IOU value
	 * 
	 * @param currentYear the current year of the member
	 * @param membershipType the type of member
	 * @param oldAmount the current (or old) IOU amount
	 * @param adjustment the amount to decrease
	 * 
	 * @returns new IOU amount
	 * 
	 * @throws Exception if member is not working or if not enough hours worked
	 */
	public double subtractFromIou(int currentYear, int membershipType, double oldAmount, double adjustment) throws Exception
	{
		if(oldAmount - adjustment < 0)
			throw new Exception("Not enough working hours to apply a discount");
		else if(membershipType == 0)
			throw new Exception("Must be a working member");
		
		return oldAmount - adjustment;
	}
	
	/**
	 * Set matches based on results.
	 * 
	 * @param results 	the list of search results from the database
	 * 
	 */
	public void setLastLookupMemberResults (ArrayList<Member> results)
	{
		//	Clear the last results.. if there are any.
		matches.clear();
		
		for (int i = 0; i < results.size(); i++)
		{
			matches.add(results.get(i));
		}
	}
	
	/**
	 * Get a member object based on index in lookup arraylist.
	 * 
	 * @param index		of the member to select
	 * @return the selected member
	 */
	public Member getMember (int index)
	{
		return matches.get(index);
	}
}
