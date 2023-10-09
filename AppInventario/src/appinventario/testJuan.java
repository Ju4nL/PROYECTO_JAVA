 
package appinventario;

import appinventario.storage.CSVUtil;
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
        // TODO code application logic here
        final String RUTA_PRODUCTOS = System.getProperty("user.dir")+ "/src/appinventario/csv/proveedores.csv";
       
        
        List<String[]> datos = CSVUtil.leerCSV(RUTA_PRODUCTOS);
        
        // Imprimir el contenido de la lista
        for (String[] linea : datos) {
            for (String valor : linea) {
                System.out.print(valor + " ");
            }
            System.out.println(); // Salto de línea después de imprimir cada línea del CSV
        }
    }
    
}
