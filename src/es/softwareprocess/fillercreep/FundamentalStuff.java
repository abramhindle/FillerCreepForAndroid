package es.softwareprocess.fillercreep;

public class FundamentalStuff {
	public FundamentalStuff copy() {
		return new FundamentalStuff();
	}
	public int intID() {
		return -1;
	}
	public boolean isa(FundamentalStuff stuff) {
		return (intID() == stuff.intID());
	}
}
