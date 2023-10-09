
package appinventario.storage;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CSVUtil {

    /**
     * @param args the command line arguments
     */
    
    private static final String CSV_DELIMITER = ","; //delimitador del csv
    
    
    //FUNCIONES
    //Listar todo
    public static List<String[]> leerCSV(String filePath) {
        List<String[]> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String linea;
            while ((linea = br.readLine()) != null) {
                registros.add(linea.split(CSV_DELIMITER));
            }
        } catch (IOException e) {
            e.printStackTrace();  
        }

        return registros;
    }
    
}
