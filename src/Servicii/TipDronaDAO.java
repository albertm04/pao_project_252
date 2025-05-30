package Servicii;

import Entitati.TipDrona;
import Utile.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipDronaDAO {
    private static TipDronaDAO instance;

    private TipDronaDAO() {}

    // Returnează instanța singleton
    public static TipDronaDAO getInstance() {
        if (instance == null) {
            instance = new TipDronaDAO();
        }
        return instance;
    }

    // Inserează un nou tip de dronă și setează ID-ul generat
    public void adaugaTipDrona(TipDrona tip) {
        String sql = "INSERT INTO TipDrona (model, autonomie, greutate) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tip.getModel());
            stmt.setDouble(2, tip.getAutonomie());
            stmt.setDouble(3, tip.getGreutate());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    tip.setId(keys.getInt(1));
                }
            }

            AuditService.getInstance().logActiune("adaugaTipDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Returnează lista tuturor tipurilor de drone
    public List<TipDrona> getToateTipurile() {
        List<TipDrona> lista = new ArrayList<>();
        String sql = "SELECT * FROM TipDrona";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipDrona tip = new TipDrona(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getFloat("greutate"),
                        rs.getInt("autonomie")
                );
                lista.add(tip);
            }

            AuditService.getInstance().logActiune("getToateTipurile");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizează un tip de dronă existent
    public void actualizeazaTipDrona(TipDrona tip) {
        String sql = "UPDATE TipDrona SET model = ?, autonomie = ?, greutate = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tip.getModel());
            stmt.setInt(2, tip.getAutonomie());
            stmt.setFloat(3, tip.getGreutate());
            stmt.setInt(4, tip.getId());
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("actualizeazaTipDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Șterge un tip de dronă după id
    public void stergeTipDrona(int id) {
        String sql = "DELETE FROM TipDrona WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("stergeTipDrona");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
