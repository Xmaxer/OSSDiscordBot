

public class Messenger {
	
	@SuppressWarnings("unused")
	public static void send(String content) {

		DiscordBot.getChannel().sendMessage(content).queue();
	}
}
