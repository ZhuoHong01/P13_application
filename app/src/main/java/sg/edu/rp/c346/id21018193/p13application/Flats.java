package sg.edu.rp.c346.id21018193.p13application;

public class Flats {

    private int flatsBuilt;
    private int year;

public Flats(int flatsBuilt, int year){
    this.flatsBuilt = flatsBuilt;
    this.flatsBuilt = year;
}

    public int getFlatsBuilt() {
        return flatsBuilt;
    }

    public void setFlatsBuilt(int flatsBuilt) {
        this.flatsBuilt = flatsBuilt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return flatsBuilt + "\n" + year  + "\n";
    }
}
