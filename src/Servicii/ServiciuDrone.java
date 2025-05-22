package Servicii;

import Entitati.*;
import Utile.Status;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ServiciuDrone {


    private List<Drona> drone = new ArrayList<>();


    private Set<TipDrona> modeleDrona = new HashSet<>();


    private List<Misiune> misiuni = new ArrayList<>();


    private Map<Pilot, List<Zbor>> zboruriPilot = new HashMap<>();


    private Map<String, List<Reparatie>> reparatiiPeDrona = new HashMap<>();


    public void lansareZbor(Drona drona, Pilot pilot, Misiune misiune, LocalDate data, int durataMinute) {
        if (!drone.contains(drona)) {
            System.out.println("Drona nu este în flotă.");
            return;
        }
        if (drona.getStatus() != Status.DISPONIBILA) {
            System.out.println("Drona " + drona.getId() + " nu este disponibilă.");
            return;
        }

        Zbor zbor = new Zbor(drona, pilot, misiune, data, durataMinute);
        drona.adaugaOreZbor(durataMinute / 60);
        pilot.adaugaOreExperienta(durataMinute / 60);
        drona.setStatus(Status.IN_ZBOR);

        zboruriPilot.computeIfAbsent(pilot, k -> new ArrayList<>()).add(zbor);

        if (!misiuni.contains(misiune)) {
            misiuni.add(misiune);
        }

        System.out.println("Zborul a fost lansat: " + zbor);
    }


    public void afiseazaZboruriDrona(String idDrona) {
        System.out.println("Zborurile dronei " + idDrona + ":");
        boolean gasit = false;
        for (List<Zbor> zboruri : zboruriPilot.values()) {
            for (Zbor z : zboruri) {
                if (z.getDrona().getId().equals(idDrona)) {
                    System.out.println(z);
                    gasit = true;
                }
            }
        }
        if (!gasit) {
            System.out.println("Nicio zbor înregistrat pentru această dronă.");
        }
    }


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


    public void afiseazaDroneSortateDupaOreZbor() {
        drone.sort(Comparator.comparingInt(Drona::getOreZbor).reversed());
        System.out.println("Drone sortate după ore de zbor:");
        for (Drona d : drone) {
            System.out.println(d);
        }
    }


    public void marcheazaAvariata(String id) {
        for (Drona d : drone) {
            if (d.getId().equals(id)) {
                d.setStatus(Status.AVARIATA);
                System.out.println("Drona " + id + " a fost marcată ca AVARIATĂ.");
                return;
            }
        }
        System.out.println("Drona cu ID-ul " + id + " nu a fost găsită.");
    }


    public void afiseazaDroneAvariate() {
        System.out.println("Drone avariate:");
        for (Drona d : drone) {
            if (d.getStatus() == Status.AVARIATA) {
                System.out.println(d);
            }
        }
    }


    public void adaugaReparatie(Reparatie r) {
        String id = r.getDrona().getId();
        reparatiiPeDrona.computeIfAbsent(id, k -> new ArrayList<>()).add(r);
        System.out.println("Reparație înregistrată pentru drona " + id);
    }


    public void adaugaTipDrona(TipDrona tip) {
        modeleDrona.add(tip);
        System.out.println("Modelul " + tip.getDenumire() + " a fost adăugat.");
    }


    public void afiseazaDroneDisponibile() {
        System.out.println("Drone disponibile:");
        for (Drona d : drone) {
            if (d.getStatus() == Status.DISPONIBILA) {
                System.out.println(d);
            }
        }
    }


    public void genereazaRaportPilot(Pilot pilot) {
        List<Zbor> zboruri = zboruriPilot.get(pilot);
        if (zboruri == null || zboruri.isEmpty()) {
            System.out.println("Pilotul " + pilot.getNume() + " nu are activitate.");
            return;
        }

        int totalOre = 0;
        Set<String> dronePilotate = new HashSet<>();
        for (Zbor z : zboruri) {
            totalOre += z.getDurataMinute() / 60;
            dronePilotate.add(z.getDrona().getId());
        }

        System.out.println("Raport pentru pilotul " + pilot.getNume() + ":");
        System.out.println("- Zboruri efectuate: " + zboruri.size());
        System.out.println("- Ore total zburate: " + totalOre);
        System.out.println("- Drone pilotate: " + dronePilotate);
    }


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


    public void afiseazaDroneCuOrePeste(int prag) {
        List<Drona> rezultat = drone.stream()
                .filter(d -> d.getOreZbor() > prag)
                .sorted(Comparator.comparingInt(Drona::getOreZbor).reversed())
                .collect(Collectors.toList());

        if (rezultat.isEmpty()) {
            System.out.println("Nu există drone cu peste " + prag + " ore de zbor.");
        } else {
            System.out.println("Drone cu peste " + prag + " ore de zbor:");
            rezultat.forEach(System.out::println);
        }
    }


    public void adaugaDrona(Drona drona) {
        drone.add(drona);
    }

    public void adaugaMisiune(Misiune misiune) {
        misiuni.add(misiune);
    }
}
