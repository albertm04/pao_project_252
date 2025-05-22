package Entitati;


public class Reparator extends Persoana {
    private String specializare;

    public Reparator(String nume, String telefon, String specializare) {
        super(nume, telefon);
        this.specializare = specializare;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return "Reparator: " + super.toString() + ", specializare: " + specializare;
    }
}
