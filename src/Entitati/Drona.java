package Entitati;

import Utile.Status;


public class Drona {
    private String id;
    private TipDrona tip;
    private int oreZbor;
    private Status status;

    public Drona(String id, TipDrona tip) {
        this.id = id;
        this.tip = tip;
        this.oreZbor = 0;
        this.status = Status.DISPONIBILA;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public TipDrona getTip() { return tip; }
    public void setTip(TipDrona tip) { this.tip = tip; }

    public int getOreZbor() { return oreZbor; }
    public void adaugaOreZbor(int ore) {
        if (ore > 0) {
            this.oreZbor += ore;
        }
    }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Drona " + id + " [" + tip.getDenumire() + "], " +
               "Ore: " + oreZbor + ", Status: " + status;
    }
}
