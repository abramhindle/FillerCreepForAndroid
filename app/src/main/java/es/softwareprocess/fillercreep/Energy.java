package es.softwareprocess.fillercreep;

public class Energy extends FundamentalStuff {
    public FundamentalStuff copy() {
        return new Energy();
    }

    public int intID() {
        return 3;
    }

    public String getName() {
        return "Energy";
    }
}
