public class ShiftInfo {
	private int day;
	private int month;
	private int shiftLength;
	private int year;
	
	public ShiftInfo(int month, int day, int year, int shiftLength) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.shiftLength = shiftLength;
	}

	public int getShiftDay() {
		return day;
	}

	public int getShiftMonth() {
		return month;
	}

	public int getshiftLength() {
		return shiftLength;
	}

	public int getShiftYear() {
		return year;
	}
}