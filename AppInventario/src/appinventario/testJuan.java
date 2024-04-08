 
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
import java.util.Scanner;

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
    Scanner scanner = new Scanner(System.in);
    
    while (true) {
        System.out.println("+------------------------------------+");
        System.out.println("| *********** INVENTARIO     ******* |");
        System.out.println("+------------------------------------+");
        System.out.println("| 1. Registrar Suministro            |");
        System.out.println("| 2. Ver Suministro por ID           |");
        
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:suministroview.mostrarMenu();
            case 2:proveedorview.mostrarMenu();
            case 4:
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("Opción no válida. Intente nuevamente.");
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        
    }
    
    
}
}