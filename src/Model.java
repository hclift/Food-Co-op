import java.util.ArrayList;

public class Model 
{
	private ArrayList<Member> lastLookupMemberResults = new ArrayList<Member>();
	
	public ArrayList<Member> lookupMember(String firstName, 
			String lastName) throws Exception
	{
		//throw new Exception();
		
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}
	
	public void setLastLookupMemberResults (ArrayList<Member> results)
	{
		//	Clear the last results.. if there are any.
		lastLookupMemberResults.clear();
		
		for (int i = 0; i < results.size(); i++)
		{
			lastLookupMemberResults.add(results.get(i));
		}
	}
	
	public Member getMember (int index)
	{
		return lastLookupMemberResults.get(index);
	}
}