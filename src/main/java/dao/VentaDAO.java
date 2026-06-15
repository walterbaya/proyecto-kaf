package dao;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class VentaDAO {

    public void registrar(String qr) throws Exception {

        Connection conn =
                DatabaseManager.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        "INSERT INTO ventas(qr) VALUES(?)");

        ps.setString(1, qr);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}