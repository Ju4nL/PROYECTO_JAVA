 
package appinventario;

import appinventario.controlers.ProductoControler;
import appinventario.controlers.ProveedorControler;
import appinventario.controlers.SuministroControler;
import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import appinventario.models.CSV;
import appinventario.views.ProveedorView;
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
    proveedorview.mostrarMenu();
}
}