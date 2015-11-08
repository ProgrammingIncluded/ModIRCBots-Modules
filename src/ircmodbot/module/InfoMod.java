package ircmodbot.module;
import ircmodbot.Module;
import ircmodbot.OpHelp;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Mod to help display the current modules loaded into the
 * bot.
 * 
 * Format: Keyword
 * @author Charles Chen
 *
 */
public class InfoMod extends Module
{
   public InfoMod()
   {
      super("SKKI");
      moduleName = "InfoMod";
   }

   public void onMessage(String channel, String sender,
      String login, String hostname, String message, ArrayList<String> cmd)
   {
      if(OpHelp.command(message, "truepurpose").length() != 0)
      {
         bot.sendNotice(channel, "Created so that senpai can notice me.");
      }
      else if(OpHelp.command(message, "about").length() != 0)
      {
         bot.sendNotice(channel, "Created for different IRC functions"
            + " such as leaving messages when people are offline. "
            + "A powerful back system for wide range of functions. "
            + "Currently in Alpha.");
      }
      else
      {
         bot.sendNotice(sender, getDisplayModules());
      }
   }

   public void onPrivateMessage(String sender, String login, 
      String hostname, String message, ArrayList<String> cmd)
   {
      onMessage(sender, sender, login, hostname, message, null);
   }

   public void onJoin(String channel,String sender, 
      String login,String hostname)
   {
      return ;
   }

   public String getDisplayModules()
   {
      String[] str = getModuleNames();
      String display = "Loaded Modules: ";
      if(str.length != 0)
      {
         display += str[0];
      }
      for(int x = 1; x < str.length; ++x)
      {
         display += ", " + str[x];
      }
      return display;
   }

   public String[] getModuleNames()
   {
      ArrayList<Module> modules = bot.getModules();
      String[] names = new String[modules.size()];
      Iterator<Module> it = modules.iterator();
      Module cMod;
      for(int x = 0; it.hasNext(); ++x)
      {
         cMod = it.next();
         if(cMod.getTrigger().equals(""))
         {
            names[x] = cMod.getName();
         }
         else
         {
            names[x] = cMod.getName() + " (" + cMod.getTrigger() + ")";
         }
      }
      return names;
   }

}
