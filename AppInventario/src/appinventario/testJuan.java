 
package appinventario;

import appinventario.models.Proveedor;
import appinventario.storage.CSV;
import java.util.List;

/**
 *
 * @author J_lozada
 */
public class testJuan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    CSV<Proveedor> proveedorCSV = new CSV<>();

    List<Proveedor> proveedores = proveedorCSV.leerCSV(Proveedor.class);
    
    for (Proveedor proveedor : proveedores) {
        System.out.println("ID: " + proveedor.getId());
        System.out.println("Nombre: " + proveedor.getNombre());
        System.out.println("Telefono: " + proveedor.getTelefono());
        System.out.println("Direccion: " + proveedor.getDireccion());
        System.out.println("Email: " + proveedor.getEmail());
        System.out.println("-------------------");
    }
        
    }
    
}
