package ircmodbot.module;

import java.util.ArrayList;

import ircmodbot.ModBot;
import ircmodbot.Module;
import ircmodbot.OpHelp;
import pokelogic.PokeDataBase;
import pokelogic.PokeInfo;
import pokelogic.Stat;

/**
 * Simple mod to randomly pick pokemon info to display.
 * @author Charles
 *
 */
public class PokeInfoMod extends Module
{
  private PokeDataBase dataBase;
  public PokeInfoMod()
  {
    super("POKE");
    moduleName = "Pokedex Mod";
    dataBase = new PokeDataBase(721, "pokedatabase.html");
  }

  @Override
  public void onMessage(String channel, String sender,
      String login, String hostname, String message, ArrayList<String> cmd)
  {
    message = message.toLowerCase();

    PokeInfo info = null;

    if(cmd.size() >= 2)
    {
      String buffer = cmd.get(1);

      if(buffer.length() != 0 && cmd.size() >= 3)
      {
        info =  dataBase.retrievePoke(cmd.get(2));
        if(info.getName().equalsIgnoreCase("MISSINGNO") 
            && !buffer.equalsIgnoreCase("MISSINGNO"))
        {
          bot.sendNotice(sender, "Entry does not exist.");
          return;
        }
      }
      else if(buffer.length() != 0 && cmd.size() >= 3)
      {
        int idVal = 0;
        try
        {
          idVal = Integer.valueOf(cmd.get(2));
        }
        catch(NumberFormatException e)
        {
          return;
        }
        info = dataBase.retrievePoke(idVal);
        if(info.getID() == 0 && idVal != 0)
        {
          bot.sendNotice(sender, "Entry does not exist.");
          return;
        }
      }
    }

    if(info == null)
    {
      info = dataBase.retrieveRandPoke();
      bot.sendMessage(channel, "You have encountered a(n)... "+info.getName()+".");
      return;
    }
    sendNoticePokeInfo(bot,sender, info);
  }

  @Override
  public void onPrivateMessage(String sender, String login, 
      String hostname, String message, ArrayList<String> cmd)
  {
    onMessage(sender, sender, login, hostname, message, cmd);
  }

  public void onJoin(String channel,String sender, 
      String login,String hostname)
  {
    return ;
  }

  /**
   * Sends notice to chat. Formats for irc display.
   */
  static public void sendNoticePokeInfo(ModBot bot, String sender, PokeInfo info)
  {
    String result = "ID: " + info.getID() + " Name: " + info.getName();
    Stat stat = info.getBaseStat();
    bot.sendNotice(sender, result);

    result = "Type(s): " + info.getTypes().toString();
    bot.sendNotice(sender, result);

    result = "Health: " + stat.getHP();
    bot.sendNotice(sender, result);

    result = "Atk: " + stat.getAtk() + " Sp. Atk: " + stat.getSPAtk();
    bot.sendNotice(sender, result);

    result = "Def: " + stat.getDef() + " Sp. Def: " + stat.getSPDef();
    bot.sendNotice(sender, result);

    result = "Spd: " + stat.getSpd();
    bot.sendNotice(sender, result);
  }
}
