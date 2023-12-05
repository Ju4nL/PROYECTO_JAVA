package appinventario.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario implements CSVConvertible {

    // Atributos del modelo Usuario
    private int id;
    private boolean Admin;
    private String nombre;
    private String apellido;
    private String telefono;
    private String usuario;
    private String password;
    private String cargo;
    // Ruta del archivo CSV
    private String filePath = System.getProperty("user.dir") + "/src/appinventario/csv/usuarios.csv";

    // Constructores
    public Usuario() {
    }

    public Usuario(int id, boolean Admin, String nombre, String apellido, String telefono, String usuario, String password, String cargo) {
        this.id = id;
        this.Admin = Admin;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
        this.cargo = cargo;
    }

    // Getters y Setters
    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return this.Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // CSV
    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toCSV() {
        return id + "," + Admin + "," + nombre + "," + apellido + "," + telefono + "," + usuario + "," + password + "," + cargo;
    }

    @Override
    public void fromCSV(String csvData) {
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.Admin = Boolean.parseBoolean(data[1]);
        this.nombre = data[2];
        this.apellido = data[3];
        this.telefono = data[4];
        this.usuario = data[5];
        this.password = data[6];
        this.cargo = data[7];
    }

    public static void cabeceras(){
        System.out.println("");
        System.out.printf("%-10s %-15s %-20s %-20s %-25s %-25s%n", "ID", "Admin", "Nombre", "Apellido", "Teléfono", "Usuario");

        // Línea separadora
        for (int i = 0; i < 115; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void mostrarTabla(){
        System.out.printf("%-10d %-15s %-20s %-20s %-25s %-25s%n",
                this.getId(), 
                this.isAdmin(), 
                this.getNombre(), 
                this.getApellido(), 
                this.getTelefono(),
                this.getUsuario());
    }

    // Genera el hash SHA-256 de la contraseña proporcionada.
    public static String hashPassword(String password) {
        try {
            // Algoritmo SHA-256 para generar el hash del password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // Convertir los bytes a una representación hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}