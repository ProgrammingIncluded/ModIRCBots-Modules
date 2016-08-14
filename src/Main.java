import java.util.Scanner;

import ircmodbot.*;
import ircmodbot.module.PokeInfoMod;

public class Main {

	private ModBot modBot;
	public GeneralSLoader loader;
	
	// Variable to decide if main loop should exit.
	private boolean exit = false;
	
	Main()
	{
		// Nothing here...
	}
	
	public boolean run()
	{
		// Set debug bot options.
		if(!ModBot.configure("system/log4j.properties"))
		{
			System.out.println("No custom properties at system/log4j.properties. "
				+ "Using basic properties.");
		}
		modBot = new ModBot();
		loader = new GeneralSLoader("system/script", modBot);
		
		// Add Poke testing
		modBot.addModule(new PokeInfoMod());
		
		String input = "";
		Scanner reader = new Scanner(System.in);
		while(!exit)
		{
			System.out.print("> ");
			input = reader.nextLine();
			// No admin command given, output to chat.
			if(!parseAdminInput(input))
			{
				modBot.sendMessage(modBot.getChannelName(), input);
			}
		}
		reader.close();
		return true;
	}

	private boolean parseAdminInput(String input)
	{
		if(input.equalsIgnoreCase("/exit"))
			exit = true;
		else if(input.equalsIgnoreCase("/reload"))
			loader.reload();
		else
			return false;

		return true;
	}
	
	public static void main(String[] args)
	{
		Main main = new Main();
		main.run();
		System.exit(0);
	}
}
