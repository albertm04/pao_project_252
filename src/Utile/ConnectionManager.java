package Utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    private static final String URL = "jdbc:h2:./database/droneDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private ConnectionManager() {}


     //Returnează o conexiune activă la baza de date H2.
    //Creează una nouă dacă nu există sau este închisă.

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
