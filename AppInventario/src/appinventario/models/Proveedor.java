package appinventario.models;

public class Proveedor {

    // Aqui sus atributos
    private int id; // identificador del proveedor
    private String nombre; // nombre o marca del proveedor
    private String telefono; // telefono del proveedor
    private String direccion; // dirección del proveedor
    private String email; // correo electronico del proveedor
    private String filePath = System.getProperty("user.dir") + "/src/appinventario/csv/proveedores.csv";

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

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getFilePath() {
        return filePath;
    }

    // Metodos CSV
    public String toCSV() {
        return id + "," + nombre + "," + telefono + "," + direccion + "," + email;
    }

    public void fromCSV(String csvData) {
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.telefono = data[2];
        this.direccion = data[3];
        this.email = data[4];
    }
}
