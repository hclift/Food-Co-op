import java.util.ArrayList;

public class Model 
{
	private ArrayList<Member> results = new ArrayList<Member>();
	
	public ArrayList<Member> lookupMember(String firstName, 
			String lastName) throws Exception
	{
		//throw new Exception();
		
		return DatabaseAbstraction.lookupMember(firstName, lastName);
	}
	
	public void setLastLookupMemberResults (ArrayList<Member> results2)
	{
		//	Clear the last results.. if there are any.
		results.clear();
		
		for (int i = 0; i < results2.size(); i++)
		{
			results.add(results2.get(i));
		}
	}
	
	public Member getMember (int index)
	{
		return results.get(index);
	}
}