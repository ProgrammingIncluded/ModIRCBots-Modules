package pokelogic;

import java.util.ArrayList;

/**
 * Simple class for handling multiple types. Only a setter and simple getter.
 * No remove functionality for simplicity. Add if needed?
 * If not type specified, will not add UNKOWN. Instead, will only output
 * ERRTYPE if toString() is called.
 * @author Charles
 *
 */
public class Poketype
{
  public enum PType
  {
    NORMAL,
    FIRE,
    FIGHTING,
    WATER,
    FLYING,
    GRASS,
    POISON,
    ELECTRIC,
    GROUND,
    PSYCHIC,
    ROCK,
    ICE,
    BUG,
    DRAGON,
    GHOST,
    DARK,
    STEEL,
    FAIRY,
    UKNOWN
  };

  private ArrayList<PType> types;

  // Constructor for multiple types.
  Poketype(ArrayList<PType> types)
  {
    this.types = types;
  }

  // Simple constructor for single type.
  Poketype(PType type)
  {
    this();
    types.add(type);
  }

  // Default constructor.
  Poketype()
  {
    types = new ArrayList<PType>();
  }

  /**
   * Returns true if added, false if repeat.
   */
  public boolean addType(PType type)
  {
    if(types.contains(type))
      return false;
    types.add(type);
    return true;
  }

  /**
   * Add type via string.
   */
  public boolean addType(String type)
  {
    PType ptype = getPType(type);
    // Make sure that uknown is indeed given.
    if(type.equalsIgnoreCase(ptype.toString()))
      return addType(ptype);
    return false;
  }

  /**
   * Get the list of arrays.
   * Returns the container object.
   */
  public ArrayList<PType> getTypes()
  {
    return types;
  }

  /** Conversion Functions. **/
  public String getStringType(PType type)
  {
    return type.toString().toLowerCase();
  }

  public PType getPType(String type)
  {
    for(PType ptype : PType.values())
    {
      if(ptype.toString().equalsIgnoreCase(type))
        return ptype;
    }
    return PType.UKNOWN;
  }

  public String toString()
  {
    String result = "";
    if(types.size() != 0)
      result += types.get(0).toString().toLowerCase();
    else
      result += "ERRTYPE";

    for(int x = 1; x < types.size(); ++x)
      result += ", " + types.get(x).toString().toLowerCase();

    return result;
  }
}
