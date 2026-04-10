package dao;

import pojo.Cliente;

import java.sql.*;

public class ClienteDAO {
    private String url = "jdbc:sqlite:prueba.sqlite3";

    public void insertarCliente(Cliente a) {

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "INSERT INTO clientes (nombre, email, telefono, edad, dinero_gastado, productos_comprados) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, a.getNombre());
            pstmt.setString(2, a.getEmail());
            pstmt.setString(3, a.getTelefono());
            pstmt.setInt(4, a.getEdad());
            pstmt.setDouble(5, a.getDineroGastado());
            pstmt.setInt(6, a.getProductosComprados());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
            }

        }catch (SQLException e) {
            System.out.println("Error 😿" + e.getMessage());
        }

    }
}
