
public class NameStandard {
	public static String standardiseName(String oldName)
	{
		if(oldName.startsWith("<"))
		{
			while(!oldName.startsWith(">"))
			{
				oldName = oldName.substring(1);
			}
			if(oldName.startsWith(">"))
			{
				oldName = oldName.substring(1);
			}
		}


		return oldName.replaceAll(" ", "_").replaceAll(" ", "_").toLowerCase();
	}
}
