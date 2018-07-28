import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	public static String getIntervalFromMillis(Long timeInMillis) {
		final Long SECOND = 1000L;
		final Long MINUTE = 60*SECOND;
		final Long HOUR = 60*MINUTE;
		final Long DAY = 24*HOUR;
		final Long WEEK = 7*DAY;

		String output = "";

		output += (timeInMillis/WEEK >= 1) ? String.valueOf((int) (Math.floor(timeInMillis/WEEK))) + "w": "";
		timeInMillis -= WEEK*((int) Math.floor(timeInMillis/WEEK));
		output += (timeInMillis/DAY >= 1) ? String.valueOf((int) (Math.floor(timeInMillis/DAY))) + "d": "";
		timeInMillis -= DAY*((int) Math.floor(timeInMillis/DAY));
		output += (timeInMillis/HOUR >= 1) ? String.valueOf((int) (Math.floor(timeInMillis/HOUR))) + "h": "";
		timeInMillis -= HOUR*((int) Math.floor(timeInMillis/HOUR));
		output += (timeInMillis/MINUTE >= 1) ? String.valueOf((int) (Math.floor(timeInMillis/MINUTE))) + "m": "";
		timeInMillis -= MINUTE*((int) Math.floor(timeInMillis/MINUTE));
		output += (timeInMillis/SECOND >= 1) ? String.valueOf((int) (Math.floor(timeInMillis/SECOND))) + "s": "";
		return output;
	}
	public static Long getTimeInMillis(String interval) {
		final Long SECOND = 1000L;
		final Long MINUTE = 60*SECOND;
		final Long HOUR = 60*MINUTE;
		final Long DAY = 24*HOUR;
		final Long WEEK = 7*DAY;
		Long totalTimeInMillis = 0L;
		Matcher m = Pattern.compile("(\\d*\\w)").matcher(interval);
		while(m.find())
		{
			String group = m.group().toLowerCase();

			if(group.matches("\\d+[wdhms]{1}"))
			{
				Integer numberOf = Integer.valueOf(group.replaceAll("[wdhms]", ""));
				if(group.contains("w"))
				{
					totalTimeInMillis += numberOf*WEEK;
				}
				else if(group.contains("d"))
				{
					totalTimeInMillis += numberOf*DAY;
				}
				else if(group.contains("h"))
				{
					totalTimeInMillis += numberOf*HOUR;
				}
				else if(group.contains("m"))
				{
					totalTimeInMillis += numberOf*MINUTE;
				}
				else if(group.contains("s"))
				{
					totalTimeInMillis += numberOf*SECOND;
				}
			}
		}
		return totalTimeInMillis;
	}
}
