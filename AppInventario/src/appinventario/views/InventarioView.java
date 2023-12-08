package appinventario.views;

import appinventario.controlers.InventarioControler;
import appinventario.models.Suministro;
import appinventario.utils.Utilidades;

import java.util.Scanner;

public class InventarioView {
 
    private InventarioControler controladorInventario;
    private SuministroView suministrovista;
    private Scanner scanner;

    public InventarioView() {
        controladorInventario = new InventarioControler();
        suministrovista = new SuministroView();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("+--------------------------------------+");
            System.out.println("| ***** GESTIÓN DE INVENTARIO *****    |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1. Registrar Suministro              |");
            System.out.println("| 2. Ver Suministro por ID             |");
            System.out.println("| 3. Ver todos los Suministros         |");
            System.out.println("| 4. Actualizar Suministro             |");
            System.out.println("| 5. Eliminar Suministro               |");
            System.out.println("| 6. Ver Suministro con Menor Cantidad |");
            System.out.println("| 7. Ver Suministro con Mayor Cantidad |");
            System.out.println("| 8. Salir                             |");
            System.out.println("+--------------------------------------+");
            System.out.print("| Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    suministrovista.registrarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 2:
                    suministrovista.verSuministroPorId();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 3:
                    suministrovista.verSuministros();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 4:
                    suministrovista.actualizarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 5:
                    suministrovista.eliminarSuministro();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 6:
                    verSuministroMenorCantidad();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 7:
                    verSuministroMayorCantidad();
                    Utilidades.cleanConsolaPausa();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    return;
                default:
                    Utilidades.imprimirMensaje("Opción no válida. Intente nuevamente.", "error");
                    Utilidades.cleanConsolaPausa();
            }
        }
    }
    
    private void verSuministroMenorCantidad() {
        Suministro suministro = controladorInventario.encontrarSuministroConMenorCantidad();
        if (suministro != null) {
            Utilidades.cleanConsola();
            System.out.println(" ====> SUMINISTRO CON MENOR CANTIDAD");
            mostrarInformacionSuministro(suministro);
        } else {
            Utilidades.imprimirMensaje(" No hay suministros en el inventario.", "error");
        }
    }
    
    private void verSuministroMayorCantidad() {
        Suministro suministro = controladorInventario.encontrarSuministroConMayorCantidad();
        if (suministro != null) {
            Utilidades.cleanConsola();
            System.out.println(" ====> SUMINISTRO CON MAYOR CANTIDAD");
            mostrarInformacionSuministro(suministro);
        } else {
            Utilidades.imprimirMensaje(" No hay suministros en el inventario.", "error");
        }
    }

    public void mostrarInformacionSuministro(Suministro suministro) {
            Suministro.cabeceras();
            suministro.mostrarTabla();
    }
    
    public static void main(String[] args) {
        Utilidades.cleanConsola();
        InventarioView vista = new InventarioView();
        vista.mostrarMenu();
    }
}
