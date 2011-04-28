/*
 * Model.java:
 * This file contains the model portion of the model/view/controller design.
 */
import java.sql.Time;
import java.util.ArrayList;

/*
 * This class provides the model portion of the MVC framework.
 */
public class Model
{
	// List of members currently signed into the kitchen.
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	// List of members currently signed into the store.
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();

	// Reference to the controller object,
	// so that it can be operated on from within model.
	private Controller controllerReference; // Creates a reference to the
											// controller.

	//Number of minutes in 1 hour.
	private final int MINUTES_PER_HOUR = 60;
	
	private final int TWO_DISCOUNT_CUTOFF_HRS = 1.5;

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Setter for the control reference.
	 * @param controllerIn
	 *            Controller to set controllerReference to
	 */
	public void setControllerReference(Controller controllerIn)
	{
		this.controllerReference = controllerIn;
	}

	/*
	 * This method provides functionality for looking up members based on name.
	 * It services requests from the view. It does this by calling the
	 * lookupMember method in the database abstraction layer and passing this
	 * return value its own return value.
	 * 
	 * @param firstName First name of the member being looked up.
	 * 
	 * @param lastName Last name of the member being looked up.
	 * 
	 * @return An ArrayList of members matching the first name and last name
	 * given.
	 */
	public ArrayList<Member> lookupMember(final String firstName, final String lastName)
			throws Exception 
	{
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}

	/*
	 * This method returns an ArrayList of members currently signed into the
	 * kitchen.
	 * 
	 * @return ArrayList<Member> of members signed into kitchen
	 */
	public ArrayList<Member> getSignedIntoKitchen()
	{
		return signedIntoKitchen;
	}

	/*
	 * This method returns an ArrayList of members currently signed into the
	 * store.
	 * 
	 * @return ArrayList<Member> of members signed into store
	 */
	public ArrayList<Member> getSignedIntoStore()
	{
		return signedIntoStore;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          This method will sign a currently working member out of store.
	 * @param index
	 *            ArrayList index of member to be signed out.
	 * @return new ArrayList with specified member removed.
	 */
	public ArrayList<Member> signOutOfStore(final int index)
	{
		signedIntoStore.remove(index);
		return signedIntoStore;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          This method will sign a currently working member out of the
	 *          kitchen. It calculates the shift length of the member, and calls
	 *          reoncileShiftLength in the controller if the shift is shorter
	 *          than 45min or longer than 120min
	 * @param ArrayList
	 *            index of the member to be removed from signedIntoKitchen
	 * @return new ArrayList of signedIntoKitchen minus the member just removed.
	 */
	public ArrayList<Member> signOutOfKitchen(final int index) {
		long startTime = signedIntoKitchen.get(index).getLastSignIn();
		long stopTime = System.currentTimeMillis();
		int shiftLength = (int) (stopTime - startTime) * 60000;

		if ((shiftLength < 45) || (shiftLength > 120))
		{
			controllerReference.reconcileShiftLength(shiftLength);
		}
		else
		{
			int numberOfDiscounts = 0;
			// If the shift length is longer than 1.5hr, user gets 2 discounts
			if (shiftLength >= (TWO_DISCOUNT_CUTOFF_HRS * MINUTES_PER_HOUR))
			{
				numberOfDiscounts = 2;
			}
			// Otherwise (shift shorter than 1.5hr), user gets 1 discount.
			else
			{
				numberOfDiscounts = 1;
			}

			DatabaseAbstraction.addDiscounts(signedIntoStore.get(index),
					numberOfDiscounts);
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
	 * First logs all working members out, then closes the program
	 */
	public void closeProgram()
	{
		signedIntoStore.clear();
		signedIntoKitchen.clear();
		System.exit(0);
	}
}