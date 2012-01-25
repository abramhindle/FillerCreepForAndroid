package es.softwareprocess.fillercreep;

public interface Player {
	public int getX();
	public int getY();
	// Get the STUFF of the player
	public FundamentalStuff stuff();
	public String getName();
	public AI getAI();
}
