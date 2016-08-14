package pokelogic;

public abstract class Balls extends Item
{
   // Capture percent rate.
   private float capRate;
   private Pokemon target;
   private boolean isCaptured;
   
   public Balls(String name)
   {
      this(name, null, 50.0f);
   }
   
   public Balls(String name, String descrp)
   {
      this(name, descrp, 50.0f);
   }
   
   public Balls(String name, String descrp, float capRate)
   {
      super(name, descrp);
      if(!setCapRate(capRate))
         capRate = 50.0f;
      isCaptured = false;
      target = new Pokemon(new PokeInfo());
   }
   
   /**
    * Used the pokeball on the target pokemon. Returns false if
    * item already has been used. Otherwise, returns true regardless of capture.
    */
   public boolean use()
   {
      if(isUsed == true)
         return false;
      if(Math.random() * 100 < capRate)
         isCaptured = true;
      isUsed = true;
      return true;
   }
   
   public float getCapRate()
   {
      return capRate;
   }
   
   public Pokemon getTarget()
   {
      return target;
   }
   
   public boolean isCaptured()
   {
      return isCaptured;
   }
   
   public boolean setCapRate(float rate)
   {
      if(rate < 0 || rate > 100)
         return false;
      
      capRate = rate;
      return true;
   }
   
   public boolean setTarget(Pokemon target)
   {
      if(target == null)
         return false;
      this.target = target;
      return true;
   }
}
