

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DiscordListener extends ListenerAdapter{

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event)
	{

	}
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event)
	{
		if(!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) 
		{
			if(!event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) 
			{
				event.getChannel().sendMessage("Author: " + event.getMessage().getAuthor() + "\nAuthor name: " + event.getMessage().getAuthor().getName() + "\nAuthorID: " + event.getMessage().getAuthor().getId() + 
						"\nRoles: " + event.getMember().getRoles()).queue();
			}
		}
	}
	@Override
	public void onReady(ReadyEvent event)
	{
		
	}
}
