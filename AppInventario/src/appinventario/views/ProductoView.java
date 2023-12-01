package appinventario.views;

import appinventario.controlers.ProductoControler;
import appinventario.models.Producto;

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
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    verProductoPorId();
                    break;
                case 3:
                    verTodosLosProductos();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private void registrarProducto() {
        System.out.println("Registrar nuevo Producto");
        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int id = 0;
        scanner.nextLine();
        // Generando inputs
        System.out.print("Ingrese nombre: ");
        nombre = scanner.nextLine();

        System.out.print("Ingrese categoria: ");
        categoria = scanner.nextLine();

        System.out.print("Ingrese descripcion: ");
        descripcion = scanner.nextLine();

        System.out.print("Ingrese unidad de medida: ");
        unidad_medida = scanner.nextLine();

        System.out.print("Ingrese precio: ");
        precio = scanner.nextDouble();

        // Creando objeto producto
        Producto producto = new Producto(id, nombre, categoria, descripcion, precio, unidad_medida);
        boolean resultado = controlador.registrarProducto(producto);
        if (resultado) {
            System.out.println("Producto registrado con éxito.");
        } else {
            System.out.println("Error al registrar el producto.");
        }
    }

    private void verProductoPorId() {
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        Producto producto = controlador.obtenerProductoPorId(id);
        if (producto != null) {
            Producto.cabeceras();
            producto.mostrarTabla();

        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void verTodosLosProductos() {
        List<Producto> productos = controlador.obtenerTodosProductos();

        // FORMATO TABLA

        // Poniendo caebceras
        // productos.get(0).cabeceras();
        Producto.cabeceras();

        // Datos de los proveedores
        for (Producto producto : productos) {
            producto.mostrarTabla();
        }
        System.out.println("");

    }

    private void actualizarProducto() {

        // Declarando variables
        String nombre, categoria, descripcion, unidad_medida;
        double precio;
        int id;
        // Generando inputs
        System.out.println("Ingrese el id del producto para actualizar:");
        id = scanner.nextInt();

        // Print de proveedor a actualizar
        Producto producto = controlador.obtenerProductoPorId(id);
        if (producto != null) {
            Producto.cabeceras();
            producto.mostrarTabla();

            System.out.println("Desea actualizar todo el producto?");
            System.out.println("(1=Si,2=No)");
            int respuesta = scanner.nextInt();

            if (respuesta == 1) {
                System.out.println("Si desea salir ingrese 1, si no omite");
                int salir = scanner.nextInt();
                if (salir == 1) {
                    System.out.println("Saliendo al Menú ");
                    return;
                } else {
                    System.out.print("Ingrese nombre: ");
                    nombre = scanner.nextLine();

                    System.out.print("Ingrese categoria: ");
                    categoria = scanner.nextLine();

                    System.out.print("Ingrese descripcion: ");
                    descripcion = scanner.nextLine();

                    System.out.print("Ingrese unidad de medida: ");
                    unidad_medida = scanner.nextLine();

                    System.out.print("Ingrese precio: ");
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
                    System.out
                            .println("Ingrese el atributo que quiere actualizar (nombre, categoria, descripcion, precio, unidad):");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            System.out.print("Ingrese nombre: ");
                            nombre = scanner.nextLine();
                            break;
                        case "categoria":
                            System.out.print("Ingrese teléfono: ");
                            categoria = scanner.nextLine();
                            break;
                        case "descripcion":
                            System.out.print("Ingrese direccion: ");
                            descripcion = scanner.nextLine();
                            break;
                        case "unidad":
                            System.out.print("Ingrese direccion: ");
                            unidad_medida = scanner.nextLine();
                            break;
                        case "precio":
                            System.out.print("Ingrese email: ");
                            precio = scanner.nextDouble();
                            break;
                        default:
                            System.out.println("Entrada no válida. Por favor, intente de nuevo.");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }

            }

            // Creando objeto proveedor
            Producto productoActualizado = new Producto(id, nombre, categoria, descripcion, precio, unidad_medida);
            boolean resultado = controlador.actualizarPorId(id, productoActualizado);
            if (resultado) {
                System.out.println("Producto actualizado con éxito.");
                Producto productoAct = controlador.obtenerProductoPorId(id);
                Producto.cabeceras();
                productoAct.mostrarTabla();

            } else {
                System.out.println("Error al actualizar el producto.");
            }

        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void eliminarProducto() {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controlador.eliminarPorId(id);
        if (resultado) {
            System.out.println("Producto eliminado con éxito.");
        } else {
            System.out.println("Error al eliminar el producto.");
        }
    }

    public static void main(String[] args) {
        ProductoView vista = new ProductoView();
        vista.mostrarMenu();
    }
}
