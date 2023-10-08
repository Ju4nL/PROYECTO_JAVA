package appinventario.storage;

import appinventario.models.Producto;
import appinventario.models.Proveedor;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CSVHandler {

    // Atributo de la clase
    private static String rutaEjecucion = System.getProperty("user.dir");
    private static final String PRODUCTOS_FILE_PATH = rutaEjecucion + "/AppInventario/src/appinventario/csv/productos.csv";
    private static final String PROVEEDORES_FILE_PATH = rutaEjecucion + "/AppInventario/src/appinventario/csv/proveedores.csv";

    // Clase interna para manejar productos
    public static class CSVProductoHandler {
        // Método para leer datos de Productos según la estructura del modelo
        // ID_Producto,Nombre_Producto,Descripcion_Producto,Precio_Producto,CantidadStock_Producto,UnidadMedida_Producto,ID_Proveedor
        public static List<Producto> leerProductos() {
            List<Producto> productos = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(PRODUCTOS_FILE_PATH))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] celdas = linea.split(",");
                    // Valores que se obtiene de cada linea
                    int id = Integer.parseInt(celdas[0]); // ID_Producto
                    String nombre = celdas[1]; // Nombre_Producto
                    String descripcion = celdas[2]; // Descripcion_Producto
                    double precio = Double.parseDouble(celdas[3]); // Precio_Producto
                    int stock = Integer.parseInt(celdas[4]); // CantidadStock_Producto
                    String unidadMedida = celdas[5]; // UnidadMedida_Producto
                    int idProveedor = Integer.parseInt(celdas[6]); //ID_Proveedor
                    Proveedor proveedor = CSVProveedorHandler.obtenerProveedorPorID(idProveedor);
                    Producto producto = new Producto(id, nombre, descripcion, precio, stock, unidadMedida, proveedor);
                    productos.add(producto);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return productos;
        }
    }

    //Clase interna para manejar proveedores
    public static class CSVProveedorHandler {
        //Método para leer proveedores desde el archivo CSV de proveedores
        //ID_Proveedor,Nombre_Proveedor,Telefono_Proveedor,Direccion_Proveedor,Email_Proveedor
        public static List<Proveedor> leerProveedores() {
            List<Proveedor> proveedores = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(PROVEEDORES_FILE_PATH))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] celdas = linea.split(",");
                    // Valores que se obtiene de cada linea
                    int id = Integer.parseInt(celdas[0]);
                    String nombre = celdas[1];
                    String telefono = celdas[2];
                    String direccion = celdas[3];
                    String email = celdas[4];
                    //Creamos un nuevo modelo y lo agregamos a la coleccion de proveedores
                    Proveedor proveedor = new Proveedor(id, nombre, telefono, direccion, email);
                    proveedores.add(proveedor);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return proveedores;
        }

        //Método para obtener buscar un Proveedor por su ID
        public static Proveedor obtenerProveedorPorID(int idProveedor) {
            List<Proveedor> proveedores = leerProveedores();
            for (Proveedor proveedor : proveedores) {
                if (proveedor.getId() == idProveedor) {
                    return proveedor; //Se encontró el proveedor con el ID especificado
                }
            }
            return null; //No se encontró ningún proveedor con el ID especificado
        }
    }

}
