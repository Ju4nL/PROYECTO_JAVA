package appinventario.models;
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

    // Constructor
    public Suministro(String nombre, String categoria, int cantidad, 
                      String unidadMedida, Date fechaCaducidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.fechaCaducidad = fechaCaducidad;
        //this.proveedor = proveedor;
    }

    // Métodos
    // Obtener el nombre del suministro
    public String getNombre() {
        return nombre;
    }

    // Establecer el nombre del suministro
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Obtener la categoría del suministro
    public String getCategoria() {
        return categoria;
    }

    // Establecer la categoría del suministro
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Obtener la cantidad en stock
    public int getCantidad() {
        return cantidad;
    }

    // Establecer la cantidad en stock
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Obtener la unidad de medida
    public String getUnidadMedida() {
        return unidadMedida;
    }

    // Establecer la unidad de medida
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    // Obtener la fecha de caducidad
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    // Establecer la fecha de caducidad
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    // Obtener el proveedor
    public Proveedor getProveedor() {
        return proveedor;
    }

    // Establecer el proveedor
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
