 
package appinventario;

import appinventario.controlers.ProductoControler;
import appinventario.controlers.ProveedorControler;
import appinventario.controlers.SuministroControler;
import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import appinventario.models.CSV;
import appinventario.views.ProveedorView;
import appinventario.views.SuministroView;
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
    
    ProveedorView proveedorview=new ProveedorView();
    SuministroView suministroview=new SuministroView();
    
    System.out.println("+------------------------------------+");
    System.out.println("| *********** INVENTARIO     ******* |");
    System.out.println("+------------------------------------+");
    System.out.println("| 1. Registrar Suministro            |");
    System.out.println("| 2. Ver Suministro por ID           |");
    proveedorview.mostrarMenu();
    suministroview.mostrarMenu();
}
}