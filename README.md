# Java-Project
# Aplicație de gestionare a unei mici flote de drone

**Etapa I – Proiect Java**

## 📌 Ce am realizat în această etapă

- ✅ 11 clase utile și 1 enum, cu atribute private/protected și metode de acces (getter/setter)
- ✅ 12 acțiuni/interogări funcționale, testate în clasa `Main`
- ✅ Moștenire: clasele `Pilot` și `Reparator` extind `Persoana`
- ✅ Utilizare de colecții Java: `List`, `Set`, `Map` + sortări și filtrări
- ✅ Clasă de serviciu `ServiciuDrone` care conține logica principală
- ✅ Testare completă cu date concrete în `Main.java`

---

## 🧱 Clase implementate (11 clase + 1 enum)

| Nr | Nume clasă       | Descriere                                |
|----|------------------|-------------------------------------------|
| 1  | `TipDrona`       | Specificații tehnice ale unui model       |
| 2  | `Drona`          | Instanță activă a unei drone              |
| 3  | `Status` (enum)  | `DISPONIBILA`, `AVARIATA`, `IN_ZBOR`     |
| 4  | `Persoana`       | Clasă de bază                            |
| 5  | `Pilot`          | Controlează drone                         |
| 6  | `Reparator`      | Repară drone                             |
| 7  | `Locatie`        | Locație geografică pentru misiuni         |
| 8  | `Misiune`        | Obiectivul unui zbor                      |
| 9  | `Zbor`           | Execuție a unei misiuni                   |
| 10 | `Reparatie`      | Istoric de intervenții                   |
| 11 | `ServiciuDrone`  | Conține colecțiile și metodele logice     |
| 12 | `Main`           | Clasa principală pentru testare          |

---

## ⚙️ Acțiuni/interogări implementate

1. Lansează o misiune nouă (zbor)  
2. Afișează toate zborurile unei drone  
3. Afișează toate zborurile unui pilot  
4. Sortează dronele după ore totale de zbor  
5. Marchează o dronă ca avariată  
6. Afișează toate dronele cu status = *avariată*  
7. Adaugă o reparație pentru o dronă  
8. Creează un nou model de dronă  
9. Afișează dronele disponibile pentru zbor  
10. Generează raportul de activitate al unui pilot  
11. Sortează misiunile după locație (alfabetic)  
12. Afișează dronele cu peste X ore de zbor  

---

## 📚 Colecții Java utilizate

- `List<Drona>` – pentru stocarea flotei
- `Set<TipDrona>` – pentru modele de drone unice
- `Map<Pilot, List<Zbor>>` – pentru zboruri asociate cu piloți
- `Map<String, List<Reparatie>>` – pentru reparațiile fiecărei drone
- `List<Misiune>` – misiuni desfășurate

👉 Sortări: cu `Comparator` sau `stream().sorted()`  
👉 Filtrări: cu `stream().filter()`

---

## 🧪 Testare

- 4 drone în total (3 modele diferite)
- 2 piloți, 2 locații distincte
- 3 zboruri efectuate
- 1 dronă avariată + 1 reparație înregistrată
- Toate cele 12 acțiuni au fost testate și afișate în consolă

---
