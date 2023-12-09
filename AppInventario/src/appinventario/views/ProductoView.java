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
            Utilidades.solicitarInput("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 2:
                    verProductoPorId();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 3:
                    verTodosLosProductos();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 4:
                    actualizarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 5:
                    eliminarProducto();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 6:
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
        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int id = 0;
        scanner.nextLine();
        // Generando inputs
        Utilidades.solicitarInput("-> Ingrese nombre: ");
        nombre = scanner.nextLine();

        Utilidades.solicitarInput("-> Ingrese categoria: ");
        categoria = scanner.nextLine();

        Utilidades.solicitarInput("-> Ingrese descripcion: ");
        descripcion = scanner.nextLine();

        Utilidades.solicitarInput("-> Ingrese unidad de medida: ");
        unidad_medida = scanner.nextLine();

        Utilidades.solicitarInput("-> Ingrese precio: ");
        precio = scanner.nextDouble();

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
        Utilidades.solicitarInput("-> Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        Producto producto = controlador.obtenerProductoPorId(id);
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
        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int id;
        // Generando inputs
        Utilidades.solicitarInput("-> Ingrese el id del producto para actualizar: ");
        id = scanner.nextInt();

        // Print de producto a actualizar
        Producto producto = controlador.obtenerProductoPorId(id);
        if (producto != null) {
            Producto.cabeceras();
            producto.mostrarTabla();
            System.out.println("");
            Utilidades.solicitarInput("¿Desea actualizar todo el producto?  (Sí = 1 | No = 2): ", 0);
            int respuesta = scanner.nextInt();
            scanner.nextLine();

            if (respuesta == 1) {
                Utilidades.solicitarInput("Presione 1 para salir, o Enter para continuar: ", 0);
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    Utilidades.solicitarInput("-> Ingrese nombre: ");
                    nombre = scanner.nextLine();

                    Utilidades.solicitarInput("-> Ingrese categoria: ");
                    categoria = scanner.nextLine();

                    Utilidades.solicitarInput("-> Ingrese descripcion: ");
                    descripcion = scanner.nextLine();

                    Utilidades.solicitarInput("-> Ingrese unidad de medida: ");
                    unidad_medida = scanner.nextLine();

                    Utilidades.solicitarInput("-> Ingrese precio: ");
                    precio = scanner.nextDouble();
                }
            } else {

                String atributo = "";
                nombre = producto.getNombre();
                categoria = producto.getCategoria();
                descripcion = producto.getDescripcion();
                unidad_medida = producto.getUnidad_medida();
                precio = producto.getPrecio();

                while (true) {
                    Utilidades.solicitarInput("Ingrese el atributo que quiere actualizar (nombre, categoria, descripcion, precio, unidad): ");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            Utilidades.solicitarInput("-> Ingrese nombre: ");
                            nombre = scanner.nextLine();
                            break;
                        case "categoria":
                            Utilidades.solicitarInput("-> Ingrese categoría: ");
                            categoria = scanner.nextLine();
                            break;
                        case "descripcion":
                            Utilidades.solicitarInput("-> Ingrese descripción: ");
                            descripcion = scanner.nextLine();
                            break;
                        case "unidad":
                            Utilidades.solicitarInput("-> Ingrese unidad: ");
                            unidad_medida = scanner.nextLine();
                            break;
                        case "precio":
                            Utilidades.solicitarInput("-> Ingrese precio: ");
                            precio = scanner.nextDouble();
                            break;
                        default:
                            Utilidades.imprimirMensaje("Entrada no válida. Por favor, intente de nuevo.", "error");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }
            }

            // Creando objeto proveedor
            Producto productoActualizado = new Producto(id, nombre, categoria, descripcion, precio, unidad_medida);
            boolean resultado = controlador.actualizarPorId(id, productoActualizado);
            if (resultado) {
                Utilidades.imprimirMensaje("Producto actualizado con éxito.", "success");
                Producto productoAct = controlador.obtenerProductoPorId(id);
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
        Utilidades.solicitarInput("-> Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controlador.eliminarPorId(id);
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
