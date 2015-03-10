package tp.pr5;

public enum StormType {
	ACIDRAIN(25), SANDSTORM(50), TORNADO(100);
	
	private int damage;
	
	private StormType(int damage)
	{
		this.damage = damage;
	}
	
	public int getStormTypeDamage()
	{
		return this.damage;
	}
}
