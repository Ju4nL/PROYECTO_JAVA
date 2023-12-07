/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package appinventario.views;

import appinventario.controlers.ProductoControler;
import appinventario.controlers.ProveedorControler;
import appinventario.controlers.SuministroControler;
import appinventario.models.Producto;
import appinventario.models.Proveedor;
import appinventario.models.Suministro;
import appinventario.utils.Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J_lozada
 */
public class SuministroView {

    private SuministroControler controladorSuministro;
    private ProveedorControler controladorProveedor;
    private ProductoControler controladorProducto;
    private Scanner scanner;
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public SuministroView() {
        controladorSuministro = new SuministroControler();
        controladorProveedor = new ProveedorControler();
        controladorProducto = new ProductoControler();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| ***** GESTIÓN DE SUMINISTROS ***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Registrar Suministro            |");
            System.out.println("| 2. Ver Suministro por ID           |");
            System.out.println("| 3. Ver todos los Suministros       |");
            System.out.println("| 4. Actualizar Suministro           |");
            System.out.println("| 5. Eliminar Suministro             |");
            System.out.println("| 6. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    registrarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 2:
                    verSuministroPorId();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 3:
                    verSuministros();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 4:
                    actualizarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 5:
                    eliminarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void registrarSuministro() {
        Utilidades.cleanConsola();
        System.out.println(" REGISTRAR NUEVO SUMINISTRO");
        // Declarando variables
        int id = 0, cantidad;
        Date fechaCaducidad;

        // Generando inputs
        // PRODUCTO
        Producto producto = inputProducto();

        // PROVEEDORES
        Proveedor proveedor = inputProveedor();

        System.out.print(" -> Ingrese cantidad de productos: ");
        cantidad = scanner.nextInt();
        scanner.nextLine();

        System.out.print(" -> Ingrese la fecha de caducidad (dd/MM/yyyy): ");
        String fechaString = scanner.nextLine();

        try {
            fechaCaducidad = formatoFecha.parse(fechaString);
            // Creando objeto proveedor
            Suministro nuevoSuministro = new Suministro(id, producto, cantidad, fechaCaducidad, proveedor);

            boolean resultado = controladorSuministro.registrarSuministro(nuevoSuministro);
            if (resultado) {
                System.out.println(" Suministro registrado con éxito.");
            } else {
                System.out.println(" Error al registrar el Suministro.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void verSuministroPorId() {
        Utilidades.cleanConsola();
        System.out.println(" VER SUMINISTRO POR ID");
        System.out.print(" -> Ingrese el ID del suministro: ");
        int id = scanner.nextInt();
        Suministro suministro = controladorSuministro.obtenerSuministroPorId(id);
        if (suministro != null) {
            Suministro.cabeceras();
            suministro.mostrarTabla();

        } else {
            System.out.println(" Suministro no encontrado.");
        }
    }

    private void verSuministros() {
        Utilidades.cleanConsola();
        System.out.println(" LISTA DE SUMINISTROS");
        List<Suministro> suministros = controladorSuministro.obtenerTodosSuministros();
        Suministro.cabeceras();

        for (Suministro suministro : suministros) {
            suministro.mostrarTabla();
        }
    }

    private void actualizarSuministro() {
        verSuministros();
        System.out.println("");
        // Declarando variables
        int id, cantidad;
        Date fechaCaducidad = null;
        Proveedor proveedor;
        Producto producto;
        String fechaString = null;
        // Generando inputs
        System.out.print(" -> Ingrese el id del suministro para actualizar: ");
        id = scanner.nextInt();

        // Print de proveedor a actualizar
        Suministro suministro = controladorSuministro.obtenerSuministroPorId(id);
        if (suministro != null) {
            Suministro.cabeceras();
            suministro.mostrarTabla();
            System.out.println("");
            System.out.print(" ¿Desea actualizar todo el suministro?  (Sí = 1 | No = 2): ");
            int respuesta = scanner.nextInt();
            scanner.nextLine();

            if (respuesta == 1) {
                System.out.print("Presione 1 para salir, o Enter para continuar: ");
                String input = scanner.nextLine();

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    producto = inputProducto();
                    proveedor = inputProveedor();

                    System.out.print(" -> Ingrese cantidad de productos: ");
                    cantidad = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(" -> Ingrese la fecha de caducidad (dd/MM/yyyy): ");
                    fechaString = scanner.nextLine();
                }
            } else {

                String atributo = "";
                proveedor = suministro.getProveedor();
                producto = suministro.getProducto();
                cantidad = suministro.getCantidad();
                fechaCaducidad = suministro.getFechaCaducidad();

                while (true) {
                    System.out.print(
                            " -> Ingrese el atributo que quiere actualizar (producto,proveedor, cantidad, fechaCaducidad): ");
                    atributo = scanner.nextLine();

                    switch (atributo.toLowerCase()) {
                        case "producto":
                            producto = inputProducto();
                            break;

                        case "proveedor":
                            proveedor = inputProveedor();
                            break;
                        case "cantidad":
                            System.out.print(" -> Ingrese cantidad de productos: ");
                            cantidad = scanner.nextInt();
                            scanner.nextLine();

                            break;
                        case "fechaCaducidad":
                            System.out.print(" -> Ingrese la fecha de caducidad (dd/MM/yyyy): ");
                            fechaString = scanner.nextLine();
                            break;
                        default:
                            System.out.println(" Entrada no válida. Por favor, intente de nuevo.");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }

            }

            try {
                if (fechaCaducidad instanceof Date) {

                } else {
                    fechaCaducidad = formatoFecha.parse(fechaString);
                }

                // Creando objeto proveedor
                Suministro suministroActualizar = new Suministro(id, producto, cantidad, fechaCaducidad, proveedor);

                boolean resultado = controladorSuministro.actualizarPorId(id, suministroActualizar);
                if (resultado) {
                    System.out.println(" Suministro actualizado con éxito.");
                } else {
                    System.out.println(" Error al actualizar el Suministro.");
                }
            } catch (ParseException ex) {
                Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println(" Suministro no encontrado.");
        }
    }

    private void eliminarSuministro() {
        verSuministros();
        System.out.println("");
        System.out.print(" -> Ingrese el ID del suministro a eliminar: ");
        int id = scanner.nextInt();
        boolean resultado = controladorSuministro.eliminarPorId(id);
        if (resultado) {
            System.out.println(" Suministro eliminado con éxito.");
        } else {
            System.out.println(" Error al eliminar el suministro.");
        }
    }

    private Producto inputProducto() {
        // PRODUCTO
        List<Suministro> suministros = controladorSuministro.obtenerTodosSuministros();
        Suministro.cabeceras();
        for (Suministro suministro : suministros) {
            suministro.mostrarTabla();
        }
        System.out.println("");
        System.out.print(" -> Ingrese el idproducto: ");
        int idproducto = scanner.nextInt();
        Producto inputproducto = controladorProducto.obtenerProductoPorId(idproducto);
        return inputproducto;
    }

    private Proveedor inputProveedor() {
        //PROVEEDOR
        List<Proveedor> proveedores = controladorProveedor.obtenerTodosProveedores();
        Proveedor.cabeceras();
        for (Proveedor proveedor : proveedores) {
            proveedor.mostrarTabla();
        }
        System.out.println("");
        System.out.print(" -> Ingrese el idproveedor: ");
        int idproveedor = scanner.nextInt();
        Proveedor inputproveedor = controladorProveedor.obtenerProveedorPorId(idproveedor);
        return inputproveedor;
    }

    public static void main(String[] args) {
        SuministroView vista = new SuministroView();
        vista.mostrarMenu();
    }
}