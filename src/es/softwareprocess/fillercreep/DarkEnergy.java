package es.softwareprocess.fillercreep;

public class DarkEnergy extends FundamentalStuff {
	public FundamentalStuff copy() {
		return new DarkEnergy();
	}
	@Override
	public int intID() { return 1; }
}
