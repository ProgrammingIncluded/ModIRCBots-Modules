package pokelogic;

/**
 * Class to hold all the Stats pertaining to a pokemon.
 * @author Charles
 *
 */
public class Stat
{
   private int hp, atk, def, spAtk, spDef, spd;

   public Stat()
   {
      this(100, 10, 10, 10, 10, 10);
   }

   public Stat( int hp, int atk, int def, int spAtk, int spDef, int spd)
   {
      if(!setHp(hp))
         this.hp = 100;
      if(!setAtk(atk))
         this.atk = 10;
      if(!setDef(def))
         this.def = 10;
      if(!setSPDef(spDef))
         this.spDef = 10;
      if(!setSPAtk(spAtk))
         this.spAtk = 10;
      if(!setSpd(spd))
         this.spd = 10;
   }

   /*Setters*/
   /**
    * No negatives, sets to zero if so.
    */
   public boolean setHp(int hp)
   {
      if(hp < 0)
         hp = 0;
      this.hp = hp;
      return true;
   }

   public boolean setAtk(int value)
   {
      this.atk = value;
      return true;
   }

   public boolean setDef(int value)
   {
      this.def = value;
      return true;
   }

   public boolean setSpd(int value)
   {
      this.spd = value;
      return true;
   }

   public boolean setSPAtk(int value)
   {
      this.spAtk = value;
      return true;
   }

   public boolean setSPDef(int value)
   {
      this.spDef = value;
      return true;
   }
   /*Getters*/
   public int getHP()
   {
      return hp;
   }

   public int getSPAtk()
   {
      return spAtk;
   }

   public int getSPDef()
   {
      return spDef;
   }

   public int getDef()
   {
      return def;
   }

   public int getAtk()
   {
      return atk;
   }

   public int getSpd()
   {
      return spd;
   }

   // Overriden.
   public String toString()
   {
      String result;
      result = "\nHealth: " + hp;
      result += "\nAttack: " + atk;
      result += "\nDefense: " + def;
      result += "\nSP Atk.: " + spAtk;
      result += "\nSP Def.: " + spDef;
      result += "\nSpeed: " + spd;
      return result;
   }
}
