
public enum MembershipTypes {
	ORDINARY(0,"Ordinary"),
	WORKING(1,"Working"),
	CORE(2,"Core"),
	COORDINATOR(3,"Coordinator");
	
	private int intVal;
	private String strVal;
	
	MembershipTypes(int intValIn, String strValIn){
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
