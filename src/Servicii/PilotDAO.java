package Servicii;

import Entitati.Pilot;
import Utile.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotDAO {
    private static PilotDAO instance;

    private PilotDAO() {}

    // Returnează instanța singleton
    public static PilotDAO getInstance() {
        if (instance == null) {
            instance = new PilotDAO();
        }
        return instance;
    }

    // Inserează un nou pilot în baza de date
    public void adaugaPilot(Pilot pilot) {
        String sql = "INSERT INTO Pilot (nume, varsta, experienta) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pilot.getNume());
            stmt.setInt(2, pilot.getVarsta());
            stmt.setInt(3, pilot.getExperienta());

            stmt.executeUpdate();

            AuditService.getInstance().logActiune("adaugaPilot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Returnează lista tuturor piloților
    public List<Pilot> getTotiPilotii() {
        List<Pilot> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pilot";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pilot p = new Pilot(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getInt("varsta"),
                        rs.getInt("experienta")
                );
                lista.add(p);
            }

            AuditService.getInstance().logActiune("getTotiPilotii");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizează un pilot existent
    public void actualizeazaPilot(Pilot pilot) {
        String sql = "UPDATE Pilot SET nume = ?, varsta = ?, experienta = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pilot.getNume());
            stmt.setInt(2, pilot.getVarsta());
            stmt.setInt(3, pilot.getExperienta());
            stmt.setInt(4, pilot.getId());

            stmt.executeUpdate();

            AuditService.getInstance().logActiune("actualizeazaPilot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Șterge un pilot după id
    public void stergePilot(int id) {
        String sql = "DELETE FROM Pilot WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("stergePilot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
