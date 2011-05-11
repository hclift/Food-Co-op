public class ShiftInfo {
	private int day;
	private int month;
	private int minWorked;
	private int year;
	
	public ShiftInfo(int month, int day, int year, int minWorked) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.minWorked = minWorked;
	}

	public int getShiftDay() {
		return day;
	}

	public int getShiftMonth() {
		return month;
	}

	public int getMinWorked() {
		return minWorked;
	}

	public int getShiftYear() {
		return year;
	}
}