package pokelogic;

/**
 * Class to house relavant pokemon info for the type and stats.
 * Is not not in charge of storing attack moves.
 * @author Charles Chen
 *
 */
public class PokeInfo 
{
   // Name in database.
	private String name;
	private int id;
	private Stat baseStat;
	
	public PokeInfo()
	{
		this(0,"MISSINGNO");
	}
	
	public PokeInfo(int id, String name)
	{
		this(id,name, new Stat());
	}
	
	public PokeInfo(Stat baseStat)
	{
		this(0, "MISSINGNO", baseStat);
	}
	
	public PokeInfo(int id, String name, Stat baseStat)
	{
		if(!setID(id))
			this.id = 0;
		if(!setName(name))
			this.name = "MISSINGNO";
		if(!setBaseStat(baseStat))
		   this.baseStat = new Stat();
	}
	
	/*Setters*/
	public boolean setID(int id)
	{
		if(id < 0)
			id = -id;
		this.id = id;
		return true;
	}
	public boolean setName(String name)
	{
		if(name == null || name.length() == 0)
			return false;
		this.name = name;
		return true;
	}

	public boolean setBaseStat(Stat stat)
	{
	   if(stat == null)
	      return false;
	   this.baseStat = stat;
	   return true;
	}
	
	/*Getters*/
	public int getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Stat getBaseStat()
	{
	   return baseStat;
	}
	
	/**
	 * Overriden to String.
	 */
	public String toString()
	{
		String result;
		result = "ID: " + String.valueOf(id);
		result += "\nName: " + name; 
		result += baseStat.toString();
		return result;
	}
}
