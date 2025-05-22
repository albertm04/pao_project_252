package Entitati;

public class Locatie {
    private String nume;
    private double latitudine;
    private double longitudine;

    public Locatie(String nume, double latitudine, double longitudine) {
        this.nume = nume;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    @Override
    public String toString() {
        return nume + " (" + latitudine + "°, " + longitudine + "°)";
    }
}
