package pokelogic;

import java.util.LinkedHashMap;

/**
 * Main inventory manager that keeps track of sub-inventories.
 * @author Charles
 *
 */
public class Inventory
{
   private String owner;
   LinkedHashMap<String, SubInventory> subs;

   Inventory(String owner)
   {
      if(!setOwner(owner))
         owner = "NoOwner";
      subs = new LinkedHashMap<String, SubInventory>();
      // Add cateogies here once implemented.
   }

   public boolean setOwner(String owner)
   {
      if(owner == null || owner.length() <= 0)
         return false;
      this.owner = owner;
      return true;
   }

   public String getOwner()
   {
      return owner;
   }
}
