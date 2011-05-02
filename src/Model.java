/**
 * Model.java:
 * This file contains the model portion of the model/view/controller design.
 **/
import java.util.ArrayList;

/**
 * This class provides the model portion of the MVC framework.
 **/
public class Model {
	// List of members currently signed into the kitchen.
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	// List of members currently signed into the store.
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();

	// Reference to the controller object,
	// so that it can be operated on from within model.
	private Controller controllerReference; // Creates a reference to the
	// controller.

	// Number of milliseconds in a minute.
	private final int MILLISECONDS_PER_MINUTE = 60000;

	// Shift length cutoff for receiving 2 discount units (In Minutes).
	private final int TWO_DISCOUNT_CUTOFF_MINS = 90;

	private int shiftLength;

	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 *          Getter for shift length
	 * @return Shift Length of the member
	 */

	public int getShiftLength()
	{
		return shiftLength;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/27/11
	 * 
	 *          Setter for shift length
	 * @param shiftLengthIn
	 *            Shift Length being passed in
	 */
	public void setShiftLength(final int shiftLengthIn)
	{
		this.shiftLength = shiftLengthIn;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          Setter for the control reference.
	 * @param controllerIn
	 *            Controller to set controllerReference to
	 **/
	public void setControllerReference(final Controller controllerIn)
	{
		this.controllerReference = controllerIn;
	}

	/**
	 * This method provides functionality for looking up members based on name.
	 * It services requests from the view. It does this by calling the
	 * lookupMember method in the database abstraction layer and passing this
	 * return value its own return value.
	 * 
	 * @param firstName
	 *            First name of the member being looked up.
	 * 
	 * @param lastName
	 *            Last name of the member being looked up.
	 * 
	 * @return An ArrayList of members matching the first name and last name
	 *         given.
	 **/
	public ArrayList<Member> lookupMember(final String firstName,
			final String lastName) throws Exception
	{
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}

	/**
	 * This method returns an ArrayList of members currently signed into the
	 * kitchen.
	 * 
	 * @return ArrayList<Member> of members signed into kitchen
	 **/
	public ArrayList<Member> getSignedIntoKitchen()
	{
		return signedIntoKitchen;
	}

	/**
	 * This method returns an ArrayList of members currently signed into the
	 * store.
	 * 
	 * @return ArrayList<Member> of members signed into store
	 **/
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
	 **/
	public ArrayList<Member> signOutOfStore(final int index)
	{
		signedIntoStore.remove(index);
		return signedIntoStore;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/28/11
	 * 
	 *          This method will sign a currently working member out of the
	 *          kitchen. It calculates the shift length of the member, and calls
	 *          reoncileShiftLength in the controller if the shift is shorter
	 *          than 45min or longer than 120min
	 * @param index
	 *            index of the member to be removed from signedIntoKitchen
	 * @return new ArrayList of signedIntoKitchen minus the member just removed.
	 */
	public ArrayList<Member> signOutOfKitchen(final int index)
	{
		// Time (in milliseconds) that the user signed in to the kitchen
		long startTime = signedIntoKitchen.get(index).getLastSignIn();
		// Time (in milliseconds) that the user is signing out (The current
		// time)
		long stopTime = System.currentTimeMillis();

		// Shift length (in minutes), the difference between stopTime
		// and startTime, converted to minutes.
		shiftLength = (int) (stopTime - startTime) / MILLISECONDS_PER_MINUTE;
		int numberOfDiscounts = 0;

		if ((shiftLength < 45) || (shiftLength > 120))
		{
			/*
			 * If shift length is under 45 or over 120 mins, the Reconcile Shift
			 * Length window will AUTOMATICALLY open, while locking the main
			 * window. Inside the Reconcile Shift Length window, there will be a
			 * textbox prompting the operator to input the new shift length (I
			 * still don't know if its going to be in minutes or hours). When
			 * the new shift length is inputted, it will percolate back to the
			 * model via the reconcileShiftLength() function. So yes, the int
			 * gets passed to the controller, then passes to the model, which
			 * will calculate the discounts. Unfortunately, I don't think the
			 * window pop-up for reconcile shift length has been created yet, or
			 * committed. However, I have attached the 4 new java files from the
			 * GUI that we received last class. You may find your answer there.
			 */
			shiftLength = controllerReference.reconcileShiftLength(shiftLength);
		}

		// If the shift length is longer than TWO_DISCOUNT_CUTOFF_MINS,
		// user gets 2 discounts
		if (shiftLength >= TWO_DISCOUNT_CUTOFF_MINS)
		{
			numberOfDiscounts = 2;
		}
		// Otherwise (shift shorter than 1.5hr), user gets 1 discount.
		else
		{
			numberOfDiscounts = 1;
		}
		signedIntoStore.get(index).setAvailableDiscounts(
				signedIntoStore.get(index).getAvailableDiscounts()
						+ numberOfDiscounts);
		DatabaseAbstraction.updateMember(signedIntoStore.get(index));
		signedIntoKitchen.remove(index);
		return signedIntoKitchen;
	}

	/**
	 * @author Ashley Chin
	 * @version 4/14/11
	 * 
	 *          First logs all working members out, then closes the program
	 */
	public void closeProgram()
	{
		signedIntoStore.clear();
		signedIntoKitchen.clear();
		System.exit(0);
	}
}
