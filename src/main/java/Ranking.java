
import net.dv8tion.jda.core.entities.Message;

public class Ranking {
	public static Integer getUserRank(String userID) {
		
		return -1;
	}
	
	public static String rankNumberToName(int rankNumber)
	{
		if(rankNumber == 0)
		{
			return "smiley";
		}
		else if(rankNumber == 1)
		{
			return "1 banana";
		}
		else if(rankNumber == 2)
		{
			return "2 banana";
		}
		else if(rankNumber == 3)
		{
			return "3 banana";
		}
		else if(rankNumber == 4)
		{
			return "bronze";
		}
		else if(rankNumber == 5)
		{
			return "silver";
		}
		else if(rankNumber == 6)
		{
			return "gold";
		}
		else if(rankNumber == 7)
		{
			return "key master";
		}
		else
		{
			return "unknown rank number: " + rankNumber;
		}
	}
	
	public static boolean hasPermissions(Message message, int rankReq) {
		
		
		return true;
	}
}
