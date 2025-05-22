package Entitati;

import java.time.LocalDate;

public class Zbor {
    private Drona drona;
    private Pilot pilot;
    private Misiune misiune;
    private LocalDate data;
    private int durataMinute;

    public Zbor(Drona drona, Pilot pilot, Misiune misiune, LocalDate data, int durataMinute) {
        this.drona = drona;
        this.pilot = pilot;
        this.misiune = misiune;
        this.data = data;
        this.durataMinute = durataMinute;
    }

    public Drona getDrona() { return drona; }
    public Pilot getPilot() { return pilot; }
    public Misiune getMisiune() { return misiune; }
    public LocalDate getData() { return data; }
    public int getDurataMinute() { return durataMinute; }

    @Override
    public String toString() {
        return "Zbor cu " + drona.getId() + ", pilot: " + pilot.getNume() +
               ", misiune: " + misiune.getDescriere() +
               ", data: " + data + ", durata: " + durataMinute + " min";
    }
}
