package pokelogic;

public class ItemList
{
   public static Item getItem(ItemID id)
   {
      switch(id)
      {
         case NULL:
            return null;
         case POKEBALL:
            return new Pokeball();
      }
      return null;
   }
}
