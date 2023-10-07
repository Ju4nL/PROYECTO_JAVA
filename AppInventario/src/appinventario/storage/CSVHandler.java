package appinventario.storage;

import appinventario.models.Producto;
import appinventario.models.Proveedor;

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
    //ID_Producto,Nombre_Producto,Descripcion_Producto,Precio_Producto,CantidadStock_Producto,UnidadMedida_Producto,ID_Proveedor
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] celdas = linea.split(",");
                int id = Integer.parseInt(celdas[0]);
                String nombre = celdas[1];
                String descripcion = celdas[2];
                double precio = Double.parseDouble(celdas[3]);
                int stock = Integer.parseInt(celdas[4]);
                String unidadMedida = celdas[5];
                //Solucionar esto 
                Proveedor proveedor = new Proveedor();
                //
                Producto producto = new Producto(id,nombre,descripcion,precio,stock,unidadMedida,proveedor);
                productos.add(producto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productos;
    }

}
