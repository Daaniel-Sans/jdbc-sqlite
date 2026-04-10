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

        System.out.println("\n🎂 Clientes mayores de 30 años");
        List<Cliente> listaMayores = clienteDAO.obtenerMayoresDe30();
        for (Cliente c : listaMayores) {
            System.out.println("Nombre: " + c.getNombre() + " | Edad: " + c.getEdad());
        }

        System.out.println("\n💸 Clientes que han gastado más de 500€");
        List<Cliente> listaGasto500 = clienteDAO.obtenerGastanMasDe500();

        for (Cliente c : listaGasto500) {
            System.out.println("Nombre: " + c.getNombre() + " | Gastado: " + c.getDineroGastado() + "€");
        }

        System.out.println("\n🏆 Top 3 clientes que más productos han comprado");
        List<Cliente> top3 = clienteDAO.obtenerTop3Compradores();
        for (Cliente c : top3) {
            System.out.println(c.getNombre() + " con " + c.getProductosComprados() + " productos.");
        }

        System.out.println("\n💰 Dinero total");
        double sumaTotal = clienteDAO.obtenerSumaTotalGastada();
        System.out.println("La suma total es: " + sumaTotal + "€");

        System.out.println("\n📈 Media de los productos comprados");
        double mediaProductos = clienteDAO.obtenerMediaProductos();
        System.out.println("Los clientes compran una media de: " + mediaProductos + " productos.");

        System.out.println("\n📈 Media del gasto (Mayores de 25 años y > 3 productos)");
        double mediaFiltrada = clienteDAO.obtenerMediaGastoFiltrado();
        System.out.println("La media de los mayores de 25 con más de 3 productos es: " + mediaFiltrada + "€");

        System.out.println("\n👥 Cantidad de clientes con gasto > 100€");
        int numClientes = clienteDAO.contarClientesGastoMayor100();
        System.out.println("Hay " + numClientes + " clientes que han gastado más de 100€.");

        System.out.println("\n🎂 Cantidad de clientes que tienen entre 30 y 50 palos");
        int cantidadRango = clienteDAO.contarClientesEntre30y50();
        System.out.println("Hay " + cantidadRango + " clientes en ese rango de edad.");

        System.out.println("\n🛒 Clientes que han gastado > 200€ (Ordenados por productos comprados de menor a mayor)");
        List<Cliente> listaCombinada = clienteDAO.obtenerGastanMas200OrdenadosPorProductos();
        for (Cliente c : listaCombinada) {
            System.out.println(c.getProductosComprados() + " productos - " + c.getNombre() + " (" + c.getDineroGastado() + "€)");
        }

        System.out.println("\n👶 El cliente más joven con un gasto > 400€");
        Cliente jovenGasto400 = clienteDAO.obtenerClienteMasJovenGasto400();

        if (jovenGasto400 != null) {
            System.out.println("El cliente es: " + jovenGasto400.getNombre() +
                    " con " + jovenGasto400.getEdad() + " años.");
        } else {
            System.out.println("Nadie tiene tanto dinero para gastar siendo tan joven si no es albañil en 2008.");
        }
    }
}
