package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL =
            "jdbc:sqlite:inventario.db";

    public static Connection getConnection() throws Exception {

        return DriverManager.getConnection(URL);
    }

    public static void inicializar() throws Exception {

        Connection conn = getConnection();

        Statement st = conn.createStatement();

        st.execute("""
            CREATE TABLE IF NOT EXISTS productos(
                qr TEXT PRIMARY KEY,
                codigo TEXT,
                descripcion TEXT,
                marca TEXT,
                talle TEXT,
                color TEXT,
                stock INTEGER
            )
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS ventas(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                qr TEXT,
                fecha DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """);

        st.close();
        conn.close();
    }
}