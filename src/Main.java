import Entitati.*;
import Servicii.ServiciuDrone;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        ServiciuDrone serviciu = new ServiciuDrone();

        TipDrona tip1 = new TipDrona("DJI Phantom", 10.5, 1.2, 60);
        TipDrona tip2 = new TipDrona("Parrot Anafi", 9.0, 0.9, 50);
        TipDrona tip3 = new TipDrona("Autel Evo", 12.0, 1.5, 70);
        serviciu.adaugaTipDrona(tip1);
        serviciu.adaugaTipDrona(tip2);
        serviciu.adaugaTipDrona(tip3);


        Drona d1 = new Drona("DR001", tip1);
        Drona d2 = new Drona("DR002", tip2);
        Drona d3 = new Drona("DR003", tip3);
        Drona d4 = new Drona("DR004", tip1);
        serviciu.adaugaDrona(d1);
        serviciu.adaugaDrona(d2);
        serviciu.adaugaDrona(d3);
        serviciu.adaugaDrona(d4);

        Pilot p1 = new Pilot("Alex Popescu", "0712345678", 20);
        Pilot p2 = new Pilot("Ruxandra Ilie", "0722334455", 50);


        Locatie bucuresti = new Locatie("București", 44.43, 26.10);
        Locatie cluj = new Locatie("Cluj-Napoca", 46.77, 23.60);
        Misiune m1 = new Misiune("MS001", "Observare trafic", bucuresti);
        Misiune m2 = new Misiune("MS002", "Verificare rețea electrică", cluj);


        serviciu.lansareZbor(d1, p1, m1, LocalDate.of(2025, 4, 21), 90);
        serviciu.lansareZbor(d2, p1, m2, LocalDate.of(2025, 4, 22), 45);
        serviciu.lansareZbor(d3, p2, m1, LocalDate.of(2025, 4, 23), 120);


        Reparator repTech = new Reparator("Ion Tehnic", "0700111222", "electronica");
        Reparatie r1 = new Reparatie(d2, LocalDate.of(2025, 4, 24), "Verificare baterie", repTech);
        serviciu.adaugaReparatie(r1);


        serviciu.marcheazaAvariata("DR002");

        System.out.println("\n--- Actiunea 3: Zboruri ale pilotului Alex ---");
        serviciu.afiseazaZboruriPilot(p1);


        System.out.println("\n--- Actiunea 2: Zboruri pentru DR001 ---");
        serviciu.afiseazaZboruriDrona("DR001");


        System.out.println("\n--- Actiunea 4: Drone sortate după ore de zbor ---");
        serviciu.afiseazaDroneSortateDupaOreZbor();


        System.out.println("\n--- Actiunea 6: Drone avariate ---");
        serviciu.afiseazaDroneAvariate();


        System.out.println("\n--- Actiunea 9: Drone disponibile ---");
        serviciu.afiseazaDroneDisponibile();


        System.out.println("\n--- Actiunea 10: Raport pentru pilotul Ruxandra ---");
        serviciu.genereazaRaportPilot(p2);


        System.out.println("\n--- Actiunea 11: Misiuni sortate după locație ---");
        serviciu.afiseazaMisiuniSortateDupaLocatie();

        System.out.println("\n--- Actiunea 12: Drone cu peste 1 oră de zbor ---");
        serviciu.afiseazaDroneCuOrePeste(1);
    }
}
