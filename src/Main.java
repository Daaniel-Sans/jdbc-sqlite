import dao.ClienteDAO;
import pojo.Cliente;

public class Main {
    public static void main (String [] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente a = new Cliente("Ramiro", "ramiroramirez@gmail.com", "999888777", 82, 20.50, 2);

       clienteDAO.insertarCliente(a);
       System.out.println("✅ Cliente insertado correctamente.\n");
    }
}
