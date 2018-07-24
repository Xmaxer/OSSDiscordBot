package constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {
	
	//Eager initialization
	@SuppressWarnings("unused")
	private static final Constants instance = new Constants();
	
	public static String DBUrl;
	public static String DBUser;
	public static String DBPass;
	public static final String CONFIG_FILE = "resources/config.properties";
	
	//Will load properties on startup
	private Constants(){
		Properties props = new Properties();
		try {
			InputStream input = new FileInputStream(CONFIG_FILE);
			
			props.load(input);
			DBUser = props.getProperty("db_user");
			DBPass = props.getProperty("db_pass");
			DBUrl = "jdbc:mysql://" + props.getProperty("db_ip") + "/" + props.getProperty("db") + "?serverTimezone=UTC";
			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
