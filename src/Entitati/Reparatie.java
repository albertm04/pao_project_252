package Entitati;

import java.time.LocalDate;

public class Reparatie {
    private Drona drona;
    private LocalDate data;
    private String descriere;
    private Reparator reparator;

    public Reparatie(Drona drona, LocalDate data, String descriere, Reparator reparator) {
            this.drona = drona;
            this.data = data;
            this.descriere = descriere;
            this.reparator = reparator;
    }

    public Drona getDrona() { return drona; }
    public LocalDate getData() { return data; }
    public String getDescriere() { return descriere; }
    public Reparator getReparator() {return reparator; }

    @Override
    public String toString() {
        return "Reparație [" + data + "] " + drona.getId() + ": " + descriere +
               " (efectuată de " + reparator.getNume() + ")";
    }
}

