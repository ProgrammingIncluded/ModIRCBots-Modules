package pokelogic;

/**
 * Simple base class to use when you want to make an item.
 * @author Charles
 *
 */
public abstract class Item
{
   protected boolean isUsed = false;
   private String description = "No description given.";
   private String name = "NONAME";
   // Internal ID tracker. Should only be modified by internal class.
   protected ItemID itemID = ItemID.NULL;

   Item(String name)
   {
      this(name, null);
   }
   
   Item(String name, String descrp)
   {
      setName(name);
      setDescription(descrp);
   }
   
   public abstract boolean use();
   
   public boolean isUsed()
   {
      return isUsed;
   }
   
   public boolean setDescription(String descrp)
   {
      if(descrp == null || descrp.length() < 0)
         return false;
      this.description = descrp;
      return true;
   }
   
   public boolean setName(String name)
   {
      if(name == null || name.length() < 0)
         return false;
      this.name = name;
      return true;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public String getName()
   {
      return name;
   }
   
   public ItemID getID()
   {
      return itemID;
   }
}
