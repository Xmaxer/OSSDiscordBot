

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.Constants;

public class DBConnection{

	private static DBConnection instance;
	private static Connection con;
	
	private DBConnection()
	{
		if(con == null)
			try {
				con = DriverManager.getConnection(Constants.getDBUrl(), Constants.getDBUser(), Constants.getDBPass());
				System.out.println("SQL Connection established!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static synchronized DBConnection getInstance()
	{
		try {
			if(instance == null || con.isClosed())
				instance = new DBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public static Connection getConnection()
	{
		return con;
	}
}
