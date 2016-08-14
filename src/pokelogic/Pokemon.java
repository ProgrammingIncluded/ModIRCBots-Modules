package pokelogic;

/**
 * Class to represent a pokemon.
 * In charge of holding attack types, owners, etc.
 * Anything that makes the a pokemon unique.
 * Level, current stat, etc.
 * 
 * Everything else that is generic and relatable to the same pokemon
 * is stored in PokeInfo variable. Base Stats, generic name, etc.
 * @author Charles
 *
 */
public class Pokemon
{
   public enum Gender{ MALE, FEMALE, NONE, UNKNOWN };
   
   // All general info about the poke is stored here.
   // Should it be stored here?
   PokeInfo pokeInfo;
   // Name given by the owner.
   private String name;
   private float weight;
   private Gender gender;
   private int level;
   private Stat stat;
   
   public Pokemon(PokeInfo pokeInfo)
   {
      this(pokeInfo, new Stat(), null, 1.0f, Gender.UNKNOWN);
   }
   
   public Pokemon(PokeInfo pokeInfo, String name)
   {
      this(pokeInfo, new Stat(),name, 1.0f, Gender.UNKNOWN);
   }
   
   public Pokemon(PokeInfo pokeInfo, Stat stat, String name, float weight, Gender gender)
   {
      if(!setPokeInfo(pokeInfo))
         this.pokeInfo = new PokeInfo();
      if(!setStat(stat))
         this.stat = new Stat();
      if(!setName(name))
         this.name = pokeInfo.getName();
      if(!setWeight(weight))
         this.weight = 1.0f;
      if(!setGender(gender))
         this.gender = Gender.UNKNOWN;
   }
   
   /*Setters*/
   public boolean setPokeInfo(PokeInfo pokeInfo)
   {
      if(pokeInfo == null)
         return false;
      this.pokeInfo = pokeInfo;
      return true;
   }
   
   public boolean setName(String name)
   {
      if(name == null || name.length() <= 0)
         return false;
      this.name = name;
      return true;
   }
   
   public boolean setLevel(int level)
   {
      if(level < 0)
         return false;
      this.level = level;
      return true;
   }
   
   public boolean setStat(Stat value)
   {
      if(value == null)
         return false;
      stat = value;
      return true;
   }
   
   /**
    * Setter for weight. Only allow up to 2 decimal places.
    * Zero and negatives are allowed.
    */
   public boolean setWeight(float weight)
   {
      this.weight = (float)Math.round(weight * 100) / 100;
      return true;
   }
   
   public boolean setGender(Gender gender)
   {
      this.gender = gender;
      return true;
   }
   
   /*Getters*/
   public PokeInfo getPokeInfo()
   {
      return pokeInfo;
   }
   public String getName()
   {
      return name;
   }
   
   public float getWeight()
   {
      return weight;
   }
   
   public Gender getGender()
   {
      return gender;
   }
   
   public int getLevel()
   {
      return level;
   }
   
   public Stat getStat()
   {
      return stat;
   }
}
