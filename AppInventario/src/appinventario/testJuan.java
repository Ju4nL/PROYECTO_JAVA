 
package appinventario;

import appinventario.controlers.ProductoControler;
import appinventario.controlers.ProveedorControler;
import appinventario.controlers.SuministroControler;
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
    
        
    ProductoControler productoCt=new ProductoControler();
    List<Producto> productos =productoCt.obtenerTodosProductos();
    
    ProveedorControler proveedorCt=new ProveedorControler();
    List<Proveedor> proveedores =proveedorCt.obtenerTodosProveedores();
    
    SuministroControler suministroCt=new SuministroControler();
    List<Suministro> suministros =suministroCt.obtenerTodosSuministros();
    
    for (Producto producto : productos) {
        System.out.println(producto.getNombre());   
    }
    
    for (Proveedor proveedor : proveedores) {
        System.out.println(proveedor.getNombre());   
    }
    
    for (Suministro suministro : suministros) {
            System.out.println(suministro.getProducto().getNombre()+"--"+suministro.getFechaCaducidad());
    }

   }
    
}
