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
        // Sí funciona -> lproductos();
        // Sí funciona -> rproveedor();
        rproducto();
    }

    public static void rproducto() {
        Proveedor prov = CSVProveedorHandler.obtenerProveedorPorID(2);
        Producto pr = new Producto(6, "Mantequilla", "Manti", 1.80, "Kilos", prov);
        if (CSVProductoHandler.registrarProducto(pr)) {
            System.out.println("Se registró correctamente!");
        } else {
            System.out.println("No se pudo registra u.u");
        }
    }

    public static void rproveedor() {
        Proveedor pr = new Proveedor(2, "Altomayo", "47962757", "Av. Bolognesi 234", "altomayo@peru.com");
        if (CSVProveedorHandler.registrarProveedor(pr)) {
            System.out.println("Se registró correctamente!");
        } else {
            System.out.println("No se pudo registra u.u");
        }
    }

    public static void lproductos() {
        List<Producto> productos = CSVProductoHandler.leerProductos();
        for (Producto pr : productos) {
            System.out.println("PRODUCTO -> ID: " + pr.getId() + " | N: " + pr.getNombre() + " | D: "
                    + pr.getDescripcion() + " | P: " + pr.getPrecio() + " | U: "
                    + pr.getUnidad_medida() + " | IDPROVEEDOR: " + pr.getProveedor().getId());
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
