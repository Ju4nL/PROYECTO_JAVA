//
// NO TOCAR XD
//
package appinventario;

import java.util.List;

import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.storage.CSVHandler.CSVProductoHandler;
import appinventario.storage.CSVHandler.CSVProveedorHandler;

public class testezer {
    public static void main(String[] args) {
        // Sí funciona -> lproveedores();
        // Sí funciona -> lidproveedor(3);
        lproductos();
    }

    public static void lproductos() {
        List<Producto> productos = CSVProductoHandler.leerProductos();
        for (Producto pr : productos) {
            System.out.println(pr.getProveedor().getNombre());
        }
    }

    public static void lproveedores() {
        // Revisando si funciona el método para recoger los proveedores XD
        List<Proveedor> proveedores = CSVProveedorHandler.leerProveedores();
        for (Proveedor pr : proveedores) {
            System.out.println("PROVEEDOR -> ID: " + pr.getId() + " | N: " +
                    pr.getNombre() + " | D: " + pr.getDireccion() + " | T: " + pr.getTelefono() + " | E: "
                    + pr.getEmail());
        }
    }

    public static void lidproveedor(int id) {
        Proveedor pr = CSVProveedorHandler.obtenerProveedorPorID(id);
        if (pr == null)
            return;
        System.out.println("PROVEEDOR -> ID: " + pr.getId() + " | N: " + pr.getNombre() + " | D: " + pr.getDireccion()
                + " | T: " + pr.getTelefono() + " | E: " + pr.getEmail());
    }
}
