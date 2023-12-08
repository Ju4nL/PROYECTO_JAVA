package appinventario.views;

import appinventario.models.Usuario;
import appinventario.utils.Utilidades;
import java.util.Scanner;

public class MenuView {

    private Scanner scanner;

    public MenuView() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(Usuario user) {
        MenuView menu = new MenuView();
        menu.mostrarMenu(user);
    }

    private void mostrarMenu(Usuario user) {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("| *** BIENVENIDO A APPINVENTARIO *** |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Gestionar Productos             |");
            System.out.println("| 2. Gestionar Proveedores           |");
            System.out.println("| 3. Gestionar Suministro            |");
            System.out.println("| 4. Gestionar Inventario            |");
            System.out.println("| 5. Gestionar Usuario               |");
            // Solo se muestra si es una cuenta administrativa
            if (user.isAdmin()) {
                System.out.println("| 6. Administración                  |");
            }
            System.out.println("| 7. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    ProductoView.main(null);
                    Utilidades.cleanConsola();
                    break;
                case 2:
                    ProveedorView.main(null);
                    Utilidades.cleanConsola();
                    break;
                case 3:
                    SuministroView.main(null);
                    Utilidades.cleanConsola();
                    break;
                case 4:
                    InventarioView.main(null);
                    Utilidades.cleanConsola();
                    break;
                case 5:
                    UsuarioView.main(user);
                    Utilidades.cleanConsola();
                    break;
                case 6:
                    AdminView.main(user);
                    Utilidades.cleanConsola();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    System.exit(0);
                default:
                    Utilidades.imprimirMensaje("Opción no válida. Intente nuevamente.", "error");
                    Utilidades.cleanConsolaPausa();
            }
        }
    }
}
