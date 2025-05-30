package Servicii;

import Entitati.Drona;
import Entitati.TipDrona;
import Utile.ConnectionManager;
import Utile.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DronaDAO {
    private static DronaDAO instance;

    private DronaDAO() {}

    // Returnează instanța singleton
    public static DronaDAO getInstance() {
        if (instance == null) {
            instance = new DronaDAO();
        }
        return instance;
    }

    // Inserează o nouă dronă în baza de date
    public void adaugaDrona(Drona drona) {
        String sql = "INSERT INTO Drona (nume, status, ore_zbor, id_tip) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, drona.getNume());
            stmt.setString(2, drona.getStatus().toString());
            stmt.setDouble(3, drona.getOreZbor());
            stmt.setInt(4, drona.getTipDrona().getId());
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("adaugaDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/// Încearcă să insereze o dronă în baza de date și să logheze acțiunea.
/// Dacă apare vreo eroare SQL, aceasta este prinsă și afișată.
    // Returnează lista tuturor dronelor (cu datele din TipDrona)
    public List<Drona> getToateDronele() {
        List<Drona> lista = new ArrayList<>();
        String sql =
                "SELECT d.id, d.nume, d.status, d.ore_zbor, " +
                        "       t.id          AS tip_id, " +
                        "       t.model       AS tip_model, " +
                        "       t.autonomie   AS tip_autonomie, " +
                        "       t.greutate    AS tip_greutate " +
                        "FROM Drona d " +
                        "JOIN TipDrona t ON d.id_tip = t.id";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Drona d = new Drona();
                d.setId(rs.getInt("id"));
                d.setNume(rs.getString("nume"));
                d.setStatus(Status.valueOf(rs.getString("status")));
                d.setOreZbor(rs.getDouble("ore_zbor"));

                TipDrona tip = new TipDrona(
                        rs.getInt("tip_id"),
                        rs.getString("tip_model"),
                        rs.getFloat("tip_greutate"),
                        rs.getInt("tip_autonomie")
                );
                d.setTipDrona(tip);

                lista.add(d);
            }

            AuditService.getInstance().logActiune("getToateDronele");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizează o dronă existentă
    public void actualizeazaDrona(Drona drona) {
        String sql = "UPDATE Drona SET nume = ?, status = ?, ore_zbor = ?, id_tip = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, drona.getNume());
            stmt.setString(2, drona.getStatus().toString());
            stmt.setDouble(3, drona.getOreZbor());
            stmt.setInt(4, drona.getTipDrona().getId());
            stmt.setInt(5, drona.getId());

            stmt.executeUpdate();

            AuditService.getInstance().logActiune("actualizeazaDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Șterge o dronă după id
    public void stergeDrona(int id) {
        String sql = "DELETE FROM Drona WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("stergeDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}







/*
public void adaugaDrona(Drona drona) {
    try {
        // 1. Deschide conexiunea
        Connection conn = ConnectionManager.getConnection();

        // 2. Creează comanda SQL
        String sql = "INSERT INTO Drona (nume, status, ore_zbor, id_tip) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        // 3. Setează valorile
        stmt.setString(1, drona.getNume());
        stmt.setString(2, drona.getStatus().toString());
        stmt.setDouble(3, drona.getOreZbor());
        stmt.setInt(4, drona.getTipDrona().getId());

        // 4. Execută inserarea
        stmt.executeUpdate();

        // 5. Închide resursele
        stmt.close();
        conn.close();

        // 6. Loghează acțiunea
        AuditService.getInstance().logActiune("adaugaDrona");

    } catch (Exception e) {
        // 7. Prinde orice eroare și afișează mesajul
        System.out.println("Eroare la adăugarea dronei: " + e.getMessage());
    }
}
Dacă apare o eroare (ex: baza nu pornește, lipsă driver, etc.), o prinde și o afișează cand adaug o drona
 */