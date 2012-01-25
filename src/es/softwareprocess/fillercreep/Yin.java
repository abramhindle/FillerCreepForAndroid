package es.softwareprocess.fillercreep;

public class Yin extends FundamentalStuff implements Player {
	private int x;
	private int y;
	public Yin(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() { return x; }			
	public int getY() { return y; }
	public FundamentalStuff copy() {
		return new Yin(x,y);
	}
	public int intID() { return 5; }
	public FundamentalStuff stuff() {
		return this;	
	}
}
