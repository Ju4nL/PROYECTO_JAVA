package appinventario.models;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Suministro {

    //Atributos de Suministro
    private int id;                 //identificador de Suministro
    private String nombre;          // Nombre del suministro
    private String categoria;       // Categoría del suministro (ej: verduras, carnes, etc.)
    private int cantidad;           // Cantidad actual en stock
    private String unidadMedida;    // Unidad de medida (ej: kilogramos, litros, etc.)
    private Date fechaCaducidad;    // Fecha de caducidad del suministro
    private Proveedor proveedor;    // Proveedor del suministro
    private String filePath=System.getProperty("user.dir")+ "/src/appinventario/csv/suministros.csv";
    // Constructor
    public Suministro(int id,String nombre, String categoria, int cantidad, 
                      String unidadMedida, Date fechaCaducidad,Proveedor proveedor) {
        this.id = id; 
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.fechaCaducidad = fechaCaducidad;
        this.proveedor = proveedor;
        
        //this.proveedor = proveedor;
    }

    // Métodos
    public int getId (){ 
        return id;
    }   
    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
            return categoria;
        }
    public int getCantidad() {
        return cantidad;
    }  
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }
    public Proveedor getProveedor() {
        return proveedor;
    }
    public String getFilePath() {
        return filePath;
    }
    
    
    public void setId (int id){ 
        this.id = id;
    } 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    
    // Metodos CSV
    public String toCSV() {
        return id + "," + nombre+","+categoria+","+cantidad+","+unidadMedida+fechaCaducidad+proveedor;
    }


    public void fromCSV(String csvData) {                      
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.categoria = data[2];
        this.cantidad = Integer.parseInt(data[3]);
        this.unidadMedida = data[4];        
    }
    
    
    // Método para actualizar el stock
    public void actualizarStock(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
    }

    // Método para verificar si el suministro está en stock
    public boolean estaEnStock() {
        return cantidad > 0;
    }

    // Método para verificar si el suministro está caducado
    public boolean estaCaducado() {
        Date hoy = new Date();
        return fechaCaducidad.before(hoy);
    }
    
    
}
