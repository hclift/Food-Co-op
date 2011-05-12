
/**
 * The ShiftInfo class stores information about work shifts
 * in the Food Co-op.
 * 
 * There are no setters- all data is set in the constructor.
 * 
 * @commentedBy Raanan
 *
 */
public class ShiftInfo {
	private int day;
	private int month;
	private int minWorked;
	private int year;
	
	/**
	 * Constructor for Shift
	 * 
	 * @param month
	 * @param day
	 * @param year
	 * @param minWorked minutes worked
	 */
	public ShiftInfo(int month, int day, int year, int minWorked) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.minWorked = minWorked;
	}
	
	/**
	 * Getter for day
	 * 
	 * @return day
	 */
	public int getShiftDay() {
		return day;
	}

	/**
	 * Getter for month
	 * 
	 * @return month
	 */
	public int getShiftMonth() {
		return month;
	}
	
	/**
	 * Getter for minutes worked
	 * 
	 * @return minWorked
	 */
	public int getMinWorked() {
		return minWorked;
	}

	/**
	 * Getter for year
	 * 
	 * @return year
	 */
	public int getShiftYear() {
		return year;
	}
}
