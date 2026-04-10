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

    public List<Cliente> obtenerMayoresDe30() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE edad > 30";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente a = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("dinero_gastado"),
                        rs.getInt("productos_comprados")
                );
                clientes.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por edad 😿: " + e.getMessage());
        }
        return clientes;
    }

    public List<Cliente> obtenerGastanMasDe500() {
        List<Cliente> clientes = new ArrayList<>();
        // Filtramos por la columna dinero_gastado
        String sql = "SELECT * FROM clientes WHERE dinero_gastado > 500";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente a = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("dinero_gastado"),
                        rs.getInt("productos_comprados")
                );
                clientes.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar por gasto 😿: " + e.getMessage());
        }
        return clientes;
    }

    public List<Cliente> obtenerTop3Compradores() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY productos_comprados DESC LIMIT 3";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente a = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("dinero_gastado"),
                        rs.getInt("productos_comprados")
                );
                clientes.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el Top 3 😿: " + e.getMessage());
        }
        return clientes;
    }

    public double obtenerSumaTotalGastada() {
        double total = 0;
        String sql = "SELECT SUM(dinero_gastado) FROM clientes";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular la suma total 😿: " + e.getMessage());
        }
        return total;
    }

    public double obtenerMediaProductos() {
        double media = 0;
        String sql = "SELECT AVG(productos_comprados) FROM clientes";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                media = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular la media de productos 😿: " + e.getMessage());
        }
        return media;
    }

    public double obtenerMediaGastoFiltrado() {
        double media = 0;
        String sql = "SELECT AVG(dinero_gastado) FROM clientes WHERE edad > 25 AND productos_comprados > 3";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                media = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular media filtrada 😿: " + e.getMessage());
        }
        return media;
    }

    public int contarClientesGastoMayor100() {
        int cantidad = 0;
        String sql = "SELECT COUNT(*) FROM clientes WHERE dinero_gastado > 100";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al contar clientes 😿: " + e.getMessage());
        }
        return cantidad;
    }

    public int contarClientesEntre30y50() {
        int cantidad = 0;
        // BETWEEN incluye tanto el 30 como el 50
        String sql = "SELECT COUNT(*) FROM clientes WHERE edad BETWEEN 30 AND 50";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cantidad = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al contar clientes por rango de edad 😿: " + e.getMessage());
        }
        return cantidad;
    }

    public List<Cliente> obtenerGastanMas200OrdenadosPorProductos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE dinero_gastado > 200 ORDER BY productos_comprados ASC";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Cliente a = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("dinero_gastado"),
                        rs.getInt("productos_comprados")
                );
                clientes.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta combinada 😿: " + e.getMessage());
        }
        return clientes;
    }

    public Cliente obtenerClienteMasJovenGasto400() {
        Cliente cliente = null;
        String sql = "SELECT * FROM clientes WHERE dinero_gastado > 400 ORDER BY edad ASC LIMIT 1";

        try (Connection conn = getConnection(url)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getInt("edad"),
                        rs.getDouble("dinero_gastado"),
                        rs.getInt("productos_comprados")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar al más manirroto 😿: " + e.getMessage());
        }
        return cliente;
    }

}


