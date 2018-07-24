


import constants.Secrets;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DiscordBot extends ListenerAdapter{

	private static JDA jda;
	//private static List<Command> validCommands = new ArrayList<Command>();
	public static void main(String[] args) {
		try
		{
			DBConnection.getInstance();
			jda = new JDABuilder(AccountType.BOT).setToken(Secrets.DISCORD_TOKEN).buildBlocking();
			jda.addEventListener(new DiscordListener());
			jda.setAutoReconnect(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
