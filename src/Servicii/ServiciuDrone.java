package Servicii;

import Entitati.*;
import Utile.Status;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ServiciuDrone {

    // List pentru drone – folosită în sortări (ex: sortare după ore de zbor)
    private List<Drona> drone = new ArrayList<>();

    // Set pentru modele de drone – se evită duplicarea tipurilor
    private Set<TipDrona> modeleDrona = new HashSet<>();

    // List pentru misiuni – este sortată alfabetic după locație
    private List<Misiune> misiuni = new ArrayList<>();

    // Map pentru asocierea fiecărui pilot cu lista sa de zboruri
    private Map<Pilot, List<Zbor>> zboruriPilot = new HashMap<>();

    // Map pentru reparații, indexate după ID-ul dronei
    private Map<String, List<Reparatie>> reparatiiPeDrona = new HashMap<>();

    // Acțiunea 1: Lansează o misiune nouă (zbor)
    public void lansareZbor(Drona drona, Pilot pilot, Misiune misiune, LocalDate data, int durataMinute) {
        AuditService.getInstance().logActiune("lansareZbor");

        if (!drone.contains(drona)) {
            System.out.println("Drona nu este în flotă.");
            return;
        }
        if (drona.getStatus() != Status.DISPONIBILA) {
            System.out.println("Drona " + drona.getId() + " nu este disponibilă.");
            return;
        }

        Zbor zbor = new Zbor(drona, pilot, misiune, data, durataMinute);
        drona.adaugaOreZbor(durataMinute / 60.0);
        pilot.adaugaOreExperienta(durataMinute / 60);
        drona.setStatus(Status.IN_ZBOR);

        zboruriPilot.computeIfAbsent(pilot, k -> new ArrayList<>()).add(zbor);

        if (!misiuni.contains(misiune)) {
            misiuni.add(misiune);
        }

        System.out.println("Zborul a fost lansat: " + zbor);
    }

    // Acțiunea 2: Afișează toate zborurile unei drone
    public void afiseazaZboruriDrona(String idDrona) {
        System.out.println("Zborurile dronei " + idDrona + ":");
        boolean gasit = false;
        for (List<Zbor> zboruri : zboruriPilot.values()) {
            for (Zbor z : zboruri) {
                if (String.valueOf(z.getDrona().getId()).equals(idDrona)) {
                    System.out.println(z);
                    gasit = true;
                }
            }
        }
        if (!gasit) {
            System.out.println("Nicio zbor înregistrat pentru această dronă.");
        }
    }

    // Acțiunea 3: Afișează toate zborurile unui pilot
    public void afiseazaZboruriPilot(Pilot pilot) {
        List<Zbor> zboruri = zboruriPilot.get(pilot);
        if (zboruri == null || zboruri.isEmpty()) {
            System.out.println("Pilotul " + pilot.getNume() + " nu are zboruri.");
            return;
        }

        System.out.println("Zborurile pilotului " + pilot.getNume() + ":");
        for (Zbor z : zboruri) {
            System.out.println(z);
        }
    }

    // Acțiunea 4: Sortează dronele după ore totale de zbor
    public void afiseazaDroneSortateDupaOreZbor() {
        drone.sort(Comparator.comparingDouble(Drona::getOreZbor).reversed());
        System.out.println("Drone sortate după ore de zbor:");
        for (Drona d : drone) {
            System.out.println(d);
        }
    }

    // Acțiunea 5: Marchează o dronă ca avariată
    public void marcheazaAvariata(String id) {
        for (Drona d : drone) {
            if (String.valueOf(d.getId()).equals(id)) {
                d.setStatus(Status.AVARIATA);
                System.out.println("Drona " + id + " a fost marcată ca AVARIATĂ.");
                return;
            }
        }
        System.out.println("Drona cu ID-ul " + id + " nu a fost găsită.");
    }

    // Acțiunea 6: Afișează toate dronele cu status = "avariată"
    public void afiseazaDroneAvariate() {
        System.out.println("Drone avariate:");
        for (Drona d : drone) {
            if (d.getStatus() == Status.AVARIATA) {
                System.out.println(d);
            }
        }
    }

    // Acțiunea 7: Adaugă o reparație pentru o dronă
    public void adaugaReparatie(Reparatie r) {
        String id = String.valueOf(r.getDrona().getId());
        reparatiiPeDrona.computeIfAbsent(id, k -> new ArrayList<>()).add(r);
        System.out.println("Reparație înregistrată pentru drona " + id);
    }

    // Acțiunea 8: Creează un nou model de dronă și salvează în DB (Etapa II)
    public void adaugaTipDrona(TipDrona tip) {
        AuditService.getInstance().logActiune("adaugaTipDrona");
        TipDronaDAO.getInstance().adaugaTipDrona(tip);
        modeleDrona.add(tip);
        System.out.println("Modelul " + tip.getDenumire() + " a fost adăugat.");
    }

    // Acțiunea 9: Afișează dronele disponibile pentru zbor
    public void afiseazaDroneDisponibile() {
        System.out.println("Drone disponibile:");
        for (Drona d : drone) {
            if (d.getStatus() == Status.DISPONIBILA) {
                System.out.println(d);
            }
        }
    }

    // Acțiunea 10: Generează raportul de activitate al unui pilot
    public void genereazaRaportPilot(Pilot pilot) {
        AuditService.getInstance().logActiune("genereazaRaportPilot");

        List<Zbor> zboruri = zboruriPilot.get(pilot);
        if (zboruri == null || zboruri.isEmpty()) {
            System.out.println("Pilotul " + pilot.getNume() + " nu are activitate.");
            return;
        }

        int totalOre = 0;
        Set<String> dronePilotate = new HashSet<>();
        for (Zbor z : zboruri) {
            totalOre += z.getDurataMinute() / 60;
            dronePilotate.add(String.valueOf(z.getDrona().getId()));
        }

        System.out.println("Raport pentru pilotul " + pilot.getNume() + ":");
        System.out.println("- Zboruri efectuate: " + zboruri.size());
        System.out.println("- Ore total zburate: " + totalOre);
        System.out.println("- Drone pilotate: " + dronePilotate);
    }

    // Acțiunea 11: Sortează misiunile după locație (alfabetic)
    public void afiseazaMisiuniSortateDupaLocatie() {
        if (misiuni.isEmpty()) {
            System.out.println("Nu există misiuni.");
            return;
        }

        List<Misiune> sortate = new ArrayList<>(misiuni);
        sortate.sort(Comparator.comparing(m -> m.getLocatie().getNume()));

        System.out.println("Misiuni sortate după locație:");
        for (Misiune m : sortate) {
            System.out.println(m);
        }
    }

    // Acțiunea 12: Afișează dronele cu peste X ore de zbor
    public void afiseazaDroneCuOrePeste(int prag) {
        List<Drona> rezultat = drone.stream()
                .filter(d -> d.getOreZbor() > prag)
                .sorted(Comparator.comparingDouble(Drona::getOreZbor).reversed())
                .collect(Collectors.toList());

        if (rezultat.isEmpty()) {
            System.out.println("Nu există drone cu peste " + prag + " ore de zbor.");
        } else {
            System.out.println("Drone cu peste " + prag + " ore de zbor:");
            rezultat.forEach(System.out::println);
        }
    }


    // Metode auxiliare pentru populare și persistare (Etapa II):

    // Adaugă drona în DB + memorie + audit
    public void adaugaDrona(Drona drona) {
        AuditService.getInstance().logActiune("adaugaDrona");
        DronaDAO.getInstance().adaugaDrona(drona);///leg drona baza de date, ret singleton si o baga pe drona
        drone.add(drona);
        System.out.println("Drona " + drona.getNume() + " a fost adăugată.");
    }

    // Adaugă misiunea în DB + memorie
    public void adaugaMisiune(Misiune misiune) {
        MisiuneDAO.getInstance().adaugaMisiune(misiune);
        misiuni.add(misiune);
    }

    // Afișează toate misiunile din baza de date
    public void afiseazaToateMisiunile() {
        System.out.println("=== Misiuni din baza de date ===");
        for (Misiune m : MisiuneDAO.getInstance().getToateMisiunile()) {
            System.out.println(m);
        }
    }

    // Adaugă pilot în DB + inițializează colecția de zboruri + audit
    public void adaugaPilot(Pilot pilot) {
        AuditService.getInstance().logActiune("adaugaPilot");
        PilotDAO.getInstance().adaugaPilot(pilot);
        zboruriPilot.putIfAbsent(pilot, new ArrayList<>());
        System.out.println("Pilotul " + pilot.getNume() + " a fost adăugat.");
    }

    // Afișează toți piloții din baza de date
    public void afiseazaTotiPilotii() {
        System.out.println("=== Piloți în baza de date ===");
        for (Pilot p : PilotDAO.getInstance().getTotiPilotii()) {
            System.out.println(p);
        }
    }
}