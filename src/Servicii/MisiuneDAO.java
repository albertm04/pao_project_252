package Servicii;

import Entitati.Locatie;
import Entitati.Misiune;
import Utile.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MisiuneDAO {
    private static MisiuneDAO instance;

    private MisiuneDAO() {}

    // Returnează instanța singleton
    public static MisiuneDAO getInstance() {
        if (instance == null) {
            instance = new MisiuneDAO();
        }
        return instance;
    }

    // Inserează o misiune nouă în baza de date
    public void adaugaMisiune(Misiune misiune) {
        String sql = "INSERT INTO Misiune (cod, descriere, locatie, latitudine, longitudine) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, misiune.getCod());
            stmt.setString(2, misiune.getDescriere());
            stmt.setString(3, misiune.getLocatie().getNume());
            stmt.setDouble(4, misiune.getLocatie().getLatitudine());
            stmt.setDouble(5, misiune.getLocatie().getLongitudine());

            AuditService.getInstance().logActiune("adaugaMisiune");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Returnează lista tuturor misiunilor
    public List<Misiune> getToateMisiunile() {
        List<Misiune> lista = new ArrayList<>();
        String sql = "SELECT * FROM Misiune";
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Locatie locatie = new Locatie(
                        rs.getString("locatie"),
                        rs.getDouble("latitudine"),
                        rs.getDouble("longitudine")
                );
                Misiune m = new Misiune(
                        rs.getString("cod"),
                        rs.getString("descriere"),
                        locatie
                );
                lista.add(m);
            }

            AuditService.getInstance().logActiune("getToateMisiunile");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizează o misiune existentă
    public void actualizeazaMisiune(Misiune misiune) {
        String sql = "UPDATE Misiune SET descriere = ?, locatie = ?, latitudine = ?, longitudine = ? WHERE cod = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, misiune.getDescriere());
            stmt.setString(2, misiune.getLocatie().getNume());
            stmt.setDouble(3, misiune.getLocatie().getLatitudine());
            stmt.setDouble(4, misiune.getLocatie().getLongitudine());
            stmt.setString(5, misiune.getCod());

            AuditService.getInstance().logActiune("actualizeazaMisiune");
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Șterge o misiune după cod
    public void stergeMisiune(String cod) {
        String sql = "DELETE FROM Misiune WHERE cod = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cod);
            stmt.executeUpdate();

            AuditService.getInstance().logActiune("stergeMisiune");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
