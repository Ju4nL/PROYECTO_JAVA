package appinventario.views;

import java.util.Scanner;

public class MenuView {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuView menu = new MenuView();
        menu.mostrarMenu();
    }

    private void mostrarMenu() {
        while (true) {
            System.out.println("+------------------------------------+");
            System.out.println("|    ***** MENÚ PRINCIPAL *****      |");
            System.out.println("+------------------------------------+");
            System.out.println("| 1. Vista de Producto               |");
            System.out.println("| 2. Vista de Proveedor              |");
            System.out.println("| 3. Vista de Suministro             |");
            System.out.println("| 4. Vista de Inventario             |");
            System.out.println("| 5. Salir                           |");
            System.out.println("+------------------------------------+");
            System.out.print("| Seleccione una opción: ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    ProductoView.main(null);
                    break;
                case 2:
                    ProveedorView.main(null);
                    break;
                case 3:
                    SuministroView.main(null);
                    break;
                case 4:
                    // Vista Inventario
                    break;
                case 5:
                    // Adiós chupapi
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
