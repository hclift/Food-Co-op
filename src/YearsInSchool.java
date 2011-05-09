/**
 * 
 */

/**
 * @author jonathan
 */
public enum YearsInSchool {
	FRESHMAN1(0, "Freshman 1"), 
	FRESHMAN2(1, "Freshman 2"), 
	SOPHOMORE1(2, "Sophomore 1"), 
	SOPHOMORE2(3, "Sophomore 2"), 
	JUNIOR1(4, "Junior 1"), 
	JUNIOR2(5, "Junior 2"), 
	SENIOR1(6, "Senior 1"), 
	SENIOR2(7, "Senior 2"), 
	GRADUATE(8, "Graduate"), 
	FACULTY(9, "Faculty");
	
	private int intVal;
	private String strVal;
	
	YearsInSchool(int intValIn, String strValIn){
		intVal = intValIn;
		strVal = strValIn;		
	}
	
	public int getIntVal() {
		return intVal;
	}

	public String getStrVal() {
		return strVal;
	}

}
