 
package appinventario;

import appinventario.controlers.ProductoControler;
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
    
    for (Producto producto : productos) {
        System.out.println(producto.getNombre());   
    }

   }
    
}
