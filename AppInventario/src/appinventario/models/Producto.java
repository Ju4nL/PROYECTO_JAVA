package appinventario.models;

public class Producto {
    
    //Atributos de producto
    private int id; //identificador de producto
    private String nombre; //nombre de producto
    private String descripcion; //descripcion de producto 
    private double precio; //precio de producto
    private String unidad_medida; //unidad de medida(unidad, gramos, litros, kilogramos)
    private Proveedor proveedor; // proveedor del producto
    
    //Constructor
    public Producto (int id, String nombre, String descripcion, double precio, String unidad_medida, Proveedor proveedor){
        this.id = id; 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidad_medida = unidad_medida;
        this.proveedor = proveedor;
    }
    
    //getters
    public int getId (){ 
        return id;
    }   
    public String getNombre (){
        return nombre;
    }
    public String getDescripcion (){
        return descripcion;
    }
    public double getPrecio (){
        return precio;
    }
    public String getUnidad_medida () {
        return unidad_medida;
    }
    public Proveedor getProveedor () {
        return proveedor;
    }
    
    //setters
    public void setId (int id){ 
        this.id = id;
    }   
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    public void setDescripcion (String descripcion){
        this.descripcion = descripcion;
    }
    public void setPrecio (double precio){
        this.precio = precio;
    }
    public void setUnidad_medida (String medida) {
        this.unidad_medida = medida;
    }
    public void setProveedor (Proveedor proveedor){
        this.proveedor = proveedor;
    }

    // Método CVS
    public String toCSV() {
        return id + "," + nombre + "," + descripcion + "," + precio + "," + unidad_medida + "," + proveedor.getId();
    }
    
}