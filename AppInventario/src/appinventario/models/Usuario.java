package appinventario.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario implements CSVConvertible {

    // Atributos del modelo Usuario
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    // Ruta del archivo CSV
    private String filePath = System.getProperty("user.dir") + "/src/appinventario/csv/usuarios.csv";

    // Constructores
    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String usuario, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
    }

    // Getters y Setters
    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    // CSV
    @Override
    public String getFilePath() {
        return filePath;
    }

    @Override
    public String toCSV() {
        return id + "," + nombre + "," + apellido + "," + usuario + "," + password;
    }

    @Override
    public void fromCSV(String csvData) {
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.apellido = data[2];
        this.usuario = data[3];
        this.password = data[4];
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