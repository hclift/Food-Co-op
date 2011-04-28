import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;



public class Model 
{
	
	private ArrayList<Member> signedIntoKitchen = new ArrayList<Member>();
	private ArrayList<Member> signedIntoStore = new ArrayList<Member>();

	public enum yearInSchool {Freshman1, Freshman, Sophmore1, Sophmore2, Junior1, Junior2, Senior1, Senior2, Graduate, Faculty}
	private ArrayList<Member> matches = new ArrayList<Member>();
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
			}
			long time = 0;
			matches.get(index).setLastSignIn(new Time(time));
			signedIntoKitchen.add(matches.get(index));
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
			}
			long time = 0;
			matches.get(index).setLastSignIn(new Time(time));
			signedIntoStore.add(matches.get(index));
			
		}
		else
		{
			throw new Exception("This member cannot be signed in");
		}
		return signedIntoStore;
	}
	
	public boolean updateMember(Member member, String firstName, String lastName, String emailAddress, int yearInSchool, int membershipType, 
			Date expirationDate, int availableDiscounts, double iouAmount,boolean receiveEmail, boolean status)
	{
		
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(emailAddress);
		member.setYearInSchool(yearInSchool);
		member.setMembershipType(membershipType);
		member.setAvailableDiscounts(availableDiscounts);
		member.setIouAmount(iouAmount);
		member.setReceiveEmail(receiveEmail);
		member.setActive(status);
		
		boolean retVal = DatabaseAbstraction.updateMember(member);
		
		return retVal;
	}
	
	public boolean validateMembershipType(double doubleIOUAmount)
	{
		return true;
	}
	public double addToIou(int currentYear, int membershipType, double oldAmount, double adjustment) throws Exception
	{
		if(adjustment <= 0)
			throw new Exception("Adjustment must be a positive number");
		else if(membershipType == 0)
			throw new Exception("Must be a working member");
		else if(adjustment + oldAmount > 100)
			throw new Exception("IOU cannot excced $100");
		else if(currentYear == 7 && oldAmount + adjustment > 50)
			throw new Exception("Second semester seniors cannot exceed an IOU of $50");
		
		return oldAmount + adjustment;
	}
	public double subtractFromIou(int currentYear, int membershipType, double oldAmount, double adjustment) throws Exception
	{
		if(oldAmount - adjustment < 0)
			throw new Exception("Not enough working hours to apply a discount");
		else if(membershipType == 0)
			throw new Exception("Must be a working member");
		
		return oldAmount - adjustment;
	}
	
	public void setLastLookupMemberResults (ArrayList<Member> results)
	{
		//	Clear the last results.. if there are any.
		matches.clear();
		
		for (int i = 0; i < results.size(); i++)
		{
			matches.add(results.get(i));
		}
	}
	
	public Member getMember (int index)
	{
		return matches.get(index);
	}
}
