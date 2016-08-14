package pokelogic;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ircmodbot.FileSystem;

/**
 * Class to retrieve PokeInfo from file.
 * @author Charles Chen
 *
 */
public class PokeDataBase extends FileSystem
{
   static final Logger LOGGER = Logger.getLogger(PokeDataBase.class);
   // Total different Pokemon types, not including mega evolutions.
   public final int TOTAL_POKE;
   public final String DATABASEFILE;

   public PokeDataBase(int totalPoke, String dataBaseFile)
   {
      this.setRoot(new File("system/resources"));
      TOTAL_POKE = totalPoke;
      DATABASEFILE = dataBaseFile;
   }

   public PokeInfo retrievePoke(String name)
   {
      if(name == null || name.length() <= 0)
         return new PokeInfo();

      PokeInfo info = retrieveInfoBasedOnName(name);

      if(info == null)
         return new PokeInfo();

      return info;
   }

   /**
    * Retrieves the specified Pokemon with ID. Only non-mega evolution poke.
    */
   public PokeInfo retrievePoke(int id)
   {
      if(id < 0 || id > TOTAL_POKE)
         return new PokeInfo();

      Elements pokeID = retrieveIDElements(id);
      if(pokeID == null)
         return new PokeInfo();

      return convertIDElementToInfo(id,pokeID.get(0));
   }

   /**
    * Retrieves a random pokemon info from the database.
    */
   public PokeInfo retrieveRandPoke()
   {
      Random rand = new Random();
      return retrievePoke(rand.nextInt(TOTAL_POKE) + 1);
   }

   /**
    * Converts the element with ID to PokeInfo. Depending on
    * Database, this function may change in the future.
    */
   private PokeInfo convertIDElementToInfo(int id, Element idElement)
   {
      Element type = idElement.nextElementSibling().nextElementSibling();
      Element name = type.previousElementSibling().getElementsByTag("a").get(0);
      Element atrVal = type.nextElementSibling();
      int val[] = new int[6];
      for(int x = 0; x < 6; ++x)
      {
         atrVal = atrVal.nextElementSibling();
         val[x] = Integer.valueOf(atrVal.html());
      }
      Stat stat = new Stat(val[0], val[1], val[2], val[3], val[4], val[5]);

      return new PokeInfo(id, name.html(), stat );
   }

   /**
    * Retrieves ID element based on pokemon name.
    * @return
    */
   private PokeInfo retrieveInfoBasedOnName(String name)
   {
      File input = null;
      Document doc = null;
      try 
      {
         input = this.getFilePath(DATABASEFILE);
         doc = Jsoup.parse(input, "UTF-8", "");
      } 
      catch (IOException e) 
      {
         LOGGER.error("Unable to open Pokemon info.", e);
         return null;
      }
      Elements pokeName = doc.select("a.ent-name");
      Integer id = null;
      // Simple linear search for now
      for(Element element : pokeName)
      {
         if(element.html().equalsIgnoreCase(name))
         {
            id = Integer.valueOf(
                  element.parent().previousElementSibling().attr("data-sort-value"));
            break;
         }
      }

      if(id == null)
         return null;

      // Select by ID of all options.
      Elements pokeID = doc.select("[data-sort-value="+String.valueOf(id)+"]");

      if(pokeID.size() == 0)
      {
         LOGGER.error("Poke ID: " + String.valueOf(id) + " does not exist.");
         return null;
      }
      return convertIDElementToInfo(id,pokeID.get(0));
   }

   /**
    * Retrieves the elements associated with the Pokemon id.
    * Handles errors for you. Returns null if problem occurs.
    */
   private Elements retrieveIDElements(int id)
   {
      File input = null;
      Document doc = null;
      try 
      {
         input = this.getFilePath(DATABASEFILE);
         doc = Jsoup.parse(input, "UTF-8", "");
      } 
      catch (IOException e) 
      {
         LOGGER.error("Unable to open Pokemon info.", e);
         return null;
      }

      Elements pokeID = doc.select("[data-sort-value="+String.valueOf(id)+"]");

      if(pokeID.size() == 0)
      {
         LOGGER.error("Poke ID: " + String.valueOf(id) + " does not exist.");
         return null;
      }
      return pokeID;
   }
}
