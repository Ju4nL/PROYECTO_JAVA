 
package appinventario;

import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import appinventario.models.CSV;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author J_lozada
 */
public class testJuan {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws ParseException{
    
    CSV<Producto> productoCSV = new CSV<>(Producto.class);
    
     
    Producto productoObj=new Producto();
    productoCSV.registrar(productoObj);
    
    List<Producto> productos=productoCSV.leerCSV();
    for (Producto producto : productos) {
        System.out.println(producto.getId()+producto.getNombre());

    }
    /*
    CSVOld<Producto> productoCSV = new CSVOld<>();
    List<Producto> productos=productoCSV.leerCSV(Producto.class);    
    
    int maxid=productoCSV.obtenerIdMaximo(Producto.class)    ;
    
    System.out.println(maxid);
    */
    /*
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = formatoFecha.parse("01/01/2024");
    
    CSVOld<Producto> productoCSV = new CSVOld<>();
    CSVOld<Proveedor> proveedorCSV = new CSVOld<>();
    List<Producto> productos=productoCSV.leerCSV(Producto.class);
    
    CSVOld<Suministro> suministroCSV = new CSVOld<>();
    suministroCSV.eliminarPorId(Suministro.class,2);
    
    Suministro suministroObj= new Suministro(5,productoCSV.leerPorId(Producto.class, 3),3,fecha,proveedorCSV.leerPorId(Proveedor.class, 0));
    //suministroCSV.registrar(suministroObj);

    
    suministroCSV.actualizarPorId(Suministro.class,5,suministroObj);
    */
    /*
    CSVOld<Suministro> suministroCSV = new CSVOld<>();
    
    Suministro nuevoSuministro=new Suministro(5,productos.get(0),5,fecha,proveedores.get(0));
    boolean action=suministroCSV.registrar(nuevoSuministro);
    */
    
    
    /* mostrar un id
    Suministro suministro1 = suministroCSV.leerPorId(Suministro.class,0);
    Suministro suministro2 = suministroCSV.leerPorId(Suministro.class,1);
    
    System.out.println(suministro1.getProducto().getNombre()+" cantidad:"+suministro1.getCantidad());
    */
    
    /*
    for (Suministro suministro : suministros) {
        System.out.println( suministro.getId());
        System.out.println( suministro.getNombre());
        System.out.println( suministro.getFechaCaducidad());
        System.out.println( suministro.getProveedor().getNombre());
    }
    
    
    for (Proveedor proveedor : proveedores) {
        System.out.println("ID: " + proveedor.getId());
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Telefono: " + proveedor.getTelefono());
        System.out.println("Direccion: " + proveedor.getDireccion());
        System.out.println("Email: " + proveedor.getEmail());
        System.out.println("-------------------");
    }
        */
    }
    
}
