package appinventario.views;

import appinventario.controlers.ProveedorControler;
import appinventario.models.Proveedor;
import appinventario.utils.Utilidades;

import java.util.List;
import java.util.Scanner;

public class ProveedorView {

    private ProveedorControler controlador;
    private Scanner scanner;

    public ProveedorView() {
        controlador = new ProveedorControler();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| ***** GESTIÓN DE PROVEEDORES ***** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Registrar Proveedor             |");
            System.out.println("| 2. Ver Proveedor por ID            |");
            System.out.println("| 3. Ver todos los Proveedores       |");
            System.out.println("| 4. Actualizar Proveedor            |");
            System.out.println("| 5. Eliminar Proveedor              |");
            System.out.println("| 6. Salir                           |");
            System.out.println("+------------------------------------+");
            String opcion = Utilidades.solicitarInput("| Seleccione una opción: ", scanner);

            switch (opcion) {
                case "1":
                    registrarProveedor();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "2":
                    verProveedorPorId();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "3":
                    verProveedores();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "4":
                    actualizarProveedor();
                    Utilidades.cleanConsolaPausa();
                    break;
                case "5":
                    eliminarProveedor();
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

    private void registrarProveedor() {
        Utilidades.cleanConsola();
        System.out.println(" ====> REGISTRAR NUEVO PROVEEDOR");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, telefono, direccion, email;
        int id = 0;
        scanner.nextLine();
        // Generando inputs
        nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
        if (nombre == null) { return; }

        telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
        if (telefono == null) { return; }

        direccion = Utilidades.solicitarInput("-> Ingrese dirección: ", scanner);
        if (direccion == null) { return; }

        email = Utilidades.solicitarInput("-> Ingrese email: ", scanner);
        if (email == null) { return; }

        // Creando objeto proveedor
        Proveedor proveedor = new Proveedor(id, nombre, telefono, direccion, email);
        boolean resultado = controlador.registrarProveedor(proveedor);
        if (resultado) {
            Utilidades.imprimirMensaje("Proveedor registrado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al registrar el proveedor.", "error");
        }
    }

    private void verProveedorPorId() {
        Utilidades.cleanConsola();
        System.out.println(" ====> VER PROVEEDOR POR ID");
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        String id = Utilidades.solicitarInput("-> Ingrese el ID del proveedor: ", scanner);
        if (id == null) { return; }
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }
        Proveedor proveedor = controlador.obtenerProveedorPorId(idInt);
        if (proveedor != null) {
            Proveedor.cabeceras();
            proveedor.mostrarTabla();
        } else {
            Utilidades.imprimirMensaje("Proveedor no encontrado.", "error");
        }
    }

    private void verProveedores() {
        Utilidades.cleanConsola();
        System.out.println(" ====> LISTA DE PROVEEDORES");
        List<Proveedor> proveedores = controlador.obtenerTodosProveedores();
        Proveedor.cabeceras();
        // Datos de los proveedores
        for (Proveedor proveedor : proveedores) {
            proveedor.mostrarTabla();
        }
    }

    private void actualizarProveedor() {
        verProveedores();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        // Declarando variables
        String nombre, telefono, direccion, email;
        int idInt;
        // Generando inputs
        String id = Utilidades.solicitarInput("-> Ingrese el id del proveedor para actualizar: ", scanner);
        if (id == null) { return; }
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }
        // Print de proveedor a actualizar
        Proveedor proveedor = controlador.obtenerProveedorPorId(idInt);
        if (proveedor != null) {
            Proveedor.cabeceras();
            proveedor.mostrarTabla();
            System.out.println("");
            String respuesta = Utilidades.solicitarInput("¿Desea actualizar todo el proveedor?  (Sí = 1 | No = 2): ", scanner, 0);
            if (respuesta == null) { return; }

            if (respuesta.equals("1")) {
                String input = Utilidades.solicitarInput("Presione 1 para salir, o Enter para continuar: ", scanner, 0);

                if (input.equals("1")) {
                    System.out.print("Saliendo al Menú");
                    return;
                } else {
                    nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                    if (nombre == null) { return; }

                    telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
                    if (telefono == null) { return; }

                    direccion = Utilidades.solicitarInput("-> Ingrese dirección: ", scanner);
                    if (direccion == null) { return; }

                    email = Utilidades.solicitarInput("-> Ingrese email: ", scanner);
                    if (email == null) { return; }
                }
            } else {

                String atributo = "";
                nombre = proveedor.getNombre();
                telefono = proveedor.getTelefono();
                direccion = proveedor.getDireccion();
                email = proveedor.getEmail();

                while (true) {
                    atributo = Utilidades.solicitarInput("-> Ingrese el atributo que quiere actualizar (nombre, telefono, direccion, email):", scanner);
                    if (atributo == null) { return; }

                    switch (atributo.toLowerCase()) {
                        case "nombre":
                            nombre = Utilidades.solicitarInput("-> Ingrese nombre: ", scanner);
                            if (nombre == null) { return; }
                            break;
                        case "telefono":
                            telefono = Utilidades.solicitarInput("-> Ingrese teléfono: ", scanner);
                            if (telefono == null) { return; }
                            break;
                        case "direccion":
                            direccion = Utilidades.solicitarInput("-> Ingrese dirección: ", scanner);
                            if (direccion == null) { return; }
                            break;
                        case "email":
                            email = Utilidades.solicitarInput("-> Ingrese email: ", scanner);
                            if (email == null) { return; }
                            break;
                        default:
                            Utilidades.imprimirMensaje("Entrada no válida. Por favor, intente de nuevo.", "error");
                            continue;
                    }
                    break; // Salir del bucle si la entrada es válida
                }
            }

            // Creando objeto proveedor
            Proveedor proveedorActualizar = new Proveedor(idInt, nombre, telefono, direccion, email);
            boolean resultado = controlador.actualizarPorId(idInt, proveedorActualizar);
            if (resultado) {
                Utilidades.imprimirMensaje("Proveedor actualizado con éxito.", "success");
                Proveedor proveedorAct = controlador.obtenerProveedorPorId(idInt);
                Proveedor.cabeceras();
                proveedorAct.mostrarTabla();
            } else {
                Utilidades.imprimirMensaje("Error al actualizar el proveedor.", "error");
            }
        } else {
            Utilidades.imprimirMensaje("Proveedor no encontrado.", "error");
        }
    }

    private void eliminarProveedor() {
        verProveedores();
        System.out.println("");
        Utilidades.imprimirMensaje("Puedes cancelar en cualquier momento, solo escribe cancelar.", "warning");
        System.out.println("");
        String id = Utilidades.solicitarInput("-> Ingrese el ID del proveedor a eliminar: ", scanner);
        if (id == null) { return; }
        int idInt;
        try {
            idInt = Integer.parseInt(id);
        } catch (Exception e) {
            idInt = -666;
        }
        boolean resultado = controlador.eliminarPorId(idInt);
        if (resultado) {
            Utilidades.imprimirMensaje("Proveedor eliminado con éxito.", "success");
        } else {
            Utilidades.imprimirMensaje("Error al eliminar el proveedor.", "error");
        }
    }

    public static void main(String[] args) {
        Utilidades.cleanConsola();
        ProveedorView vista = new ProveedorView();
        vista.mostrarMenu();
    }
}
