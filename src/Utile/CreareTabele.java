package Utile;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreareTabele {

    // true  ⇒ DROP + CREATE la fiecare rulare (pentru testare curată)
    // false ⇒ doar CREATE IF NOT EXISTS
    private static final boolean RESET_DB = true;

    // Creează sau resetează tabelele bazei de date
    public static void creeazaTabele() {
        try (Connection conn = ConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {

            if (RESET_DB) {
                stmt.execute("DROP TABLE IF EXISTS Drona");
                stmt.execute("DROP TABLE IF EXISTS Pilot");
                stmt.execute("DROP TABLE IF EXISTS Misiune");
                stmt.execute("DROP TABLE IF EXISTS TipDrona");
                System.out.println("\n Schema resetată (RESET_DB=true)\n");
            }

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS TipDrona (" +
                            "  id INT AUTO_INCREMENT PRIMARY KEY," +
                            "  model VARCHAR(255)," +
                            "  autonomie INT," +
                            "  greutate FLOAT" +
                            ");"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Drona (" +
                            "  id INT AUTO_INCREMENT PRIMARY KEY," +
                            "  nume VARCHAR(255)," +
                            "  status VARCHAR(50)," +
                            "  ore_zbor DOUBLE," +
                            "  id_tip INT" +
                            ");"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Pilot (" +
                            "  id INT AUTO_INCREMENT PRIMARY KEY," +
                            "  nume VARCHAR(255)," +
                            "  varsta INT," +
                            "  experienta INT" +
                            ");"
            );

            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS Misiune (" +
                            "  cod VARCHAR(50) PRIMARY KEY," +
                            "  descriere VARCHAR(255)," +
                            "  locatie VARCHAR(255)," +
                            "  latitudine DOUBLE," +
                            "  longitudine DOUBLE" +
                            ");"
            );

            System.out.println(" Tabelele au fost create cu succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
