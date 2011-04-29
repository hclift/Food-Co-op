import java.sql.Time;
import java.util.ArrayList;

public class Model 
{
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();
	private Controller controllerReference; // Creates a reference to the controller.
	private int shiftLength;
	
	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 * Getter
	 */
			
	public int getShiftLength()
	{
		return shiftLength;
	}
	
	
	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 * Setter
	 * @param shiftLengthIn
	 */
	public void setShiftLength(int shiftLengthIn)
	{
		this.shiftLength = shiftLengthIn;
	}
	
	
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 * Setter for the control reference.
	 * @param controller
	 */
	public void setControllerReference(Controller controllerIn)
	{
		this.controllerReference = controllerIn;
	}
	
	public ArrayList<Member> lookupMember(String firstName, 
			String lastName) throws Exception
	{
		//throw new Exception();
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}

	public ArrayList<Member> getSignedIntoKitchen() {
		return signedIntoKitchen;
	}

	public ArrayList<Member> getSignedIntoStore() {
		return signedIntoStore;
	}
	
	
	
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 * Signs working member out of store.
	 * @param designated index of member in array list
	 * @return modified array list of currently working members
	 */
	public ArrayList<Member> signOutOfStore(int index)
	{
		signedIntoStore.remove(index);
		return signedIntoStore;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/28/11
	 * 
	 * Signs working member out of kitchen.
	 * @param designated index of member in array list
	 * @return modified array list of currently working members
	 */
	public ArrayList<Member> signOutOfKitchen(int index)
	{
		long startTime = signedIntoKitchen.get(index).getLastSignIn();
		long stopTime = System.currentTimeMillis();
		shiftLength = (int)(stopTime - startTime)/60000;
	    int numberOfDiscounts;
       
        if ((shiftLength < 45) || (shiftLength > 120))
        {	
          	shiftLength = controllerReference.reconcileShiftLength(shiftLength);
        }
        if (shiftLength >= 90)	
        {
        	numberOfDiscounts = 2; 
        }
        else
        {
        	numberOfDiscounts = 1;
        }
        signedIntoStore.get(index).setAvailableDiscounts(signedIntoStore.get(index).getAvailableDiscounts()+numberOfDiscounts);
        DatabaseAbstraction.updateMember(signedIntoStore.get(index));   
        signedIntoKitchen.remove(index);
        return signedIntoKitchen;
    }
	
	  
	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 * Closes the program and logs all working members out.
	 */
    public void closeProgram()
    {
        signedIntoStore.clear();
        signedIntoKitchen.clear();
        System.exit(0);
    }
}