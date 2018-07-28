import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import constants.Constants;

public class WebDownloader {

	public static String getLinkData(String link) {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(link).openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setRequestProperty("User-Agent", Constants.USER_AGENT);
			con.connect();

			StringBuilder sb = new StringBuilder();
			InputStream input = con.getInputStream();
			int c = -1;
			while((c = input.read()) >= 0)
			{
				sb.append((char) c);
			}

			return sb.toString();
		}
		catch(IOException e) {

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
