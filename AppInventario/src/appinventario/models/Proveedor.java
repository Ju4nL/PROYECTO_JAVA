package appinventario.models;

public class Proveedor implements CSVConvertible{

    // Aqui sus atributos
    private int id; // identificador del proveedor
    private String nombre; // nombre o marca del proveedor
    private String telefono; // telefono del proveedor
    private String direccion; // dirección del proveedor
    private String email; // correo electronico del proveedor
    private String filePath = System.getProperty("user.dir") + "/src/appinventario/csv/proveedores.csv";

    // Constructor

    public Proveedor(int id,String nombre, String telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }
     
    public Proveedor() {
        // Constructor sin argumentos
    }
    // getters
    @Override
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
    
    @Override
    public String getFilePath() {
        return filePath;
    }
    
    //setters
    
    public void setId(int id){
        this.id=id;
    }
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setDireccion (String direccion){
        this.direccion = direccion;
    }
    public void setEmail (String email){
        this.email = email;
    }
    
    // Metodos CSV
    @Override
    public String toCSV() {
        return id + "," + nombre + "," + telefono + "," + direccion + "," + email;
    }
    @Override
    public void fromCSV(String csvData) {
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.telefono = data[2];
        this.direccion = data[3];
        this.email = data[4];
    }
}
