package pokelogic;

import pokelogic.Balls;
import pokelogic.ItemID;

public class Pokeball extends Balls
{

   public Pokeball()
   {
      super("Pokeball", "The standard capturing ball.");
      itemID = ItemID.POKEBALL;
   }
}
