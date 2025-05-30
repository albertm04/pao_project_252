
📦 Aplicație Java – Gestionarea unei flote de drone (Etapa I + II)

Student: Albert Moloceniuc
Tema: Aplicație de gestionare a unei mici flote de drone
Etape: I și II
Limbaj: Java

────────────────────────────────────────────────────────────
🔧 Ce am realizat până acum

✅ Etapa I
- Am creat 11 clase + 1 enum, fiecare cu atribute și metode de acces (get, set)
- Am implementat 12 acțiuni principale, toate testate în clasa Main
- Am folosit moștenire între clase (Pilot, Reparator ← Persoana)
- Am folosit colecții Java (List, Set, Map), împreună cu sortări și filtrări
- Am creat o clasă ServiciuDrone care conține logica aplicației
- Am testat totul în Main.java folosind date reale

✅ Etapa II
- Am adăugat persistență: aplicația citește și salvează date din fișiere .csv
- Am creat DAO-uri pentru entitățile principale: TipDrona, Drona, Pilot, Misiune
- Am creat servicii separate pentru fiecare entitate: DronaService, PilotService etc.
- Am adăugat un serviciu de audit care scrie fiecare acțiune într-un fișier audit.csv
- Am modificat clasa Main ca să folosească serviciile și să logheze automat acțiunile
- Codul este acum mai bine organizat: datele, logica și interfața sunt separate

────────────────────────────────────────────────────────────
🧠 Acțiuni implementate (12 total)

1. Lansează o misiune nouă (zbor)
2. Afișează toate zborurile unei drone
3. Afișează toate zborurile unui pilot
4. Sortează dronele după ore totale de zbor
5. Marchează o dronă ca avariată
6. Afișează toate dronele avariate
7. Adaugă o reparație pentru o dronă
8. Creează un nou model de dronă
9. Afișează dronele disponibile pentru zbor
10. Generează raportul de activitate al unui pilot
11. Sortează misiunile după locație (alfabetic)
12. Afișează dronele cu peste X ore de zbor

────────────────────────────────────────────────────────────
📚 Colecții și fișiere folosite

- List<Drona> – flota de drone
- Set<TipDrona> – modelele disponibile
- Map<Pilot, List<Zbor>> – zborurile fiecărui pilot
- Map<String, List<Reparatie>> – reparații pentru fiecare dronă
- List<Misiune> – misiuni înregistrate

Fișiere CSV:
- tipuri_drone.csv
- drone.csv
- piloti.csv
- misiuni.csv
- audit.csv

────────────────────────────────────────────────────────────
📂 Structura proiectului

src/
├── Entitati/
│   ├── Drona.java
│   ├── TipDrona.java
│   ├── Zbor.java
│   ├── Misiune.java
│   ├── Locatie.java
│   ├── Reparatie.java
│   ├── Pilot.java
│   ├── Reparator.java
│   └── Persoana.java
│
├── Utile/
│   ├── Status.java
│   └── CititorDate.java
│
├── DAO/
│   ├── TipDronaDAO.java
│   ├── DronaDAO.java
│   ├── PilotDAO.java
│   └── MisiuneDAO.java
│
├── Servicii/
│   ├── TipDronaService.java
│   ├── DronaService.java
│   ├── PilotService.java
│   ├── MisiuneService.java
│   ├── ServiciuDrone.java
│   └── AuditService.java
│
└── Main.java

────────────────────────────────────────────────────────────
✅ Testare

- Am testat aplicația cu:
  - 4 drone din 3 modele
  - 2 piloți
  - 2 locații
  - 3 zboruri efectuate
  - 1 dronă avariată și o reparație înregistrată
- Toate cele 12 acțiuni funcționează și sunt afișate în consolă
- Fiecare acțiune este înregistrată automat în fișierul audit.csv
