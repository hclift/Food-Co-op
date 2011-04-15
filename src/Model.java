import java.sql.Time;
import java.util.ArrayList;

public class Model 
{
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();
	
	private Controller controllerReference; // Creates a reference to the controller.
	
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
	
	public ArrayList<Member> signIntoKitchen(int index) throws Exception
	{
		if(matches.get(index).canSignIn())
		{
			for(int i = 0; i < signedIntoKitchen.size(); i++)
			{
				if(signedIntoKitchen.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the kitchen");
				}
			}
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the store");
				}
				long time;
			matches.get(index).setLastSignIn(new Time(time));
			signedIntoKitchen.add(matches.get(index));
			}
		}
		else
		{
			throw new Exception("This member cannot be signed in");
		}
		
		return signedIntoKitchen;
	}
	
	public ArrayList<Member> signIntoStore(int index) throws Exception
	{
		if(matches.get(index).canSignIn())
		{
			for(int i = 0; i < signedIntoStore.size(); i++)
			{
				if(signedIntoStore.get(i).equals(matches.get(index)))
				{
					throw new Exception("This member is already signed into the kitchen");
				}
			}
			for(int i = 0; i < signedIntoKitchen.size(); i++)
			{
				if(signedIntoKitchen.get(i).equals(matches.get(index)))
				{
					throw new Exception();
				}
				long time;
			matches.get(index).setLastSignIn(new Time(time));
			throw new Exception("This member is already signed into the store");
			}
		}
		else
		{
			throw new Exception("This member cannot be signed in");
		}
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
	 * @version 4/14/11
	 * 
	 * Signs working member out of kitchen.
	 * @param designated index of member in array list
	 * @return modified array list of currently working members
	 */
	public ArrayList<Member> signOutOfKitchen(int index)
	{
		long startTime = signedIntoKitchen.get(index).getLastSignIn();
		long stopTime = System.currentTimeMillis();
		int shiftLength = (int)(stopTime - startTime)*60000;
       
        if ((shiftLength < 45) || (shiftLength > 120))
        {
            controllerReference.reconcileShiftLength(shiftLength);
        }
        else
        {
            int numberOfDiscounts;
            if (shiftLength>(1.5*60))
            {
            	numberOfDiscounts = 2; 
            }
            else
            {
            	numberOfDiscounts = 1;
            }
            	
        	DatabaseAbstraction.addDiscounts(signedIntoStore.get(index),numberOfDiscounts);   
        	/*----------------------------------------------------------------
             * addDiscounts() incomplete.  MUST. FIX.
             *
             *-----------------------------------------------------------------
             */
        	
        }
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