package appinventario.storage;

import appinventario.models.Producto;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CSVHandler {

    //Atributo de la clase
    private String filePath; //Ruta del archivo CSV

    //Constructor
    public CSVHandler(String filePath) {
        this.filePath = filePath;
    }

    //Método para leer datos de Productos según la estructura del modelo
    public List<Producto> leerProductos(String modelo) {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] celdas = linea.split(",");
                Producto producto = new Producto(Integer.parseInt(celdas[0]),celdas[1],celdas[2],Double.parseDouble(celdas[3]),Integer.parseInt(celdas[4]),celdas[5],);
                productos.add(producto);
            }
        } catch (IOException e) {

        }
        return productos;
    }

}
