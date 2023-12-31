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
            int opcion = Utilidades.solicitarInputInt("| Seleccione una opción: ", scanner);

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
                    Utilidades.imprimirMensaje("Opción no válida. Intente nuevamente.", "error");
                    Utilidades.cleanConsolaPausa();
            }
        }
    }

    public void registrarSuministro() {
        Utilidades.cleanConsola();
        System.out.println(" ====> REGISTRAR NUEVO SUMINISTRO");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        int id = 0, cantidad;
        Date fechaCaducidad;

        // Generando inputs
        // PRODUCTO
        Producto producto = inputProducto();
        // PROVEEDORES
        Proveedor proveedor = inputProveedor();

        cantidad = Utilidades.solicitarInputInt("-> Ingrese cantidad de productos: ", scanner);
        if (cantidad == -666) { return; }

        String fechaString = Utilidades.solicitarInput("-> Ingrese la fecha de caducidad (dd/MM/yyyy): ", scanner);
        if (fechaString == null) { return; }
        try {
            fechaCaducidad = formatoFecha.parse(fechaString);
            // Creando objeto proveedor
            Suministro nuevoSuministro = new Suministro(id, producto, cantidad, fechaCaducidad, proveedor);

            boolean resultado = controladorSuministro.registrarSuministro(nuevoSuministro);
            if (resultado) {
                Utilidades.imprimirMensaje("Suministro registrado con éxito.", "success");
            } else {
                Utilidades.imprimirMensaje("Error al registrar el Suministro.", "error");
            }
        } catch (ParseException ex) {
            Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void verSuministroPorId() {
        Utilidades.cleanConsola();
        System.out.println(" ====> VER SUMINISTRO POR ID");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        int id = Utilidades.solicitarInputInt("-> Ingrese el ID del suministro: ", scanner);
        if (id == -666) { return; }

        Suministro suministro = controladorSuministro.obtenerSuministroPorId(id);
        if (suministro != null) {
            Suministro.cabeceras();
            suministro.mostrarTabla();
        } else {
            Utilidades.imprimirMensaje("Suministro no encontrado.", "error");
        }
    }

    public void verSuministros() {
        Utilidades.cleanConsola();
        System.out.println(" ====> LISTA DE SUMINISTROS");
        List<Suministro> suministros = controladorSuministro.obtenerTodosSuministros();
        Suministro.cabeceras();
        for (Suministro suministro : suministros) {
            suministro.mostrarTabla();
        }
    }

    public void actualizarSuministro() {
        verSuministros();
        System.out.println("");
        // Declarando variables
        int cantidad;
        Date fechaCaducidad = null;
        Proveedor proveedor;
        Producto producto;
        String fechaString = null;
        // Generando inputs
        int id = Utilidades.solicitarInputInt("-> Ingrese el id del suministro para actualizar: ", scanner);
        if (id == -666) { return; }
        // Print de proveedor a actualizar
        Suministro suministro = controladorSuministro.obtenerSuministroPorId(id);
        if (suministro != null) {
            Suministro.cabeceras();
            suministro.mostrarTabla();
            System.out.println("");
            String respuesta = Utilidades.solicitarInput("¿Desea actualizar todo el suministro?  (Sí = 1 | No = 2): ", scanner, 0);
            if (respuesta == null) { return; }

            if (respuesta.equals("1")) {
                String input = Utilidades.solicitarInput("Presione 1 para salir, o Enter para continuar: ", scanner, 0);

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    producto = inputProducto();
                    proveedor = inputProveedor();

                    cantidad = Utilidades.solicitarInputInt("-> Ingrese cantidad de productos: ", scanner);
                    if (cantidad == -666) { return; }

                    fechaString = Utilidades.solicitarInput("-> Ingrese la fecha de caducidad (dd/MM/yyyy): ", scanner);
                    if (fechaString == null) { return; }
                }
            } else {

                String atributo = "";
                proveedor = suministro.getProveedor();
                producto = suministro.getProducto();
                cantidad = suministro.getCantidad();
                fechaCaducidad = suministro.getFechaCaducidad();

                while (true) {
                    atributo = Utilidades.solicitarInput(" -> Ingrese el atributo que quiere actualizar (producto,proveedor, cantidad, fechaCaducidad): ", scanner);
                    if (atributo == null) { return; }

                    switch (atributo.toLowerCase()) {
                        case "producto":
                            producto = inputProducto();
                            break;

                        case "proveedor":
                            proveedor = inputProveedor();
                            break;
                        case "cantidad":
                            cantidad= Utilidades.solicitarInputInt("-> Ingrese cantidad de productos: ", scanner);
                            if (cantidad == -666) { return; }
                            break;
                        case "fechaCaducidad":
                            fechaString = Utilidades.solicitarInput("-> Ingrese la fecha de caducidad (dd/MM/yyyy): ", scanner);
                            if (fechaString == null) { return; }
                            break;
                        default:
                            Utilidades.imprimirMensaje("Entrada no válida. Por favor, intente de nuevo.", "error");
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

                // Creando objeto suministro
                Suministro suministroActualizar = new Suministro(id, producto, cantidad, fechaCaducidad, proveedor);

                boolean resultado = controladorSuministro.actualizarPorId(id, suministroActualizar);
                if (resultado) {
                    Utilidades.imprimirMensaje("Suministro actualizado con éxito.", "success");
                } else {
                    Utilidades.imprimirMensaje("Error al actualizar el Suministro.", "error");
                }
            } catch (ParseException ex) {
                Logger.getLogger(SuministroView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Utilidades.imprimirMensaje("Suministro no encontrado.", "error");
        }
    }

    public void eliminarSuministro() {
        verSuministros();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        int id = Utilidades.solicitarInputInt("-> Ingrese el ID del suministro a eliminar: ", scanner);
        if (id == -666) { return; }
        boolean resultado = controladorSuministro.eliminarPorId(id);
        if (resultado) {
            Utilidades.imprimirMensaje("Suministro eliminado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al eliminar el suministro.", "error");
        }
    }

    private Producto inputProducto() {
        // PRODUCTO
        List<Producto> productos = controladorProducto.obtenerTodosProductos();
        Suministro.cabeceras();
        for (Producto producto : productos) {
            producto.mostrarTabla();
        }
        System.out.println("");
        int idproducto = Utilidades.solicitarInputInt("-> Ingrese el idproducto: ", scanner);
        if (idproducto == -666) { return null; }
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
        int idproveedor = Utilidades.solicitarInputInt("-> Ingrese el idproveedor: ", scanner);
        if (idproveedor == -666) { return null; }
        Proveedor inputproveedor = controladorProveedor.obtenerProveedorPorId(idproveedor);
        return inputproveedor;
    }

    public static void main(String[] args) {
        Utilidades.cleanConsola();
        SuministroView vista = new SuministroView();
        vista.mostrarMenu();
    }
}