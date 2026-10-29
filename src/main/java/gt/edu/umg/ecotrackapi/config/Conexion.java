package gt.edu.umg.ecotrackapi.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
    private static final Properties props = new Properties();

    static {
        // 1. Cargar explícitamente el driver de SQL Server
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("✅ Driver SQL Server cargado correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ No se encontró el driver SQL Server. Verifica la dependencia en pom.xml.");
            e.printStackTrace();
        }

        // 2. Cargar db.properties
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.err.println("❌ ERROR CRÍTICO: No se encontró el archivo db.properties en la carpeta resources.");
            } else {
                props.load(input);
                System.out.println("✅ Archivo db.properties cargado correctamente.");
            }
        } catch (Exception ex) {
            System.err.println("❌ ERROR al leer db.properties: " + ex.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
        );
    }
}