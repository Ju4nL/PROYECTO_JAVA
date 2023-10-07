
package appinventario.models;


public class Producto {
    //atributos de producto
    private int id; //identificador de producto
    private String nombre; //nombre de producto
    private String descripcion; //descripcion de producto 
    private double precio; //precio de producto
    private int cantidad_stock; //cantidad disponible en el inventario
    private String unidad_medida; //unidad de medida(unida, gramos, litros, kilogramos)
    private Proveedor proveedor; // proveedor del producto
    
    public Producto (int id, String nombre, String descripcion, double precio, int cantidad_stock, String unidad_medida, Proveedor proveedor){
    
        this.id = id; 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad_stock = cantidad_stock;
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
    public int getCantidad_stock (){
        return cantidad_stock;
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
    public void setCantidad_stock (int stock){
        this.cantidad_stock = stock;
    }
    public void setUnidad_medida (String medida) {
        this.unidad_medida = medida;
    }
}