import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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
	 * @return memberList	An arraylist of member objects which match the 
	 *						parameters
	 */
	public static ArrayList<Member> lookupMember(String first_name, String last_name)
	{
		ArrayList<Member> memberList = new ArrayList<Member>();

		try
		{
			Connection connection = connectToDatabase();
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery(
					"SELECT * FROM members WHERE first_name='" + first_name +
					"' AND last_name='" + last_name + "';"
			);
				while (rs.next())
				{
				Member m = new Member(
					rs.getInt("id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("email_address"),
					new Date((long)rs.getInt("last_signup_date")),
					rs.getInt("membership_length"),
					rs.getInt("membership_type"),
					rs.getInt("year_in_school"), rs.getInt("available_discounts"), rs.getDouble("iou_amount"),
					(rs.getInt("receive_email") != 0),
					(rs.getInt("is_active") != 0)
				);
				memberList.add(m);
			}
			connection.close();
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}

		return memberList;
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
						"receive_email = ? " +
						"number_of_discounts = ?, " +		//added by Ashley
						"WHERE id = ?"
				);
				ps.setString(1, m.getFirstName());
				ps.setString(2, m.getLastName());
				ps.setString(3, m.getEmailAddress());
				ps.setInt(4, m.getMembershipLength());
				ps.setInt(5, m.getMembershipType());
				ps.setInt(6, m.getYearsInSchool());
				ps.setBoolean(7, m.getActive());
				ps.setBoolean(8, m.getReceiveEmail());
				ps.setInt(9, m.getId());
				ps.setInt(10,m.getAvailableDiscounts());	//added by Ashley

				ResultSet rs = ps.executeQuery();

				rs.close();
				ps.close();

				PreparedStatement ps_iou = connection.prepareStatement(
						"UPDATE member_iou SET " +
						"iou_amount = ?, " +
						"WHERE member_id = ?"
				);
				ps.setDouble(1, m.getIouAmount());
				ps.setInt(2, m.getId());
				ResultSet rs_iou = ps_iou.executeQuery();

				rs_iou.close();
				ps_iou.close();

				PreparedStatement ps_disc = connection.prepareStatement(
						"UPDATE member_discounts SET " +
						"discounts = ?, " +
						"WHERE member_id = ?"
				);
				ps.setInt(1, m.getAvailableDiscounts());
				ps.setInt(2, m.getId());
				ResultSet rs_disc = ps_disc.executeQuery();

				rs_disc.close();
				ps_disc.close();


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
		 * @author Ashley Chin
		 * @version 4/28/11
		 * 
		 */
		public static boolean addDiscounts(Member m,int numberOfDiscounts)
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
						"receive_email = ? " +
						"WHERE id = ?"
				);
				ps.setString(1, m.getFirstName());
				ps.setString(2, m.getLastName());
				ps.setString(3, m.getEmailAddress());
				ps.setInt(4, m.getMembershipLength());
				ps.setInt(5, m.getMembershipType());
				ps.setInt(6, m.getYearsInSchool());
				ps.setBoolean(7, m.getActive());
				ps.setBoolean(8, m.getReceiveEmail());
				ps.setInt(9, m.getId());
				ResultSet rs = ps.executeQuery();

				rs.close();
				ps.close();

				PreparedStatement ps_iou = connection.prepareStatement(
						"UPDATE member_iou SET " +
						"iou_amount = ?, " +
						"WHERE member_id = ?"
				);
				ps.setDouble(1, m.getIouAmount());
				ps.setInt(2, m.getId());
				ResultSet rs_iou = ps_iou.executeQuery();

				rs_iou.close();
				ps_iou.close();

				PreparedStatement ps_disc = connection.prepareStatement(
						"UPDATE member_discounts SET " +
						"discounts = ?, " +
						"WHERE member_id = ?"
				);
				ps.setInt(1, m.getAvailableDiscounts());
				ps.setInt(2, m.getId());
				ResultSet rs_disc = ps_disc.executeQuery();

				rs_disc.close();
				ps_disc.close();


				connection.close();

			}
			catch (Exception e)
			{
				System.out.println(e);
				return false;
			}
			return true;
		}
	}