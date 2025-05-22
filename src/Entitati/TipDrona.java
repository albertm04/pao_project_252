package Entitati;

public class TipDrona {
    private String denumire;
    private double autonomie;
    private double greutate;
    private double vitezaMaxima;

    public TipDrona(String denumire, double autonomie, double greutate, double vitezaMaxima) {
        this.denumire = denumire;
        this.autonomie = autonomie;
        this.greutate = greutate;
        this.vitezaMaxima = vitezaMaxima;
    }

    public String getDenumire() { return denumire; }
    public void setDenumire(String denumire) { this.denumire = denumire; }

    public double getAutonomie() { return autonomie; }
    public void setAutonomie(double autonomie) { this.autonomie = autonomie; }

    public double getGreutate() { return greutate; }
    public void setGreutate(double greutate) { this.greutate = greutate; }

    public double getVitezaMaxima() { return vitezaMaxima; }
    public void setVitezaMaxima(double vitezaMaxima) { this.vitezaMaxima = vitezaMaxima; }

    @Override
    public String toString() {
        return denumire + " (" + autonomie + " km, " + greutate + " kg, " + vitezaMaxima + " km/h)";
    }
}
