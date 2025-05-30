package Entitati;

import Utile.Status;

public class Drona {
    private int id;
    private String nume;
    private Status status;
    private double oreZbor;
    private TipDrona tipDrona;

    public Drona() {} // constructor gol pentru DAO

    public Drona(String nume, TipDrona tipDrona) {
        this.nume = nume;
        this.tipDrona = tipDrona;
        this.status = Status.DISPONIBILA;
        this.oreZbor = 0;
    }

    // GETTERS & SETTERS
    public void adaugaOreZbor(double ore) {
        this.oreZbor += ore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getOreZbor() {
        return oreZbor;
    }

    public void setOreZbor(double oreZbor) {
        this.oreZbor = oreZbor;
    }

    public TipDrona getTipDrona() {
        return tipDrona;
    }

    public void setTipDrona(TipDrona tipDrona) {
        this.tipDrona = tipDrona;
    }

    @Override
    public String toString() {
        return nume + " | " + status + " | " + oreZbor + " ore | tip: " + tipDrona.getModel();
    }
}
