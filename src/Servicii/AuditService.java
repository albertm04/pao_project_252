package Servicii;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    // Instanța singleton
    private static AuditService instance = null;
    // Cale către fișierul CSV de audit
    private static final String FILE_NAME = "database/Resources/audit.csv";

    private AuditService() {}

    // Returnează instanța singleton
    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    // Scrie acțiunea și timestamp-ul în fișierul audit.csv
    public void logActiune(String actiune) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            String timestamp = LocalDateTime
                    .now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.append(actiune)
                    .append(",")
                    .append(timestamp)
                    .append("\n");
        } catch (IOException e) {
            System.out.println("Eroare scriere audit: " + e.getMessage());
        }
    }
}
