import constants.Constants;
import net.dv8tion.jda.core.entities.Message;

public abstract class Command {
	
	private final String commandName;
	private final int rankRequirement;
	private final String[][] commandParams;
	private String[] userCommandParams;
	private int level;
	
	public abstract void execute();
	public abstract void commandFiles();
	
	public Command(int rankReq, String[][] commandParams)
	{
		this.rankRequirement = rankReq;
		this.commandParams = commandParams;
		this.commandName = this.getClass().getSimpleName();
		this.level = Constants.DEFAULT_RANK;
		//BotFiles.checkProperties(this.commandName, this.rankRequirement, this.commandParams);
	}
	
	public boolean canExecute()
	{
		Message msg = DiscordBot.getEvent().getMessage();
		if(Ranking.hasPermissions(msg, this.rankRequirement))
		{
			//BotFiles.addToUsedCounter(this.commandName);
			setLevel(msg);
			return true;
		}
		return false;
	}
	
	public int getLevel() {
		return level;
	}
	
	private void setLevel(Message msg) {
		
		String message = msg.getContentRaw().substring(2);
		this.level = message.split(" ").length;

		this.userCommandParams = identifyParams(message);
		this.level = userCommandParams.length;
		
	}
	
	public String getCommandName() {
		return commandName;
	}
	
	public int getRankRequirement() {
		return rankRequirement;
	}
	
	public String[][] getCommandParams() {
		return commandParams;
	}
	
	private String[] identifyParams(String fullCommand)
	{
		if(fullCommand.contains("'"))
		{
			int count = 0;
			for(int i = 0, n = fullCommand.length(); i < n; i++)
			{
				char c = fullCommand.charAt(i);

				if(c == '\'')
				{
					count++;
				}
			}

			if(count%2 != 0)
			{
				return null;
			}

			while(fullCommand.contains("'"))
			{
				fullCommand = (fullCommand.substring(0, fullCommand.indexOf("'"))) + (fullCommand.substring(fullCommand.indexOf("'") + 1, fullCommand.indexOf("'", fullCommand.indexOf("'") + 1)).replaceAll(" ", "_")) + (fullCommand.substring(fullCommand.indexOf("'", fullCommand.indexOf("'") + 1) + 1, fullCommand.length()));
			}
		}
		return fullCommand.substring((fullCommand.contains(" ")) ? fullCommand.indexOf(" ") + 1 : fullCommand.length(), fullCommand.length()).split(" ");
	}
	public String[] getUserCommandParams() {
		return this.userCommandParams;
	}
}
