package dao;

import database.DatabaseManager;
import model.Producto;

import java.sql.*;

public class ProductoDAO {

    public void guardar(Producto p) throws Exception {

        Connection conn =
                DatabaseManager.getConnection();

        PreparedStatement ps =
                conn.prepareStatement("""
                INSERT OR REPLACE INTO productos
                (qr,codigo,descripcion,marca,talle,color,stock)
                VALUES(?,?,?,?,?,?,?)
                """);

        ps.setString(1, p.getQr());
        ps.setString(2, p.getCodigo());
        ps.setString(3, p.getDescripcion());
        ps.setString(4, p.getMarca());
        ps.setString(5, p.getTalle());
        ps.setString(6, p.getColor());
        ps.setInt(7, p.getStock());

        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public Producto buscarPorQR(String qr) throws Exception {

        Connection conn =
                DatabaseManager.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        "SELECT * FROM productos WHERE qr=?");

        ps.setString(1, qr);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {

            Producto p = new Producto();

            p.setQr(rs.getString("qr"));
            p.setCodigo(rs.getString("codigo"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setMarca(rs.getString("marca"));
            p.setTalle(rs.getString("talle"));
            p.setColor(rs.getString("color"));
            p.setStock(rs.getInt("stock"));

            rs.close();
            ps.close();
            conn.close();

            return p;
        }

        rs.close();
        ps.close();
        conn.close();

        return null;
    }

    public void actualizarStock(
            String qr,
            int stock) throws Exception {

        Connection conn =
                DatabaseManager.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(
                        "UPDATE productos SET stock=? WHERE qr=?");

        ps.setInt(1, stock);
        ps.setString(2, qr);

        ps.executeUpdate();

        ps.close();
        conn.close();
    }
}