package es.softwareprocess.fillercreep;

public class Yang extends FundamentalStuff implements Player {
	private int x;
	private int y;
	public Yang(int x,int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() { return x; }			
	public int getY() { return y; }
	
	public FundamentalStuff copy() {
		return new Yang(x,y);
	}
	@Override
	public int intID() { return 66; }
	public FundamentalStuff stuff() {
		return this;	
	}
	public String getName() {
		return "Yang";
	}
	public AI getAI() {
		return new AIMaximum();
	}

}
