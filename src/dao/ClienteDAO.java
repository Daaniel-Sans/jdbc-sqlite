package dao;
import pojo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ClienteDAO {
    private String url = "jdbc:sqlite:prueba.sqlite3";

    public void insertarCliente(Cliente a) {
        String sql = "INSERT INTO clientes (nombre, email, telefono, edad, dinero_gastado, productos_comprados) VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

    public Cliente obtenerClientePorId (int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente clienteEncontrado = null;

        try(Connection conn = getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try(ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    clienteEncontrado = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getInt("edad"),
                            rs.getDouble("dinero_gastado"),
                            rs.getInt("productos_comprados")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar al cliente por id. Hubo un error" + e.getMessage());
        }

        return clienteEncontrado;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = getConnection(url)) {
             String sql = "SELECT * FROM clientes";
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String nombre = rs.getString("nombre");
                 String email = rs.getString("email");
                 String telefono = rs.getString("telefono");
                 int edad = rs.getInt("edad");
                 double dineroGastado = rs.getDouble("dinero_gastado");
                 int productosComprados = rs.getInt("productos_comprados");
                 Cliente a = new Cliente(id, nombre, email, telefono, edad, dineroGastado, productosComprados);
                 clientes.add(a);
             }
        }catch (SQLException e) {
            System.out.println("Error 😿" + e.getMessage());
        }
        return clientes;
    }
}


