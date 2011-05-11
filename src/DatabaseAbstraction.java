import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DatabaseAbstraction
{
	/**
	* Connects to the hardcoded database file "database.sqlite3".
	* @return connection 	The connection to the database
	* @return null   		If no connection can be made.
	*/
	private static Connection connectToDatabase()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection connection = 
				DriverManager.getConnection("jdbc:sqlite:db/database.sqlite3");
			connection.setAutoCommit(false);
			connection.setAutoCommit(true);
			return connection;
		} catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

	/**
	* Connects to the queries the database and then queries it given the input
	* parameters
	* @param first_name		First name of the member to look for
	* @param last_name		Last name of the member to look for
<<<<<<< HEAD
	* @return memberList	An arraylist of member objects which match the 
=======
	* @return memberList	An ArrayList of member objects which match the 
>>>>>>> branch 'refs/heads/dev' of ssh://git@github.com/team-williams/Food-Co-op.git
	*						parameters
	*/
	public static ArrayList<Member> lookupMember(String first_name, String last_name)
	{
		if (first_name.equals(""))
			first_name = "%";
		
		if (last_name.equals(""))
			last_name = "%";
		
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		try
		{
			Connection connection = connectToDatabase();
			//ResultSet rs = stat.executeQuery(
			//		"SELECT * FROM members, member_discounts, member_iou WHERE first_name='" + first_name +
			//		"' AND last_name='" + last_name + "' AND members.id = member_discounts.member_id AND members.id = member_iou.member_id;"
			//);
			PreparedStatement ps = connection.prepareStatement(
					"SELECT members.*	, member_discounts.discounts, member_iou.iou_amount " +
					"FROM members " +
					"LEFT OUTER JOIN member_discounts " +
					"LEFT OUTER JOIN member_iou " +
					"ON (member_discounts.member_id = members.id AND member_iou.member_id = members.id)" +
					" WHERE first_name LIKE ?" +
					" AND last_name LIKE ?;"
			);
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			ResultSet rs = ps.executeQuery();
			
			long s;
			Date t2;
			while (rs.next())
			{
				s = rs.getLong("last_signup_date");
				t2 = new Date(s);
				
				Member m = new Member(
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("email_address"),
					t2,
					rs.getInt("membership_length"),
					rs.getInt("membership_type"),
					rs.getInt("year_in_school"), 
					rs.getInt("discounts"),
					rs.getDouble("iou_amount"),
					(rs.getInt("is_active") != 0)
				);
				memberList.add(m);
			}
			
			ps.close();
			connection.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return memberList;
	}
	
//	getShifts(Member member, int desiredMonth, int desiredYear){
//		DB query (Select day, minWorked From Shifts where member.id, desiredMonth, and 
//		desiredYear match)
//		For each shifts that match{
//		Extract day, minWorked from DB
//		Create ShiftInfo object with desiredMonth, day, desiredYear, minWorked
//		Place ShiftInfo object in arraylist
//		{
//		return arraylist
	public static ArrayList<ShiftInfo> getShifts(Member member, int desiredMonth,int desiredYear){
	ArrayList<ShiftInfo> arrayList = new ArrayList<ShiftInfo>();
	
	try{
		Connection connection = connectToDatabase();
		PreparedStatement ps = connection.prepareStatement(
				"SELECT day, minWorked" +
				"FROM shifts" +
				" WHERE id LIKE  ?" +
				" AND desiredMonth  LIKE ?" +
				" AND desiredYear LIKE ?;"
		);

		ps.setInt(1, member.getId());
		ps.setInt(2, desiredMonth);
		ps.setInt(3, desiredYear);
		
		ResultSet rs = ps.executeQuery();
		
		
		while (rs.next())
		{
			
			ShiftInfo shiftInfo = new ShiftInfo(
				rs.getInt("id"),
				rs.getInt("day"),
				rs.getInt("year"),
				rs.getInt("minWorked")
			);
			arrayList.add(shiftInfo);
		}
		
		ps.close();
		connection.close();
	} 
	catch (Exception e)
	{
		e.printStackTrace();
		System.exit(1);
	}
	
	return arrayList;
}


	
	
	
	
	/**
	* Updates a member in the database. Uses a PreparedStatement.
	* @param m updated Member object.
	*/
	public static boolean updateMember(Member m)
	{
	try
	{
	Connection connection = connectToDatabase();
	PreparedStatement ps = connection.prepareStatement(
		"UPDATE members SET " +
		"first_name = ?, " +
		"last_name = ?, " +
		"email_address = ?, " +
		"membership_length = ?, " +
		"membership_type = ?, " +
		"year_in_school = ?, " +
		"is_active = ? " +
		"WHERE id = ?"
	);
	ps.setString(1, m.getFirstName());
	ps.setString(2, m.getLastName());
	ps.setString(3, m.getEmailAddress());
	ps.setInt(4, m.getMembershipLength());
	ps.setInt(5, m.getMembershipType());
	ps.setInt(6, m.getYearsInSchool());
	ps.setBoolean(7, m.getActive());
	ps.setInt(8, m.getId());
	ps.executeUpdate();
	ps.close();
	
	//	Query if a member exists in member_iou table
	PreparedStatement ps_iouCheck = connection.prepareStatement(
		"SELECT * FROM member_iou WHERE member_id = ?"
	);
	ps_iouCheck.setInt(1, m.getId());
	ResultSet rs_iouCheck = ps_iouCheck.executeQuery();
	
	//	If they exist, do an update, else do an insert
	if (rs_iouCheck.next())
	{
		PreparedStatement ps_iou = connection.prepareStatement(
				"UPDATE member_iou SET " +
				"iou_amount = ?" +
				"WHERE member_id = ? "
		);
		ps_iou.setDouble(1, ( m.getIouAmount()) );
		ps_iou.setInt(2, m.getId());
		ps_iou.executeUpdate();

		ps_iou.close();
	}
	else
	{
		PreparedStatement ps_iou = connection.prepareStatement(
				"INSERT INTO member_iou VALUES (?, ?)"
			);
		ps_iou.setInt(1, m.getId());
		ps_iou.setDouble(2, m.getIouAmount());
		ps_iou.executeUpdate();
		
		ps_iou.close();
	}
	
	//	Query to see if a member exists in the member_discounts table
	PreparedStatement ps_discountCheck = connection.prepareStatement(
		"SELECT * FROM member_discounts WHERE member_id = ?"
	);
	ps_discountCheck.setInt(1, m.getId());
	ResultSet rs_discountCheck = ps_discountCheck.executeQuery();
	
	//	If they exist, do an update, else do an insert
	if (rs_discountCheck.next())
	{
		PreparedStatement ps_disc = connection.prepareStatement(
				"UPDATE member_discounts SET " +
				"discounts = ? " +
				"WHERE member_id = ?"
			);
		ps_disc.setInt(1, ( m.getAvailableDiscounts() ));
		ps_disc.setInt(2, m.getId());
		ps_disc.executeUpdate();
		ps_disc.close();
		
	}
	else
	{
		PreparedStatement ps_disc = connection.prepareStatement(
				"INSERT INTO member_discounts VALUES (?, ?)"
			);
		ps_disc.setInt(1, m.getId());
		ps_disc.setInt(2, m.getAvailableDiscounts());
		ps_disc.executeUpdate();	
		ps_disc.close();
			
	}

	
	connection.close();
	
	}
	catch (Exception e)
	{
	System.out.println(e);
	return false;
	}
	return true;
	}
	
	/**
	* Adds a member to the database.  Uses a PreparedStatement.
	* @param first_name			First name of the member to look for
	* @param last_name			Last name of the member to look for
	* @param membership_length	Length of member's membership, 0 for
									half semester, 1 for full ??
	* @param membership_type	Membership type ??
	* @param year_in_school		Member's year in school 0 for freshman
									1 for sophomore, 2 for junior,
									3 for senior, 4 for graduate,
									5 for faculty ??
	* @param receive_email		Can the member receive emails from the
									Food Co-op?
	* @param is_active			Is this member active?
	*/
	public static void addMember(Member m)
	{
		Calendar cal = Calendar.getInstance();
		Date last_signup_date = new Date(cal.getTime().getTime());
		m.setLastSignupDate(last_signup_date);
		long temp_signup_date = last_signup_date.getTime();  //temp value to store sign-up date into database.
		//m.setId(java.sql.Types.INTEGER);
		m.setLastSignIn(temp_signup_date);
		try
		{
			Connection connection = connectToDatabase();
			PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO members VALUES(?,?,?,?,?,?,?,?,?)");
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, m.getFirstName());
			ps.setString(3, m.getLastName());
			ps.setString(4, m.getEmailAddress());
			ps.setLong(5, temp_signup_date); // Is setLong correct?
			ps.setInt(6, m.getMembershipLength());
			ps.setInt(7, m.getMembershipType());
			ps.setInt(8, m.getYearInSchool());
			ps.setInt(9, 1);
			ps.executeUpdate();
			//rs.close();
			ps.close();
			connection.close();
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}

