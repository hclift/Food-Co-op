import java.util.ArrayList;
import java.util.Date;

public class Member {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date lastSignupDate;
	private int availableDiscounts;
	private int membershipLength;
	private int membershipType;
	private int yearInSchool;
	private double IouAmount;
	private boolean receiveEmail;
	private boolean isActive;
	private static final String[] YEAR_IN_SCHOOL = {"Freshman 1", "Freshman 2", "Sophmore 1", "Sophmore 2", "Junior 1", "Junior 2", "Senior 1", "Senior 2", "Graduate", "Faculty"};
	private static final String[] MEMBERSHIP_LENGTHS = { "Semester", "Year" };
	private static final String[] MEMBERSHIP_TYPES = {
		"Ordinary",
		"Working",
		"Core",
		"Coordinator"
	};
	
	public Member(int id, String firstName, String lastName, String email,
			Date last_signup_date, int membership_length, int membership_type,
			int year_in_school, int available_Discounts, double Iou_Amount,boolean recieve_email, boolean is_active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.lastSignupDate = last_signup_date;
		this.membershipLength = membership_length;
		this.membershipType = membership_type;
		this.yearInSchool = year_in_school;
		this.receiveEmail = recieve_email;
		this.availableDiscounts = available_Discounts;
		this.IouAmount = Iou_Amount;
		this.isActive = is_active;
	}

	public String getFirstName() {
		return firstName;
	}
	/*
	 * TODO: Add member
	 */
	public void addMember() {
		
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}
	
	public String getEmailAddress()
	{
		return email;
	}
	
	public int getMembershipType() {
		return membershipType;
	}
	
	public int getMembershipLength() {
		return membershipLength;
	}
	public int getYearsInSchool(){
		return yearInSchool;
	}
	public double getIouAmount()
	{
		return IouAmount;
	}
	public int getAvailableDiscounts()
	{
		return availableDiscounts;
	}
	public void setAvailableDiscounts(int available_discounts)
	{
		availableDiscounts = available_discounts;
	}
	public void setIouAmount(double iou_amount)
	{
		IouAmount = iou_amount;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public boolean getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public boolean getActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getYearInSchool() {
		return yearInSchool;
	}

	public void setYearInSchool(int yearInSchool) {
		this.yearInSchool = yearInSchool;
	}

	public void setMembershipLength(int membershipLength) {
		this.membershipLength = membershipLength;
	}

	public void setMembershipType(int membershipType) {
		this.membershipType = membershipType;
	}
	
}
