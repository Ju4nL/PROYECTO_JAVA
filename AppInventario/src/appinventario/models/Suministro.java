package appinventario.models;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Suministro implements CSVConvertible{
      
    //Atributos de Suministro
    private int id;                 // Identificador de Suministro
    private Producto producto;      // Producto 
    private int cantidad;           // Cantidad actual en stock
    private Date fechaCaducidad;    // Fecha de caducidad del suministro
    private Proveedor proveedor;    // Proveedor del suministro
    private String filePath=System.getProperty("user.dir")+ "/csv/suministros.csv";
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    
    // Constructor
    public Suministro(int id,Producto producto, int cantidad,Date fechaCaducidad,Proveedor proveedor) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.proveedor = proveedor;
    }

    public Suministro() {
    }
    
    
    // Métodos
    @Override
    public int getId (){ 
        return id;
    }   
    public Producto getProducto() {
        return producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }  

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }
    public Proveedor getProveedor() {
        return proveedor;
    }
    @Override
    public String getFilePath() {
        return filePath;
    }
    
    public void setId (int id){ 
        this.id = id;
    } 
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    
    // Metodos CSVOld
    @Override
    public String toCSV() {
        return id + "," + producto.getId()+","+cantidad+","+formatoFecha.format(fechaCaducidad)+","+proveedor.getId();
    }

    @Override
    public void fromCSV(String csvData) {                      
        String[] data = csvData.split(",");
        this.id = Integer.parseInt(data[0]);
        this.cantidad = Integer.parseInt(data[2]);
        
        //Convirtiendo fecha de String a Date 
        
        try {
            this.fechaCaducidad = formatoFecha.parse(data[3]);
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + data[3]);
            e.printStackTrace();  // Esto imprimirá la traza del error en la consola
        }
        
        //Creando modelos de relacion
            //Creando Objeto Producto
            CSV<Producto> productoCSV = new CSV<>(Producto.class);
            int productoId = Integer.parseInt(data[1]);
            Producto productoObjeto=productoCSV.leerPorId( productoId);
            if (productoObjeto != null) {
                this.producto = productoObjeto;
            } else {
                this.producto=null;
            }

            //Creando Objeto Proveedor por idproveedor
            CSV<Proveedor> proveedorCSV = new CSV<>(Proveedor.class);
            int proveedorId = Integer.parseInt(data[4]);  // Índice actualizado
            Proveedor proveedorObjeto = proveedorCSV.leerPorId( proveedorId);

            if (proveedorObjeto != null) {
                this.proveedor = proveedorObjeto;
            } else {
                this.proveedor=null;
            }

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
    
    
    public void mostrarTabla(){
         System.out.printf("%-10s %-20s %-15s %-15s %-25s%n",  
                this.getId(), 
                this.getProveedor().getNombre(),
                this.getProducto().getNombre(), 
                this.getCantidad(), 
                this.getFechaCaducidad());
    }
    
    //Metodo statico
    public static void cabeceras(){
        System.out.println("");
        System.out.printf("%-10s %-20s %-15s %-15s %-25s%n", "ID", "Proveedor", "Producto", "Cantidad", "Fecha Cad.");

        // Línea separadora
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
     // Método para agregar stock
    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        } else {
            System.out.println("Cantidad a agregar debe ser positiva.");
        }
    }

    // Método para reducir stock
    public void reducirStock(int cantidad) {
        if (cantidad > 0) {
            if (this.cantidad >= cantidad) {
                this.cantidad -= cantidad;
            } else {
                System.out.println("No hay suficiente stock para reducir.");
            }
        } else {
            System.out.println("Cantidad a reducir debe ser positiva.");
        }
    }
}
