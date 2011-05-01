/**
 * Member.java
 * 
 * The Member class stores information about a member including their id, name,
 * email address, latest sign-up date, number of available discounts, 
 * membership type and length, year in school, and IOU amount owed.  It also 
 * keeps track if the member is active.
 */

import java.util.ArrayList;
import java.util.Date;

public class Member 
{
	// unique member id number 
	private int id;
	
	// member's first name
	private String firstName;
	
	// member's last name
	private String lastName;
	
	// member's email address
	private String email;
	
	// the most recent date the member signed up
	private Date lastSignupDate;
	
	// the number of available discounts for the member
	private int availableDiscounts;
	
	// the length of the member's membership
	private int membershipLength;
	
	// the type of the member's membership
	private int membershipType;
	
	// member's year in school
	// also used for grad. students and faculty members
	private int yearInSchool;
	
	// the amount of money the member owes
	private double IouAmount;

	//TODO: No longer need receiveEmail?
	// Indicates if the member wants to receive emails
	private boolean receiveEmail;
	
	// Indicates whether the member is active or expired
	private boolean isActive;
	
	private static final String[] YEAR_IN_SCHOOL = 
	{
		"Freshman 1", "Freshman 2", 
		"Sophmore 1", "Sophmore 2",
		"Junior 1", "Junior 2",
		"Senior 1", "Senior 2",
		"Graduate", "Faculty"
	};
	
	private static final String[] MEMBERSHIP_LENGTHS = { "Semester", "Year" };
	
	private static final String[] MEMBERSHIP_TYPES =
	{
		"Ordinary",
		"Working",
		"Core",
		"Coordinator"
	};
	
	/**
	 * Explicit Constructor for a Member object
	 * 
	 * Creates a Member with the following information 
	 * 
	 * @param id					member's id
	 * @param firstName				member's first name
	 * @param lastName				member's last name
	 * @param email					member's e-mail address
	 * @param lastSignupDate		member's most recent sign-up date
	 * @param membershipLength		member's membership length
	 * @param membershipType		member's membership type
	 * @param yearInSchool			member's year in school
	 * @param availableDiscounts	member's available discounts
	 * @param iouAmount				member's IOU amount
	 * @param receiveEmail			member's receive email status
	 * @param isActive				member's active status
	 */
	public Member(int id, String firstName, String lastName, String email,
			Date lastSignupDate, int membershipLength, int membershipType,
			int yearInSchool, int availableDiscounts, double iouAmount,
			boolean receiveEmail, boolean isActive)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.lastSignupDate = lastSignupDate;
		this.membershipLength = membershipLength;
		this.membershipType = membershipType;
		this.yearInSchool = yearInSchool;
		this.receiveEmail = receiveEmail;
		this.availableDiscounts = availableDiscounts;
		this.IouAmount = iouAmount;
		this.isActive = isActive;
	}

	/**
	 * Returns the member's first name
	 * 
	 * @return	first name of the member
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Changes the member's first name
	 * 
	 * @param firstName	new first name of the member
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Returns the member's last name
	 * 
	 * @return	last name of the member
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Changes the member's last name
	 * 
	 * @param lastName	new last name of the member
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Returns the member's id
	 * 
	 * @return	id of the member
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Returns the memeber's e-mail address
	 * 
	 * @return	e-mail address of the member
	 */
	public String getEmailAddress()
	{
		return email;
	}
	
	/**
	 * Returns the member's membership type
	 * 
	 * @return	membership type of the member
	 */
	public int getMembershipType()
	{
		return membershipType;
	}
	
	/**
	 * Returns the member's membership length
	 * 
	 * @return	membership length of the member
	 */
	public int getMembershipLength()
	{
		return membershipLength;
	}
	
	/**
	 * Return the member's year in school
	 * 
	 * @return	year in school of the member
	 */
	public int getYearsInSchool()
	{
		return yearInSchool;
	}
	
	/**
	 * Returns the member's IOU amount
	 * 
	 * @return	amount owed by the member
	 */
	public double getIouAmount()
	{
		return IouAmount;
	}
	
	/**
	 * Returns the member's number of available discounts
	 * 
	 * @return	number of discounts available for the member
	 */
	public int getAvailableDiscounts()
	{
		return availableDiscounts;
	}
	
	/**
	 * Changes the member's number of available discounts
	 * 
	 * @param availableDiscounts	new available discounts for the member
	 */
	public void setAvailableDiscounts(int availableDiscounts)
	{
		this.availableDiscounts = availableDiscounts;
	}
	
	/**
	 * Changes the member's IOU amount
	 * 
	 * @param iouAmount	amount owed by the member
	 */
	public void setIouAmount(double iouAmount)
	{
		IouAmount = iouAmount;
	}
	
	/**
	 * Changes the e-mail address of member
	 * 
	 * @param email	new e-mail address of member
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	//TODO Receive Email
	/**
	 * Returns the member's receive e-mail status
	 * (true for yes, false for no)
	 * 
	 * @return	receive e-mail status of the member
	 */
	public boolean getReceiveEmail()
	{
		return receiveEmail;
	}

	//TODO Receive Email
	/**
	 * Changes the member's receive e-mail status
	 * 
	 * @param receiveEmail	new receive e-mail status of the member
	 */
	public void setReceiveEmail(boolean receiveEmail)
	{
		this.receiveEmail = receiveEmail;
	}

	/**
	 * Returns the member's active status 
	 * (true for active, false for inactive)
	 * 
	 * @return	active status of member
	 */
	public boolean getActive()
	{
		return isActive;
	}

	/**
	 * Changes the member's active status
	 * (true for active, false for inactive)
	 * 
	 * @param isActive	new active status of member
	 */
	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	/**
	 * Returns the member's year in school
	 * 
	 * @return	year in school of the member
	 */
	public int getYearInSchool()
	{
		return yearInSchool;
	}

	/**
	 * Changes the member's year in school
	 * 
	 * @param yearInSchool	new year in school of the member
	 */
	public void setYearInSchool(int yearInSchool)
	{
		this.yearInSchool = yearInSchool;
	}

	/**
	 * Changes the member's membership length
	 * 
	 * @param membershipLength	new membership length for the member
	 */
	public void setMembershipLength(int membershipLength)
	{
		this.membershipLength = membershipLength;
	}

	/**
	 * Changes the member's membership type
	 * 
	 * @param membershipType	 new membership type for the member
	 */
	public void setMembershipType(int membershipType)
	{
		this.membershipType = membershipType;
	}
}