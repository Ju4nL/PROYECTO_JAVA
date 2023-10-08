package appinventario.models;

public class Proveedor {

    // Aqui sus atributos
    private int id; // identificador del proveedor
    private String nombre; // nombre o marca del proveedor
    private String telefono; // telefono del proveedor
    private String direccion; // dirección del proveedor
    private String email; // correo electronico del proveedor

    // Constructor
    public Proveedor(int _id, String _nombre, String _telefono, String _direccion, String _email) {
        this.id = _id;
        this.nombre = _nombre;
        this.telefono = _telefono;
        this.direccion = _direccion;
        this.email = _email;
    }

    // getters
    public int getId() {
        return id;
    }

}
