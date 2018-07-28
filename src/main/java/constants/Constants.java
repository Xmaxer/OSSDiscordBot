package constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Constants {
	
	//Eager initialization
	@SuppressWarnings("unused")
	private static final Constants instance = new Constants();
	
	private static String DBUrl;
	private static String DBUser;
	private static String DBPass;
	private static List<String> channels = new ArrayList<String>();
	public static final String CONFIG_FILE = "resources/config.properties";
	public static final int DEFAULT_RANK = -1;
	public static final String PREFIX = "!!";
	public final static String[][] ALL_SKILLS = {{"total","0", "ttl"},{"attack","1","atk","att"},{"defence","2", "defense", "def"},{"strength","3", "str"},{"hitpoints","4","hp"},{"ranged","5","rng","range"},{"prayer","6", "pray"},{"magic","7","mage"},{"cooking","8","cook"},{"woodcutting","9","wc"},{"fletching","10","fletch"},{"fishing","11","fish"},{"firemaking","12","fm"},{"crafting","13","craft"},{"smithing","14","smith"},{"mining","15","mine"},{"herblore","16","herb"},{"agility","17","agil"},{"thieving","18","thief","thieve","thiev"},{"slayer","19","slay"},{"farming","20","farm"},{"runecrafting","21","rc","runecraft"},{"hunter","22","hunt"},{"construction","23","cons","con"},{"ehp","24"}};
	public final static String CML_UPDATE_LINK = "http://crystalmathlabs.com/tracker/api.php?type=update&player=";
	public final static String CML_TRACKING_LINK = "http://crystalmathlabs.com/tracker/api.php?type=track&player=";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

	
	//Will load properties on startup
	private Constants(){
		Properties props = new Properties();
		try {
			InputStream input = new FileInputStream(CONFIG_FILE);
			
			props.load(input);
			DBUser = props.getProperty("db_user");
			DBPass = props.getProperty("db_pass");
			DBUrl = "jdbc:mysql://" + props.getProperty("db_ip") + "/" + props.getProperty("db") + "?serverTimezone=UTC";
			channels = Arrays.asList(props.getProperty("channels").split(","));
			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDBUrl() {
		return DBUrl;
	}

	public static String getDBUser() {
		return DBUser;
	}

	public static String getDBPass() {
		return DBPass;
	}

	public static List<String> getChannels() {
		return channels;
	}
}
