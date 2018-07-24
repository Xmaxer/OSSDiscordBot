

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.Constants;

public class DBConnection{

	private static DBConnection instance;
	private Connection con;
	
	private DBConnection()
	{
		if(con == null)
			try {
				this.con = DriverManager.getConnection(Constants.DBUrl, Constants.DBUser, Constants.DBPass);
				System.out.println("SQL Connection established!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static synchronized DBConnection getInstance()
	{
		try {
			if(instance == null || instance.getConnection().isClosed())
				instance = new DBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public Connection getConnection()
	{
		return this.con;
	}
}
