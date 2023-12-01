package appinventario.models;

public class Producto implements CSVConvertible{
    
    //Atributos de producto
    private int id; //identificador de producto
    private String nombre; //nombre de producto
    private String categoria;
    private String descripcion; //descripcion de producto 
    private double precio; //precio de producto
    private String unidad_medida; //unidad de medida(unidad, gramos, litros, kilogramos)
    private String filePath=System.getProperty("user.dir")+ "/src/appinventario/csv/productos.csv";
    //private Proveedor proveedor; // proveedor del producto esto lo hara la clase suministro
    
    //Constructor
    public Producto (int id,String nombre,String categoria, String descripcion, double precio, String unidad_medida){
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.unidad_medida = unidad_medida;
        //this.proveedor = proveedor;
    }

    public Producto() {
    }

    public Producto(int productoId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //getters
    @Override
    public int getId (){ 
        return id;
    }   
    public String getNombre (){
        return nombre;
    }
    public String getCategoria() {
            return categoria;
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
    @Override
    public String getFilePath() {
        return filePath;
    }
    /*
    public Proveedor getProveedor () {
        return proveedor;
    }
    */
    //setters
    public void setId (int id){ 
        this.id = id;
    }   
    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
     /*
    public void setProveedor (Proveedor proveedor){
        this.proveedor = proveedor;
    }*/

    // Método CVS
    @Override
    public String toCSV() {
        return id + "," + nombre +","+categoria+ "," + descripcion + "," + precio + "," + unidad_medida ;
    }
    
    @Override
    public void fromCSV(String csvData) {
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.nombre = data[1];
        this.categoria = data[2];
        this.descripcion = data[3];
        this.precio = Double.parseDouble(data[4]);
        this.unidad_medida = data[5];
    }
    
     public void mostrarTabla(){
        System.out.printf("%-10d %-20s %-15s %-30s %-25s %-20s%n",
                this.getId(), 
                this.getNombre(), 
                this.getCategoria(), 
                this.getDescripcion(), 
                this.getPrecio(),
                this.getUnidad_medida());
    }
      
    //Metodo statico
    public static void cabeceras(){
        System.out.println("");
        System.out.printf("%-10s %-20s %-15s %-30s %-25s %-15s%n", "ID", "Nombre", "Categoría", "Descripción", "Precio", "Unidad");

        // Línea separadora
        for (int i = 0; i < 115; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}