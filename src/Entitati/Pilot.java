package Entitati;


public class Pilot extends Persoana {
    private int oreExperienta;

    public Pilot(String nume, String telefon, int oreExperienta) {
        super(nume, telefon);
        this.oreExperienta = oreExperienta;
    }

    public int getOreExperienta() {
        return oreExperienta;
    }

    public void adaugaOreExperienta(int ore) {
        if (ore > 0) {
            this.oreExperienta += ore;
        }
    }

    @Override
    public String toString() {
        return "Pilot: " + super.toString() + ", experienta: " + oreExperienta + " ore";
    }
}
