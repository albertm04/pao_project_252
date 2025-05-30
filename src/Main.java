import Entitati.*;
import Utile.CreareTabele;

import java.time.LocalDate;

import Servicii.DronaDAO;
import Servicii.MisiuneDAO;
import Servicii.PilotDAO;
import Servicii.ServiciuDrone;
import Servicii.TipDronaDAO;

public class Main {
    public static void main(String[] args) {


        // Creează tabelele în baza de date
        CreareTabele.creeazaTabele();

        // Inițializare serviciu
        ServiciuDrone serviciu = new ServiciuDrone();

        // Adăugare modele de drone (folosit ulterior la acțiunea 8)
        TipDrona tip1 = new TipDrona("DJI Phantom", 10.5, 1.2, 60);
        TipDrona tip2 = new TipDrona("Parrot Anafi", 9.0, 0.9, 50);
        TipDrona tip3 = new TipDrona("Autel Evo", 12.0, 1.5, 70);
        serviciu.adaugaTipDrona(tip1);
        serviciu.adaugaTipDrona(tip2);
        serviciu.adaugaTipDrona(tip3);

        // Drone (una rămâne disponibilă, fără zbor)
        Drona d1 = new Drona("DR001", tip1);
        Drona d2 = new Drona("DR002", tip2);
        Drona d3 = new Drona("DR003", tip3);
        Drona d4 = new Drona("DR004", tip1);
        serviciu.adaugaDrona(d1);
        serviciu.adaugaDrona(d2);
        serviciu.adaugaDrona(d3);
        serviciu.adaugaDrona(d4);

        // Piloți
        Pilot p1 = new Pilot("Alex Popescu", "0712345678", 20);
        Pilot p2 = new Pilot("Maria Ilie", "0722334455", 50);

        // Locații și misiuni
        Locatie bucuresti = new Locatie("București", 44.43, 26.10);
        Locatie cluj = new Locatie("Cluj-Napoca", 46.77, 23.60);
        Misiune m1 = new Misiune("MS001", "Observare trafic", bucuresti);
        Misiune m2 = new Misiune("MS002", "Verificare rețea electrică", cluj);

        // Actiunea 1: Lansează misiuni noi (zboruri)
        serviciu.lansareZbor(d1, p1, m1, LocalDate.of(2025, 4, 21), 90);
        serviciu.lansareZbor(d2, p1, m2, LocalDate.of(2025, 4, 22), 45);
        serviciu.lansareZbor(d3, p2, m1, LocalDate.of(2025, 4, 23), 120);

        // Actiunea 7: Adaugă o reparație
        Reparator repTech = new Reparator("Ion Tehnic", "0700111222", "electronica");
        Reparatie r1 = new Reparatie(d2, LocalDate.of(2025, 4, 24), "Verificare baterie", repTech);
        serviciu.adaugaReparatie(r1);

        // Actiunea 5: Marchează drona DR002 ca avariată
        serviciu.marcheazaAvariata("DR002");

        // Actiunea 3: Afișează toate zborurile unui pilot
        System.out.println("\n--- Actiunea 3: Zboruri ale pilotului Alex ---");
        serviciu.afiseazaZboruriPilot(p1);

        // Actiunea 2: Afișează toate zborurile unei drone
        System.out.println("\n--- Actiunea 2: Zboruri pentru DR001 ---");
        serviciu.afiseazaZboruriDrona("DR001");

        // Actiunea 4: Sortează dronele după ore de zbor
        System.out.println("\n--- Actiunea 4: Drone sortate după ore de zbor ---");
        serviciu.afiseazaDroneSortateDupaOreZbor();

        // Actiunea 6: Afișează dronele avariate
        System.out.println("\n--- Actiunea 6: Drone avariate ---");
        serviciu.afiseazaDroneAvariate();

        // Actiunea 9: Afișează dronele disponibile
        System.out.println("\n--- Actiunea 9: Drone disponibile ---");
        serviciu.afiseazaDroneDisponibile();

        // Actiunea 10: Generează raport pentru un pilot
        System.out.println("\n--- Actiunea 10: Raport pentru pilotul Maria ---");
        serviciu.genereazaRaportPilot(p2);

        // Actiunea 11: Sortează misiunile după locație
        System.out.println("\n--- Actiunea 11: Misiuni sortate după locație ---");
        serviciu.afiseazaMisiuniSortateDupaLocatie();

        // Actiunea 12: Afișează dronele cu peste 1 oră de zbor
        System.out.println("\n--- Actiunea 12: Drone cu peste 1 oră de zbor ---");
        serviciu.afiseazaDroneCuOrePeste(1);


        //  ETAPA II
        System.out.println("\n--- [Etapa II] ---");

        // TipDronaDAO: listare modele
        System.out.println("\n=== Tipuri de drone – listare (TipDronaDAO) ===");
        for (TipDrona tip : TipDronaDAO.getInstance().getToateTipurile()) {
            System.out.println(tip);
        }

        // DronaDAO: listare (fără re–inserare)
        System.out.println("\n=== Drone – listare (DronaDAO) ===");
        for (Drona dr : DronaDAO.getInstance().getToateDronele()) {
            System.out.println(dr);
        }

        // PilotDAO: inserare și listare
        System.out.println("\n=== Piloți – inserare + listare (PilotDAO) ===");
        PilotDAO.getInstance().adaugaPilot(p1);
        PilotDAO.getInstance().adaugaPilot(p2);
        for (Pilot p : PilotDAO.getInstance().getTotiPilotii()) {
            System.out.println(p);
        }

        // MisiuneDAO: inserare și listare
        System.out.println("\n=== Misiuni – inserare + listare (MisiuneDAO) ===");
        MisiuneDAO.getInstance().adaugaMisiune(m1);
        MisiuneDAO.getInstance().adaugaMisiune(m2);
        for (Misiune m : MisiuneDAO.getInstance().getToateMisiunile()) {
            System.out.println(m);
        }

        // Confirmare log audit
        System.out.println("\nVerificați database/Resources/audit.csv pentru log-urile operațiilor.");

    }
}
