package Entitati;

public class Misiune {
    private String cod;
    private String descriere;
    private Locatie locatie;

    public Misiune(String cod, String descriere, Locatie locatie) {
        this.cod = cod;
        this.descriere = descriere;
        this.locatie = locatie;
    }

    public String getCod() { return cod; }
    public String getDescriere() { return descriere; }
    public Locatie getLocatie() { return locatie; }

    public void setDescriere(String descriere) { this.descriere = descriere; }
    public void setLocatie(Locatie locatie) { this.locatie = locatie; }

    @Override
    public String toString() {
        return "Misiune " + cod + ": " + descriere + " în " + locatie.getNume();
    }
}
