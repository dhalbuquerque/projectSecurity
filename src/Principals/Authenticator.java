package Principals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DBConnection.DBConnection;

public class Authenticator {

	public String login (String username, String password) {

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String usernameDB = "";
		String passwordDB = "";

		try
		{
			con = DBConnection.createConnection(); //establishing connection
			statement = con.createStatement(); //Statement is used to write queries. Read more about it.
			resultSet = statement.executeQuery("select username,password from users"); //Here table name is users and username,password are columns. fetching all the records and storing in a resultSet.
			while(resultSet.next()) // Until next row is present otherwise it return false
			{
				usernameDB = resultSet.getString("username"); //fetch the values present in database
				passwordDB = resultSet.getString("password");
				if(username.equals(usernameDB) && password.equals(passwordDB))
				{
					return "SUCCESS"; ////If the user entered values are already present in database, which means user has already registered so I will return SUCCESS message.
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Invalid user credentials"; // Just returning appropriate message otherwise
	}
}
