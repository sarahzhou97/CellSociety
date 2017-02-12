package SugarClasses;

public class SugarCell {
	private final int maxSugar;
	private final int sugarRegen;
	private final int sugarGrowthInterval;
	private int sugarCount;
	private SugarPerson myOccupant;
	
	public SugarCell( int maxSugar, int sugarRegen, int sugarGrowthInterval,int sugarCount,  SugarPerson myOccupant){
		this.maxSugar=maxSugar;
		this.sugarRegen=sugarRegen;
		this.sugarGrowthInterval=sugarGrowthInterval;
		this.sugarCount=sugarCount;
		this.myOccupant=myOccupant;
	}
	
	public SugarCell(int maxSugar,int sugarRegen,int sugarGrowthInterval, int sugarCount){
		this(maxSugar,sugarRegen,sugarGrowthInterval,sugarCount,null);
	}
	
	public void growSugar(){
		sugarCount+=sugarRegen;
		if(sugarCount>maxSugar) sugarCount=maxSugar;
	}
	
	public void decrementSugarCount(int sugar){
		sugarCount-=sugar;
	}
	
	public int getSugar(){
		return sugarCount;
	}
	
	public SugarPerson getOccupant(){
		return myOccupant;
	}

}
