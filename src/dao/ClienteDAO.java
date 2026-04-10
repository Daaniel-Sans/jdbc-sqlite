package dao;

import pojo.Cliente;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class ClienteDAO {
    private String url = "jdbc:sqlite:prueba.sqlite3";

    public void insertarCliente(Cliente a) {

        try (Connection conn = getConnection(url)) {
            String sql = "INSERT INTO clientes (nombre, email, telefono, edad, dinero_gastado, productos_comprados) VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    public void actualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, email=?, telefono=?, edad=?, " +
                "dinero_gastado=?, productos_comprados=? WHERE id=?";
        try (Connection con = getConnection(url);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setInt(4, cliente.getEdad());
            pstmt.setDouble(5, cliente.getDineroGastado());
            pstmt.setInt(6, cliente.getProductosComprados());
            pstmt.setInt(7, cliente.getId());
            int filas = pstmt.executeUpdate();
            System.out.println("Clientes actualizados: " + filas);

        } catch (SQLException e) {
            System.err.println("Vaaaaya hombre... Hubo un error al actualizar cliente: " + e.getMessage() + " 😿");
        }
    }

    public void borrar(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection con = getConnection(url);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();
            System.out.println("Clientes borrados: " + filas);

        } catch (SQLException e) {
            System.err.println("uUuUups... Error al borrar cliente: " + e.getMessage() + " 😿");
        }
    }



}


