package appinventario.views;

import appinventario.controlers.AdminControler;
import appinventario.models.Usuario;
import appinventario.utils.Utilidades;

import java.util.Scanner;
import java.util.List;

public class AdminView {

    private AdminControler controlador;
    private Scanner scanner;

    public AdminView() {
        this.controlador = new AdminControler();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        AdminView adminview = new AdminView();
        adminview.mostrarMenu();
    }

    private void mostrarMenu() {

        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| **  ADMINISTRACION DE USUARIOS  ** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Ver usuarios                    |");
            System.out.println("| 2. Agregar usuario                 |");
            System.out.println("| 3. Eliminar usuario                |");
            System.out.println("| 4. Actualizar usuario              |");
            System.out.println("| 5. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verListaUsuarios();
                    Utilidades.cleanConsola();
                    break;
                case 2:
                    // Insertar código para agregar usuario
                    break;
                case 3:
                    // Insertar código para eliminar usuario
                    break;
                case 4:
                    // Insertar código para actualizar usuario
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    private void verListaUsuarios() {
        List<Usuario> listUser = controlador.obtenerTodosUsuarios();
        Usuario.cabeceras();
        for (Usuario user : listUser) {
            user.mostrarTabla();
        }
    }
}
