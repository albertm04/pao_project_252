package Entitati;

public class TipDrona {
    private int id;
    private String denumire;
    private double autonomie;
    private double greutate;
    private double vitezaMaxima;

    // Constructor original (fără id)
    public TipDrona(String denumire, double autonomie, double greutate, double vitezaMaxima) {
        this.denumire = denumire;
        this.autonomie = autonomie;
        this.greutate = greutate;
        this.vitezaMaxima = vitezaMaxima;
    }

    // Constructor NOU (cu id – pentru citire din DB)
    public TipDrona(int id, String denumire, double greutate, double autonomie) {
        this.id = id;
        this.denumire = denumire;
        this.greutate = greutate;
        this.autonomie = autonomie;
    }


    // Getteri și setteri suplimentari pentru DAO
    public String getModel() {
        return denumire;
    }

    public void setModel(String denumire) {
        this.denumire = denumire;
    }

    public int getAutonomie() {
        return (int) autonomie;
    }

    public float getGreutate() {
        return (float) greutate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }

    public double getVitezaMaxima() { return vitezaMaxima; }
    public void setVitezaMaxima(double vitezaMaxima) { this.vitezaMaxima = vitezaMaxima; }

    @Override
    public String toString() {
        return denumire + " (" + autonomie + " km, " + greutate + " kg, " + vitezaMaxima + " km/h)";
    }
}
