import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Model.java:
 * This file contains the model portion of the model/view/controller design.
 **/
public class Model 
{

	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();
	private ArrayList<Member> matches = new ArrayList<Member>();

	



	// List of members currently signed into the kitchen.
	// List of members currently signed into the store.

	// Reference to the controller object,
	// so that it can be operated on from within model.
	private Controller controllerReference; // Creates a reference to the
	// controller.

	// Number of milliseconds in a minute.
	private final long MILLISECONDS_PER_MINUTE = 60000;

	// Shift length cutoff for receiving 2 discount units (In Minutes).
	private final long TWO_DISCOUNT_CUTOFF_MINS = 90;

	private long shiftLength;

	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 *          Getter for shift length
	 * @return Shift Length of the member
	 */
	public long getShiftLength() {
		return shiftLength;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 *          Setter for shift length
	 * @param shiftLengthIn
	 *            Shift Length being passed in
	 */
	public void setShiftLength(long shiftLengthIn) {
		this.shiftLength = shiftLengthIn;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Setter for the control reference.
	 * @param controllerIn
	 *            Controller to set controllerReference to
	 **/
	public void setControllerReference(final Controller controllerIn)

	{
		this.controllerReference = controllerIn;
	}

	/**
	 * This method provides functionality for looking up members based on name.
	 * It services requests from the view. It does this by calling the
	 * lookupMember method in the database abstraction layer and passing this
	 * return value its own return value.
	 * 
	 * @param firstName
	 *            First name of the member being looked up.
	 * 
	 * @param lastName
	 *            Last name of the member being looked up.
	 * 
	 * @return An ArrayList of members matching the first name and last name
	 *         given.
	 **/
	public ArrayList<Member> lookupMember(final String firstName, final String lastName) throws Exception
	{
		//throw new Exception();  // Is this suppose to do anything?
		
		matches = DatabaseAbstraction.lookupMember(firstName, lastName);
		return matches;
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
		Member mem = new Member();
		
		mem.setActive(true);
		mem.setYearInSchool(year_in_school);
		mem.setMembershipType(membership_type);
		mem.setMembershipLength(membership_length);
		mem.setEmail(email_address);
		mem.setLastName(last_name);
		mem.setFirstName(first_name);
		
		
		
		DatabaseAbstraction.addMember(mem);
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
					throw new Exception("The member is already signed into the kitchen.");
				}
			}
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("The member is already signed into the store.");
				}
			}
			long time = System.currentTimeMillis();
			matches.get(index).setLastSignIn(time);
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
					throw new Exception("This member is already signed in.");
				}
			}
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed in.");
				}
			}
			long time = System.currentTimeMillis();
			matches.get(index).setLastSignIn(time);
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
			Date expirationDate, int availableDiscounts, double iouAmount, boolean status)
	{
		
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(emailAddress);
		member.setYearInSchool(yearInSchool);
		member.setMembershipType(membershipType);
		member.setAvailableDiscounts(availableDiscounts);
		member.setIouAmount(iouAmount);
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
		else if(membershipType < 2)
			throw new Exception("Must be a core member or coordinator.");
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
			throw new Exception("Can't subtract more than what is owed");
		else if(membershipType == 0)
			throw new Exception("Must be a working member");
		
		return oldAmount - adjustment;
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

// TODO:	caused error because there is no getLastSignIn() method
	 
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          This method will sign a currently working member out of store.
	 * @param index
	 *            ArrayList index of member to be signed out.
	 * @return new ArrayList with specified member removed.
	 **/
	public ArrayList<Member> signOutOfStore(final int index)
	{
		// Time (in milliseconds) that the user signed in to the kitchen
		long startTime = signedIntoStore.get(index).getLastSignIn();
		// Time (in milliseconds) that the user is signing out (The current
		// time)
		long stopTime = System.currentTimeMillis();

		// Shift length (in minutes), the difference between stopTime
		// and startTime, converted to minutes.
		long lengthOfShift = (stopTime - startTime) / MILLISECONDS_PER_MINUTE;

		if ((lengthOfShift < 45) || (lengthOfShift > 120))
		{
			lengthOfShift = controllerReference.reconcileShiftLength(lengthOfShift);
		}

		DatabaseAbstraction.updateMember(signedIntoStore.get(index));
		signedIntoStore.remove(index);
		
		return signedIntoStore;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/28/11
	 * 
	 *          This method will sign a currently working member out of the
	 *          kitchen. It calculates the shift length of the member, and calls
	 *          reoncileShiftLength in the controller if the shift is shorter
	 *          than 45min or longer than 120min
	 * @param index
	 *            index of the member to be removed from signedIntoKitchen
	 * @return new ArrayList of signedIntoKitchen minus the member just removed.
	 */
	public ArrayList<Member> signOutOfKitchen(final int index)
	{
		// Time (in milliseconds) that the user signed in to the kitchen
		long startTime = signedIntoKitchen.get(index).getLastSignIn();
		// Time (in milliseconds) that the user is signing out (The current
		// time)
		long stopTime = System.currentTimeMillis();

		// Shift length (in minutes), the difference between stopTime
		// and startTime, converted to minutes.
		long lengthOfShift = (stopTime - startTime) / MILLISECONDS_PER_MINUTE;

		int numberOfDiscounts = 0;

		if ((lengthOfShift < 45) || (lengthOfShift > 120))
		{
			lengthOfShift = controllerReference.reconcileShiftLength(lengthOfShift);
		}

		// TODO: ask
		numberOfDiscounts = ((int)lengthOfShift) / 60;
		
		signedIntoKitchen.get(index).setAvailableDiscounts(
				signedIntoKitchen.get(index).getAvailableDiscounts()
						+ numberOfDiscounts);
		
		DatabaseAbstraction.updateMember(signedIntoKitchen.get(index));
		
		signedIntoKitchen.remove(index);
		return signedIntoKitchen;
	}
	
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          First logs all working members out, then closes the program
	 */
	public void closeProgram()
	{
		signedIntoStore.clear();
		signedIntoKitchen.clear();
		System.exit(0);
	}
	
	public ArrayList<YearsInSchool> getYearsInSchool(){
		ArrayList<YearsInSchool> ret = new ArrayList<YearsInSchool>();
		for(YearsInSchool x: YearsInSchool.values()){
			ret.add(x);
		}
		return ret;
	}
	
	public ArrayList<MembershipTypes> getMembershipTypes(){
		ArrayList<MembershipTypes> ret = new ArrayList<MembershipTypes>();
		for(MembershipTypes x: MembershipTypes.values()){
			ret.add(x);
		}
		return ret;
	}
	
	public boolean isSignedIn(Member m)
	{
		return (signedIntoStore.contains(m) || signedIntoKitchen.contains(m));
	}
}

