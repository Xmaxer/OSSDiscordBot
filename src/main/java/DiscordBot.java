


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import constants.Constants;
import constants.Secrets;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DiscordBot extends ListenerAdapter{

	private static JDA jda;
	private static List<Command> validCommands = new ArrayList<Command>();
	private static GuildMessageReceivedEvent event;
	private static MessageChannel channel;

	public static void main(String[] args) {
		event = null;
		try
		{
			
			DBConnection.getInstance();
			jda = new JDABuilder(AccountType.BOT).setToken(Secrets.DISCORD_TOKEN).buildBlocking();
			jda.addEventListener(new DiscordBot());
			jda.setAutoReconnect(true);
			
			Collections.addAll(validCommands,
					new CML());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event)
	{
		
	}
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		DiscordBot.event = event;
		DiscordBot.channel = event.getChannel();
		if(!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) 
		{
			processMessage();
/*			event.getChannel().sendMessage("Author: " + event.getMessage().getAuthor() + "\nAuthor name: " + event.getMessage().getAuthor().getName() + "\nAuthorID: " + event.getMessage().getAuthor().getId() + 
					"\nRoles: " + event.getMember().getRoles()).queue();*/

		}
	}
	@Override
	public void onReady(ReadyEvent event)
	{

	}

	private void processMessage() {
		String msg = event.getMessage().getContentRaw();
		if(msg.length() > 2 && Constants.PREFIX.equals(msg.substring(0, 2)))
		{
			processCommand(msg);
		}
	}
	
	private void processCommand(String msg) {
		for(Command cmd : validCommands)
			if(cmd.canExecute())
			{
				cmd.execute();
				return;
			}
	}

	public static GuildMessageReceivedEvent getEvent()
	{
		return DiscordBot.event;
	}
	public static MessageChannel getChannel()
	{
		return DiscordBot.channel;
	}
}
