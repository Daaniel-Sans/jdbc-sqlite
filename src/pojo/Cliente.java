package pojo;

public class Cliente {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private int edad;
    private double dineroGastado;
    private int productosComprados;

    public Cliente(int id, String nombre, String email, String telefono, int edad, double dineroGastado, int productosComprados) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.dineroGastado = dineroGastado;
        this.productosComprados = productosComprados;
    }

    //Este constructor sin ID como parámetro lo necesito para manejar su autoincrement en el Main
    public Cliente(String nombre, String email, String telefono, int edad, double dineroGastado, int productosComprados) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;
        this.dineroGastado = dineroGastado;
        this.productosComprados = productosComprados;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getDineroGastado() {
        return dineroGastado;
    }

    public void setDineroGastado(double dineroGastado) {
        this.dineroGastado = dineroGastado;
    }

    public int getProductosComprados() {
        return productosComprados;
    }

    public void setProductosComprados(int productosComprados) {
        this.productosComprados = productosComprados;
    }
}
