package appinventario.views;

import appinventario.controlers.InventarioControler;
import appinventario.models.Suministro;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InventarioView {

    private InventarioControler controladorInventario;
    private Scanner scanner;

    public InventarioView() {
        controladorInventario = new InventarioControler();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| ***** GESTIÓN DE INVENTARIO *****  |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Ver todos los Suministros       |");
            System.out.println("| 2. Ver Suministro por ID           |");
            System.out.println("| 3. Ver Suministro con Menor Cantidad |");
            System.out.println("| 4. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        verSuministros();
                        break;
                    case 2:
                        verSuministroPorId();
                        break;
                    case 3:
                        verSuministroMenorCantidad();
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, introduzca un número válido.");
                scanner.next(); // Limpiar el buffer del scanner
            }
        }
    }

    private void verSuministros() {
        List<Suministro> suministros = controladorInventario.obtenerTodosSuministros();
        for (Suministro suministro : suministros) {
            mostrarInformacionSuministro(suministro);
        }
    }

    private void verSuministroPorId() {
        System.out.print("Ingrese el ID del suministro: ");
        try {
            int id = scanner.nextInt();
            Suministro suministro = controladorInventario.obtenerSuministroPorId(id);
            if (suministro != null) {
                mostrarInformacionSuministro(suministro);
            } else {
                System.out.println("Suministro no encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, introduzca un número válido.");
            scanner.next(); // Limpiar el buffer del scanner
        }
    }

    private void verSuministroMenorCantidad() {
        Suministro suministro = controladorInventario.encontrarSuministroConMenorCantidad();
        if (suministro != null) {
            mostrarInformacionSuministro(suministro);
        } else {
            System.out.println("No hay suministros en el inventario.");
        }
    }

    private void mostrarInformacionSuministro(Suministro suministro) {
        System.out.println("ID: " + suministro.getId());
        System.out.println("Producto: " + suministro.getProducto().getNombre());
        System.out.println("Cantidad: " + suministro.getCantidad());
        System.out.println("Proveedor: " + suministro.getProveedor().getNombre());
        System.out.println("Fecha de Caducidad: " + suministro.getFechaCaducidad());
        System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {
        InventarioView vista = new InventarioView();
        vista.mostrarMenu();
    }
}
