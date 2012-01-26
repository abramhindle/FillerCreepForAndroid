package es.softwareprocess.fillercreep;

public class Matter extends FundamentalStuff {
    public FundamentalStuff copy() {
        return new Matter();
    }

    public int intID() {
        return 4;
    }

    public String getName() {
        return "Matter";
    }
}
