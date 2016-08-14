package pokelogic;

import java.util.LinkedHashMap;

/**
 * Abstract sub-inventory that will take care of storing items by converting
 * them into integer counts. Great for generic items!
 * @author Charles
 *
 */
public abstract class SubInventory
{
   private String subName;
   // Database for storing items and # that you have.
   LinkedHashMap<ItemID, Integer> inv;
   
   SubInventory(String subName)
   {
      inv = new LinkedHashMap<ItemID, Integer>();
      if(!setSubName(subName))
         subName = "NoName";
   }
   
   public abstract boolean putItem(Item item);
   public abstract Item takeItem();

   protected boolean insertItem(ItemID ID)
   {
      Integer value = inv.get(ID);
      if(value == null)
         inv.put(ID, 1);
      else
         inv.put(ID, ++value);
      return true;
   }
   protected Item pullItem(ItemID ID)
   {
      return ItemList.getItem(ID);
   }
   
   public String getSubName()
   {
      return subName;
   }
   
   public boolean setSubName(String name)
   {
      if(name == null || name.length() <= 0)
         return false;
      subName = name;
      return true;
   }
}
