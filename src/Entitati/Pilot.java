package Entitati;

public class Pilot {
    private int id;
    private String nume;
    private String telefon;
    private int varsta;
    private int experienta;

    public Pilot(String nume, String telefon, int varsta) {
        this.nume = nume;
        this.telefon = telefon;
        this.varsta = varsta;
        this.experienta = 0;
    }

    // Constructor pentru citire din baza de date
    public Pilot(int id, String nume, int varsta, int experienta) {
        this.id = id;
        this.nume = nume;
        this.varsta = varsta;
        this.experienta = experienta;
    }

    // Getteri & setteri
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public int getVarsta() { return varsta; }
    public void setVarsta(int varsta) { this.varsta = varsta; }

    public int getExperienta() { return experienta; }
    public void setExperienta(int experienta) { this.experienta = experienta; }

    public void adaugaOreExperienta(int ore) {
        this.experienta += ore;
    }

    @Override
    public String toString() {
        return nume + " (" + varsta + " ani, " + experienta + " ore)";
    }
}
