
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import constants.Constants;

public class CML extends Command{

	public CML()
	{
		super(0, new String[][]{{"insertSkill", "update"},{"insertInterval"},{"insertName"}});
	}

	@Override
	public void execute() {
		if(super.getLevel() > 1)
		{
			checkFirstParam();
		}
		else
		{
			Messenger.send("This command requires more than 1 parameters.");
		}
	}

	private void checkFirstParam() {

		String playerName = null;
		if(super.getUserCommandParams()[0].equals(super.getCommandParams()[0][1]))
		{
			playerName = NameStandard.standardiseName(super.getUserCommandParams()[1]);
			updateCML(playerName);
			return;
		}
		else
			playerName = NameStandard.standardiseName(super.getUserCommandParams()[2]);

		for(int i = 0, skills = Constants.ALL_SKILLS.length; i < skills; i++)
		{
			for(int j = 0, abvs = Constants.ALL_SKILLS[i].length; j < abvs; j++)
			{
				if(Constants.ALL_SKILLS[i][j].equalsIgnoreCase(super.getUserCommandParams()[0]))
				{
					if(super.getLevel() >= 2)
					{
						getCMLXP(Constants.ALL_SKILLS[i][0],Constants.ALL_SKILLS[i][1], playerName, super.getUserCommandParams()[1]);
					}
					else
					{
						Messenger.send("A valid time interval is required.");
					}
					return;
				}
			}
		}
	}
	private void updateCML(String playerName) {
		int response = Integer.valueOf(WebDownloader.getLinkData(Constants.CML_UPDATE_LINK + playerName).replace("ï»¿", ""));
		String output = "";
		switch(response)
		{
		case 1:
			output = "Successfully updated " + playerName;
			break;
		case 2:
			output = playerName + " does no exist on the Runescape hiscores";
			break;
		case 3:
			output = "Negative xp gain? Not updated.";
			break;
		case 4:
			output = "Unknown cml error";
			break;
		case 5:
			output = playerName + " was already updated in the last 30 seconds";
			break;
		case 6:
			output = playerName + " is an invalid playername";
			break;
		default:
			output = "Something weird happened with cml";
			break;
		}

		Messenger.send(output);

	}
	private void getCMLXP(String skill,String number, String playerName, String interval) {
		Long timeIntervalLong = Time.getTimeInMillis(interval);
		String timeInterval = Time.getIntervalFromMillis(timeIntervalLong);
		String xpData = WebDownloader.getLinkData(Constants.CML_TRACKING_LINK + playerName + "&time=" + timeInterval).replace("ï»¿", "");
		String output = "";
		int skillNumber = Integer.valueOf(number);
		if(xpData.length() >= 10)
		{
			Matcher m = Pattern.compile("([\\d-]+),([\\d-]+),([\\d-]+),([\\d-]+)").matcher(xpData);

			int counter = 0;
			while(m.find())
			{
				if(m.groupCount() == 4 && skillNumber != 24)
				{
					if(skillNumber == counter)
					{
						NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);

						String xpGain = nf.format(Integer.valueOf(m.group(1)));
						String rankGain = nf.format(Integer.valueOf(m.group(2)) * -1);
						String rank = nf.format(Integer.valueOf(m.group(4)));
						String xp = nf.format(Integer.valueOf(m.group(3)));
						if(!rankGain.startsWith("-"))
						{
							rankGain = "+" + rankGain;
						}
						output =  "[" + skill + "] Rank: " + rank + "(" + rankGain + ") X—P: " + xp + "(+" + xpGain + ")";
						break;
					}
					counter++;
				}
			}
		}
		else
		{
			if(xpData.equalsIgnoreCase("-1"))
			{
				output = playerName + " is not in the C—M—L database.";
			}
			else if(xpData.equalsIgnoreCase("-2"))
			{
				output = playerName + " is an invalid name";
			}
			else if(xpData.equalsIgnoreCase("-3"))
			{
				output = "C—M—L database error";
			}
			else if(xpData.equalsIgnoreCase("-4"))
			{
				output = "C—M—L is under heavy seer load";
			}
			else
			{
				output = "Unexpected stuff happened. Try again?";
			}
		}

		Messenger.send(output);
	}

	@Override
	public void commandFiles() {
	}

}
