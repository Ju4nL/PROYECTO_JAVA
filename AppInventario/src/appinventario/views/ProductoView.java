package appinventario.views;

import appinventario.controlers.ProductoControler;
import appinventario.models.Producto;
import appinventario.utils.Utilidades;

import java.util.List;
import java.util.Scanner;

public class ProductoView {

    private ProductoControler controlador;
    private Scanner scanner;

    public ProductoView() {
        controlador = new ProductoControler();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| *****  GESTIÓN DE PRODUCTOS  ***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Registrar Producto              |");
            System.out.println("| 2. Ver Producto por ID             |");
            System.out.println("| 3. Ver todos los Productos         |");
            System.out.println("| 4. Actualizar Producto             |");
            System.out.println("| 5. Eliminar Producto               |");
            System.out.println("| 6. Salir                           |");
            System.out.println("+------------------------------------+");
            String opcion = Utilidades.solicitarInput("| Seleccione una opción: ", scanner);

            switch (opcion) {
                case "1":
                    registrarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "2":
                    verProductoPorId();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "3":
                    verTodosLosProductos();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "4":
                    actualizarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "5":
                    eliminarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "6":
                    System.out.println("Saliendo...");
                    return;
                default:
                    Utilidades.imprimirMensaje("Opción no válida. Por favor, intente de nuevo.", "error");
                    Utilidades.cleanConsolaPausa();
            }
        }
    }

    private void registrarProducto() {
        Utilidades.cleanConsola();
        System.out.println(" ====> REGISTRAR NUEVO PRODUCTO");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int id = 0;
        // Generando inputs
        nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
        if (nombre == null) { return; }

        categoria = Utilidades.solicitarInput("-> Ingrese categoria: ", scanner);
        if (categoria == null) { return; }

        descripcion = Utilidades.solicitarInput("-> Ingrese descripcion: ", scanner);
        if (descripcion == null) { return; }

        unidad_medida = Utilidades.solicitarInput("-> Ingrese unidad de medida: ", scanner);
        if (unidad_medida == null) { return; }

        String precioString = Utilidades.solicitarInput("-> Ingrese precio: ", scanner);
        if (precioString == null) { return; }

        try {
            precio = Double.parseDouble(precioString);
        } catch (Exception e) {
            precio = 0.0f;
        }

        // Creando objeto producto
        Producto producto = new Producto(id, nombre, categoria, descripcion, precio, unidad_medida);
        boolean resultado = controlador.registrarProducto(producto);
        if (resultado) {
            Utilidades.imprimirMensaje("Producto registrado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al registrar el producto.", "error");
        }
    }

    private void verProductoPorId() {
        Utilidades.cleanConsola();
        System.out.println(" ====> VER PRODUCTO POR ID");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        String id = Utilidades.solicitarInput("-> Ingrese el ID del producto: ", scanner);
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }
        Producto producto = controlador.obtenerProductoPorId(idInt);
        if (producto != null) {
            Producto.cabeceras();
            producto.mostrarTabla();
        } else {
            Utilidades.imprimirMensaje("Producto no encontrado.", "error");
        }
    }

    private void verTodosLosProductos() {
        Utilidades.cleanConsola();
        System.out.println(" ====> LISTA DE PRODUCTOS");
        List<Producto> productos = controlador.obtenerTodosProductos();
        Producto.cabeceras();
        // Datos de los productos
        for (Producto producto : productos) {
            producto.mostrarTabla();
        }
    }

    private void actualizarProducto() {
        verTodosLosProductos();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int idInt;
        // Generando inputs
        String id = Utilidades.solicitarInput("-> Ingrese el id del producto para actualizar: ", scanner);
        if (id == null) { return; }
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }

        // Print de producto a actualizar
        Producto producto = controlador.obtenerProductoPorId(idInt);
        if (producto != null) {
            Producto.cabeceras();
            producto.mostrarTabla();
            System.out.println("");
            String respuesta = Utilidades.solicitarInput("¿Desea actualizar todo el producto?  (Sí = 1 | No = 2): ", scanner, 0);
            if (respuesta == null) { return; }

            if (respuesta.equals("1")) {
                String input = Utilidades.solicitarInput("Presione 1 para salir, o Enter para continuar: ", scanner, 0);

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                    if (nombre == null) { return; }

                    categoria = Utilidades.solicitarInput("-> Ingrese categoria: ", scanner);
                    if (categoria == null) { return; }

                    descripcion = Utilidades.solicitarInput("-> Ingrese descripcion: ", scanner);
                    if (descripcion == null) { return; }

                    unidad_medida = Utilidades.solicitarInput("-> Ingrese unidad de medida: ", scanner);
                    if (unidad_medida == null) { return; }

                    String precioString = Utilidades.solicitarInput("-> Ingrese precio: ", scanner);
                    if (precioString == null) { return; }

                    try {
                        precio = Double.parseDouble(precioString);
                    } catch (Exception e) {
                        precio = 0.0f;
                    }
                }
            } else {

                String atributo = "";
                nombre = producto.getNombre();
                categoria = producto.getCategoria();
                descripcion = producto.getDescripcion();
                unidad_medida = producto.getUnidad_medida();
                precio = producto.getPrecio();

                while (true) {
                    atributo = Utilidades.solicitarInput("Ingrese el atributo que quiere actualizar (nombre, categoria, descripcion, precio, unidad): ", scanner);
                    if (atributo == null) { return; }
                    
                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                            if (nombre == null) { return; }
                            break;
                        case "categoria":
                            categoria = Utilidades.solicitarInput("-> Ingrese categoria: ", scanner);
                            if (categoria == null) { return; }
                            break;
                        case "descripcion":
                            descripcion = Utilidades.solicitarInput("-> Ingrese descripcion: ", scanner);
                            if (descripcion == null) { return; }
                            break;
                        case "unidad":
                            unidad_medida = Utilidades.solicitarInput("-> Ingrese unidad de medida: ", scanner);
                            if (unidad_medida == null) { return; }
                            break;
                        case "precio":
                            String precioString = Utilidades.solicitarInput("-> Ingrese precio: ", scanner);
                            if (precioString == null) { return; }
                            try {
                                precio = Double.parseDouble(precioString);
                            } catch (Exception e) {
                                precio = 0.0f;
                            }
                            break;
                        default:
                            Utilidades.imprimirMensaje("Entrada no válida. Por favor, intente de nuevo.", "error");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }
            }

            // Creando objeto proveedor
            Producto productoActualizado = new Producto(idInt, nombre, categoria, descripcion, precio, unidad_medida);
            boolean resultado = controlador.actualizarPorId(idInt, productoActualizado);
            if (resultado) {
                Utilidades.imprimirMensaje("Producto actualizado con éxito.", "success");
                Producto productoAct = controlador.obtenerProductoPorId(idInt);
                Producto.cabeceras();
                productoAct.mostrarTabla();
            } else {
                Utilidades.imprimirMensaje("Error al actualizar el producto.", "error");
            }
        } else {
            Utilidades.imprimirMensaje("Producto no encontrado.", "error");
        }
    }

    private void eliminarProducto() {
        verTodosLosProductos();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        String id = Utilidades.solicitarInput("-> Ingrese el ID del producto a eliminar: ", scanner);
        if (id == null) { return; }
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }
        boolean resultado = controlador.eliminarPorId(idInt);
        if (resultado) {
            Utilidades.imprimirMensaje("Producto eliminado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al eliminar el producto.", "error");
        }
    }

    public static void main(String[] args) {
        Utilidades.cleanConsola();
        ProductoView vista = new ProductoView();
        vista.mostrarMenu();
    }
}
