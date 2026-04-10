import dao.ClienteDAO;
import pojo.Cliente;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();

        System.out.println("\n Insertar cliente");
        Cliente a = new Cliente("Ramiro Ramirez", "ramiroramirez@gmail.com", "999888777", 82, 20.50, 2);
        clienteDAO.insertarCliente(a);
        System.out.println("✅ Cliente insertado correctamente con ID: " + a.getId());

        a.setEdad(83);
        a.setDineroGastado(120.75);
        a.setProductosComprados(3);
        System.out.println("Actualización del cliente con ID " + a.getId() + "...");
        clienteDAO.actualizar(a);

        System.out.println("Borrando al cliente con ID " + a.getId());
        clienteDAO.borrar(a.getId());

        System.out.println("\n✅ Cliente borrado. Esto no ha pasado... 🥷");

        Cliente encontrado = clienteDAO.obtenerClientePorId(1);

        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getNombre() + " por su id #" + encontrado.getId());
        } else {
            System.out.println("Cliente no encontrado.");
        }

        System.out.println("\n🗒️ Listado de clientes \n --------------------------------------------------------------------------------------------------------------------------------------------");
        List<Cliente> lista = clienteDAO.obtenerClientes();
        for (Cliente c : lista) {
            System.out.println(c);
        }
    }
}
