package es.softwareprocess.fillercreep;

abstract public class FundamentalStuff {
	abstract public FundamentalStuff copy();
	abstract public int intID();
	public boolean isSame(FundamentalStuff stuff) {
		return (this.intID() == stuff.intID());
	}
	abstract public String getName();
}
