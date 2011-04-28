import java.util.Date;

public class Member {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date lastSignupDate;
	private int membershipLength;
	private int membershipType;
	private int yearInSchool;
	private boolean isActive;
	private static final String[] MEMBERSHIP_LENGTHS = { "Semester", "Year" };
	private static final String[] MEMBERSHIP_TYPES = {
		"Ordinary",
		"Working",
		"Core",
		"Coordinator"
	};

	public Member(int id, String firstName, String lastName, String email,
			Date last_signup_date, int membership_length, int membership_type,
			int year_in_school, boolean is_active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setLastSignupDate(last_signup_date);
		this.membershipLength = membership_length;
		this.membershipType = membership_type;
		this.yearInSchool = year_in_school;
		this.setActive(is_active);
	}

	public String getFirstName() {
		return firstName;
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

	public String getMembershipType() {
		return MEMBERSHIP_TYPES[membershipType];
	}

	public String getMembershipLength() {
		return MEMBERSHIP_LENGTHS[membershipLength];
	}


	public int getMembershipLengthInt() {
		String memLength = MEMBERSHIP_LENGTHS[membershipLength];
		int status = 0;
		if(memLength.equals("Semester")){
			status = 0;
		}else if(memLength.equals("Year")){
			status = 1;
		}

		return status;
	}


	public int getYearInSchool() {
		return yearInSchool;
	}

	public void setYearInSchool(int yearInSchool) {
		this.yearInSchool = yearInSchool;
	}



	public int getMembershipTypeInt() {
		/*
		"Ordinary",
		"Working",
		"Core",
		"Coordinator"
		 */
		String memType = MEMBERSHIP_LENGTHS[membershipLength];
		int status = 0;

		if(memType.equals("Ordinary")){
			status = 0;
		}else if(memType.equals("Working")){
			status = 1;
		}else if(memType.equals("Core")){
			status = 2;
		}else if(memType.equals("Coordinator")){
			status = 3;
		}

		return status;
	}

	/**
	 * @param lastSignupDate the lastSignupDate to set
	 */
	public void setLastSignupDate(Date lastSignupDate) {
		this.lastSignupDate = lastSignupDate;
	}

	/**
	 * @return the lastSignupDate
	 */
	public Date getLastSignupDate() {
		return lastSignupDate;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}


}